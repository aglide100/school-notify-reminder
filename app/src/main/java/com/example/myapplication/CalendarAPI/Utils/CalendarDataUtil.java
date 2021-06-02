package com.example.myapplication.CalendarAPI.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.myapplication.MyApplication;

public class CalendarDataUtil {
    private static final String PREF_NAME = "CalendarAPI";
    private static final String PREF_ACCOUNT_NAME = "accountName";

    public static String getSaveAccountName() {
        SharedPreferences settings = MyApplication.ApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return settings.getString(PREF_ACCOUNT_NAME, null);
    }

    public static void setSaveAccountName(String accountName) {
        SharedPreferences settings = MyApplication.ApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(PREF_ACCOUNT_NAME, accountName);
        editor.apply();
    }
}
