package com.example.tiktalk.UI.Activities.Seller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.tiktalk.R;

public class SellerCallActivity extends AppCompatActivity {

    ImageView declineCallBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_call);

        declineCallBtn = findViewById(R.id.decline_call);

        declineCallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(SellerCallActivity.this, SellerCallDetailsActivity.class);
                startActivity(in);

            }
        });
    }
}
