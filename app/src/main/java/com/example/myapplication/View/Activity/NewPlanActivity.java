package com.example.myapplication.View.Activity;

import android.os.Bundle;

import com.example.myapplication.Model.MainModel;
import com.example.myapplication.Model.Plan;
import com.example.myapplication.Presenter.Contract;
import com.example.myapplication.View.Basic.BasicActivity;

// 새로운 플랜을 생성하는 액티비티
public class NewPlanActivity extends BasicActivity {
    private MainModel mainModel;
    private Contract.Presenter presenter;
    private Plan newPlan;

    //  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        newPlan.setPlanID();
        newPlan.setPlanName("플랜이름");

        mainModel.makeNewPlan(newPlan);
    }
}
