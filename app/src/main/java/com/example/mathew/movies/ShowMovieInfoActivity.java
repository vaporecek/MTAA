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

    TextView tv_Title;
    TextView tv_desc;
    TextView tv_where_back;
    TextView tv_rating_back;
    TextView tv_gander_back;
    TextView tv_screenplay_back;
    TextView tv_country_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_movie_info);

        tv_Title = (TextView)findViewById(R.id.tv_Title);
        tv_desc = (TextView)findViewById(R.id.tv_desc);
        tv_where_back = (TextView)findViewById(R.id.tv_where_back);
        tv_rating_back = (TextView)findViewById(R.id.tv_rating_back);
        tv_gander_back = (TextView)findViewById(R.id.tv_gander_back);
        tv_screenplay_back = (TextView)findViewById(R.id.tv_screenplay_back);
        tv_country_back = (TextView)findViewById(R.id.tv_country_back);

        Bundle b = getIntent().getExtras();
        int indexMovie = b.getInt("indexMovie");

        try {
            ConnectionResponse poslednaOdpoved = CRUDhandler.GET(); //takto volam GET
            filmy = poslednaOdpoved.getFilmy(); //takto ziskam zoznam filmou (pozri triedu ConnectionResponse)
        }
        catch(Exception e){e.printStackTrace();}


        Movies m = filmy.get(indexMovie);
        

        tv_Title.setText(m.getTitle());
        tv_desc.setText(m.getDescription());
        tv_where_back.setText(m.getTo_watch());
     //   tv_rating_back.setText(m.getRating());
     //   tv_gander_back.setText(m.getGender());
        tv_screenplay_back.setText(m.getScreenplay());
     //   tv_country_back.setText(m.getCountry());


    //    m.setDescription("Tvoja matka");      //puzijem na post alebo aby som to nastavil

        try {
            ConnectionResponse poslednaOdpoved = CRUDhandler.PUT(m); //takto volam PUT
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
