
package com.test.websocket;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * @author Arun Gupta
 */
public class DataDecoder implements Decoder.Text<Gamedata> {
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
