package com.example.myapplication.View.Activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myapplication.DB.DBmanager;
import com.example.myapplication.Model.MainModel;
import com.example.myapplication.Model.Post;
import com.example.myapplication.MyApplication;
import com.example.myapplication.Presenter.Contract;
import com.example.myapplication.Presenter.MainPresenter;
import com.example.myapplication.R;
import com.example.myapplication.View.Basic.BasicActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ItemDetailActivity extends BasicActivity {
    Contract.Presenter presenter;
    MainModel mainModel;
    private Post post;
    private TextView titleView, dateView, contentView;

    private ConstraintLayout progressLayout, postInnerLayout;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemdetail);

        // UI Setting
        titleView = findViewById(R.id.postTitleView);
        dateView = findViewById(R.id.postDateView);
        progressLayout = findViewById(R.id.gettingData);
        postInnerLayout = findViewById(R.id.postDetailInnerLayout);
        contentView = findViewById(R.id.postContentView);

        Intent postIntent = getIntent();
        String postID = postIntent.getStringExtra("postID");

        presenter = new MainPresenter();
        mainModel = new MainModel();

        post = mainModel.getPost(postID);

        if (post.isCustom()) {
            progressLayout.setVisibility(View.INVISIBLE);
            postInnerLayout.setVisibility(View.VISIBLE);

            titleView.setText(post.getTitle());
            dateView.setText(post.getDate());
            contentView.setText(post.getContent());
        } else {
            new getPostAsyncTask().execute(post);
        }
    }

    private class getPostAsyncTask extends AsyncTask<Post, Void, Post> {
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

            postInnerLayout.setVisibility(View.INVISIBLE);
            progressLayout.setVisibility(View.VISIBLE);
            titleView.setText(post.getTitle());
            dateView.setText(post.getDate());
        }

        @Override
        protected Post doInBackground(Post... asyncPost) {
            Post updatePost = asyncPost[0];
            String url = updatePost.getUrl();
            Document doc = null;

            try {
                doc = Jsoup.connect(url).get();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

            Element content = doc.select("div[class=board-view-contents]").first();
            Log.e("Detail", content.text());
            updatePost.setContent(content.text());

            return updatePost;
        }

        @Override
        protected void onPostExecute(Post asyncPost) {
            progressLayout.setVisibility(View.INVISIBLE);
            postInnerLayout.setVisibility(View.VISIBLE);

            if (asyncPost == null || asyncPost.getID() == null) {

            } else {
                Log.e("Detail", asyncPost.getContent());
                dbManager.updateContentInPost(asyncPost);
                contentView.setText(asyncPost.getContent());
            }
        }
    }
}
