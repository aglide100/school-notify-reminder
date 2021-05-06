package com.example.myapplication.Presenter;

import android.util.Log;

import com.example.myapplication.Model.MainModel;
import com.example.myapplication.View.BasicFragment;

import java.util.ArrayList;

public class MainPresenter implements Contract.Presenter {
    BasicFragment MainView;
    MainModel mainModel;
    private Crawler mainCrawler = new Crawler();

    public MainPresenter(BasicFragment view) {
        MainView = view;
        mainModel = new MainModel(this);
    }

    @Override
    public void startFetchData(ArrayList subjectList) {
        Log.e("Start", "start fetch data" + subjectList);
        mainCrawler.FetchPost(subjectList);
    }

    @Override
    public void crawler() {

//        MainView.showCrawlerResult();
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
