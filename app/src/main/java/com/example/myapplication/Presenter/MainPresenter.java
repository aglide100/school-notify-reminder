package com.example.myapplication.Presenter;


import android.util.Log;

import com.example.myapplication.Model.MainModel;
import com.example.myapplication.View.BasicFragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainPresenter implements Contract.Presenter {
    BasicFragment MainView;
    MainModel mainModel;

    public MainPresenter(BasicFragment view) {
        MainView = view;
        mainModel = new MainModel(this);
    }


    @Override
    public void crawler() {

        new Thread() {
            @Override
            public void run() {
                Document doc = null;

                try {
                    doc = Jsoup.connect("https://www.naver.com").get();
//                    doc = Jsoup.connect("https://www.dongseo.ac.kr/kr/index.php?pCode=MN2000197&pg=1").get();
                    Elements contents = doc.select(".tot-num");

                    Log.e("get img", contents.toString());
                    Log.e("get doc", doc.toString());

                } catch (
                        IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

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
