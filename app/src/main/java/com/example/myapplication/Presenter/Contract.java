package com.example.myapplication.Presenter;

import com.example.myapplication.Model.Plan;
import com.example.myapplication.Model.Post;

import java.util.ArrayList;

public interface Contract {
    // 뷰에서 쓸 함수
    interface View {
        void showResult(int answer);

        void showCrawlerResult();

        void showPost();

    }

    //   함수 인터페이스 선언
    interface Presenter {
        void addNum(int num1, int num2);

        boolean startFetchData(Plan plan);

        Post[] getPostList(String planID);

//        void crawler();

        void requestPost();
    }

}
