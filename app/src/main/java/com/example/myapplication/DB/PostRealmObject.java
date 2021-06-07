package com.example.myapplication.DB;

import com.example.myapplication.Model.Post;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PostRealmObject extends RealmObject {
    @PrimaryKey
    String ID;

    private String parent;
    private String code;
    private String title;
    private String status;
    private String url;
    private String date;
    private String content;
    private String writer;
    private Boolean custom;
    private int num;
    private Boolean isRead;

    public PostRealmObject() {}

    public void PostToRealmObject(Post obj) {
        this.code = obj.getCode();
        this.content = obj.getContent();
        this.date = obj.getDate();
        this.ID = obj.getID();
        this.num = obj.getNum();
        this.status = obj.getStatus();
        this.writer = obj.getWriter();
        this.title = obj.getTitle();
        this.url = obj.getUrl();
        this.parent = obj.getParent();
        this.custom = obj.isCustom();
        this.isRead = obj.isRead();
    }

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

    public void setUnRead() { this.isRead = false; }

    public void setIsRead() { this.isRead = true; }

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

    public void setContent(String content) {
        this.content = content;
    }
}
