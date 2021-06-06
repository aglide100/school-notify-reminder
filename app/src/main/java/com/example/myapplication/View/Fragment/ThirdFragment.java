package com.example.myapplication.View.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.DB.DBmanager;
import com.example.myapplication.EventBus.BusEvent;
import com.example.myapplication.EventBus.BusProvider;
import com.example.myapplication.Model.MainModel;
import com.example.myapplication.Model.Plan;
import com.example.myapplication.Presenter.Contract;
import com.example.myapplication.Presenter.MainPresenter;
import com.example.myapplication.R;
import com.example.myapplication.View.Basic.BasicFragment;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.Observable;

public class ThirdFragment extends BasicFragment {
    private Context mContext;
    private Contract.Presenter presenter;
    private CheckBox checkMN2000191, checkMN2000194, checkMN2000195, checkMN2000196, checkMN2000197, checkMN2000198;
    private CheckBox customSet;
    private ProgressBar progressBar;
    private Boolean ok = true;
    private EditText planNameEdit, customURl;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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

        presenter = new MainPresenter();

        checkMN2000191 = view.findViewById(R.id.MN2000191);
        checkMN2000194 = view.findViewById(R.id.MN2000194);
        checkMN2000195 = view.findViewById(R.id.MN2000195);
        checkMN2000196 = view.findViewById(R.id.MN2000196);
        checkMN2000197 = view.findViewById(R.id.MN2000197);
        checkMN2000198 = view.findViewById(R.id.MN2000198);
        progressBar = view.findViewById(R.id.fetchData);

        customSet = view.findViewById(R.id.setCustom);
        customURl = view.findViewById(R.id.setCustomURL);

        planNameEdit = view.findViewById(R.id.editPlanName);

        if (ok) {
            progressBar.setVisibility(View.INVISIBLE);
        }

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

        view.findViewById(R.id.create_testPlan_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> subjectList = new ArrayList<String>();

                if (checkMN2000191.isChecked()) {
                    subjectList.add("MN2000191");
                }

                if (checkMN2000194.isChecked()) {
                    subjectList.add("MN2000194");
                }

                if (checkMN2000195.isChecked()) {
                    subjectList.add("MN2000195");
                }

                if (checkMN2000196.isChecked()) {
                    subjectList.add("MN2000196");
                }

                if (checkMN2000197.isChecked()) {
                    subjectList.add("MN2000197");
                }

                if (checkMN2000198.isChecked()) {
                    subjectList.add("MN2000198");
                }


                if (subjectList.size() == 0) {
                    Toast.makeText(mContext, "하나 이상 선택해주십시오", Toast.LENGTH_SHORT).show();
                } else {
                    ok = false;
                    MainModel mainModel = new MainModel();

                    Plan newPlan = new Plan();
                    newPlan.setSubjects(subjectList);
                    newPlan.setPlanName(planNameEdit.getText().toString());
                    newPlan.setPlanID();

                    if (customSet.isChecked()) {
                        newPlan.setCustom();
                        newPlan.setCustomURL(customURl.getText().toString());
                    }

                    mainModel.makeNewPlan(newPlan);
                }
            }
        });

        view.findViewById(R.id.crawler_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    MainModel mainModel = new MainModel();
                    Plan plan = new Plan();
                    plan = mainModel.getPlan().get(0);

                    progressBar.setVisibility(View.VISIBLE);

                    presenter.startFetchData(plan);
                }
        });
    }
}
