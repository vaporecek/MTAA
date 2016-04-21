package com.example.mathew.movies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ImgViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.img_view);
    }

    @Override
    public void onBackPressed() {
        Intent mainIntent = new Intent(ImgViewActivity.this, ShowMovieInfoActivity.class);
        startActivity(mainIntent);
        finish();
    }
}
