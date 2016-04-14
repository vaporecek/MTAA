package com.example.mathew.movies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.backendless.Backendless;
import com.example.mathew.movies.DataClasses.Movies;
import com.example.mathew.movies.RESTbackend.CRUDhandler;
import com.example.mathew.movies.RESTbackend.ConnectionResponse;
import com.example.mathew.movies.RESTbackend.RestCommunicator;
import com.example.mathew.movies.RESTbackend.RestConfig;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
//import com.backendless.async.callback.AsyncCallback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Spustam appku!!!!!!!!!!!!!!!!");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //*KAROL: Na backend sa dostanes jednoducho pomocou volania statickych metod PUT, POST, GET, DELETE triedy CRUDhandler

            try {

                ConnectionResponse poslednaOdpoved = CRUDhandler.GET(); //takto volam GET
                ArrayList<Movies> filmy = poslednaOdpoved.getFilmy(); //takto ziskam zoznam filmou (pozri triedu ConnectionResponse)
                Movies film = filmy.get(1);
                CRUDhandler.PUT(film); //PUT
                CRUDhandler.POST(film); //PUT
                CRUDhandler.DELETE(film); //PUT

            }catch(Exception e){e.printStackTrace();}



    }
}
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);

/*
        public void login_click(View v) {
            EditText login = (EditText) findViewById(R.id.username);
            EditText passwd = (EditText) findViewById(R.id.password);

            CharSequence l = login.getText().toString();
            CharSequence p = passwd.getText().toString();

            LoadingCallback<BackendlessUser> loginCallback = createLoginCallback();

            loginCallback.showLoading();
            loginUser(l.toString(), p.toString(), loginCallback);
        }
    */

      // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
     /*   fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }); */

/*
    public void loginUser( String email, String password, AsyncCallback<BackendlessUser> loginCallback )
    {
        Backendless.UserService.login( email, password, loginCallback );
    }

    public LoadingCallback<BackendlessUser> createLoginCallback()
    {
        return new LoadingCallback<BackendlessUser>( this, "string" );
      {
            @Override
            public void handleResponse( BackendlessUser loggedInUser )
            {
                super.handleResponse( loggedInUser );
                Toast.makeText(MainActivity.this, String.format("string"), loggedInUser.getObjectId()), Toast.LENGTH_LONG).show();
              //  Intent intent = new Intent(MainActivity.this, Main_page.class);
            //    startActivity(intent);
             //   finish();
            }
        };
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}*/
