package com.botarmy.tourwala;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.botarmy.tourwala.Utility.NetworkChangeListener;

public class busbook extends AppCompatActivity {

    NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    Button buttonbook,buttonbusstops;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busbook);
        getSupportActionBar().hide();
        setTitle("Bus");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        buttonbook= findViewById(R.id.buttonbook);
        buttonbusstops= findViewById(R.id.buttonbusstops);

        buttonbook.setOnClickListener(v -> {
            gotoUrl("https://www.makemytrip.com/bus-tickets");

//                Intent i = getPackageManager().getLaunchIntentForPackage("com.android.chrome");
        });

        buttonbusstops.setOnClickListener(v -> {
            Intent movetobus = new Intent(busbook.this,Bus.class);
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