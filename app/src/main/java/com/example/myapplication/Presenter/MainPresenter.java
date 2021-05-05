package com.example.myapplication.Presenter;

import com.example.myapplication.Model.MainModel;
import com.example.myapplication.View.BasicFragment;

public class MainPresenter implements Contract.Presenter {
    BasicFragment MainView;
    MainModel mainModel;
    private Crawler mainCrawler = new Crawler();

    public MainPresenter(BasicFragment view) {
        MainView = view;
        mainModel = new MainModel(this);
    }

    @Override
    public void startFetchData(String code) {
        mainCrawler.GetData(code);
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
