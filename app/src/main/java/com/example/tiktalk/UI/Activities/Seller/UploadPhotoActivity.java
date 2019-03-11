package com.example.tiktalk.UI.Activities.Seller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.tiktalk.R;

import spencerstudios.com.bungeelib.Bungee;

public class UploadPhotoActivity extends AppCompatActivity {

    Button cancelBtn, doneBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_uploadphoto);

        cancelBtn = findViewById(R.id.cancel_btn);
        doneBtn = findViewById(R.id.done_btn);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(UploadPhotoActivity.this, SellerDashboardActivity.class);
                startActivity(in);
                Bungee.slideDown(UploadPhotoActivity.this);
            }
        });
    }
}
