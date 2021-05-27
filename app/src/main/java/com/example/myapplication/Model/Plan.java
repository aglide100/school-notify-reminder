package com.example.myapplication.Model;

import java.util.ArrayList;
import java.util.UUID;

public class Plan {
    private String planID;
    //   uri을 저장
    private ArrayList<String> subjects;
    private String planName;

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


}
