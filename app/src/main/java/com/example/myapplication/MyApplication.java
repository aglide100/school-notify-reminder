package com.example.myapplication;

import android.content.Context;

import androidx.multidex.MultiDexApplication;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends MultiDexApplication {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = this;

        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder().allowWritesOnUiThread(true).name("InternalDB10.realm").schemaVersion(0).build();

        Realm.setDefaultConfiguration(config);
    }
    
    public static Context ApplicationContext() {
        return MyApplication.context;
    }
}
