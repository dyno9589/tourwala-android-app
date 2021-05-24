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
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class UserProfile extends AppCompatActivity {

    TextInputEditText fullName, email, phoneNo, password;
    TextView fullNameLabel, usernameLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Hooks
        fullName = findViewById(R.id.full_name_Text_input_edit_text);
        email = findViewById(R.id.Email_Text_input_edit_text);
        phoneNo = findViewById(R.id.Phone_number_Text_input_edit_text);
        password = findViewById(R.id.password_Text_input_edit_text);

        fullNameLabel = findViewById(R.id.full_name);
        usernameLabel = findViewById(R.id.username);

        //showAllData
        showAllUserData();
    }

    private void showAllUserData() {
        Intent intent = getIntent();

        String user_username = "";
        String user_name = "";
        String user_emails = "";
        String user_phoneNo = "";
        String user_passwords = "";

        if (intent != null) {
            user_username = intent.getStringExtra("usernames");
            user_name = intent.getStringExtra("name");
            user_emails = intent.getStringExtra("emails");
            user_phoneNo = intent.getStringExtra("phoneNo");
            user_passwords = intent.getStringExtra("passwords");
        }

        if (fullNameLabel != null)
            fullNameLabel.setText(user_name);

        if (usernameLabel != null)
            usernameLabel.setText(user_username);

        if (fullName != null)
            fullName.setText(user_name);

        if (email != null)
            email.setText(user_emails);

        if (phoneNo != null)
            phoneNo.setText(user_phoneNo);

        if (password != null)
            password.setText(user_passwords);
    }
}