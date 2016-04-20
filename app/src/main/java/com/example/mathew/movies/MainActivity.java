package com.example.mathew.movies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import com.example.mathew.movies.DataClasses.Movies;
import com.example.mathew.movies.RESTbackend.CRUDhandler;
import com.example.mathew.movies.RESTbackend.ConnectionResponse;

public class MainActivity extends AppCompatActivity {


    public class CustomList extends ArrayAdapter<String>{

        private final Activity context;
        public final ArrayList<String> listOfTexts;
        public final ArrayList<String> listofDesc;
        public final ArrayList<Integer> imageIDs;
        public CustomList(Activity context, ArrayList<String> listOfTexts, ArrayList<Integer> imageId, ArrayList<String> listofDescript) {
            super(context, R.layout.imagetextlist, listOfTexts);
            this.context = context;
            this.listOfTexts = listOfTexts;
            this.imageIDs = imageId;
            this.listofDesc = listofDescript;
        }
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView= inflater.inflate(R.layout.imagetextlist, null, true);
            TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
            TextView txtDescript = (TextView) rowView.findViewById(R.id.txt2);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
            txtTitle.setText(listOfTexts.get(position));
            txtDescript.setText(listofDesc.get(position));

            imageView.setImageResource(imageIDs.get(position));
            return rowView;
        }

        public void addImageWithText(String text, int imageID, String textDesc)
        {
            listOfTexts.add(text);
            listofDesc.add(textDesc);
            imageIDs.add(imageID);
        }
    }

    private ListView list;
    ArrayList<Movies> filmy;

    CustomList adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<String> texty = new ArrayList<String>();
        final ArrayList<String> textDesc = new ArrayList<String>();
        final ArrayList<Integer> imageIDs = new ArrayList<Integer>();

        try {
            ConnectionResponse poslednaOdpoved = CRUDhandler.GET(); //takto volam GET
             filmy = poslednaOdpoved.getFilmy(); //takto ziskam zoznam filmou (pozri triedu ConnectionResponse)
            for(Movies m : filmy)
            {
                texty.add(m.getTitle() + "   ");
                imageIDs.add(R.drawable.logo);
                textDesc.add(m.getDescription());

            }
        }
        catch(Exception e){e.printStackTrace();}

        adapter = new CustomList(MainActivity.this, texty, imageIDs,textDesc);

        //adapter.addImageWithText("fdfdfd", R.drawable.logo);

        list=(ListView)findViewById(R.id.listView);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this, "You Clicked at " +adapter.listOfTexts.get(position), Toast.LENGTH_SHORT).show();
                //texty.add("DSdsdsd");
                //imageIDs.add(R.drawable.logo);
                //adapter = new CustomList(MainActivity.this, texty, imageIDs);
                //list.setAdapter(adapter);
                Intent mainIntent = new Intent(MainActivity.this, ShowMovieInfoActivity.class);
                Bundle b = new Bundle();
                b.putInt("indexMovie", position); //Your id
                mainIntent.putExtras(b); //Put your id to your next Intent


                startActivity(mainIntent);
                finish();
            }
        });



    }
    @Override
    public void onBackPressed() {
        Intent mainIntent = new Intent(MainActivity.this, LoginActivity.class);

        startActivity(mainIntent);
        finish();
    }
}
