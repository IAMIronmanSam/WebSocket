<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<title>WebSocket in Java</title>
        <script language="javascript" type="text/javascript">
            var dice,name;
            var wsUri = "ws://" + document.location.host + document.location.pathname + "hello";
            var websocket = new WebSocket(wsUri);
            websocket.onopen = function(evt) { onOpen(evt) };
            websocket.onmessage = function(evt) { onMessage(evt) };
            websocket.onerror = function(evt) { onError(evt) };
            
            function init() {
                output = document.getElementById("output");
            }

            function say_hello() {
                name = $("#nameField").val();             
                dice = Math.floor((Math.random()*6)+1);
                var json={Name:name,Dice:dice};
                websocket.send(json);
                writeToScreen("SENT: " +JSON.stringify(json));
                console.log("Msg Sent"+JSON.stringify(json));    
            }

            function onOpen(evt) {
                writeToScreen("CONNECTED");
            }

            function onMessage(evt) {
                console.log("Msg Recieving");
                 console.log(evt.data);
                writeToScreen("RECEIVED: " + evt.data);
                
            }

            function onError(evt) {
                writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
            }

            function writeToScreen(message) {
               
                var pre = document.createElement("p");
                pre.style.wordWrap = "break-word";
                pre.innerHTML = message;
                output.appendChild(pre);
            }

            window.addEventListener("load", init, false);
        </script>
    </head>
    <body>
        <h1>WebSocket in Java!!</h1>

        <div style="text-align: center;">
            <form action=""> 
                <input onclick="say_hello()" value="Roll" type="button"/> 
                <input id="nameField" placeholder="Name"type="text"/><br>
            </form>
        </div>
        <div id="output"></div>
    </body>
</html>
