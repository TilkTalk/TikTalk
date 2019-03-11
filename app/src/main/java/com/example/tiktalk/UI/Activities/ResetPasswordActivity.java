package com.example.tiktalk.UI.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.tiktalk.R;

public class ResetPasswordActivity extends AppCompatActivity {

    Button sendPasswordBtn;
    Button cancelBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);

        sendPasswordBtn = findViewById(R.id.sendpassword_btn);
        cancelBtn = findViewById(R.id.cancel_btn);

        sendPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(ResetPasswordActivity.this, NumberVerificationActivity.class);
                startActivity(in);
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
