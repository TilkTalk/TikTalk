package com.example.tiktalk.UI.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.tiktalk.R;
import com.example.tiktalk.UI.Activities.Buyer.BuyerCallActivity;
import com.example.tiktalk.UI.Activities.Buyer.BuyerChatActivity;
import com.example.tiktalk.UI.Activities.Seller.SellerProfileActivity;

public class AllSellerActivity extends AppCompatActivity {

    LinearLayout sellerProfileBtn;
    Button cancelBtn;
    ImageView callBtn, chatBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_sellers);

        sellerProfileBtn = findViewById(R.id.sellerProfile_btn);
        cancelBtn = findViewById(R.id.cancel_btn);
        callBtn = findViewById(R.id.call_btn);
        chatBtn = findViewById(R.id.chat_btn);

        sellerProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(AllSellerActivity.this, SellerProfileActivity.class);
                startActivity(in);
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(AllSellerActivity.this, BuyerCallActivity.class);
                startActivity(in);
            }
        });

        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(AllSellerActivity.this, BuyerChatActivity.class);
                startActivity(in);
            }
        });
    }
}
