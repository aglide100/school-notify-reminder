package com.example.myapplication.View;

import android.util.Log;

import androidx.fragment.app.Fragment;

import com.example.myapplication.Presenter.Contract;

public class BasicFragment extends Fragment implements Contract.View {

    @Override
    public void showResult(int answer) {
        Log.e("????", "??????");
    }
}
