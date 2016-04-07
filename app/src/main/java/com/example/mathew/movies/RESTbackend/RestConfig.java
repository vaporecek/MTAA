package com.example.mathew.movies.RESTbackend;

import android.util.Log;

import java.net.URL;

/**
 * Created by Karol on 02.04.2016.
 */

//storuje URL-ky, neskor cely post a get messadge config
public class RestConfig{

    private static URL moviesURL;
    private int type; //1 - GET, 2-PUT, 3-POST

    static {
        try {
            moviesURL = new URL("https://api.backendless.com/v1/data/Movies");
        }
        catch (Exception e){
            Log.e("URL", e.getClass() + ": " + e.getMessage());
        }


    }

    public URL getURL(){
        return moviesURL;
    }

    public String getMETHOD(){
        switch (this.type) {
            case 1:
                return "GET";
            case 2:
                return "PUT";
            case 3:
                return "POST";
        }
        return "";
    }

    public int getTYPE(){
        return this.type;
    }

}
