package com.example.myapplication.Model;

import java.util.ArrayList;
import java.util.UUID;

public class CustomPlan {
    private String planID;
    //   uri을 저장
    private String planName;
    private String url;

    public void setPlanID() {
        this.planID = UUID.randomUUID().toString();
    }

    public void setUrl(String url) {this.url = url;}

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

    public String getUrl() {return this.url;}
}
