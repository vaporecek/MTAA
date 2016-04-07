package com.example.mathew.movies.RESTbackend;

/**
 * Created by Karol on 17.03.16.
 */
import android.os.AsyncTask;
import android.util.Log;

import com.example.mathew.movies.DataClasses.JsonProcesing;
import com.example.mathew.movies.DataClasses.Movies;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class RestCommunicator extends AsyncTask<RestConfig, Void, ConnectionResponse>{



    public RestCommunicator() {
    }



    //metoda na pripajanie, hlavna exekucna metoda
    protected ConnectionResponse doInBackground(RestConfig... params) {



        try {

            System.out.println("******Pripajam sa na: "+params[0].getURL()+"******");
            HttpURLConnection connection = (HttpURLConnection)params[0].getURL().openConnection();
            connection.setRequestMethod(params[0].getMETHOD());
            connection.setUseCaches(false);
            connection.setDoInput(true);
            //connection.setRequestProperty("Connection", "Keep-Alive");
            //connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("application-id", BackendLessOption.APPLICATION_ID );
            connection.setRequestProperty("secret-key", BackendLessOption.ANDROID_SECRET_KEY);
            connection.setRequestProperty("version", BackendLessOption.VERSION);
            //connection.setRequestProperty("application-type", "application/json");


            //pre GET
            if(params[0].getTYPE()==1) {

                connection.connect();
                String responseBody = readStream(connection.getInputStream());
                int responseCode = connection.getResponseCode();
                Log.d("REST", "Response: " + responseCode);

                //ak bol request uspesny idem parsovat Json
                if (responseCode == 200) {
                    JSONObject objektus = new JSONObject(responseBody);

                    //zavolam parser ktory spracuje Json
                    ArrayList<Movies> list = JsonProcesing.GenerateMovies(objektus.getJSONArray("data"));

                    return new ConnectionResponse(1, list);

                } else {
                    return new ConnectionResponse(0);
                }
            }

            if (params[0].getTYPE()==2){
                OutputStreamWriter out = new OutputStreamWriter(
                        connection.getOutputStream());
                out.write("some data");
                out.close();


                int responseCode = connection.getResponseCode();
                Log.d("REST", "Response: " + responseCode);

                //ak bol request uspesny idem parsovat Json
                if (responseCode == 200) {
                } else {
                    return new ConnectionResponse(0);
                }

            }


        } catch (Exception e)
        {
            Log.e("REST", e.getClass() + ": " + e.getMessage());
        }



        return null;
    }

    @Override
    //vracia vysledky operacie
    protected void onPostExecute(ConnectionResponse answer){
        super.onPostExecute(answer);
    }

    //parser na imputstream, vrati jason ako string
    public String readStream(InputStream stream) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder sb = new StringBuilder();
        String line;

        while((line = reader.readLine()) != null)
        {
            sb.append(line);
        }

        return sb.toString();
    }

}

