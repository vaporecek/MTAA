package com.example.mathew.movies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

          import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.example.mathew.movies.DataClasses.Movies;
import com.example.mathew.movies.RESTbackend.CRUDhandler;
import com.example.mathew.movies.RESTbackend.ConnectionResponse;

import java.util.ArrayList;

public class EditMovieInfoActivity extends AppCompatActivity {
    ArrayList<Movies> filmy;
    EditText et_Title;
    EditText et_desc;
    private Spinner spinner_Rating;
    private Spinner spinner_Gander;
    private Spinner spinner_Country;
    int indexMovie;
    public static Movies m;
    TextView tv_where_back;
    TextView tv_screenplay_back;
    private static String rating[] = {"Slaby", "Priemerny", "Perfektny"};
    private static String gander[] = {"Sci-fi", "Comendy", "Porn"};
    private static String country[] = {"Slovakia", "USA", "France"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie_info);

        Bundle b = getIntent().getExtras();
        if(b!=null)
            indexMovie = b.getInt("indexMovie");

        et_Title = (EditText)findViewById(R.id.et_title);
        et_desc = (EditText)findViewById(R.id.et_desc);
        spinner_Rating = (Spinner)findViewById(R.id.spinner_Rating);
        spinner_Gander = (Spinner)findViewById(R.id.spinner_Gander);
        tv_where_back = (TextView)findViewById(R.id.tv_where_back);
        tv_screenplay_back = (TextView)findViewById(R.id.tv_screenplay_back);
        spinner_Country = (Spinner)findViewById(R.id.spinner_Country);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, rating);
        spinner_Rating.setAdapter(adapter);
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, gander);
        spinner_Gander.setAdapter(adapter2);
        ArrayAdapter adapter3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, country);
        spinner_Country.setAdapter(adapter3);


        try {
            ConnectionResponse poslednaOdpoved = CRUDhandler.GET(); //takto volam GET
            filmy = poslednaOdpoved.getFilmy(); //takto ziskam zoznam filmou (pozri triedu ConnectionResponse)
        }
        catch(Exception e){e.printStackTrace();}

        m = filmy.get(indexMovie);

        et_Title.setText(m.getTitle());
        et_desc.setText(m.getDescription());
        tv_where_back.setText(m.getTo_watch());
        tv_screenplay_back.setText(m.getScreenplay());

      /*  adapter = ArrayAdapter.createFromResource(this,R.array.Rating,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Rating.setAdapter(adapter);
        spinner_Rating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position) == "Perfektny")
                    m.setRating(1);
                else if (parent.getItemAtPosition(position) == "Priemerny")
                    m.setRating(2);
                else if (parent.getItemAtPosition(position) == "Slaby")
                    m.setRating(3);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

/*
            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.btn_save:

                        m.setRating(2);

                        break;
                }
                return true;
            }

        });
*/
        /*
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                m.setTitle(et_Title.getText().toString());
                m.setDescription(et_desc.getText().toString());

                String kokot = " " + (int) spinner_Rating.getSelectedItemId();
                m.setRating((int) spinner_Rating.getSelectedItemId());
           //     Log.d("ahoj", kokot);

                try {
                    ConnectionResponse poslednaOdpoved = CRUDhandler.PUT(m); //takto volam PUT
                } catch (Exception e) {
                    e.printStackTrace();
                }


                Intent mainIntent = new Intent(EditMovieInfoActivity.this, ShowMovieInfoActivity.class);
                Bundle b = new Bundle();
                mainIntent.putExtra("indexMovie", indexMovie);
                startActivity(mainIntent);
                finish();

            }
        });
*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.update_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.act_button_ok:


                m.setTitle(et_Title.getText().toString());
                m.setDescription(et_desc.getText().toString());
                m.setRating((int) spinner_Rating.getSelectedItemId() + 1);
                m.setGender((int) spinner_Gander.getSelectedItemId() + 1);
                m.setCountry((int) spinner_Country.getSelectedItemId() + 1);


                try {
                    ConnectionResponse poslednaOdpoved = CRUDhandler.PUT(m); //takto volam PUT
                } catch (Exception e) {
                    e.printStackTrace();
                }


                Intent mainIntent = new Intent(EditMovieInfoActivity.this, ShowMovieInfoActivity.class);
                Bundle b = new Bundle();
                mainIntent.putExtra("indexMovie", indexMovie);
                startActivity(mainIntent);
             //   finish();

                break;

        }
        return true;
    }

/*
    @Override
    public void onBackPressed() {
        Intent mainIntent = new Intent(EditMovieInfoActivity.this, ShowMovieInfoActivity.class);

        startActivity(mainIntent);
        finish();
    }*/
}
