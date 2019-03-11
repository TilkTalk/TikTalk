package com.example.tiktalk.UI.Activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.example.tiktalk.R
import com.example.tiktalk.UI.Activities.Buyer.BuyerLoginActivity
import spencerstudios.com.bungeelib.Bungee

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen);

        Handler().postDelayed({

            val intent = Intent(this, BuyerLoginActivity::class.java);
            startActivity(intent)
            Bungee.slideUp(this);
            finish()

        }, 2000)
    }
}