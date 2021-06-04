package com.example.myapplication.Presenter;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.myapplication.DB.DBmanager;
import com.example.myapplication.Model.AsyncResult;
import com.example.myapplication.Model.ErrorModel;
import com.example.myapplication.Model.MainModel;
import com.example.myapplication.Model.Post;
import com.example.myapplication.MyApplication;
import com.example.myapplication.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class FetchPostData extends AsyncTask<ArrayList<String>, Void, String> {
    private ConnectivityManager connectivityManager;

    private boolean ok = false;
    private DBmanager dbManager;

    private void CheckState() {
        Context ctx = MyApplication.ApplicationContext();
        connectivityManager = (ConnectivityManager) ctx.getSystemService(CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            if (!(connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected())) {
                new AlertDialog.Builder(ctx).setMessage("인터넷에 연결되어 있지 않습니다.").setCancelable(false).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
            } else {
                ok = true;
            }
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        CheckState();
        dbManager = new DBmanager();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


    @SafeVarargs
    @Override
    protected final String doInBackground(ArrayList<String>... arrayLists) {
        if (!ok) {
            return "";
        }
        Document doc = null;
       String result = "";

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);


    }
}