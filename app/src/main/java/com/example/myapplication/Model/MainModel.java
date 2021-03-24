package com.example.myapplication.Model;

import com.example.myapplication.Presenter.Contract;

public class MainModel {
    private Contract.Presenter presenter;
    private Post post = new Post();
    private Announce newPost;

    public MainModel(Contract.Presenter presenter) {
        this.presenter = presenter;
    }

//    public saveData(int data) {
//        //
//    }

    // Post 뿌리는 역할
    public Announce GetPost(int ID) {
        // 하드 코딩
        newPost = post.GetAnnounce();

        return newPost;
    }

}
