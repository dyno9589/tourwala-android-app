package com.botarmy.tourwala;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.botarmy.tourwala.Utility.NetworkChangeListener;
import com.google.android.material.navigation.NavigationView;

public class home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //variables
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    private static final String SHARED_PREF_NAME = "loginpref";
    String emailAddress;
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
//    DatabaseHelper db = null;
//    TextView textViewWelcome;
    boolean doubleBackToExitPressedOncehome = false;
    TextView email_profile, password_profile;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(null);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);




        Intent intent = getIntent();
        if (intent != null) {
            emailAddress = intent.getStringExtra("Email");
        }

        /*----------------------------Hooks-----------------------------*/

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

//        textViewWelcome = (TextView) findViewById(R.id.welcome);


        email_profile = findViewById(R.id.email_profile);
        password_profile = findViewById(R.id.password_profile);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);



//        textView =(TextView) findViewById(R.id.textView);

        /*----------------------------Tool Bar-----------------------------*/
//        toolbar.setVisibility(View.VISIBLE);
        setSupportActionBar(toolbar);

        /*----------------------------Navigation Drawer Menu-----------------------------*/

//hide or show items
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_logout).setVisible(true);
//        menu.findItem(R.id.nav_profile).setVisible(false);

        navigationView.bringToFront();


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);

//        db = new DatabaseHelper(this);
//        String userName = db.chkwelcome(emailAddress);
//        if (!userName.isEmpty()) {
//            textViewWelcome.setText("Welcome " + userName + " ..");
//        }


        onBackPressedhome();
    }

    public void onBackPressedhome() {
        if (doubleBackToExitPressedOncehome) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOncehome = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(() -> doubleBackToExitPressedOncehome = false, 2000);
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_home:
                break;

            case R.id.nav_bus:
                Intent intent = new Intent(home.this, busbook.class);
                startActivity(intent);
                break;

            case R.id.nav_share:
                Toast.makeText(this, "Share the application", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_rate:
                Toast.makeText(this, "Rate the application", Toast.LENGTH_SHORT).show();
                gotoUrlrate("https://forms.gle/zfL8ugQmFDyy7Pkn8");
                break;

            case R.id.nav_login:
                Intent intent_login = new Intent(home.this, login_form.class);
                startActivity(intent_login);
                break;

            case R.id.nav_logout:
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(this, "Log out successfully", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_cycle:
                Intent intent_cycle = new Intent(home.this, Cycle.class);
                startActivity(intent_cycle);
                break;

            case R.id.nav_plane:
                Intent intent_plane = new Intent(home.this, planebook.class);
                startActivity(intent_plane);
                break;

            case R.id.nav_food:
                gotoUrl("https://www.zomato.com");
                break;

            case R.id.nav_hotel:
                Intent intent_hotel = new Intent(home.this, hotelbook.class);
                startActivity(intent_hotel);
                break;

            case R.id.nav_about:
                Intent intent_about = new Intent(home.this, about.class);
                startActivity(intent_about);
                break;

            case R.id.nav_profile:
                Intent intent_profile = new Intent(home.this, UserProfile.class);
                startActivity(intent_profile);
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void gotoUrlrate(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

//    private void gotoUrl1(String s) {
//        Uri uri = Uri.parse(s);
//        startActivity(new Intent(Intent.ACTION_VIEW,uri));
//    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener, filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }


}

