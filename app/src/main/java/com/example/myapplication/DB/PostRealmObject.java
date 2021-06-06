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

    public PostRealmObject() {}

    public void PostToRealmObject(Post newPost) {
        this.code = newPost.getCode();
        this.content = newPost.getContent();
        this.date = newPost.getDate();
        this.ID = newPost.getID();
        this.num = newPost.getNum();
        this.status = newPost.getStatus();
        this.writer = newPost.getWriter();
        this.title = newPost.getTitle();
        this.url = newPost.getUrl();
        this.parent = newPost.getParent();
        this.custom = newPost.isCustom();
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

    public Boolean isCustom() {
        if (this.custom == null) {
            return false;
        }

        return this.custom;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
