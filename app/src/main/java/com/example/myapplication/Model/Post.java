package com.example.myapplication.Model;

public class Post {

//    Plan의 ID
    private String parent;
    private String code;
    private String title;
    private String Status;
    private String url;
    private String date;
    private String content;
    private String writer;
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

    public void setUrl(String url) {
        this.url = url;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setWriter(String writer) {
        this.writer = writer;
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

    public String getUrl() {
        return this.url;
    }

    public String getDate() {
        return this.date;
    }

    public String getContent() {
        return this.content;
    }

    public String getWriter() {
        return this.writer;
    }

}