package com.example.myapplication.Presenter;

import android.util.Log;

import com.example.myapplication.Model.MainModel;
import com.example.myapplication.Model.Plan;
import com.example.myapplication.Model.Post;
import com.example.myapplication.View.Basic.BasicFragment;

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
    public boolean startFetchData(Plan plan) {
        boolean flag = false;
        ArrayList<String> subjectList = plan.getSubjects();
        ArrayList<String> planDetail = new ArrayList<String>();
        planDetail.add(plan.getPlanID());

        Log.e("Start", "start fetch data" + subjectList);

        new FetchData().execute(subjectList, planDetail);

        return flag;
    }

    @Override
    public Post[] getPostList(String planID) {

        return null;
    }

    @Override
    public void requestPost() {
        MainView.showPost();
    }
}
