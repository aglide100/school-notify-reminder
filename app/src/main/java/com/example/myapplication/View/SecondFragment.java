package com.example.myapplication.View;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.Presenter.Contract;
import com.example.myapplication.Presenter.MainPresenter;
import com.example.myapplication.R;

public class SecondFragment extends Fragment implements Contract.View {

    private TextView answer;
    private EditText number1;
    private EditText number2;
    private Contract.Presenter presenter;

    private Context context;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        context = container.getContext();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        answer = view.findViewById(R.id.textview_second);
        number1 = view.findViewById(R.id.number1);
        number2 = view.findViewById(R.id.number2);

        presenter = new MainPresenter(this);


        view.findViewById(R.id.sumBtn).setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                presenter.addNum(Integer.parseInt(number1.getText().toString()),
                        Integer.parseInt(number2.getText().toString()));
            }
        });

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
    public void showResult(int answer) {
//      토스트에서 에러뜨는듯....
        Toast.makeText(context, answer, Toast.LENGTH_SHORT);
    }
}
