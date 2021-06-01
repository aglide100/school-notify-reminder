package com.example.myapplication.Model;

import android.util.Log;

import com.example.myapplication.DB.DBmanager;
import com.example.myapplication.Presenter.Contract;

import java.util.ArrayList;

public class MainModel {
    private Contract.Presenter presenter;
    private Post newPost;
    private Plan newPlan;
    private DBmanager dbManager;

    private ArrayList<Plan> planList;
    private ArrayList<Post> postList;

    public MainModel(Contract.Presenter presenter) {
        this.presenter = presenter;
    }

    public void makeNewPlan(Plan plan) {
        dbManager = new DBmanager();
        dbManager.addNewPlan(plan);

        Log.e("New", "Receive new Plan!");
    }

    //   현재 작업 중인 plan 리스트를 가지고 온다.
    public ArrayList<Plan> getPlan() {
        dbManager = new DBmanager();
        ArrayList<Plan> planList = new ArrayList<Plan>();
        planList = dbManager.getPlanList();
        if (planList == null){
            Log.e("MainModel", "리스트가 없습니다.!!!");
            return null;
        }

        return planList;
    }

    public ArrayList<Post> getPost(Plan plan) {
        dbManager = new DBmanager();
        return dbManager.getPost(plan);
    }

    //    id값으로 post를 가져온다.
    public Post getPost(String ID) {
        dbManager = new DBmanager();
        // 하드 코딩
        newPost = new Post();
        newPost.setTitle("테스트 중!");
        newPost.setDate("1999-01-01");
        newPost.setCode("MN0000123");
        newPost.setContent("해당 글의 내용 ~~~~~~~");

        return newPost;
    }


}
