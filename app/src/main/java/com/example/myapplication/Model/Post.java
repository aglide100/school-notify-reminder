package com.example.myapplication.Model;

import com.example.myapplication.DB.PostRealmObject;

import java.util.UUID;

public class Post {

    //    Plan의 ID
    private String parent;
    private String code;
    private String title;
    private String status;
    private String url;
    private String date;
    private String content;
    private String writer;
    private String ID;
    private Boolean custom;
    private int num;
    private Boolean isRead;

    public void RealmObjectToPost(PostRealmObject obj) {
        this.parent = obj.getParent();
        this.code = obj.getCode();
        this.content = obj.getContent();
        this.title = obj.getTitle();
        this.status = obj.getStatus();
        this.url = obj.getUrl();
        this.date = obj.getDate();
        this.writer = obj.getWriter();
        this.ID = obj.getID();
        this.num = obj.getNum();
        this.custom = obj.isCustom();
        this.isRead = obj.isRead();
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setID() {
        this.ID = UUID.randomUUID().toString();
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

    public void setParent(String parentID) {
        this.parent = parentID;
    }

    public void setUnRead() { this.isRead = false; }

    public void setIsRead() { this.isRead = true; }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }

    public String getStatus() {
        return this.status;
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

    public String getID() {
        return this.ID;
    }

    public int getNum() {
        return this.num;
    }

    public String getParent() {
        return this.parent;
    }

    public Boolean isCustom() {
        if (this.custom == null) {
            return false;
        }

        return this.custom;
    }

    public Boolean isRead() {
        if (this.isRead == null) {
            return false;
        }

        return this.isRead;
    }

}