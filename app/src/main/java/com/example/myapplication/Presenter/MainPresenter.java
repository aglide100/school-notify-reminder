package com.example.myapplication.Presenter;

import android.util.Log;

import com.example.myapplication.Model.Plan;
import com.example.myapplication.Model.Post;

import java.util.ArrayList;

public class MainPresenter implements Contract.Presenter {
    @Override
    public void startFetchPostData(Post post){
        new FetchPostData().execute(post);
    }

    @Override
    public void startFetchData(ArrayList<Plan> plans) {
        for (int i = 0; i < plans.size(); i++) {
            startFetchData(plans.get(i));
        }
    }


    @Override
    public boolean startFetchData(Plan plan) {
        boolean flag = false;

        ArrayList<String> planDetail = new ArrayList<>();
        planDetail.add(plan.getPlanID());
        planDetail.add(plan.getCustomURL());

        if (plan.isCustom()) {
            new FetchCustomData().execute(planDetail);

            return true;
        }

        ArrayList<String> subjectList = plan.getSubjects();

        Log.e("Start", "start fetch data");
        new FetchData().execute(subjectList, planDetail);


        return flag;
    }

//    @Override
//    public Post[] getPostList(String planID) {
//        return null;
//    }
}
