package com.example.tiktalk.UI.Activities.Seller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.tiktalk.R;
import com.example.tiktalk.UI.Activities.Buyer.BuyerCallActivity;
import com.example.tiktalk.UI.Activities.Buyer.BuyerChatActivity;

import spencerstudios.com.bungeelib.Bungee;

public class SellerProfileActivity extends AppCompatActivity {

    Button CancelBtn;
    Button profileChatBtn;
    Button profileCallBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_profile);

        CancelBtn = findViewById(R.id.profile_cancel_btn);
        profileChatBtn = findViewById(R.id.profile_chat_btn);
        profileCallBtn = findViewById(R.id.profile_call_btn);

        CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        profileChatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(SellerProfileActivity.this, BuyerChatActivity.class);
                startActivity(in);
                Bungee.fade(SellerProfileActivity.this);
            }
        });

        profileCallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(SellerProfileActivity.this, BuyerCallActivity.class);
                startActivity(in);
                Bungee.fade(SellerProfileActivity.this);
            }
        });
    }
}
