package com.botarmy.tourwala;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

//    private static int SPLASH_SCREEN = 3000;
    //variables
//    Animation topAnim, bottomAnim;
    ImageView image;
    TextView logo, slogan;
    Button login, signup, skip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

//        //Animations
//        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
//        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //Hooks
        image = findViewById(R.id.imageView3);

        logo = findViewById(R.id.textView_logo_name);
        slogan = findViewById(R.id.tagline_for_tourwala_text);

        login = findViewById(R.id.login_btn_splash_screen);
        signup = findViewById(R.id.sign_up_btn_splash_screen);
        skip = findViewById(R.id.skip_btn_splash_screen);

//        image.setAnimation(topAnim);
//
//        logo.setAnimation(bottomAnim);
//        slogan.setAnimation(bottomAnim);
//        login.setAnimation(bottomAnim);
//        signup.setAnimation(bottomAnim);


//        new Handler().postDelayed(new Runnable(){
//            @Override
//            public void run() {
//                Intent intenth = getIntent();
//                Pair[] pairs = new Pair[2];
//                pairs[0] = new Pair<View, String>(image,"logo_image");
//                pairs[1] = new Pair<View, String>(logo,"logo_text");
//
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    ActivityOptions options =ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
//                    startActivity(intenth,options.toBundle());
//
//                }
//
//            }
//        },SPLASH_SCREEN);
//        onBackPressed();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, login_form.class);
                startActivity(intent);
                finish();


            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, register_form.class);
                startActivity(intent);
                finish();


            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!isConnected(MainActivity.this)) {
//                    showCustomDialog();
//                }
                Intent intent = new Intent(MainActivity.this, home.class);
                startActivity(intent);
                finish();


            }
        });


//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(MainActivity.this,login_form.class);
//                startActivity(intent);
//                finish();
//            }
//        });
    }

//    boolean doubleBackToExitPressedOnce = false;
//
//    @Override
//    public void onBackPressed() {
//        if (doubleBackToExitPressedOnce) {
//            super.onBackPressed();
//            return;
//        }
//
//        this.doubleBackToExitPressedOnce = true;
//        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
//
//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                doubleBackToExitPressedOnce = false;
//            }
//        }, 2000);
//    }




    //show custom dialog if the internet is not availabe;
//    private void showCustomDialog() {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//        builder.setMessage("PLease connect to the internet to proceed further")
//                .setCancelable(false)
//                .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
//                    }
//                })
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                        finish();
//                    }
//                });
//
//    }
//
//    //Check internet connection
//    private boolean isConnected(MainActivity main) {
//        ConnectivityManager connectivityManager = (ConnectivityManager) main.getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//
//        if ((wifiConn != null && wifiConn.isConnected()) || (mobileConn != null && mobileConn.isConnected())) {
//            return true;
//        } else {
//            return false;
//        }
//
//    }
}