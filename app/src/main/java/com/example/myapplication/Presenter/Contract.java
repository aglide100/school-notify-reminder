package com.example.myapplication.Presenter;

import com.example.myapplication.Model.Plan;
import com.example.myapplication.Model.Post;

import java.util.ArrayList;

public interface Contract {
    // 뷰에서 쓸 함수
    interface View {}

    //   함수 인터페이스 선언
    interface Presenter {
        boolean startFetchData(Plan plan);

        void startFetchData(ArrayList<Plan> plans);

        void startFetchPostData(Post post);
    }

}
