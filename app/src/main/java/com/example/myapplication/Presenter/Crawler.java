package com.example.myapplication.Presenter;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.util.Log;

import com.example.myapplication.MyApplication;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class Crawler {

    private ConnectivityManager connectivityManager;
    private Context ctx = MyApplication.ApplicationContext();

    private boolean ok = false;

    // 인터넷 상황을 채크
    public boolean CheckState(Context ctx) {
        connectivityManager = (ConnectivityManager) ctx.getSystemService(CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        if (!(connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected())) {
            new AlertDialog.Builder(ctx).setMessage("인터넷에 연결되어 있지 않습니다.").setCancelable(false).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                    dialog.dismiss();
                }
            }).show();
            ok = false;
        } else {
            ok = true;
        }

        return ok;
    }

    public void GetData() {
        Log.e("Crawler", "Call GetData()");


        if (CheckState(ctx)) {
            new Thread() {
                @Override
                public void run() {
                    Document doc = null;

                    try {
//                    doc = Jsoup.connect("https://www.naver.com").get();
                        doc = Jsoup.connect("https://www.dongseo.ac.kr/kr/index.php?pCode=MN2000197&pg=1").get();
                        Elements contents = doc.select(".tot-num");

                        Log.e("get img", contents.toString());
                        Log.e("get doc", doc.toString());

                    } catch (
                            IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();


        } else {


        }
    }
}
