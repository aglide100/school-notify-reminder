package com.example.myapplication.Model;

public class Post {

    private String code;
    private String title;
    private String Status;
    private String uri;
    private String date;
    private int num;

    public void setCode(String code) {
        this.code = code;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(String status) {
        this.Status = status;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }

    public String getStatus() {
        return this.Status;
    }

    public String getUri() {
        return this.uri;
    }

    public String getDate() {
        return this.date;
    }

}