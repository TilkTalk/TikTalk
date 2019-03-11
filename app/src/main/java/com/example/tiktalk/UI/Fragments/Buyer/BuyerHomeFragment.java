package com.example.tiktalk.UI.Fragments.Buyer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tiktalk.BaseClasses.BaseFragment;
import com.example.tiktalk.R;
import com.example.tiktalk.UI.Activities.AllSellerActivity;
import com.example.tiktalk.UI.Activities.Buyer.BuyerCallActivity;
import com.example.tiktalk.UI.Activities.Buyer.BuyerChatActivity;
import com.example.tiktalk.UI.Activities.Seller.SellerProfileActivity;

import spencerstudios.com.bungeelib.Bungee;

public class BuyerHomeFragment extends BaseFragment {

    CardView myCardView;
    TextView seeAllBtn;
    Button chatBtn, callBtn;

    @Override
    public void initializeComponents(View rootView) {

        myCardView = rootView.findViewById(R.id.myCardView);
        seeAllBtn = rootView.findViewById(R.id.seeAll_btn);
        chatBtn = rootView.findViewById(R.id.chat_btn);
        callBtn = rootView.findViewById(R.id.call_btn);
    }

    @Override
    public void setupListeners(View rootView) {

        myCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getContext(), SellerProfileActivity.class);
                startActivity(in);
                Bungee.slideLeft(getContext());
            }
        });

        seeAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getContext(), AllSellerActivity.class);
                startActivity(in);
                Bungee.slideLeft(getContext());
            }
        });

        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getContext(), BuyerChatActivity.class);
                startActivity(in);
                Bungee.slideLeft(getContext());
            }
        });

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getContext(), BuyerCallActivity.class);
                startActivity(in);
                Bungee.slideLeft(getContext());
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_buyer_home, null);
        return view;
    }

}

