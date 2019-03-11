package com.example.tiktalk.UI.Activities.Seller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tiktalk.R;
import com.example.tiktalk.UI.Activities.NumberVerificationActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import spencerstudios.com.bungeelib.Bungee;

public class SellerSignupActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore firestore;

    EditText nameEditTxt;
    EditText emailEditTxt;
    EditText passwordEditTxt ;
    Button signUpBtn, cancelBtn;
    TextView signinTextview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_signup);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        nameEditTxt = findViewById(R.id.signup_name_seller);
        emailEditTxt = findViewById(R.id.signup_email_seller);
        passwordEditTxt = findViewById(R.id.signup_password_seller);
        signUpBtn = findViewById(R.id.signup_btn_seller);
        cancelBtn = findViewById(R.id.sign_cancel_btn);
        signinTextview = findViewById(R.id.signup_textView);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(SellerSignupActivity.this, NumberVerificationActivity.class);
                startActivity(in);
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        signinTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                Bungee.slideUp(SellerSignupActivity.this);
            }
        });
    }
}
