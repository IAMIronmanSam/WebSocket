/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.websocket;

/**
 *
 * @author iarivu
 */
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * @author Arun Gupta
 */
public class Figure {
    private JSONObject json;

    public Figure() {
    }

    public Figure(JSONObject json) {
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
            Logger.getLogger(Figure.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
