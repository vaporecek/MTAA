package com.example.mathew.movies.DataClasses;

import android.util.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Karol on 02.04.2016.
 */

//trieda co implementuje filmy je napisana presne podla databazy, ak tu budes nieco menit tak to konzultuj so mnou lebo s nou intenzivne pracujem
public class Movies {
    int listingID;
    int country;
    String description;
    int gender;
    String picture;
    Date play_time;
    int rating;
    String screenplay;
    String title;
    String to_watch;
    String backendlessObjectid;
    String ownerId;
    Date created;
    Date updated;
    SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMddHHMMSS"); //uklada format datumu a casu v akom je to ulozene na backendlesse

    //Zakladny konstruktor, keby si potrebujes vytvorit novy zaznam
    public Movies(int listingID, int country, String description, int gender, String picture, Date play_time, int rating, String screenplay, String title, String to_watch, String backendlessObjectid, String ownerId, Date created, Date updated) {
        this.listingID = listingID;
        this.country = country;
        this.description = description;
        this.gender = gender;
        this.picture = picture;
        this.play_time = play_time;
        this.rating = rating;
        this.screenplay = screenplay;
        this.title = title;
        this.to_watch = to_watch;
        this.backendlessObjectid = backendlessObjectid;
        this.ownerId = ownerId;
        this.created = created;
        this.updated = updated;
    }

    //konstruktoz z Jason, pouziva sa na vytvaranie zaznamou pri parsovani z jasona, ked pride zo serveru
    public Movies(JSONObject toParse, int ID) {

        try {

            this.listingID = ID;
            this.country = toParse.getInt("country");
            this.description = toParse.getString("Description");
            this.gender = toParse.getInt("gender");
            this.picture = toParse.getString("picture");
            this.rating = toParse.getInt("rating");
            this.screenplay = toParse.getString("Screenplay");
            this.title = toParse.getString("Title");
            this.to_watch = toParse.getString("to_watch");
            this.backendlessObjectid = toParse.getString("objectId");
            this.ownerId = toParse.getString("ownerId");
            this.play_time = originalFormat.parse(toParse.getString("Play_time"));
            this.created = originalFormat.parse(toParse.getString("created"));
            String updateddate = toParse.getString("updated");
            if(updateddate != "null")
                this.updated = originalFormat.parse(toParse.getString("updated"));
            else
                this.updated = null;


        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println("******"+toParse);
        //System.out.println("******"+this.toString());
    }

    //taky pekny tostring aby si si vedel klasu vypisat ako vyzera, feel free to edit
    public String toString() {
        return "Movies [listingID=" + listingID + ", country=" + country + ", description=" + description + ", gender="
                + gender + ", picture=" + picture + ", play_time=" + play_time + ", rating=" + rating + ", screenplay="
                + screenplay + ", title=" + title + ", to_watch=" + to_watch + ", backendlessObjectid="
                + backendlessObjectid + ", ownerId=" + ownerId + ", created=" + created + ", updated=" + updated + "]";
    }
}
