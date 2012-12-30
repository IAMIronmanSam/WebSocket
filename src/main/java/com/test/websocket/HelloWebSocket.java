/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.websocket;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointFactory;
import javax.websocket.WebSocketEndpoint;
import javax.websocket.WebSocketMessage;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

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

    class FigureEncoder implements Encoder.Text<Figure> {
    @Override
    public String encode(Figure figure) throws EncodeException {
        return figure.getJson().toString();
    }
}

    class FigureDecoder implements Decoder.Text<Figure> {
    @Override
    public Figure decode(String string) throws DecodeException {
        try {
            System.out.println("decoding: " + string);
            JSONObject jsonObject = new JSONObject(string);
            return new Figure(jsonObject);
        } catch (JSONException ex) {
            throw new DecodeException("Error parsing JSON", ex.getMessage(), ex.fillInStackTrace());
        }
    }

    @Override
    public boolean willDecode(String string) {
        try {
            new JSONObject(string);
            return true;
        } catch (JSONException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
}
