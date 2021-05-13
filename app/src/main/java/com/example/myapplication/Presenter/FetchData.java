package com.example.myapplication.Presenter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.util.Log;

import com.example.myapplication.MyApplication;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class FetchData extends AsyncTask<ArrayList, Void, String> {
    private ConnectivityManager connectivityManager;
    private Context ctx = MyApplication.ApplicationContext();

    private boolean ok = false;
    private int postTotalNum;
    String uri = "https://www.dongseo.ac.kr/kr/index.php?pCode=";
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

                    doc = Jsoup.connect(uri + code + page).get();
                    Elements totalNum = doc.select("div[class=board-tot-wrap]").select("span[class=tot-num]").select("span:first-child");
                    String str = totalNum.text().replaceAll("[^0-9]", "");
                    int num = Integer.parseInt(str);
                    Log.e("Result " + code, String.valueOf(num));

                    postTotalNum += num;
                    // 기존의 데이터 비교 하는 코드

                    // 없으면 처음부터
                    for (int k = num; k < 0; k++) {
                        // 페이지 갯수 (1페이지에 15개의 글이 들어감)
                        int page = num / 15;

                        for (int j = 1; j < page; j++) {
//                        uri += code;
//                        uri += "&pg=" + page;

//                        GetPostBone(uri);
                        }

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "";
    }

    @Override
    protected void onPostExecute(String result) {
//        이벤트 버스로 값 전달
    }
}
