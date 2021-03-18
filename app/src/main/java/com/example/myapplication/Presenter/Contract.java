package com.example.myapplication.Presenter;

public interface Contract {
    interface View {
        void showResult(int answer);
    }

    interface Presenter {
        void addNum(int num1, int num2);
    }

}
