package com.example.tiktalk.UI.Activities.Seller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.tiktalk.BaseClasses.BaseActivity;
import com.example.tiktalk.R;
import com.example.tiktalk.UI.Fragments.Buyer.BuyerNotificationFragment;
import com.example.tiktalk.UI.Fragments.Seller.SellerHomeFragment;
import com.example.tiktalk.UI.Fragments.Seller.SellerInboxFragment;
import com.example.tiktalk.UI.Fragments.Seller.SellerNotificationFragment;

public class SellerDashboardActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button buyerSettingBtn;
    Button dashboardFinishBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_seller);
        View header = getLayoutInflater().inflate(R.layout.seller_navheader_dashboard, null, false);
        navigationView.addHeaderView(header);
        navigationView.setNavigationItemSelectedListener(this);

        onReplaceFragment(R.id.seller_content_frame, new SellerHomeFragment(), true);

        buyerSettingBtn = header.findViewById(R.id.buyer_setting_btn);
        dashboardFinishBtn = header.findViewById(R.id.dashboard_finish_btn);

        buyerSettingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(SellerDashboardActivity.this, SellerSettingsActivity.class);
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
        //getMenuInflater().inflate(R.menu.seller_dashboard, menu);
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

        if (id == R.id.nav_dashboard) {
            // Handle the camera action
            onReplaceFragment(R.id.seller_content_frame, new SellerHomeFragment(), true);

        } else if (id == R.id.nav_inbox) {
            onReplaceFragment(R.id.seller_content_frame, new SellerInboxFragment(), true);

        } else if (id == R.id.nav_notifications) {
            onReplaceFragment(R.id.seller_content_frame, new SellerNotificationFragment(), true);

        } else if (id == R.id.nav_contact) {

        } else if (id == R.id.nav_logout) {
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
