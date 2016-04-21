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
    EditText et_desc;
    Button btn_save;
    int indexMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie_info);

        Bundle b = getIntent().getExtras();
        if(b!=null)
            indexMovie = b.getInt("indexMovie");

        et_Title = (EditText)findViewById(R.id.et_title);
        et_desc = (EditText)findViewById(R.id.et_desc);
        btn_save = (Button)findViewById(R.id.btn_save);


        try {
            ConnectionResponse poslednaOdpoved = CRUDhandler.GET(); //takto volam GET
            filmy = poslednaOdpoved.getFilmy(); //takto ziskam zoznam filmou (pozri triedu ConnectionResponse)
        }
        catch(Exception e){e.printStackTrace();}

        final Movies m = filmy.get(indexMovie);

        et_Title.setText(m.getTitle());
        et_desc.setText(m.getDescription());



        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                m.setTitle(et_Title.getText().toString());

                try {
                    ConnectionResponse poslednaOdpoved = CRUDhandler.PUT(m); //takto volam PUT
                }
                catch(Exception e){e.printStackTrace();}



                Intent mainIntent = new Intent(EditMovieInfoActivity.this, ShowMovieInfoActivity.class);
                Bundle b = new Bundle();
                mainIntent.putExtra("indexMovie", indexMovie);
                startActivity(mainIntent);
                finish();

            }
        });

    }

}
