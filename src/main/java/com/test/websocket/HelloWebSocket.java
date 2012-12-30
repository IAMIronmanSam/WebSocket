/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.websocket;

import javax.websocket.EndpointFactory;
import javax.websocket.WebSocketEndpoint;
import javax.websocket.WebSocketMessage;

/**
 *
 * @author iarivu
 */


@WebSocketEndpoint(value = "/hello",
encoders = {FigureEncoder.class},
decoders = {FigureDecoder.class},
factory = com.test.websocket.DummyEndpointFactory.class)
public class HelloWebSocket {

    @WebSocketMessage
    public String sayHello(String name) {
        return "Hello " + name + "!";
    }
} 

  class DummyEndpointFactory implements EndpointFactory {

        @Override
        public Object createEndpoint() {
            return null;
        }
    }