package com.example.myapplication.Presenter;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.provider.Telephony;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.myapplication.R;

import java.util.logging.Handler;

public class MyService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class MyThread extends Thread {
        private boolean isStop = false;
        public void setStop() {
            isStop = true;
        }

        @Override
        public void run() {
            while (!isStop) {
                Log.e("!!", "!!");
            }
        }
    }
    private MyThread myThread;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.e("Service", "Service onCreate");

        NotificationCompat.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String CHANNEL_ID = "notification_service_common_channel";
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "공통알림",
                    NotificationManager.IMPORTANCE_DEFAULT);

            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE))
                    .createNotificationChannel(channel);

            builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        } else {
            builder = new NotificationCompat.Builder(this);
        }

        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("Service")
                .setContentText("running!!");

        startForeground(1, builder.build());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("Service", "Service onStartCommand");

        if (myThread == null || !myThread.isAlive()) {
            myThread = new MyThread();
            myThread.start();
        }
//        for (int i = 0; i < 50; i++){
//            try {
//                Thread.sleep(1000);
//            } catch (Exception e){};
//            Log.e("Service", "hello!");
//        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e("Service", "Service onDestroy");
        if (myThread != null && myThread.isAlive()) {
            myThread.setStop();
        }
        myThread = null;
    }


}
