package com.example.mathew.movies;

/**
 * Created by Mathew on 16.03.16.
 */

import android.app.ProgressDialog;
import android.content.Context;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;


public class LoadingCallback<T> implements AsyncCallback<T>
{
private Context context;
private ProgressDialog progressDialog;


    public LoadingCallback( Context context, String loadingMessage )
    {
        this.context = context;
        progressDialog = new ProgressDialog( context );
        progressDialog.setMessage( loadingMessage );
    }
    public LoadingCallback( Context context, String progressDialogMessage, boolean showProgressDialog )
    {
        this( context, progressDialogMessage );
        progressDialog.show();
    }

        @Override
        public void handleResponse( T response )
        {
            progressDialog.dismiss();
        }

        @Override
        public void handleFault( BackendlessFault fault )
        {
            progressDialog.dismiss();
            DialogHelper.createErrorDialog( context, "BackendlessFault", fault.getMessage() ).show();
        }

        /**
         * Shows ProgressDialog.
         */
public void showLoading()
        {
            progressDialog.show();
        }

        /**
         * Hides ProgressDialog.
         */
public void hideLoading()
        {
            progressDialog.dismiss();
        }
}
