package com.example.myapplication;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();

        Realm.init(App.ApplicationContext());
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("InternalDB.realm").schemaVersion(0).build();

        Realm.setDefaultConfiguration(config);
    }
    
    public static Context ApplicationContext() {
        return App.context;
    }
}
