<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hello WebSocket</title>

        <script language="javascript" type="text/javascript">
            var wsUri = "ws://localhost:8080/WebSocket/hello";
            var websocket = new WebSocket(wsUri);
            websocket.onopen = function(evt) { onOpen(evt) };
            websocket.onmessage = function(evt) { onMessage(evt) };
            websocket.onerror = function(evt) { onError(evt) };
            var dice = Math.floor((Math.random()*6)+1);
             
             
             var json = JSON.stringify({
                    "name": nameField.value,
                    "dice": dice
                });
            function init() {
                output = document.getElementById("output");
            }

            function say_hello() {
                websocket.send(json);
                writeToScreen("SENT: " + JSON.Stringify(json));
                   
            }

            function onOpen(evt) {
                writeToScreen("CONNECTED");
            }

            function onMessage(evt) {
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
        <h1>Getting Started with WebSocket!!</h1>

        <div style="text-align: center;">
            <form action=""> 
                <input onclick="say_hello()" value="Roll" type="button"> 
                <input id="nameField" name="name" value="WebSocket" type="text"><br>
            </form>
        </div>
        <div id="output"></div>
    </body>
</html>
