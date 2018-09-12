<script>
    var $ = jQuery.noConflict();

    var extId = "fmpdgdpnholklpaibdlfhicdbbendjkp";
    var port = chrome.runtime.connect(extId);
    var selectedPort = "ttyUSB0";
    var lastPorts = [];
    var checkCrmOs = true

    setPortListener()

    searchPort();
    $("#portSelect").mouseover(function () {
        searchPort();
    });

    function portsAreSame(x, y) {
        var objectsAreSame = true;
        if ((x.length != y.length) || (x.length == 0) || (y.length == 0))
            return false
        for (var i = 0; i < x.length; i++) {
            if (x[i].path !== y[i].path) {
                objectsAreSame = false;
                break;
            }
        }
        return objectsAreSame;
    }

    function burnFirmware(hexName) {
        console.log(hexName)
        var portIndex = $("#portSelect")[0].selectedIndex;
        if (portIndex != 0) {
            $("#labBurnMsg").text("UPLOADING......");
            console.log(portIndex);
            selectedPort = lastPorts[portIndex - 1].path;
            console.log(selectedPort);
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    console.log(this.response);
                    console.log("send hex");
                    port.postMessage({
                        "cmd": "compile",
                        "port": selectedPort,
                        "data": this.response
                    });
                }
            };
            xhttp.open("GET", "https://stage.fliprobot.com/firmware/" + hexName + ".hex", true);
            xhttp.send();
        }
    }

    function searchPort() {
        console.log("searchPort");
        try {
            port.postMessage({
                "cmd": "searchPort",
                "data": ""
            });
        } catch (error) {
            console.log(error);
            if (checkCrmOs) {
                console.log("check chrome os");
                var crmOSExtId = "nkkifkipcmcammmabpkpiolikffodefe";
                port = chrome.runtime.connect(crmOSExtId);
                setPortListener();
                checkCrmOs = false;
                searchPort();
            } else {
                console.log("Please install extension");
            }
        }
    }

    function setPortListener() {
        port.onMessage.addListener(function (msg) {
            console.log("web page received: ", msg.cmd);
            if (msg.cmd == "compile") {
                console.log("web page received: ", msg.data);
            } else if (msg.cmd == "searchPort") {
                var ports = msg.data;
                ports.sort(function (a, b) {
                    a = a.path.replace("COM", "");
                    b = b.path.replace("COM", "");
                    return a - b;
                });
                if (!portsAreSame(ports, lastPorts)) {
                    $("#portSelect").empty();
                    $("#portSelect").append("<option value='' disabled selected>選擇端口</option>");
                    for (var i = 0; i < ports.length; i++) {
                        var portName;
                        var eachPort = ports[i].path;
                        if (ports[i].productId == 29987) portName = "FR.Device (" + ports[i].path + ")";
                        else portName = ports[i].path;
                        var newOption = "<option value=" + ports[i].path + "'>" + portName + "</option>";
                        // "<a class='item' id='" + ports[i].path + "'>" + portName + "</a>";
                        $("#portSelect").append(newOption);
                    }
                    lastPorts = ports;
                }
            } else if (msg.cmd == "upload") {
                switch (msg.upload_status) {
                    case "UPLOAD_SUCCESS":
                        $("#labBurnMsg").text("您已成功完成燒錄。");
                        console.log("Upload Status: " + msg.upload_status);
                        break;
                    case "UPLOAD_ERROR":
                        console.log("Upload Status: " + msg.upload_status);
                        $("#labBurnMsg").text("請重新檢查是否連結E300及USB線，再重新燒錄一次。");
                        break;
                }
            }
        });
    }
</script>