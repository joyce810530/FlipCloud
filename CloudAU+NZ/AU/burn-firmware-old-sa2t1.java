<?php 
if ( ! defined( 'ABSPATH' ) ) exit;
?>
<div id="footerbottom">
	<div id="scrolltop">
        <a><i class="icon-arrow-1-up"></i><span><?php _e('top','vibe'); ?></span></a>
    </div>
    <div class="<?php echo vibe_get_container(); ?>">
        <div class="row">
            <div class="col-md-12">
                <?php $copyright=vibe_get_option('copyright'); echo (isset($copyright)?do_shortcode($copyright):'&copy; 2013, All rights reserved.'); ?>
            </div>
        </div>
    </div>
</div>
</div><!-- END PUSHER -->
</div><!-- END MAIN -->
	<!-- SCRIPTS -->
<?php
wp_footer();
?>
<script>
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
  function burnFirmware_topic12(){
    var portIndex = $("#portSelect")[0].selectedIndex;
    if(portIndex!=0){
      $("#labBurnMsg").text("UPLOADING......");
      console.log(portIndex);
      selectedPort = lastPorts[portIndex-1].path;
      console.log(selectedPort);
      var xhttp = new XMLHttpRequest();
      xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
          console.log(this.response);
          console.log("send hex");
          port.postMessage({"cmd":"compile","port":selectedPort,"data":this.response});
        }
      };
      xhttp.open("GET", "https://stage.fliprobot.com/firmware/E300_firmware_a5.hex", true);
      xhttp.send();
    }
  }
function burnFirmwaret3(){
    var portIndex = $("#portSelect")[0].selectedIndex;
    if(portIndex!=0){
      $("#labBurnMsg").text("UPLOADING......");
      console.log(portIndex);
      selectedPort = lastPorts[portIndex-1].path;
      console.log(selectedPort);
      var xhttp = new XMLHttpRequest();
      xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
          console.log(this.response);
          console.log("send hex");
          port.postMessage({"cmd":"compile","port":selectedPort,"data":this.response});
        }
      };
      xhttp.open("GET", "https://stage.fliprobot.com/firmware/SA1_T3.hex", true);
      xhttp.send();
    }
  }
function burnFirmwaret4(){
    var portIndex = $("#portSelect")[0].selectedIndex;
    if(portIndex!=0){
      $("#labBurnMsg").text("UPLOADING......");
      console.log(portIndex);
      selectedPort = lastPorts[portIndex-1].path;
      console.log(selectedPort);
      var xhttp = new XMLHttpRequest();
      xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
          console.log(this.response);
          console.log("send hex");
          port.postMessage({"cmd":"compile","port":selectedPort,"data":this.response});
        }
      };
      xhttp.open("GET", "https://stage.fliprobot.com/firmware/SA1_T4.hex", true);
      xhttp.send();
    }
  }
function burnFirmwaret5(){
    var portIndex = $("#portSelect")[0].selectedIndex;
    if(portIndex!=0){
      $("#labBurnMsg").text("UPLOADING......");
      console.log(portIndex);
      selectedPort = lastPorts[portIndex-1].path;
      console.log(selectedPort);
      var xhttp = new XMLHttpRequest();
      xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
          console.log(this.response);
          console.log("send hex");
          port.postMessage({"cmd":"compile","port":selectedPort,"data":this.response});
        }
      };
      xhttp.open("GET", "https://stage.fliprobot.com/firmware/SA1_T5.hex", true);
      xhttp.send();
    }
  }
function burnFirmwaret6(){
    var portIndex = $("#portSelect")[0].selectedIndex;
    if(portIndex!=0){
      $("#labBurnMsg").text("UPLOADING......");
      console.log(portIndex);
      selectedPort = lastPorts[portIndex-1].path;
      console.log(selectedPort);
      var xhttp = new XMLHttpRequest();
      xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
          console.log(this.response);
          console.log("send hex");
          port.postMessage({"cmd":"compile","port":selectedPort,"data":this.response});
        }
      };
      xhttp.open("GET", "https://stage.fliprobot.com/firmware/SA1_T6.hex", true);
      xhttp.send();
    }
  }
  function burnFirmwaresa2t1(){
    var portIndex = $("#portSelect")[0].selectedIndex;
    if(portIndex!=0){
      $("#labBurnMsg").text("UPLOADING......");
      console.log(portIndex);
      selectedPort = lastPorts[portIndex-1].path;
      console.log(selectedPort);
      var xhttp = new XMLHttpRequest();
      xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
          console.log(this.response);
          console.log("send hex");
          port.postMessage({"cmd":"compile","port":selectedPort,"data":this.response});
        }
      };
      xhttp.open("GET", "https://stage.fliprobot.com/firmware/L3SA2TP1_20180908.hex", true);
      xhttp.send();
    }
  }

function searchPort() {
    console.log("searchPort");
    try {
        port.postMessage({ "cmd": "searchPort", "data": "" });
    } catch (error) {
        console.log(error);
        if (checkCrmOs) {
            console.log("check chrome os");
            var crmOSExtId = "nkkifkipcmcammmabpkpiolikffodefe";
            port = chrome.runtime.connect(crmOSExtId);
            setPortListener();
            checkCrmOs = false;
            searchPort();
        }
        else {
            //alert("Please install extension");
        }
    }
}

function setPortListener() {
    port.onMessage.addListener(function (msg) {
        console.log("web page received: ", msg.cmd);
        if (msg.cmd == "compile") {
            console.log("web page received: ", msg.data);
        }
        else if (msg.cmd == "searchPort") {
            var ports = msg.data;
            ports.sort(function (a, b) {
                a = a.path.replace("COM", "");
                b = b.path.replace("COM", "");
                return a - b;
            });
            if (!portsAreSame(ports, lastPorts)) {
                $("#portSelect").empty();
                $("#portSelect").append("<option value='' disabled selected>Select your port</option>");
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
        }
        else if (msg.cmd == "upload") {
            switch (msg.upload_status) {
                case "UPLOAD_SUCCESS":
                    $("#labBurnMsg").text("SUCCESS!!");
                    console.log("Upload Status: " + msg.upload_status);
                    break;
                case "UPLOAD_ERROR":
                    console.log("Upload Status: " + msg.upload_status);
                    $("#labBurnMsg").text("ERROR!!");
                    break;
            }
        }
    });
}

</script>

</body>
</html>