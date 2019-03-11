package com.example.tiktalk;

import android.app.Application;
import android.content.Context;

public class TikTalk extends Application {

    public static final String TAG = TikTalk.class.getSimpleName();

    private static Context context;
    private static TikTalk mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        context = this.getApplicationContext();
    }

    public static synchronized TikTalk getInstance () {
        return mInstance;
    }
    public static Context getContext () {
        return context;
    }
}
