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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.botarmy.tourwala.Utility.NetworkChangeListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class about extends AppCompatActivity {

    NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    Button versionbutton,contactbutton,instagrambutton,youtubebutton,feedbackbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        versionbutton = (Button) findViewById(R.id.versionbutton);
        contactbutton = (Button) findViewById(R.id.contactbutton);
        instagrambutton = (Button) findViewById(R.id.instagrambutton);
        youtubebutton = (Button) findViewById(R.id.youtubebutton);
        feedbackbutton = (Button) findViewById(R.id.feedbackbutton);

        versionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(about.this, "Tourwala Version 0.1", Toast.LENGTH_SHORT).show();
            }
        });

        contactbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(about.this,email.class);
                startActivity(intent);
            }
        });

        instagrambutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.instagram.com/bot_army_team");
            }
        });

        youtubebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.youtube.com/channel/UC2uE5BsGG6Xsk2szQ8bOCrA");
            }
        });

        feedbackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://forms.gle/QvfX3pU1rmvNdPoP7");
            }
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