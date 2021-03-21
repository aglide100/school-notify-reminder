package com.example.myapplication.View;

import androidx.fragment.app.Fragment;

import com.example.myapplication.Presenter.Contract;

public class BasicFragment extends Fragment implements Contract.View {

    @Override
    public void showResult(int answer) {
//        String str = String.valueOf(answer);
//        Log.e("showResult", str);
    }
}
