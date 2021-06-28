//package com.botarmy.tourwala;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.WindowManager;
//import android.widget.TextView;
//
//import com.google.android.material.textfield.TextInputEditText;
//import com.google.android.material.textfield.TextInputLayout;
//
//public class UserProfile extends AppCompatActivity {
//
////    TextInputLayout fullName,email,phoneNo,password;
////    TextView fullNameLabel,usernameLabel;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_user_profile);
//        getSupportActionBar().hide();
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//
//        //Hooks
//
////        fullName = findViewById(R.id.full_name_Text_input_edit_text);
////        email = findViewById(R.id.Email_Text_input_edit_text);
////        phoneNo = findViewById(R.id.Phone_number_Text_input_edit_text);
////        password = findViewById(R.id.password_Text_input_edit_text);
////        fullNameLabel = findViewById(R.id.full_name);
////        usernameLabel = findViewById(R.id.username);
//
//        //showAllData
////        showAllUserData();
//
//    }
//
////    private void showAllUserData() {
////        Intent intent= getIntent();
////        String user_username = intent.getStringExtra("usernames");
////        String user_name = intent.getStringExtra("name");
////        String user_emails = intent.getStringExtra("emails");
////        String user_phoneNo = intent.getStringExtra("phoneNo");
////        String user_passwords = intent.getStringExtra("passwords");
////
////        fullNameLabel.setText(user_name);
////        usernameLabel.setText(user_username);
////        fullName.getEditText().setText(user_name);
////        email.getEditText().setText(user_emails);
////        phoneNo.getEditText().setText(user_phoneNo);
////        password.getEditText().setText(user_passwords);
////
////    }
//}


package com.botarmy.tourwala;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.botarmy.tourwala.Utility.NetworkChangeListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class UserProfile extends AppCompatActivity {

    NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    private static final String SHARED_PREF_NAME = "loginpref";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
//    TextView username_top, username_profile;
    TextView email_profile, password_profile;
    Button btn_update;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);





        //Hooks
//        username_top = findViewById(R.id.username_top);
//        username_profile = findViewById(R.id.username_profile);
        email_profile = findViewById(R.id.email_profile);
        password_profile = findViewById(R.id.password_profile);


        btn_update = findViewById(R.id.btn_update_profile);




        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        String emailsp = sharedPreferences.getString(KEY_EMAIL,null);
        String password = sharedPreferences.getString(KEY_PASSWORD,null);

        if(emailsp != null || password != null){

            //so set the data on textview
            email_profile.setText("Email-Id - " + emailsp);
            password_profile.setText("Password - " + password);
        }

        //so call the button for logout Session




        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this,update_profile.class);
                startActivity(intent);
            }
        });









        //showAllData
//        showAllUserData();
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

//    private void showAllUserData() {
//        Intent intent = getIntent();
//
//        String user_username = "";
//        String user_name = "";
//        String user_emails = "";
//        String user_phoneNo = "";
//        String user_passwords = "";
//
//        if (intent != null) {
//            user_username = intent.getStringExtra("usernames");
//            user_name = intent.getStringExtra("name");
//            user_emails = intent.getStringExtra("emails");
//            user_phoneNo = intent.getStringExtra("phoneNo");
//            user_passwords = intent.getStringExtra("passwords");
//        }
//
//        if (fullNameLabel != null)
//            fullNameLabel.setText(user_name);
//
//        if (usernameLabel != null)
//            usernameLabel.setText(user_username);
//
//        if (fullName != null)
//            fullName.setText(user_name);
//
//        if (email != null)
//            email.setText(user_emails);
//
//        if (phoneNo != null)
//            phoneNo.setText(user_phoneNo);
//
//        if (password != null)
//            password.setText(user_passwords);
//    }
}