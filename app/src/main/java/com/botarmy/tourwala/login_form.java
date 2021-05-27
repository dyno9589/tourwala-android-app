package com.botarmy.tourwala;

import androidx.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class  login_form extends AppCompatActivity {

    DatabaseHelper db;

    Button callSignUp;
    Button signup_btn;
    Button login_btn;
    ImageView image;
    TextView logoText,sloganText;
    TextInputLayout e1,e2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_form);


        db = new DatabaseHelper(this);
        //Hooks

        callSignUp = (Button) findViewById(R.id.new_user_signup_btn);
        image = findViewById(R.id.logo_image_transparent);
        logoText = findViewById(R.id.id_for_logo_name);
        sloganText = findViewById(R.id.slogan_name_for_logo_name);

        e1 =(TextInputLayout) findViewById(R.id.email_login_page);
        e2 =(TextInputLayout) findViewById(R.id.password_login_page);

//        signup_btn = findViewById(R.id.new_user_signup_btn);
        login_btn =(Button) findViewById(R.id.login_btn);


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = e1.getEditText().getText().toString();
                String password =e2.getEditText().getText().toString();

                Boolean chkemailpass = db.emailpassword(email,password);

                if(chkemailpass==true){
                    Toast.makeText(getApplicationContext(),"Successfully Login",Toast.LENGTH_SHORT).show();
                    Intent movetohome = new Intent(login_form.this,home.class);
                    startActivity(movetohome);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Incorrect Email or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        callSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(login_form.this, register_form.class);
//                Pair[] pairs = new Pair[6];
//                pairs[0] = new Pair<View, String>(image, "logo_image");
//                pairs[1] = new Pair<View, String>(logoText, "logo_text");
//                pairs[2] = new Pair<View, String>(sloganText, "logo_desc");
//                pairs[3] = new Pair<View, String>(username, "username_tran");
//                pairs[4] = new Pair<View, String>(password, "password_tran");
//                pairs[5] = new Pair<View, String>(signup_btn, "login_signup_tran");
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(login_form.this, pairs);
//                    startActivity(intent, options.toBundle());
//
//                }
//            }
//        });


    }


//    private Boolean validateUsername() {
//        String val = username.getEditText().getText().toString();
//
//        if (val.isEmpty()) {
//            username.setError("Field cannot be empty");
//            return false;
//        }  else {
//            username.setError(null);
//            username.setErrorEnabled(false);
//            return true;
//        }
//    }
//
//    private Boolean validatePassword() {
//        String val = password.getEditText().getText().toString();
//        if (val.isEmpty()) {
//            password.setError("Field cannot be empty");
//            return false;
//        }
//        else {
//            password.setError(null);
//            password.setErrorEnabled(false);
//            return true;
//        }
//    }



//    public void loginUser(View view){
        //Validate Login Info
//        if(!validateUsername() | !validatePassword()){
//            return;
//        }
//        else {
//            isUser();
//        }
//    }






//    private void isUser() {
//
//        String userEnteredUsername = username.getEditText().getText().toString().trim();
//        String userEnteredPassword = password.getEditText().getText().toString().trim();
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
//        Query checkUser = reference.orderByChild("usernames").equalTo(userEnteredUsername);
//        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                if(snapshot.exists()){
//
//                    username.setError(null);
//                    username.setErrorEnabled(false);
//
//                    String passwordsFromDB = snapshot.child(userEnteredUsername).child("passwords").getValue(String.class);
//
//                    if(passwordsFromDB.equals(userEnteredPassword)){
//
//                        username.setError(null);
//                        username.setErrorEnabled(false);
//
//                        String nameFromDB = snapshot.child(userEnteredUsername).child("name").getValue(String.class);
//                        String usernamesFromDB = snapshot.child(userEnteredUsername).child("usernames").getValue(String.class);
//                        String phoneNoFromDB = snapshot.child(userEnteredUsername).child("phoneNo").getValue(String.class);
//                        String emailsFromDB = snapshot.child(userEnteredUsername).child("emails").getValue(String.class);
//
//                        Intent intent = new Intent(getApplicationContext(),UserProfile.class);
//
//                        intent.putExtra("name",nameFromDB);
//                        intent.putExtra("usernames",usernamesFromDB);
//                        intent.putExtra("phoneNo",phoneNoFromDB);
//                        intent.putExtra("emails",emailsFromDB);
//                        intent.putExtra("passwords",passwordsFromDB);
//                        startActivity(intent);
//
//                    }
//                    else{
//                        password.setError("Wrong password");
//                        password.requestFocus();
//                    }
//
//                }
//                else{
//                    username.setError("No such User exist");
//                    username.requestFocus();
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error){
//
//            }
//        });
//
//    }
//
//
//    public void btn_login_form(View view) {
//        //Validate Login Info
//        if(!validateUsername() | !validatePassword()){
//            return;
//        }
//        else {
//            isUser();
//        }
//    }




}



