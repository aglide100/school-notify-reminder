package com.example.myapplication.Presenter;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.provider.Telephony;
import android.util.Log;

import java.util.logging.Handler;

public class MyService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.e("Service", "Service onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("Service", "Service onStartCommand");

        for (int i = 0; i < 50; i++){
            try {
                Thread.sleep(1000);
            } catch (Exception e){};
            Log.e("Service", "hello!");
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e("Service", "Service onDestroy");
    }
}
