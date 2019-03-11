package com.example.tiktalk.UI.Activities.Seller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tiktalk.R;

import spencerstudios.com.bungeelib.Bungee;

public class SellerLoginActivity extends AppCompatActivity {

    Button loginBtn, cancelBtn;
    TextView signupBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_login);

        loginBtn = findViewById(R.id.login_btn);
        cancelBtn = findViewById(R.id.cancel_btn);
        signupBtn = findViewById(R.id.signup_textView);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(SellerLoginActivity.this, SellerDashboardActivity.class);
                startActivity(in);
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(SellerLoginActivity.this, SellerSignupActivity.class);
                startActivity(in);
                Bungee.slideUp(SellerLoginActivity.this);
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
