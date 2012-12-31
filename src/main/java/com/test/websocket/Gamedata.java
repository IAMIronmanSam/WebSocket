
package com.test.websocket;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * @author iArivu
 */
public class Gamedata {
    private JSONObject json;

    public Gamedata() {
    }

    public Gamedata(JSONObject json) {
        this.json = json;
    }
    
    public JSONObject getJson() {
        return json;
    }

    public void setJson(JSONObject json) {
        this.json = json;
    }
    
    @Override
    public String toString() {
        try {
            return json.toString(2);
        } catch (JSONException ex) {
            Logger.getLogger(Gamedata.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}