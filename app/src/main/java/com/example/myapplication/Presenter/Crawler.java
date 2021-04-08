package com.example.myapplication.Presenter;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.util.Log;

import com.example.myapplication.Model.Post;
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

    public int GetTotalNum(String subject) {
        final String totalNum;

        if (CheckState(ctx)) {
            String code = TranslatorSubjectToCode(subject);
            String uri = "https://www.dongseo.ac.kr/kr/index.php?pCode=";
            uri += code;

            final String finalUri = uri;
            new Thread() {
                @Override
                public void run() {
                    Document doc = null;

                    try {
                        doc = Jsoup.connect(finalUri).get();
                        Elements contents = doc.select(".tot-num span");

//                        totalNum = contents.toString();

                    } catch (
                            IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();


        } else {

        }

        return 0;
    }

    public String TranslatorSubjectToCode(String subject) {

        switch (subject) {
            case "모집/취업":
                return "MN2000197";
            case "학사":
                return "MN2000194";
            case "공지":
                return "MN2000191";
            case "행사":
                return "MN2000198";
            case "장학":
                return "MN2000195";
            case "입찰":
                return "MN2000196";
        }

        return "error";
    }


    public void FetchPost(String[] subject) {

        String code;
        String uri = "https://www.dongseo.ac.kr/kr/index.php?pCode=";
        int totalNum;

        if (CheckState(ctx)) {
            for (int i = 0; i < subject.length; i++) {
                code = null;
                code = TranslatorSubjectToCode(subject[i]);

                totalNum = GetTotalNum(subject[i]);
                // 기존의 데이터 비교 하는 코드

                // 없으면 처음부터
                for (int k = totalNum; k < 0; k++) {
                    // 페이지 갯수 (1페이지에 15개의 글이 들어감)
                    int page = totalNum / 15;

                    for (int j = 1; j < page; j++) {
                        uri += code;
                        uri += "&pg=" + page;

                        GetPostBone(uri);
                    }

                }


            }
        }
    }

    public Post[] GetPostBone(final String uri) {
        Post[] posts = null;
        if (CheckState(ctx)) {
            new Thread() {
                @Override
                public void run() {
                    Document doc = null;

                    try {
                        doc = Jsoup.connect(uri).get();
                        Elements child1 = doc.select("tbody .child_1");
                        Elements child2 = doc.select("tbody .child_2");


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }.start();
        }

        return posts;
    }

    public void GetData() {
        Log.e("Crawler", "Call GetData()");

        if (CheckState(ctx)) {
            new Thread() {
                @Override
                public void run() {
                    Document doc = null;

                    try {
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
