package com.example.tiktalk.UI.Fragments.Seller;

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
import com.example.tiktalk.UI.Activities.Seller.UploadPhotoActivity;
import com.example.tiktalk.UI.Activities.WithdrawActivity;

import spencerstudios.com.bungeelib.Bungee;

public class SellerHomeFragment extends BaseFragment {

    TextView withdrawBtn;

    @Override
    public void initializeComponents(View rootView) {

        withdrawBtn = rootView.findViewById(R.id.withdraw_btn);

    }

    @Override
    public void setupListeners(View rootView) {

        withdrawBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getContext(), WithdrawActivity.class);
                startActivity(in);
                Bungee.slideLeft(getContext());
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_seller_home, null);
        return view;
    }

}

