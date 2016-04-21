package com.example.mathew.movies;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
    Integer rating;
    Integer gander;
    Integer country;
    Button btn_edit;
    Button btn_del;
    ImageView image;
    int indexMovie;

  //  AlertDialog.Builder builder1 = new AlertDialog.Builder(getApplicationContext());



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
        btn_edit = (Button)findViewById(R.id.btn_edit);
        btn_del = (Button)findViewById(R.id.btn_delete);
        image = (ImageView) findViewById(R.id.img);

        Bundle b = getIntent().getExtras();
         indexMovie = b.getInt("indexMovie");

        try {
            ConnectionResponse poslednaOdpoved = CRUDhandler.GET(); //takto volam GET
            filmy = poslednaOdpoved.getFilmy(); //takto ziskam zoznam filmou (pozri triedu ConnectionResponse)
        }
        catch(Exception e){e.printStackTrace();}

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(ShowMovieInfoActivity.this, ImgViewActivity.class);
                startActivity(mainIntent);
              //  finish();
            }
        });

        final Movies m = filmy.get(indexMovie);

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(ShowMovieInfoActivity.this, EditMovieInfoActivity.class);
                Bundle b = new Bundle();
                mainIntent.putExtra("indexMovie", indexMovie);

               // System.out.println(indexMovie + "            ceckyyyyyyyyyyyyyyyyyyy");
                startActivity(mainIntent);
                finish();

            }
        });
      //  String imageSource;
      //  imageSource = m.getPicture();
      //  image.setImageResource(imageSource);

        rating = m.getRating();
        if (rating == 1)
            tv_rating_back.setText("Perfektny");
        else if (rating == 2)
            tv_rating_back.setText("Priemerny");
        else if (rating == 3)
            tv_rating_back.setText("Slaby");

        gander = m.getGender();
        if (gander == 1)
            tv_gander_back.setText("Sci-Fi");
        else if (gander == 2)
            tv_gander_back.setText("Comedy");
        else if (gander == 3)
            tv_gander_back.setText("Porn");

        country = m.getCountry();
        if (country == 1)
            tv_country_back.setText("Slovakia");
        if (country == 2)
            tv_country_back.setText("USA");
        if (country == 3)
            tv_country_back.setText("France");


        tv_Title.setText(m.getTitle());
        tv_desc.setText(m.getDescription());
        tv_where_back.setText(m.getTo_watch());
     //   tv_rating_back.setText(rating.toString());
     //   tv_gander_back.setText(gander.toString());
        tv_screenplay_back.setText(m.getScreenplay());
     //   tv_country_back.setText(country.toString());



        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(ShowMovieInfoActivity.this);
                builder1.setMessage("Are you sure you want to delete this movie?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                try {
                                    ConnectionResponse poslednaOdpoved = CRUDhandler.DELETE(m); //takto volam GET
                                    filmy = poslednaOdpoved.getFilmy(); //takto ziskam zoznam filmou (pozri triedu ConnectionResponse)
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                dialog.cancel();
                                Intent mainIntent = new Intent(ShowMovieInfoActivity.this, MainActivity.class);
                                startActivity(mainIntent);
                                finish();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        });
    /*    m.setDescription("Tvoja matka");      //puzijem na post alebo aby som to nastavil

        try {
            ConnectionResponse poslednaOdpoved = CRUDhandler.PUT(m); //takto volam PUT
        }
        catch(Exception e){e.printStackTrace();}
    */
    }


    @Override
    public void onBackPressed() {
        Intent mainIntent = new Intent(ShowMovieInfoActivity.this, MainActivity.class);


        startActivity(mainIntent);
        finish();
    }
}
