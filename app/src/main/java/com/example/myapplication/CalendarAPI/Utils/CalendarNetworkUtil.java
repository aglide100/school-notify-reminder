package com.example.myapplication.CalendarAPI.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

import com.example.myapplication.MyApplication;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class CalendarNetworkUtil {
    public static boolean isNetworkAvailable() {
        Context ctx = MyApplication.ApplicationContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) ctx.getSystemService(CONNECTIVITY_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network nw = connectivityManager.getActiveNetwork();
            if (nw == null) return false;
            NetworkCapabilities actNw = connectivityManager.getNetworkCapabilities(nw);
            return actNw != null && (actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH));
        } else {
            NetworkInfo nwInfo = connectivityManager.getActiveNetworkInfo();
            return nwInfo != null && nwInfo.isConnected();
        }
    }

    public static int getGooglePlayServicesStatus() {
        Context ctx = MyApplication.ApplicationContext();

        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();

        return apiAvailability.isGooglePlayServicesAvailable(ctx);
    }

    public static boolean isGooglePlayServicesAvailable() {
        return getGooglePlayServicesStatus() == ConnectionResult.SUCCESS;
    }

    public static boolean acquireGooglePlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        return apiAvailability.isUserResolvableError(getGooglePlayServicesStatus());
    }
}
