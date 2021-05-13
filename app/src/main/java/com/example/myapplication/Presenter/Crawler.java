package com.example.myapplication.Presenter;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.util.Log;

import com.example.myapplication.Model.Post;
import com.example.myapplication.MyApplication;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

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
                    dialog.dismiss();
                }
            }).show();
            ok = false;
        } else {
            ok = true;
        }

        return ok;
    }

    public static class FetchPost extends AsyncTask<ArrayList, Void, Integer> {
        private String page = "&pg=1";
        private int postTotalNum = 0;
        String uri = "https://www.dongseo.ac.kr/kr/index.php?pCode=";
        final int[] num = new int[1];


        public Integer GetTotalNum(String code) {

            String finalUri1 = uri + code + page;

            Document doc = null;
            try {
                doc = Jsoup.connect(finalUri1).get();
                Elements totalNum = doc.select("div[class=board-tot-wrap]").select("span[class=tot-num]").select("span:first-child");
                String str = totalNum.text().replaceAll("[^0-9]", "");
                num[0] = Integer.parseInt(str);
                Log.e("Result", String.valueOf(num[0]));

                return num[0];

            } catch (
                    IOException e) {
                e.printStackTrace();
            }

            return 0;
        }

        @Override
        protected Integer doInBackground(ArrayList... params) {

//            if (CheckState(ctx)) {
//                for (int i = 0; i < params[0].size(); i++) {
//
//                    int totalNum = GetTotalNum((String) params[0].get(i));
//                    postTotalNum += totalNum;
//                    // 기존의 데이터 비교 하는 코드
//
//                    // 없으면 처음부터
//                    for (int k = totalNum; k < 0; k++) {
//                        // 페이지 갯수 (1페이지에 15개의 글이 들어감)
//                        int page = totalNum / 15;
//
//                        for (int j = 1; j < page; j++) {
////                        uri += code;
////                        uri += "&pg=" + page;
////
////                        GetPostBone(uri);
//                        }
//
//                    }
//                }
//            }

            return 0;
        }

        @Override
        protected void onPostExecute(Integer result) {

        }


    }


    public void FetchPost(ArrayList subjectList) {

        String code;
        String uri = "https://www.dongseo.ac.kr/kr/index.php?pCode=";
        int postTotalNum = 0;

        if (CheckState(ctx)) {
            for (int i = 0; i < subjectList.size(); i++) {

//                int totalNum = GetTotalNum((String) subjectList.get(i));
//                postTotalNum += totalNum;
//                // 기존의 데이터 비교 하는 코드
//
//                // 없으면 처음부터
//                for (int k = totalNum; k < 0; k++) {
//                    // 페이지 갯수 (1페이지에 15개의 글이 들어감)
//                    int page = totalNum / 15;
//
//                    for (int j = 1; j < page; j++) {
////                        uri += code;
////                        uri += "&pg=" + page;
////
////                        GetPostBone(uri);
//                    }

            }
        }
    }

//        Log.e("Result","Total:"+postTotalNum);


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

//    public void GetData(String code) {
//        final int[] num = new int[1];
//        String page = "&pg=1";
//
//        final String uri = "https://www.dongseo.ac.kr/kr/index.php?pCode=" + code + page;
//
//
//        if (CheckState(ctx)) {
//            new Thread() {
//                @Override
//                public void run() {
//                    Document doc = null;
//
//                    try {
//                        doc = Jsoup.connect(uri).get();
//                        Elements totalNum = doc.select("div[class=board-tot-wrap]").select("span[class=tot-num]").select("span:first-child");
//                        String str = totalNum.text().replaceAll("[^0-9]", "");
//                        num[0] = Integer.parseInt(str);
//                        Log.e("Result", String.valueOf(num[0]));
//
//
//                    } catch (
//                            IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }.start();
//
//
//        } else {
//
//
//        }
//
//    }

    //    public String TranslatorSubjectToCode(String subject) {
//
//        switch (subject) {
//            case "모집/취업":
//                return "MN2000197";
//            case "학사":
//                return "MN2000194";
//            case "공지":
//                return "MN2000191";
//            case "행사":
//                return "MN2000198";
//            case "장학":
//                return "MN2000195";
//            case "입찰":
//                return "MN2000196";
//        }
//
//        return "error";
//    }

}
