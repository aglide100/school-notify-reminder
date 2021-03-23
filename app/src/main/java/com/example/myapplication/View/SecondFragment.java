package com.example.myapplication.View;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.Presenter.Contract;
import com.example.myapplication.Presenter.MainPresenter;
import com.example.myapplication.R;

public class SecondFragment extends BasicFragment implements View.OnClickListener {

    private TextView answer;
    private EditText number1;
    private EditText number2;
    private Button sumbtn;
    private Contract.Presenter presenter;

    private Context mContext;

    //    프래그먼트가 아직 첨부되기 전이라 액티비티를 받아오기 위해서 onAttach를 오버라이딩 해야됨
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new MainPresenter(this);

        answer = view.findViewById(R.id.textview_second);
        number1 = view.findViewById(R.id.number1);
        number2 = view.findViewById(R.id.number2);

        sumbtn = view.findViewById(R.id.sumBtn);
        sumbtn.setOnClickListener(this);

        view.findViewById(R.id.button_previous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        view.findViewById(R.id.button_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this).navigate(R.id.action_SecondFragment_to_ThirdFragment);
            }
        });
    }

    @Override
    public void showPost() {
        Log.e("test", "called showPost()");
    }

    // 결과 출력
    @Override
    public void showResult(int answer) {
//        super.showResult(answer);

        String str = String.valueOf(answer);
        Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == sumbtn.getId()) {
            int number_1 = Integer.parseInt(number1.getText().toString());
            int number_2 = Integer.parseInt(number2.getText().toString());

//          addNum 호출
            presenter.addNum(number_1, number_2);
        }
    }
}
