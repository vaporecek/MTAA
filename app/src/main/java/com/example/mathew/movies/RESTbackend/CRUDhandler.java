package com.example.mathew.movies.RESTbackend;

import android.util.Log;

import com.example.mathew.movies.DataClasses.Movies;

import java.util.ArrayList;

/**
 * Created by Karol on 14.04.2016.
 */
public class CRUDhandler {

    public static ConnectionResponse GET() throws Exception{

        RestCommunicator com = new RestCommunicator();
        RestConfig config = new RestConfig(1);
        com.execute(config);

        ConnectionResponse Odpoved = com.get();

        System.out.println(Odpoved.toString());

        if (Odpoved.getStatusID()==10)
            Odpoved.updateFilmy(CRUDhandler.GET(Odpoved.getURLka()));

        return (Odpoved);
    }

    private static ArrayList<Movies> GET(String URLka) throws Exception{

        RestCommunicator com = new RestCommunicator();
        RestConfig config = new RestConfig(URLka);
        com.execute(config);
        ConnectionResponse Odpoved = com.get();

        System.out.println(Odpoved.toString());


        return (Odpoved.getFilmy());
    }

    public static ConnectionResponse PUT(Movies film) throws Exception{

        RestCommunicator com = new RestCommunicator();
        RestConfig config = new RestConfig(2,film);
        com.execute(config);

        ConnectionResponse Odpoved = com.get();

        System.out.println(Odpoved.toString());


        return (Odpoved);
    }

    public static ConnectionResponse POST(Movies film) throws Exception{

        RestCommunicator com = new RestCommunicator();
        RestConfig config = new RestConfig(3,film);
        com.execute(config);

        ConnectionResponse Odpoved = com.get();

        System.out.println(Odpoved.toString());


        return (Odpoved);
    }

    public static ConnectionResponse DELETE(Movies film) throws Exception{

        RestCommunicator com = new RestCommunicator();
        RestConfig config = new RestConfig(4,film);
        com.execute(config);

        ConnectionResponse Odpoved = com.get();

        System.out.println(Odpoved.toString());


        return (Odpoved);
    }

}
