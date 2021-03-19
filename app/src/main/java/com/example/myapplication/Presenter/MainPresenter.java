package com.example.myapplication.Presenter;

import com.example.myapplication.Model.MainModel;

public class MainPresenter implements Contract.Presenter {
    Contract.View view;
    MainModel mainModel;

    public MainPresenter(Contract.View view) {
        this.view = view;
        mainModel = new MainModel(this);
    }

    @Override
    public void addNum(int num1, int num2) {
        view.showResult(num1 + num2);
    }
}
