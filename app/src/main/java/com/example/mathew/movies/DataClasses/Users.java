package com.example.mathew.movies.DataClasses;

import org.json.JSONObject;

/**
 * Created by Karol on 17.04.2016.
 */
public class Users {
    String username;
    String password;

    public Users(JSONObject toParse) {
        try {
            this.username = toParse.getString("meno");
            this.password = toParse.getString("password");
        }catch(Exception e){e.printStackTrace();}
    }

    public boolean isUser (String name, String pass){
        return this.password == pass && this.username == name;
    }

}
