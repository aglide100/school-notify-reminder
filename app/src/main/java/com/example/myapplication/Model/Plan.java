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

    public void RealmObjectToPlan(PlanRealmObject plan) {
        this.planID = plan.getPlanID();
        this.planName = plan.getPlanName();
        this.custom = plan.isCustom();
    }

    public void setCustom() {
        this.custom = true;
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

    //    플랜 아이디 리턴
    public String getPlanID() {
        return this.planID;
    }

    //    플랜 이름을 리턴
    public String getPlanName() {
        return this.planName;
    }

    public ArrayList<String> getSubjects() { return this.subjects;}

    public boolean isCustom() {return this.custom; }

}
