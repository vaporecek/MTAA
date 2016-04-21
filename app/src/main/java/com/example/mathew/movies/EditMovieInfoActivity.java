package com.example.mathew.movies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mathew.movies.DataClasses.Movies;
import com.example.mathew.movies.RESTbackend.CRUDhandler;
import com.example.mathew.movies.RESTbackend.ConnectionResponse;

import java.util.ArrayList;

public class EditMovieInfoActivity extends AppCompatActivity {
    ArrayList<Movies> filmy;

    EditText et_Title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie_info);

        et_Title = (EditText)findViewById(R.id.et_title);


        Bundle b = getIntent().getExtras();
        int indexMovie = b.getInt("indexMovie");

        try {
            ConnectionResponse poslednaOdpoved = CRUDhandler.GET(); //takto volam GET
            filmy = poslednaOdpoved.getFilmy(); //takto ziskam zoznam filmou (pozri triedu ConnectionResponse)
        }
        catch(Exception e){e.printStackTrace();}

        final Movies m = filmy.get(indexMovie);

        et_Title.setText(m.getTitle());

        System.out.println("jebeeeeeeeeeem ceckyyyyyyyyyyyyy");

    }

}
