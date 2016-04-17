package com.example.mathew.movies.DataClasses;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Karol on 02.04.2016.
 */

//trieda co implementuje filmy je napisana presne podla databazy, ak tu budes nieco menit tak to konzultuj so mnou lebo s nou intenzivne pracujem
public class Movies {
    private int listingID;
    private int country;
    private String description;
    private int gender;
    private String picture;
    private Date play_time;
    private int rating;
    private String screenplay;
    private String title;
    private String to_watch;
    private String backendlessObjectid;
    private String ownerId;
    private Date created;
    private Date updated;
    private SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMddHHMMSS"); //uklada format datumu a casu v akom je to ulozene na backendlesse

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
            String prehravanie = toParse.getString("Play_time");
            String aktualizacnycas = toParse.getString("created");
            if(aktualizacnycas != null)
                this.created = originalFormat.parse(aktualizacnycas);
            else
                this.created=null;
            if(prehravanie != null)
                this.play_time = originalFormat.parse(toParse.getString(prehravanie));
            else
                this.play_time = null;
            String updateddate = toParse.getString("updated");
            if(updateddate != null)
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

    //getters and setters
    public int getListingID() {
        return listingID;
    }

    public void setListingID(int listingID) {
        this.listingID = listingID;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Date getPlay_time() {
        return play_time;
    }

    public void setPlay_time(Date play_time) {
        this.play_time = play_time;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getScreenplay() {
        return screenplay;
    }

    public void setScreenplay(String screenplay) {
        this.screenplay = screenplay;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTo_watch() {
        return to_watch;
    }

    public void setTo_watch(String to_watch) {
        this.to_watch = to_watch;
    }

    public String getBackendlessObjectid() {
        return backendlessObjectid;
    }

    public void setBackendlessObjectid(String backendlessObjectid) {
        this.backendlessObjectid = backendlessObjectid;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
