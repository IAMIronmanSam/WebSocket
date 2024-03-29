<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<title>WebSocket in Java</title>
        <script language="javascript" type="text/javascript">
            var dice=0;
            var name;
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
                
                var json = JSON.stringify({
                        "Name": name,
                        "Dice": dice
                    });
                websocket.send(json);
                writeToScreen("SENT: " +json);
                console.log("Msg Sent"+json);    
                document.getElementById("diceField").value=dice;  
            }

            function onOpen(evt) {
                writeToScreen("CONNECTED");
            }

            function onMessage(evt) {
                var ab =evt.data;
                console.log("Msg Recieving");
                 console.log(evt.data);
                 console.log("Dice Move"+dice+dice);
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
                <label>Name:</label>
                <input id="nameField" placeholder="Name"type="text"/><br><br>
                <label>Dice Count:</label>
                <input id="diceField" placeholder="Dice"type="text"/><br><br>
                 <label>Position</label>
                <input id="positionField" placeholder="Position"type="text"/><br><br>
                <input onclick="say_hello()" value="Roll" type="button"/>
            </form>
        </div>
        <div id="output"></div>
    </body>
</html>
