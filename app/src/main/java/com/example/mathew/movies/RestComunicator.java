package com.example.mathew.movies;

/**
 * Created by Karol on 17.03.16.
 */
    import android.os.AsyncTask;
    import android.util.Log;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStream;
    import java.io.InputStreamReader;
    import java.net.HttpURLConnection;
    import java.net.URL;

    class RestCommunicator extends AsyncTask<String, Void, String>{

//        public AsyncResponse delegate = null;

        public RestCommunicator() {
        }



        protected String doInBackground(String... params) {
            System.out.println("!!!!!!!!!!!!! APP POKRACUJE");
//            RestParams reqParams = params[0];

            try {

                String urlString = "https://api.backendless.com/v1/data/Users";
                System.out.println("SKUSKA SPOJENIA!!!!");
                //handluje spojenie
                URL url = new URL(urlString); // sem treba dat url kde sa dopytujes (das v backendless rest console a hore ta url-ka)
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                connection.setUseCaches(false);
                connection.setDoInput(true);
                //connection.setRequestProperty("Connection", "Keep-Alive");
                //connection.setRequestProperty("Accept", "application/json");
                connection.setRequestProperty("application-id", BackendLessOption.APPLICATION_ID );
                connection.setRequestProperty("secret-key", BackendLessOption.ANDROID_SECRET_KEY);
                connection.setRequestProperty("version", BackendLessOption.VERSION);
                //connection.setRequestProperty("application-type", "application/json");

                connection.connect();

                int responseCode = connection.getResponseCode();
                Log.d("REST", "Response: " + responseCode);

                String responseBody = readStream(connection.getInputStream());

                if( responseCode == 200 )
                {
                    System.out.println(responseBody);
                    return new String(responseBody);
                }


            } catch (Exception e)
            {
                Log.e("REST", e.getClass() + ": " + e.getMessage());
            }



            return null;
        }

        @Override

        //ked sa skonci POST tak tu je vystup
        protected void onPostExecute(String answer){
  //          delegate.processFinish(answer);
        }

        //parser na imputstream, vrati jason ako string
        public String readStream(InputStream stream) throws IOException
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder sb = new StringBuilder();
            String line;

            while((line = reader.readLine()) != null)
            {
                sb.append(line);
            }

            return sb.toString();
        }

    }

