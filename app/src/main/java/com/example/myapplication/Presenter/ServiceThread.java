package com.example.myapplication.Presenter;

import android.provider.Telephony;
import android.util.Log;

import java.util.logging.Handler;

public class ServiceThread extends Thread {
    boolean isRun = true;

    public void stopForever() {
        synchronized (this) {
            this.isRun = false;
        }
    }

    public boolean isRun(){return this.isRun;}

    public void run() {
        while (isRun) {
            Log.d("Thread", "백그라운드에서 실행");
            try{
                Thread.sleep(10000);
            }catch (Exception e) {}
        }
    }
    
}
