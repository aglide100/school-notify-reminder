package com.example.myapplication.Presenter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.Model.Post;
import com.example.myapplication.MyApplication;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
    //    private String page = "&pg=1";
    private ArrayList<Post> newPostList = new ArrayList<Post>();

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
                int num;
                for (int i = 0; i < arrayLists[0].size(); i++) {
                    num = 0;

                    String code = (String) arrayLists[0].get(i);
                    Log.e("Start", code + " 시작");

                    doc = Jsoup.connect(uri + code).get();
                    Elements totalNum = doc.select("div[class=board-tot-wrap]").select("span[class=tot-num]").select("span:first-child");
                    String str = totalNum.text().replaceAll("[^0-9]", "");
                    num = Integer.parseInt(str);
                    Log.e("Result " + code, String.valueOf(num));

                    postTotalNum += num;
                    // 기존의 데이터 비교 하는 코드

                    // 없으면 처음부터
                    int page = (num / 15) + 1;
                    Log.e("While", code + "중 페이지 수: " + page);
                    for (int p = 1; p <= page; p++) {
                        // 페이지 갯수 (1페이지에 15개의 글이 들어감)
                        String getUri = uri + code + "&pg=" + p;

                        Document innerDoc = Jsoup.connect(getUri).get();
                        Elements child1 = innerDoc.select("tbody").select("tr[class=child_1]");
                        Elements child2 = innerDoc.select("tbody").select("tr[class=child_2]");

                        for (int l = 0; l < child1.size(); l++) {
                            Element elements = child1.get(l);
                            Post newPost = new Post();
                            String title = elements.select("td[class=f-tit subject]").select("p").select("span").text();
                            String postURL = elements.select("td[class=f-tit subject]").select("p").select("a").attr("href");
                            String writer = elements.select("td[class=f-nm writer]").select("p").text();
                            String date = elements.select("td[class=f-date date]").select("p").text();

                            newPost.setTitle(title);
                            newPost.setDate(date);
                            newPost.setWriter(writer);
                            newPost.setUrl(postURL);

                            newPostList.add(newPost);
//                            Log.e("getPost", title + "/" + writer + "/" + date);
                        }

                        for (int l = 0; l < child2.size(); l++) {
                            Element elements = child2.get(l);
                            Post newPost = new Post();
                            String title = elements.select("td[class=f-tit subject]").select("p").select("span").text();
                            String postURL = elements.select("td[class=f-tit subject]").select("p").select("a").attr("href");
                            String writer = elements.select("td[class=f-nm writer]").select("p").text();
                            String date = elements.select("td[class=f-date date]").select("p").text();

                            newPost.setTitle(title);
                            newPost.setDate(date);
                            newPost.setWriter(writer);
                            newPost.setUrl(postURL);

                            newPostList.add(newPost);

//                            Log.e("getPost", title + "/" + writer + "/" + date);
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
        super.onPostExecute(result);
        Log.e("End", "항목 끝!!! " + newPostList.size() + "갯수의 포스트 생성");
        Toast.makeText(ctx, String.valueOf(postTotalNum), Toast.LENGTH_SHORT).show();
//        이벤트 버스로 값 전달
    }
}
