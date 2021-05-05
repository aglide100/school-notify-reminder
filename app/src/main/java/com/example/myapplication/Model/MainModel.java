package com.example.myapplication.Model;

import com.example.myapplication.Presenter.Contract;

public class MainModel {
    private Contract.Presenter presenter;
    private Post newPost;

    public MainModel(Contract.Presenter presenter) {
        this.presenter = presenter;
    }

//    public saveData(int data) {
//        //
//    }

    // Post 뿌리는 역할
    public Post GetPost(int ID) {
        // 하드 코딩
        newPost = new Post();
        newPost.setTitle("Title");
        newPost.setDate("1999-01-01");
        newPost.setCode("MN0000123");

        return newPost;
    }

    public Post[] GetFromLocalData() {
        Post[] posts = null;




        return posts;
    }

}
