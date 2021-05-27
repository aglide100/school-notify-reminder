package com.example.myapplication.View.Basic;

import androidx.fragment.app.Fragment;

import com.example.myapplication.Presenter.Contract;

public class BasicFragment extends Fragment implements Contract.View {

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
