package com.example.mathew.movies.RESTbackend;

import android.util.Log;

import com.example.mathew.movies.DataClasses.JsonProcesing;
import com.example.mathew.movies.DataClasses.Movies;

import java.net.URL;

import static java.lang.String.*;

/**
 * Created by Karol on 02.04.2016.
 */

//clasa obsahuje config na volanie backendoveho komunikatora, ak zavolam s paramentrom type 1 - GET, 2-PUT, 3-POST, 4-DELETE
public class RestConfig{

    private static URL moviesURL;
    private static URL usersURL;
    private int type; //1 - GET, 2-PUT, 3-POST, 4-DELETE, 5-USER AUTENTKATION
    private static Movies film;

    //nakonfigurujeme aj URL
    static {
        try {
            moviesURL = new URL("https://api.backendless.com/v1/data/Movies");
            usersURL = new URL("https://api.backendless.com/v1/data/Users");
        }
        catch (Exception e){
            Log.e("URL", e.getClass() + ": " + e.getMessage());
        }


    }

    //vracia URL
    public URL getURL(){
        //ak sa jedna o user autentikaciu
        if(this.type==5){
            return usersURL;
        }

        //ak vraciame URL pre DELETE a PUT
        if(this.type==4 || this.type==2){
            try {
                return new URL("https://api.backendless.com/v1/data/Movies/" + film.getBackendlessObjectid());
            }catch (Exception e){
               Log.e("URL", e.getClass() + ": " + e.getMessage());
            }
        }

        //URL pre PUT, GET
        return moviesURL;
    }

    //Vrati HTTP metodu
    public String getMETHOD(){
        switch (this.type) {
            case 1:
                return "GET"; //ziskat zoznam vsetkych
            case 2:
                return "PUT"; //aktualizacia existujuceho objektu
            case 3:
                return "POST"; //pridanie noveho objektu
            case 4:
                return "DELETE"; //zmazem entitu
            case 5:
                return "GET";
        }
        return "";
    }

    //getter na type
    public int getTYPE(){
        return this.type;
    }

    //konstruktor na GET
    public RestConfig(int type) {
        this.type = type;
    }

    //konstruktor pre POST, PUT, DELETE, treba pridat aj entitu o mazem
    public RestConfig(int type, Movies film) {
        this.type = type;
        RestConfig.film = film;
    }

    //vrati JSON z prilozeneho filmu pre PUT a POST
    public String getJson(){
        return valueOf((JsonProcesing.GenerateJason(film)));
    }

}
