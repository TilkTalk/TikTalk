package com.example.tiktalk.UI.Fragments.Buyer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tiktalk.BaseClasses.BaseFragment;
import com.example.tiktalk.R;

public class BuyerMyWalletFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_buyer_mywallet, null);
        return view;
    }

    @Override
    public void initializeComponents(View rootView) {

    }

    @Override
    public void setupListeners(View rootView) {

    }
}
