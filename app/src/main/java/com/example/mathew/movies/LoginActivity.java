package com.example.mathew.movies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.mathew.movies.DataClasses.Movies;
import com.example.mathew.movies.RESTbackend.CRUDhandler;
import com.example.mathew.movies.RESTbackend.ConnectionResponse;

import java.util.ArrayList;
//import com.backendless.async.callback.AsyncCallback;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private static final String[] SAMPLE_CREDENTIALS = new String[]{
            "matus@gmail.com:123", "matus@stuba.sk:123"
    };

    public class AsyncLoginTask extends AsyncTask<Void, Void, Integer> {

        public final Integer resultOK = 0;
        public final Integer resultNoInternetConnection = 1;
        public final Integer resultBadCredentials = 2;

        private final String m_email;
        private final String m_password;

        AsyncLoginTask(String email, String password) {
            m_email = email;
            m_password = password;
        }

        @Override
        protected Integer doInBackground(Void... params) {
            try{
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (String crd : SAMPLE_CREDENTIALS) {
                String[] parts = crd.split(":");
                if (parts[0].equals(m_email)) {
                    // Account exists, return true if the password matches.
                    return parts[1].equals(m_password) ? resultOK : resultBadCredentials;
                }
            }

            return resultBadCredentials;
        }

        @Override
        protected void onPostExecute(final Integer result) {
            loginTask = null;


            if (result == resultOK) {
                Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);

                startActivity(mainIntent);
                finish();
            }
            else if(result == resultNoInternetConnection){
                showLoginErrorDialog("Login Failed!", "No internet connection! Login cannot continue!");
            }
            else if(result == resultBadCredentials){
                showLoginErrorDialog("Login Failed!", "Invalid user e-mail or password!");
                et_Password.requestFocus();
            }

            pb_Spinner.setVisibility(View.GONE);
        }

        @Override
        protected void onCancelled() {
            loginTask = null;
        }
    }

    private AsyncLoginTask loginTask = null;

    //-----------------------------------
    //
    // Activity Widgets
    //
    //-----------------------------------

    private EditText et_Email;
    private EditText et_Password;
    private Button btn_Login;
    private ProgressBar pb_Spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Spustam appku!!!!!!!!!!!!!!!!");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        btn_Login = (Button)findViewById(R.id.btn_Login);
        et_Email = (EditText)findViewById(R.id.et_Email);
        et_Password = (EditText)findViewById(R.id.et_Password);
        pb_Spinner = (ProgressBar)findViewById(R.id.pb_Spinner);

        btn_Login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                pb_Spinner.setVisibility(View.VISIBLE);
                loginTask = new AsyncLoginTask(et_Email.getText().toString(), et_Password.getText().toString());
                loginTask.execute();

            }
        });

        //*KAROL: Na backend sa dostanes jednoducho pomocou volania statickych metod PUT, POST, GET, DELETE triedy CRUDhandler

            /*try {

                ConnectionResponse poslednaOdpoved = CRUDhandler.GET(); //takto volam GET
                ArrayList<Movies> filmy = poslednaOdpoved.getFilmy(); //takto ziskam zoznam filmou (pozri triedu ConnectionResponse)
                Movies film = filmy.get(1);
                CRUDhandler.PUT(film); //PUT
                CRUDhandler.POST(film); //PUT
                CRUDhandler.DELETE(film); //PUT

            }catch(Exception e){e.printStackTrace();}*/
    }

    private void showLoginErrorDialog(String title, String message) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        alertDialog.setTitle(title);
        alertDialog.setMessage(message);

        // Setting Icon to Dialog
        //alertDialog.setIcon(R.drawable.delete);

        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                // Write your code here to invoke YES event
                //Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();
            }
        });

        // Showing Alert Message
        AlertDialog alert = alertDialog.create();
        alert.show();
    }
}
