package com.example.tiktalk.UI.Activities.Buyer;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.tiktalk.BaseClasses.BaseActivity;
import com.example.tiktalk.R;
import com.example.tiktalk.UI.Fragments.Buyer.BuyerHomeFragment;
import com.example.tiktalk.UI.Fragments.Buyer.BuyerInboxFragment;
import com.example.tiktalk.UI.Fragments.Buyer.BuyerMyWalletFragment;
import com.example.tiktalk.UI.Fragments.Buyer.BuyerNotificationFragment;

public class BuyerDashBoardActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button buyCoinsBtn;
    Button buyerSettingBtn;
    Button dashboardFinishBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_buyer);
        View header = getLayoutInflater().inflate(R.layout.buyer_navheader_dashboard, null, false);
        navigationView.addHeaderView(header);
        navigationView.setNavigationItemSelectedListener(this);
        onReplaceFragment(R.id.buyer_content_frame, new BuyerHomeFragment(), true);

        buyCoinsBtn = findViewById(R.id.buycoins_btn);
        buyerSettingBtn = header.findViewById(R.id.buyer_setting_btn);
        dashboardFinishBtn = header.findViewById(R.id.dashboard_finish_btn);

        buyCoinsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(BuyerDashBoardActivity.this);
                final AlertDialog alert = dialog.create();
                LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                View attachFileLayout = inflater.inflate(R.layout.buy_coins_layout, null);
                attachFileLayout.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                attachFileLayout.setBackgroundResource(R.drawable.sendmsg_background);
                alert.setView(attachFileLayout);

                Button buy_coins_btn = attachFileLayout.findViewById(R.id.buy_coins_btn);
                buy_coins_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alert.dismiss();
                        onReplaceFragment(R.id.buyer_content_frame, new BuyerMyWalletFragment(), false);
                    }
                });

                alert.show();
            }
        });

        buyerSettingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(BuyerDashBoardActivity.this, BuyerSettingsActivity.class);
                startActivity(in);
            }
        });

        dashboardFinishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawers();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void initializeComponents() {

    }

    @Override
    public void setupListeners() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.buyer_dashboard, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            onReplaceFragment(R.id.buyer_content_frame, new BuyerHomeFragment(), false);
            //onAddFragment(R.id.content_frame, new BuyerHomeFragment(), true);
        } else if (id == R.id.nav_mywallet) {
            onReplaceFragment(R.id.buyer_content_frame, new BuyerMyWalletFragment(), false);
            //onAddFragment(R.id.content_frame, new BuyerMyWalletFragment(), true);

        } else if (id == R.id.nav_inbox) {
            onReplaceFragment(R.id.buyer_content_frame, new BuyerInboxFragment(), false);
            //onAddFragment(R.id.content_frame, new BuyerInboxFragment(), true);

        } else if (id == R.id.nav_notifications) {
            onReplaceFragment(R.id.buyer_content_frame, new BuyerNotificationFragment(), false);

        } else if (id == R.id.nav_contact) {

        } else if (id == R.id.nav_logout) {
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
