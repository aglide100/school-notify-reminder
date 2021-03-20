package com.example.myapplication.Presenter;

import android.util.Log;

import com.example.myapplication.Model.MainModel;
import com.example.myapplication.View.BasicFragment;

public class MainPresenter implements Contract.Presenter {
    BasicFragment MainView;
    MainModel mainModel;

    public MainPresenter(BasicFragment view) {
        MainView = view;
//        this.view = view;
        mainModel = new MainModel(this);
    }


    @Override
    public void addNum(int num1, int num2) {
        MainView.showResult(num1 + num2);
        Log.e("!!", "!!!!!!!!!");
    }
}
