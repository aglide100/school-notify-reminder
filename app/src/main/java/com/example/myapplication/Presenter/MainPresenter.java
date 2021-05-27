package com.example.myapplication.Presenter;

import android.util.Log;

import com.example.myapplication.Model.MainModel;
import com.example.myapplication.Model.Post;
import com.example.myapplication.View.BasicFragment;

import java.util.ArrayList;

public class MainPresenter implements Contract.Presenter {
    BasicFragment MainView;
    MainModel mainModel;
//    private Crawler mainCrawler = new Crawler();


    public MainPresenter(BasicFragment view) {
        MainView = view; 
        mainModel = new MainModel(this);
    }

    @Override
    public boolean startFetchData(ArrayList subjectList) {
        boolean flag = false;

        Log.e("Start", "start fetch data" + subjectList);

        new FetchData().execute(subjectList);

        return flag;
    }

    @Override
    public Post[] getPostList(String planID) {


        return null;
    }


    @Override
    public void addNum(int num1, int num2) {
        MainView.showResult(num1 + num2);
    }

    @Override
    public void requestPost() {
        MainView.showPost();
    }
}
