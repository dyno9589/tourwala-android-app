package com.botarmy.tourwala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import com.botarmy.tourwala.Utility.NetworkChangeListener;

public class trainbook extends AppCompatActivity {

    NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    Button buttonbook,buttonrailstations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainbook);

        getSupportActionBar().hide();
        setTitle("Bus");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        buttonbook= findViewById(R.id.buttonbookrail);
        buttonrailstations= findViewById(R.id.buttonrailstations);

        buttonbook.setOnClickListener(v -> {
            gotoUrl("https://www.makemytrip.com/railways");

//                Intent i = getPackageManager().getLaunchIntentForPackage("com.android.chrome");
        });

        buttonrailstations.setOnClickListener(v -> {
            Intent movetobus = new Intent(trainbook.this,train.class);
            startActivity(movetobus);
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