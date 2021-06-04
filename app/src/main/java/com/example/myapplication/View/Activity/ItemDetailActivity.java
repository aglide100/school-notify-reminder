package com.example.myapplication.View.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myapplication.Model.MainModel;
import com.example.myapplication.Model.Post;
import com.example.myapplication.Presenter.Contract;
import com.example.myapplication.Presenter.MainPresenter;
import com.example.myapplication.R;
import com.example.myapplication.View.Basic.BasicActivity;


public class ItemDetailActivity extends BasicActivity {
    private Contract.Presenter presenter;
    private MainModel mainModel;
    private Post post;
    private TextView titleView, dateView, contentView;

    private ConstraintLayout progressLayout, postInnerLayout;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemdetail);

        Intent postIntent = getIntent();
        String postID = postIntent.getStringExtra("postID");

        Toolbar toolbar = findViewById(R.id.toolbar);

        presenter = new MainPresenter(findViewById(R.layout.activity_itemdetail));
        mainModel = new MainModel(presenter);

        post = mainModel.getPost(postID);

        titleView = findViewById(R.id.postTitleView);
        dateView = findViewById(R.id.postDateView);
        progressLayout = findViewById(R.id.gettingData);
        postInnerLayout = findViewById(R.id.postDetailInnerLayout);

        new getPostAsyncTask().execute(post);
    }

    private class getPostAsyncTask extends AsyncTask<Post, Void, Post> {
        @Override
        protected void onPreExecute(){
            postInnerLayout.setVisibility(View.INVISIBLE);
            progressLayout.setVisibility(View.VISIBLE);
            titleView.setText(post.getTitle());
            dateView.setText(post.getDate());
        }

        @Override
        protected Post doInBackground(Post... posts) {
            presenter.startFetchPostData(post);
            return null;
        }

        @Override
        protected void onPostExecute(Post post){
            progressLayout.setVisibility(View.INVISIBLE);
            postInnerLayout.setVisibility(View.VISIBLE);
        }
    }

}
