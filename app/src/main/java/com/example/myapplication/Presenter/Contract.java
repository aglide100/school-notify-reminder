package com.example.myapplication.Presenter;

public interface Contract {
    interface View {
        void showResult(int answer);
    }

//   함수 인터페이스 선언
    interface Presenter {
        void addNum(int num1, int num2);
    }

}
