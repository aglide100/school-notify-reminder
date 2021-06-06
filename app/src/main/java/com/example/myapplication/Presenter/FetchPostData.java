package com.example.myapplication.Presenter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.AsyncTask;

import com.example.myapplication.DB.DBmanager;
import com.example.myapplication.Model.Post;
import com.example.myapplication.MyApplication;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class FetchPostData extends AsyncTask<Post, Void, Post> {
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
    protected Post doInBackground(Post... post) {
        Post updatePost = post[0];
        String url = updatePost.getUrl();
        Document doc = null;

        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Elements content = doc.select("div[class=board-view-contents]");

        updatePost.setContent(content.text());

        return updatePost;
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

    @Override
    protected void onPostExecute(Post post) {
        super.onPostExecute(post);

        dbManager.updateContentInPost(post);
    }
}
