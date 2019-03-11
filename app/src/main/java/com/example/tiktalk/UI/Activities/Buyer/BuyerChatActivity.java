package com.example.tiktalk.UI.Activities.Buyer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

import com.example.tiktalk.R;
import com.example.tiktalk.UI.Activities.Seller.SellerProfileActivity;

import spencerstudios.com.bungeelib.Bungee;

public class BuyerChatActivity extends AppCompatActivity {

    Button chatCancelBtn, callBtn;
    CardView receiver1, receiver2, sender1, sender2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_chat);

        chatCancelBtn = findViewById(R.id.chat_cancel_btn);
        callBtn = findViewById(R.id.call_btn);
        receiver1 = findViewById(R.id.chat_receiverCardView1);
        receiver2 = findViewById(R.id.chat_receiverCardView2);
        sender1 = findViewById(R.id.chat_senderCardView1);
        sender2 = findViewById(R.id.chat_senderCardView2);

        chatCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(BuyerChatActivity.this, BuyerCallActivity.class);
                startActivity(in);
            }
        });

        receiver1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(BuyerChatActivity.this, SellerProfileActivity.class);
                startActivity(in);
                Bungee.fade(BuyerChatActivity.this);
            }
        });

        receiver2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(BuyerChatActivity.this, SellerProfileActivity.class);
                startActivity(in);
                Bungee.fade(BuyerChatActivity.this);
            }
        });

        sender1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(BuyerChatActivity.this, BuyerProfileActivity.class);
                startActivity(in);
                Bungee.fade(BuyerChatActivity.this);
            }
        });

        sender2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(BuyerChatActivity.this, BuyerProfileActivity.class);
                startActivity(in);
                Bungee.fade(BuyerChatActivity.this);
            }
        });

    }
}
