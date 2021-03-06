package com.example.tiktalk.UI.Activities.Buyer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.tiktalk.R;

import spencerstudios.com.bungeelib.Bungee;

public class BuyerRatingActivity extends AppCompatActivity {

    Button ratingSubmitBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_rating);

        ratingSubmitBtn = findViewById(R.id.rating_submit_btn);
        ratingSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(BuyerRatingActivity.this, BuyerDashBoardActivity.class);
                startActivity(in);
                Bungee.slideDown(BuyerRatingActivity.this);
                finish();
            }
        });
    }
}
