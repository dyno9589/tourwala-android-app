package com.botarmy.tourwala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.botarmy.tourwala.Utility.NetworkChangeListener;

public class planebook extends AppCompatActivity {

    NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    Button buttonBookPlane,buttonAirport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planebook);
//        getActionBar().hide();
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        buttonBookPlane = (Button) findViewById(R.id.buttonBookPlane);
        buttonAirport = (Button) findViewById(R.id.buttonAirports);

        buttonBookPlane.setOnClickListener(v -> {
            gotoUrl("https://www.makemytrip.com/flights");

        });

        buttonAirport.setOnClickListener(v -> {
            Intent movetoplane = new Intent(planebook.this,plane.class);
            startActivity(movetoplane);
        });
    }

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener,filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}