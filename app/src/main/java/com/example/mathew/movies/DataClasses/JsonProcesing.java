package com.example.mathew.movies.DataClasses;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Karol on 02.04.2016.
 */
//Trieda prijme JsonString s jasona, postupne ho prejde a rozdeli najednotlive objekty nad ktorymi zavola konstruktor, vracia array list filmou
public class JsonProcesing {

    private static SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMddHHMMSS");

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

    //vytvory z filmu novy JSONobject
    public static JSONObject GenerateJason(Movies film){
        JSONObject json = new JSONObject();

        try {

            json.put("country",film.getCountry());
            json.put("Description",film.getDescription());
            json.put("gender",film.getGender());
            json.put("rating",film.getRating());
            json.put("Title",film.getTitle());
            json.put("picture",film.getPicture());
            json.put("Play_time",originalFormat.format(film.getPlay_time()));
            json.put("to_watch",film.getTo_watch());
            json.put("Screenplay",film.getScreenplay());

        } catch (Exception e) {
            Log.e("JsonProcesing", e.getClass() + ": " + e.getMessage());
        }

        return json;
    }

    public static ArrayList<Users> GenerateUsers(JSONArray imputarray) {

        ArrayList<Users> usry = new ArrayList<Users>();

        try {

            //nad kazdym objektom v sprave zavolam konstruktor na vytvorenie novej instancie Movies
            for (int i=0; i<imputarray.length(); i++) {
                usry.add(new Users(imputarray.getJSONObject(i)));

            }
        } catch (Exception e) {
            Log.e("JsonProcesing", e.getClass() + ": " + e.getMessage());
        }

        return usry;
    }
}
