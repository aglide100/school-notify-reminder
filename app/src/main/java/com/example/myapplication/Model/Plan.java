package com.example.myapplication.Model;

import com.example.myapplication.DB.PlanRealmObject;

import java.util.ArrayList;
import java.util.UUID;

public class Plan {
    private String planID;
    //   uri을 저장
    private ArrayList<String> subjects;
    private String planName;
    private boolean custom;
    private String customURL;
    private Integer countUnReadPost;

    public void RealmObjectToPlan(PlanRealmObject plan) {
        this.planID = plan.getPlanID();
        this.planName = plan.getPlanName();
        this.subjects = plan.getSubjects();
        this.custom = plan.isCustom();
        this.customURL = plan.getCustomURL();
    }

    public void setCustom() {
        this.custom = true;
    }

    public void setCustomURL(String url) {
        this.customURL = url;
    }

    public void setPlanID() {
        this.planID = UUID.randomUUID().toString();
    }

    public void setSubjects(ArrayList<String> subjects) {
        this.subjects = subjects;
    }

    public void setPlanName(String name) {
        this.planName = name;
    }


    public String getCustomURL() {
        return this.customURL;
    }

    //    플랜 아이디 리턴
    public String getPlanID() {
        return this.planID;
    }

    //    플랜 이름을 리턴
    public String getPlanName() {
        return this.planName;
    }

    public ArrayList<String> getSubjects() {
        return this.subjects;
    }

    public boolean isCustom() {
        if (this.custom == true) {
            return this.custom;
        } else {
            return false;
        }
    }

    public void setCountUnReadPost(Integer countUnReadPost) {
        this.countUnReadPost = countUnReadPost;
    }

    public Integer getCountUnReadPost() {
        return this.countUnReadPost;
    }
}
