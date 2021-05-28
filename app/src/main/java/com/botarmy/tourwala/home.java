package com.botarmy.tourwala;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.botarmy.tourwala.Utility.NetworkChangeListener;
import com.google.android.material.navigation.NavigationView;

public class home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //varibles
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    DatabaseHelper db=null;
    private TextView textViewWelcome;
    boolean doubleBackToExitPressedOncehome = false;
    private String emailAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();
        setTitle(null);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);


        Intent intent = getIntent();
        if (intent != null) {
            emailAddress = intent.getStringExtra("Email");
        }

        /*----------------------------Hooks-----------------------------*/

        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);

        textViewWelcome=(TextView)findViewById(R.id.welcome);

        /*----------------------------Tool Bar-----------------------------*/

        setSupportActionBar(toolbar);

        /*----------------------------Navigation Drawer Menu-----------------------------*/

//hide or show items
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_logout).setVisible(false);
        menu.findItem(R.id.nav_profile).setVisible(false);

        navigationView.bringToFront();



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);

/*-------------------------------------------------------Here I am getting issue----------------------------------------------------------------------------------------------------*/
        db = new DatabaseHelper(this);
        String userName = db.chkwelcome(emailAddress);
        if (!userName.isEmpty()) {
            textViewWelcome.setText("Welcome " + userName + " ..");
        }
        onBackPressedhome();
    }

    public void onBackPressedhome() {
        if (doubleBackToExitPressedOncehome) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOncehome = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOncehome = false;
            }
        }, 2000);
    }


    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_home:
            break;

            case R.id.nav_bus:
            Intent intent = new Intent(home.this,Bus.class);
            startActivity(intent);
            break;

            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;

                case R.id.nav_login:
                    Intent intent_login = new Intent(home.this,login_form.class);
                    startActivity(intent_login);
                break;


        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
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


}

