package com.example.mathew.movies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mathew.movies.DataClasses.Movies;
import com.example.mathew.movies.RESTbackend.CRUDhandler;
import com.example.mathew.movies.RESTbackend.ConnectionResponse;

import java.util.ArrayList;

public class ShowMovieInfoActivity extends AppCompatActivity {
    ArrayList<Movies> filmy;

    TextView tv_IDcko;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_movie_info);

        tv_IDcko = (TextView)findViewById(R.id.tv_IDcko);

        Bundle b = getIntent().getExtras();
        int indexMovie = b.getInt("indexMovie");



        try {
            ConnectionResponse poslednaOdpoved = CRUDhandler.GET(); //takto volam GET
            filmy = poslednaOdpoved.getFilmy(); //takto ziskam zoznam filmou (pozri triedu ConnectionResponse)
        }
        catch(Exception e){e.printStackTrace();}

        Movies m = filmy.get(indexMovie);


        tv_IDcko.setText(m.getDescription());

        m.setDescription("Tvoja matka");

        try {
            ConnectionResponse poslednaOdpoved = CRUDhandler.PUT(m); //takto volam GET
        }
        catch(Exception e){e.printStackTrace();}
    }

    @Override
    public void onBackPressed() {
        Intent mainIntent = new Intent(ShowMovieInfoActivity.this, MainActivity.class);

        startActivity(mainIntent);
        finish();
    }
}
