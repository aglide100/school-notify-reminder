package com.example.myapplication.Model;

import java.util.ArrayList;

public class Subject {
    private String name;
    private boolean parent;
    private ArrayList<Subject> childList;

    public void setParent(Boolean answer) {
        this.parent = answer;
    }

    public void setSubjectName(String name) {
        this.name = name;
    }

    public void setChildList(ArrayList<Subject> childList) {
        this.childList = childList;
    }

    public String getSubjectName() {
        return this.name;
    }

    public ArrayList<Subject> getChildList() {
        return this.childList;
    }

    public boolean isParent() {
        return parent;
    }
}
