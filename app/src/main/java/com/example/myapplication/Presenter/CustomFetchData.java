package com.example.myapplication.Presenter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.myapplication.MyApplication;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class CustomFetchData extends AsyncTask<ArrayList, Void, String> {
    private ConnectivityManager connectivityManager;
    private Context ctx = MyApplication.ApplicationContext();

    private boolean ok = false;
    String content;
    private String page = "&pg=1";

    private boolean CheckState(Context ctx) {
        connectivityManager = (ConnectivityManager) ctx.getSystemService(CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        if (!(connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected())) {
            new AlertDialog.Builder(ctx).setMessage("인터넷에 연결되어 있지 않습니다.").setCancelable(false).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).show();
            ok = false;
        } else {
            ok = true;
        }

        return ok;
    }


    @Override
    protected String doInBackground(ArrayList... arrayLists) {

        Document doc = null;
        try {
            if (CheckState(ctx)) {
                for (int i = 0; i < arrayLists[0].size(); i++) {
                    String code = (String) arrayLists[0].get(i);

                    doc = Jsoup.connect( code ).get();
                    content = doc.toString();

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "";
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Toast.makeText(ctx, content, Toast.LENGTH_SHORT).show();
//        이벤트 버스로 값 전달
    }

}
