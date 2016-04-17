package com.example.mathew.movies.RESTbackend;

import com.example.mathew.movies.DataClasses.Movies;
import com.example.mathew.movies.DataClasses.Users;

import java.util.ArrayList;

/**
 * Created by Karol on 02.04.2016.
 */

//trieda vracia odpoved od spojenia
public class ConnectionResponse {
    private int StatusID; // podla ID vidis ci sa to podarilo alebo nie, to preto aby si si to vedel ohandlovat v aplikacii
    private String ReturnedMessadge; // string, sprava o vysledku operacie, podla toho zistis ktore ID co znamena
    private ArrayList<Movies> filmy; // toto obsahuje samotny zoznam filmou stiahnuty zo servera, ak sa to nepodarilo obsahuje null
    private ArrayList<Users> users;

    //konstruktor na vytvaranie aj so zoznamom filmou
    public ConnectionResponse(int ID, ArrayList<Movies> filmy){
        this.StatusID=ID;
        this.filmy = null;
        switch (ID){
            case 1: this.ReturnedMessadge = "Download sucessfull"; this.filmy=filmy; break;
            case 0: this.ReturnedMessadge = "Download failure"; break;
            case 2: this.ReturnedMessadge = "Upload sucessfull"; break;
            case 3: this.ReturnedMessadge = "Upload failure"; break;
            case 4: this.ReturnedMessadge = "Update sucessfull"; break;
            case 5: this.ReturnedMessadge = "Update failure"; break;
            case 6: this.ReturnedMessadge = "Deletion sucessfull"; break;
            case 7: this.ReturnedMessadge = "Deletion failure"; break;
            default: this.ReturnedMessadge = "Unknown error occoured";
        }


    }

    //konstruktor na vytvaranie s custom spravou
    public ConnectionResponse(ArrayList<Users> usery){
        this.StatusID=8;
        this.users=usery;
    }

    //konstruktor na vytvaranie bez zoznamu filmou
    public ConnectionResponse(int ID){
        this.StatusID=ID;
        this.filmy = null;
        switch (ID){
            case 1: this.ReturnedMessadge = "Download sucessfull"; this.filmy=filmy; break;
            case 0: this.ReturnedMessadge = "Download failure"; break;
            case 2: this.ReturnedMessadge = "Upload sucessfull"; break;
            case 3: this.ReturnedMessadge = "Upload failure"; break;
            case 4: this.ReturnedMessadge = "Update sucessfull"; break;
            case 5: this.ReturnedMessadge = "Update failure"; break;
            case 6: this.ReturnedMessadge = "Deletion sucessfull"; break;
            case 7: this.ReturnedMessadge = "Deletion failure"; break;
            default: this.ReturnedMessadge = "Unknown error occoured";
        }


    }


    //konstruktor na vytvaranie s custom spravou
    public ConnectionResponse(int ID, String messadge){
        this.StatusID=ID;
        this.ReturnedMessadge=messadge;
        this.filmy=null;
    }

    //getter na filmy, vrati ti array list filmou
    public ArrayList<Movies> getFilmy() {
        return filmy;
    }

    public ArrayList<Users> getUsers() {
        return users;
    }

    public String toString(){
        return ("****CRUD operacia: "+this.ReturnedMessadge);
    }


}
