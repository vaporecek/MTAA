package com.example.mathew.movies.Micelaus;

import com.example.mathew.movies.DataClasses.Users;
import com.example.mathew.movies.RESTbackend.ConnectionResponse;
import com.example.mathew.movies.RESTbackend.RestCommunicator;
import com.example.mathew.movies.RESTbackend.RestConfig;

import java.util.ArrayList;

/**
 * Created by Karol on 17.04.2016.
 */
public class UserValidation {

    public static boolean IsRegistered(String name, String pass){
        try {

        RestCommunicator com = new RestCommunicator();
        RestConfig config = new RestConfig(5);
        com.execute(config);


            ConnectionResponse Odpoved = com.get();

        ArrayList<Users> users = Odpoved.getUsers();
            System.out.println(users.toString());

        for(Users pouzivatel : users){
            if(pouzivatel.isUser(name,pass))
                return true;
        }
        return false;

        }catch(Exception e){e.printStackTrace();}

        return false;
    }
}
