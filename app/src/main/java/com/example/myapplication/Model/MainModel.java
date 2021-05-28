package com.example.myapplication.Model;

import android.util.Log;

import com.example.myapplication.Presenter.Contract;

import java.util.ArrayList;

public class MainModel {
    private Contract.Presenter presenter;
    private Post newPost;
    private Plan newPlan;

    private ArrayList<Plan> planList;
    private ArrayList<Post> postList;

    public MainModel(Contract.Presenter presenter) {
        this.presenter = presenter;
    }

//    public saveData(int data) {
//        //
//    }


//    !!!!!지금 아직 작업중이라 하드 코딩으로 결과값을 뱉습니다. 이러한 점 유의 바랍니다.

    public void makeNewPlan(Plan plan) {
        Log.e("New", "Receive new Plan!");
    }

    //   현재 작업 중인 plan 리스트를 가지고 온다.
    public ArrayList<Plan> getPlans() {
        newPlan = new Plan();
        newPlan.setPlanID();
        newPlan.setPlanName("더미 플랜!");
        planList = new ArrayList<Plan>();
        planList.add(newPlan);
        planList.add(newPlan);

        return planList;
    }

    //   특정 플랜안에 있는 post를 가져온다.
    public ArrayList<Post> getPostsInPlan(Plan plan) {
        postList = new ArrayList<Post>();
        newPost = new Post();
        newPost.setTitle("테스트 중!");
        newPost.setDate("1999-01-01");
        newPost.setCode("MN0000123");

        for (int i = 0; i < 10; i++) {
            newPost.setTitle("테스트 중! " + i);
            postList.add(newPost);
        }

        return postList;
    }

    //    id값으로 post를 가져온다.
    public Post GetPost(int ID) {
        // 하드 코딩
        newPost = new Post();
        newPost.setTitle("테스트 중!");
        newPost.setDate("1999-01-01");
        newPost.setCode("MN0000123");
        newPost.setContent("해당 글의 내용 ~~~~~~~");

        return newPost;
    }


}
