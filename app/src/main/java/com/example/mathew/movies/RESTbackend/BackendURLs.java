package com.example.mathew.movies.RESTbackend;

import android.util.Log;

import java.net.URL;

/**
 * Created by Karol on 02.04.2016.
 */

//storuje URL-ky, neskor cely post a get messadge config
public class BackendURLs{

    public static URL GETmoviesURL;

    static {
        try {
            GETmoviesURL = new URL("https://api.backendless.com/v1/data/Movies");
        }
        catch (Exception e){
            Log.e("URL", e.getClass() + ": " + e.getMessage());
        }


    }


}
