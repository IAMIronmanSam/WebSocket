/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.websocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointFactory;
import javax.websocket.Session;
import javax.websocket.WebSocketClose;
import javax.websocket.WebSocketEndpoint;
import javax.websocket.WebSocketMessage;
import javax.websocket.WebSocketOpen;
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

    Set<Session> user = Collections.synchronizedSet(new HashSet<Session>());

    @WebSocketOpen
    public void onOpen(Session peer) {
        user.add(peer);
    }

    @WebSocketClose
    public void onClose(Session peer) {
        user.remove(peer);
    }


    @WebSocketMessage
    public void boradcast(Gamedata gd, Session session) throws IOException, EncodeException {
        System.out.println("boradcast: " + gd);
        for (Session peer : user) {
            if (!peer.equals(session)) {
                peer.getRemote().sendObject(gd);
            }
        }
    }
} 

  class DummyEndpointFactory implements EndpointFactory {

        @Override
        public Object createEndpoint() {
            return null;
        }
    }

    class FigureEncoder implements Encoder.Text<Gamedata> {
    @Override
    public String encode(Gamedata figure) throws EncodeException {
        return figure.getJson().toString();
    }
}

    class FigureDecoder implements Decoder.Text<Gamedata> {
    @Override
    public Gamedata decode(String string) throws DecodeException {
        try {
            System.out.println("decoding: " + string);
            JSONObject jsonObject = new JSONObject(string);
            return new Gamedata(jsonObject);
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
