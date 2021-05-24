 package com.botarmy.tourwala;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register_form extends AppCompatActivity {

    //variables

    DatabaseHelper db;   //creating variable for Database helper

    Button calllogin;
    Button login_btn;
    Button signupbtn;
    ImageView image;
    TextView logoText, sloganText;
//    TextInputLayout fullname;
//    TextInputLayout username;
    TextInputLayout  e1;
//    TextInputLayout phone;
    TextInputLayout e2;
    TextInputLayout e3;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        db=new DatabaseHelper(this);

        //Hooks of all xml elements in activity_register_form.xml

        calllogin = findViewById(R.id.already_have_account_login);
        image = findViewById(R.id.logo_image_transparent);
        logoText = findViewById(R.id.id_for_logo_name);
        sloganText = findViewById(R.id.slogan_name_for_logo_name);
//        fullname = findViewById(R.id.full_name);
//        username = findViewById(R.id.username);
        e1 =(TextInputLayout)findViewById(R.id.email);
        e2 =(TextInputLayout)findViewById(R.id.pass);
        e3 =(TextInputLayout)findViewById(R.id.cpass);
        signupbtn =(Button)findViewById(R.id.sign_up_btn);
//        login_btn =(Button)findViewById(R.id.already_have_account_login);


        //Save data in firebase on button click
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                rootNode = FirebaseDatabase.getInstance();
//                reference = rootNode.getReference("Users");

//                if(!validateName() | !validateEmail() | !validatePassword() | !validatePhoneNo() | !validateUsername()){
//                    return;
//                }
                    //Get all the values in string
//                String name = fullname.getEditText().getText().toString();
//                String usernames = username.getEditText().getText().toString();
                    String s1 = e1.getEditText().getText().toString();
//                String phoneNo = phone.getEditText().getText().toString();
//                Integer phoneno=Integer.parseInt(phoneNo);
                    String s2 = e2.getEditText().getText().toString();
                    String s3 = e3.getEditText().getText().toString();

                    if (s1.equals("") || s2.equals("") || s3.equals("")) {
                        Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                    } else {
                        if (s2.equals(s3)) {
                            Boolean chkemail = db.chkemail(s1);
                            if (chkemail == true) {
                                Boolean insert = db.insert(s1, s2);
                                if (insert == true) {
                                    Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                                    Intent movetologin = new Intent(register_form.this,login_form.class);
                                    startActivity(movetologin);
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Email Already Exists", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        Toast.makeText(getApplicationContext(), "Password do not  match", Toast.LENGTH_SHORT).show();
                    }
                    }

//                UserHelperClass helperClass = new UserHelperClass(name, usernames, emails, phoneNo, passwords);
//
//                reference.child(usernames).setValue(helperClass);
                }

        }); // Register button method end

//        calllogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(register_form.this, login_form.class);
//                Pair[] pairs = new Pair[7];
//                pairs[0] = new Pair<View, String>(image, "logo_image");
//                pairs[1] = new Pair<View, String>(logoText, "logo_text");
//                pairs[2] = new Pair<View, String>(sloganText, "logo_desc");
////                pairs[3] = new Pair<View,                                   String>(fullname, "fullname_tran");
////                pairs[4] = new Pair<View, String>(username, "username_tran");
//                pairs[3] = new Pair<View, String>(email, "email_tran");
////                pairs[6] = new Pair<View, String>(phone, "phone_tran");
//                pairs[4] = new Pair<View, String>(password, "password_tran");
//                pairs[5] = new Pair<View, String>(cnfpassword, "cnfpassword_tran");
//                pairs[6] = new Pair<View, String>(login_btn, "login_signup_tran");
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(register_form.this, pairs);
//                    startActivity(intent, options.toBundle());
//                }
//
//            }
//        }); // Transition button method end

    } // On create method end

//    private Boolean validateName() {
//        String val = fullname.getEditText().getText().toString();
//
//        if (val.isEmpty()) {
//            fullname.setError("Field cannot be empty");
//            return false;
//        } else {
//            fullname.setError(null);
//            fullname.setErrorEnabled(false);
//            return true;
//        }
//    }

//   private Boolean validateUsername() {
////        String val = username.getEditText().getText().toString();
////        String noWhiteSpace = "\\A\\w{4,20}\\z";  // "\\A\\w{4,20}\\z";     //"(?=\\S+$)"
////
////
////        if (val.isEmpty()) {
////            username.setError("Field cannot be empty");
////            return false;
////        } else if (val.length() >= 15) {
////            username.setError("Username too long");
////            return false;
////        }else if(val.length() <= 3){
////            username.setError("Username too small");
////            return false;
////        }
////        else if (!val.matches(noWhiteSpace)) {
////            username.setError("Whitespaces are not allowed");
////            return false;
////        } else {
////            username.setError(null);
////            username.setErrorEnabled(false);
////            return true;
////        }
////    }
//
//    private Boolean validateEmail() {
//        String val = email.getEditText().getText().toString();
//        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
//
//        if (val.isEmpty()) {
//            email.setError("Field cannot be empty");
//            return false;
//        } else if (!val.matches(emailPattern)) {
//            email.setError("Invalid email address");
//            return false;
//
//        } else {
//            email.setError(null);
//            email.setErrorEnabled(false);
//            return true;
//        }
//    }
//
//    private Boolean validatePhoneNo() {
//        String val = phone.getEditText().getText().toString();
//
//
//        if (val.isEmpty()) {
//            phone.setError("Field cannot be empty");
//            return false;
//        } else {
//            phone.setError(null);
//            phone.setErrorEnabled(false);
//            return true;
//        }
//    }
//
//    private Boolean validatePassword() {
//        String val = password.getEditText().getText().toString();
//        String passwordVal = "^" +
//                //"(?=.*[0-9])" +       //at least 1 digit
//                //"(?=.*[a-z])" +       //at least 1 lower case letter
//                //"(?=.*[A-Z])" +       //at least 1 upper case letter
//                "(?=.*[a-zA-Z])" +      //any letter
//                "(?=.*[@#$%^&+=])" +    //at least 1 special characters
//                "(?=\\S+$)" +           //no white spaces
//                ".{4,}" +               ////at least 4 characters
//                "$";
//
//        if (val.isEmpty()) {
//            password.setError("Field cannot be empty");
//            return false;
//        } else if(!val.matches(passwordVal)){
//            password.setError("Password is too weak");
//            return false;
//        }
//        else {
//            password.setError(null);
//            password.setErrorEnabled(false);
//            return true;
//        }
//    }

}