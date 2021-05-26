package com.example.myapplication.View.Basic;

import android.app.Activity;

import com.example.myapplication.Presenter.Contract;

public class BasicActivity extends Activity implements Contract.View {

    @Override
    public void showResult(int answer) {
//        String str = String.valueOf(answer);
//        Log.e("showResult", str);
    }

    @Override
    public void showPost() {

    }

    @Override
    public void showCrawlerResult() {

    }
}
