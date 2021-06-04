package com.example.myapplication.DB;

import com.example.myapplication.Model.Plan;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class PlanRealmObject extends RealmObject {
    @PrimaryKey
    String ID;
    @Required
    private RealmList<String> subjectList = new RealmList<String>();
    @Required
    private String planName;
    private Boolean custom;
    private String customURL;

    public PlanRealmObject() {}

    public void PlanToRealmObject(Plan plan) {
        this.ID = plan.getPlanID();
        this.planName = plan.getPlanName();
        this.custom = plan.isCustom();
        this.customURL = plan.getCustomURL();
        for (int i = 0; i < plan.getSubjects().size(); i++){
            this.subjectList.add(plan.getSubjects().get(i));
        }
    }

    public void setSubjects(ArrayList<String> subjects) {
        this.subjectList = null;
        for (int i = 0; i < subjects.size(); i++){
            this.subjectList.add(subjects.get(i));
        }
    }

    public void setPlanName(String name) {
        this.planName = name;
    }

    //    플랜 아이디 리턴
    public String getPlanID() {
        return this.ID;
    }

    //    플랜 이름을 리턴
    public String getPlanName() {
        return this.planName;
    }

    public ArrayList<String> getSubjects() {
        ArrayList<String> convertSubjects = new ArrayList<String>();
        for (int i = 0; i < this.subjectList.size(); i++){
            convertSubjects.add(this.subjectList.get(i));
        }
        return convertSubjects;
    }
    public String getCustomURL() { return this.customURL; }

    public boolean isCustom() {
        if (this.custom == true) {
            return this.custom;
        } else {
            return false;
        }
    }
}
