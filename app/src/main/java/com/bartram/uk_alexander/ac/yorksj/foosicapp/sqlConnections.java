package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class sqlConnections extends AsyncTask<Void, Void, String> {

    public MainActivity parent;

    public sqlConnections(){
    }



    @Override
    protected String doInBackground(Void... voids) {


            boolean isConnected = false;
            URL url;
            URLConnection conn = null;
            String result = "";

            try {
                url = new URL("https://cs2s.yorkdc.net/~alexander.bartram/test.php");
                conn = url.openConnection();
                InputStream is = conn.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                result = br.readLine();
                Log.d("HTTP-GET", result);

                return result;
            }catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (conn != null){
                    //         conn.disconnect();
                }
            }

        //    return  isConnected;



        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (!s.equals("Connected")){
            parent.UpdateTextError("Database Error");

        }
    }
}
