package com.botarmy.tourwala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class busbook extends AppCompatActivity {

    Button buttonbook,buttonbusstops;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busbook);
        getActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        buttonbook=(Button)findViewById(R.id.buttonbook);
        buttonbusstops=(Button)findViewById(R.id.buttonbusstops);

        buttonbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getPackageManager().getLaunchIntentForPackage("com.android.chrome");
            }
        });

        buttonbusstops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent movetobus = new Intent(busbook.this,Bus.class);
                startActivity(movetobus);
            }
        });

    }
}