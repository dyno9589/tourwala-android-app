//package com.botarmy.tourwala;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.ActivityOptions;
//import android.content.Intent;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Handler;
//import android.util.Pair;
//import android.view.View;
//import android.view.WindowManager;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//public class splash_screen extends AppCompatActivity {
//
//    private static int SPLASH_SCREEN = 3000;
//    //variables
//    Animation topAnim, bottomAnim;
//    ImageView image;
//    TextView logo,slogan;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setContentView(R.layout.activity_splash_screen);
//        //Animations
//        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
//        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
//
//        //Hooks
//        image = findViewById(R.id.imageView3);
//        logo = findViewById(R.id.textView_logo_name);
//        slogan = findViewById(R.id.tagline_for_tourwala_text);
//
//        image.setAnimation(topAnim);
//        logo.setAnimation(bottomAnim);
//        slogan.setAnimation(bottomAnim);
//
//        new Handler().postDelayed(new Runnable(){
//            @Override
//            public void run() {
//                Intent intent = new Intent(splash_screen.this, MainActivity.class);
//                Pair[] pairs = new Pair[2];
//                pairs[0] = new Pair<View, String>(image,"logo_image");
//                pairs[1] = new Pair<View, String>(logo,"logo_text");
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    ActivityOptions options =ActivityOptions.makeSceneTransitionAnimation(splash_screen.this,pairs);
//                    startActivity(intent,options.toBundle());
//                    onBackPressed();
//                }
//
//            }
//        },SPLASH_SCREEN);
//
//
//    }
//
//    }
