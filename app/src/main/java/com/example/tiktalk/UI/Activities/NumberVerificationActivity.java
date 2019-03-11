package com.example.tiktalk.UI.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.tiktalk.R;
import com.example.tiktalk.UI.Activities.Buyer.BuyerLoginActivity;
import com.example.tiktalk.UI.Activities.Seller.UploadPhotoActivity;

public class NumberVerificationActivity extends AppCompatActivity {

    Button cancelBtn, codeSendButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numberverification);

        codeSendButton = findViewById(R.id.codeResend_btn);
        cancelBtn = findViewById(R.id.number_cancel_btn);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        codeSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(NumberVerificationActivity.this, UploadPhotoActivity.class);
                startActivity(in);
            }
        });
    }
}
