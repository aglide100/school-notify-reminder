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
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder().name("InternalDB1.realm").schemaVersion(0).build();

        Realm.setDefaultConfiguration(config);
    }
    
    public static Context ApplicationContext() {
        return App.context;
    }
}
