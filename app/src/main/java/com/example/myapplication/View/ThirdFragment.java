package com.example.myapplication.View;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.Presenter.Contract;
import com.example.myapplication.Presenter.MainPresenter;
import com.example.myapplication.R;

public class ThirdFragment extends BasicFragment {

    private Context mContext;
    private Contract.Presenter presenter;
    private RadioGroup radioGroup;
    private RadioButton rdbMN2000191, rdbMN2000194, rdbMN2000195, rdbMN2000196, rdbMN2000197, rdbMN2000198;

    private String code;

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
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new MainPresenter(this);

//        rdbMN2000191 = view.findViewById(R.id.MN2000191);
//        rdbMN2000194 = view.findViewById(R.id.MN2000194);
//        rdbMN2000195 = view.findViewById(R.id.MN2000195);
//        rdbMN2000196 = view.findViewById(R.id.MN2000196);
//        rdbMN2000197 = view.findViewById(R.id.MN2000197);
//        rdbMN2000198 = view.findViewById(R.id.MN2000198);

        radioGroup = view.findViewById(R.id.rdoGroupCode);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.MN2000191) {
                    code = "MN2000191";
                }

                if (checkedId == R.id.MN2000194) {
                    code = "MN2000194";
                }

                if (checkedId == R.id.MN2000195) {
                    code = "MN2000195";
                }

                if (checkedId == R.id.MN2000196) {
                    code = "MN2000196";
                }

                if (checkedId == R.id.MN2000197) {
                    code = "MN2000197";
                }

                if (checkedId == R.id.MN2000198) {
                    code = "MN2000198";
                }


            }
        });


        view.findViewById(R.id.button_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ThirdFragment.this).navigate(R.id.action_ThirdFragment_to_FirstFragment);
            }
        });

        view.findViewById(R.id.button_previous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ThirdFragment.this).navigate(R.id.action_ThirdFragment_to_SecondFragment);
            }
        });

        view.findViewById(R.id.crawler_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (code == null) {
                    presenter.startFetchData("all");
                } else {
                    presenter.startFetchData(code);
                }

            }


        });


    }
}
