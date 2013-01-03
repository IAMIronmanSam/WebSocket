
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
    public Gamedata(JSONObject json) {
        this.json = json;
        logger.info ("In GameData"+json);
        logger.info ("GameData Length"+json.length());
       /* for(int i=0;i<=json.length();i++)
        {
            UserData[i].equals(json);
             logger.info ("UserData"+UserData[i]);
        }
       */
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
