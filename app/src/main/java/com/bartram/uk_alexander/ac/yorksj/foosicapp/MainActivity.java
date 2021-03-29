package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    private TextView txtError;
    private Button btnRetry;
    private ProgressBar progBar;


    public void UpdateTextError(String input) {
        btnRetry.setVisibility(View.VISIBLE);
        txtError.setText(input);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //Initialise variables
        txtError = (TextView) findViewById(R.id.txtConnectionError);
        btnRetry = (Button) findViewById(R.id.btnRetry);
        progBar = (ProgressBar) findViewById(R.id.progressBar);
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });


        reset();


    }

    public void reset() {
        btnRetry.setVisibility(View.INVISIBLE);
        txtError.setText("");
        if (checkConnection() && checkSQLConnection()) {
            //TODO loadData from SQL database before app launches
            txtError.setText("COMPLETED");
            checkSQLConnection();

        } else {

        }

    }

    public boolean checkConnection() {
        boolean isConnected = false;
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (!isConnected) {
            UpdateTextError("Check Internet Connection");
        }
        return isConnected;
    }


    public boolean checkSQLConnection() {
        boolean isConnected = false;

        sqlConnections test = (sqlConnections) new sqlConnections();
        test.parent = this;
        test.execute();
        if (txtError.getText().equals("")) {
            isConnected = true;
        }
        return isConnected;

    }
}
