
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
     org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(com.test.websocket.Gamedata.class);

    public Gamedata() {
    }
String [] UserData = new String[50];

    public Gamedata(JSONObject json) throws JSONException {
        this.json = json;
        String uName = (String) json.get("Name");
        String uDice = (String) json.get("Dice");
        logger.info ("In GameData"+json);
        logger.info ("In GameData"+json);
        logger.info ("GameData Length"+json.length());
        logger.info ("User Name:"+uName);
        logger.info ("User Dice Count:"+uDice);
       
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
