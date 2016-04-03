package com.example.mathew.movies.DataClasses;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Karol on 02.04.2016.
 */
//Trieda prijme JsonString s jasona, postupne ho prejde a rozdeli najednotlive objekty nad ktorymi zavola konstruktor, vracia array list filmou
public class JsonProcesing {

    public static ArrayList<Movies> GenerateMovies(JSONArray imputarray){

        ArrayList<Movies> filmy = new ArrayList<Movies>();

        try {

            //nad kazdym objektom v sprave zavolam konstruktor na vytvorenie novej instancie Movies
            for (int i=0; i<imputarray.length(); i++) {
                filmy.add(new Movies(imputarray.getJSONObject(i), i));

            }
        } catch (Exception e) {
            Log.e("JsonProcesing", e.getClass() + ": " + e.getMessage());
        }

        return filmy;
    }

}
