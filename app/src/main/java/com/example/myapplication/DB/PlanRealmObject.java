package com.example.myapplication.DB;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PlanRealmObject extends RealmObject {
    @PrimaryKey
    String ID;
//    private List<String> subjects;
    private String planName;
//
//    public PlanRealmObject() {}
//
    public void PlanToRealmObject(String planName, ArrayList<String> subjects) {
        this.ID = UUID.randomUUID().toString();
        this.planName = planName;
//        this.subjects = subjects;
    }
//
//
//    public void setPlanID() {
//        this.ID = UUID.randomUUID().toString();
//    }
//
//    public void setSubjects(ArrayList<String> subjects) {
//        this.subjects = subjects;
//    }
//
//    public void setPlanName(String name) {
//        this.planName = name;
//    }
//
//    //    플랜 아이디 리턴
//    public String getPlanID() {
//        return this.ID;
//    }
//
//    //    플랜 이름을 리턴
//    public String getPlanName() {
//        return this.planName;
//    }
//

}
