package com.example.myapplication.Model;

import java.util.UUID;

public class Plan {
    private String planID;

    public void setPlanID() {
        this.planID = UUID.randomUUID().toString();
    }


}
