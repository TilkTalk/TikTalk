package com.example.tiktalk.UI.Activities.Buyer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.tiktalk.R;
import com.ncorti.slidetoact.SlideToActView;

import org.jetbrains.annotations.NotNull;

public class BuyerCallActivity extends AppCompatActivity {

    SlideToActView sta;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_call);

        sta = findViewById(R.id.example);

        sta.setOnSlideCompleteListener(new SlideToActView.OnSlideCompleteListener() {
            @Override
            public void onSlideComplete(@NotNull SlideToActView slideToActView) {
                Intent in = new Intent(BuyerCallActivity.this, BuyerRatingActivity.class);
                startActivity(in);
            }
        });
    }
}
