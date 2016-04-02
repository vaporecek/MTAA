package com.example.mathew.movies.RESTbackend;

/**
 * Created by Karol on 02.04.2016.
 */
public class ConnectionReturnedStatus {
    int StatusID; //1 - successfull, 0-failure
    String ReturnedMessadge;


    public ConnectionReturnedStatus(int ID){
        this.StatusID=ID;
        switch (ID){
            case 1: this.ReturnedMessadge = "sucessfull"; break;
            case 0: this.ReturnedMessadge = "failure"; break;
            default: this.ReturnedMessadge = "undefined";
        }
    }

    public ConnectionReturnedStatus(int ID, String messadge){
        this.StatusID=ID;
        this.ReturnedMessadge=messadge;
    }
}
