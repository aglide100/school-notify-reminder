package com.example.myapplication.Model;

import com.example.myapplication.Presenter.Contract;

public class MainModel {
    private Contract.Presenter presenter;

    public MainModel(Contract.Presenter presenter) {
        this.presenter = presenter;
    }

//    public saveData(int data) {
//        //
//    }

    // Post 뿌리는 역할
    public Post GetPost(int ID) {
        // 하드 코딩
        Post newPost = new Post();
        newPost.Status = "test";
        newPost.title = "test";
        newPost.uri = "www.example.com";

        return newPost;
    }

}
