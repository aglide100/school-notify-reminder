package com.example.myapplication.DB;

import com.example.myapplication.Model.Post;

public class InsertPostToDB {

    //   나중에 에러 리턴하도록 리턴 타입 수정
    public void InsertPost(Post post) {
        String title = post.getTitle();

        String sql = "INSERT INTO mytable() values('');";

    }
}
