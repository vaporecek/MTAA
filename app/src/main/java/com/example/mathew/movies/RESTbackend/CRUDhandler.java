package com.example.mathew.movies.RESTbackend;

import com.example.mathew.movies.DataClasses.Movies;

import java.util.ArrayList;

/**
 * Created by Karol on 14.04.2016.
 */
public class CRUDhandler {

    public static ConnectionResponse GET() throws Exception{

        SoketCommunicator com = new SoketCommunicator();
        RestConfig config = new RestConfig(1);
        com.execute(config);

        ConnectionResponse Odpoved = com.get();

        System.out.println(Odpoved.toString());


        return (Odpoved);
    }


    public static ConnectionResponse PUT(Movies film) throws Exception{

        SoketCommunicator com = new SoketCommunicator();
        RestConfig config = new RestConfig(2,film);
        com.execute(config);

        return (new ConnectionResponse(4));
    }

    public static ConnectionResponse POST(Movies film) throws Exception{

        SoketCommunicator com = new SoketCommunicator();
        RestConfig config = new RestConfig(3,film);
        com.execute(config);


        return (new ConnectionResponse(2));
    }

    public static ConnectionResponse DELETE(Movies film) throws Exception{

        SoketCommunicator com = new SoketCommunicator();
        RestConfig config = new RestConfig(4,film);
        com.execute(config);

        return (new ConnectionResponse(6));
    }

    public static ConnectionResponse backendlessGET() throws Exception{

        RestCommunicator com = new RestCommunicator();
        RestConfig config = new RestConfig(1);
        com.execute(config);

        ConnectionResponse Odpoved = com.get();

        System.out.println(Odpoved.toString());

        if (Odpoved.getStatusID()==10)
            Odpoved.updateFilmy(CRUDhandler.GET(Odpoved.getURLka()));

        return (Odpoved);
    }

    //pomocna v pripade viac stranoveho listu pre backendless GET requesty
    private static ArrayList<Movies> GET(String URLka) throws Exception{

        RestCommunicator com = new RestCommunicator();
        RestConfig config = new RestConfig(URLka);
        com.execute(config);
        ConnectionResponse Odpoved = com.get();

        System.out.println(Odpoved.toString());


        return (Odpoved.getFilmy());
    }

    public static ConnectionResponse backendlessPUT(Movies film) throws Exception{

        RestCommunicator com = new RestCommunicator();
        RestConfig config = new RestConfig(2,film);
        com.execute(config);

        ConnectionResponse Odpoved = com.get();

        System.out.println(Odpoved.toString());


        return (Odpoved);
    }

    public static ConnectionResponse backendlessPOST(Movies film) throws Exception{

        RestCommunicator com = new RestCommunicator();
        RestConfig config = new RestConfig(3,film);
        com.execute(config);

        ConnectionResponse Odpoved = com.get();

        System.out.println(Odpoved.toString());


        return (Odpoved);
    }

    public static ConnectionResponse backendlessDELETE(Movies film) throws Exception{

        RestCommunicator com = new RestCommunicator();
        RestConfig config = new RestConfig(4,film);
        com.execute(config);

        ConnectionResponse Odpoved = com.get();

        System.out.println(Odpoved.toString());


        return (Odpoved);
    }

}
