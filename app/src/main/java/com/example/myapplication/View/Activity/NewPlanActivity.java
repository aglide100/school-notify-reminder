package com.example.myapplication.View.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.LinearLayoutCompat;

import com.example.myapplication.Model.MainModel;
import com.example.myapplication.Model.Plan;
import com.example.myapplication.MyApplication;
import com.example.myapplication.Presenter.Contract;
import com.example.myapplication.Presenter.MainPresenter;
import com.example.myapplication.R;
import com.example.myapplication.View.Basic.BasicActivity;

import java.util.ArrayList;

import javax.security.auth.login.LoginException;

// 새로운 플랜을 생성하는 액티비티
public class NewPlanActivity extends BasicActivity implements View.OnClickListener {
    private int currentPage;
    private boolean isCustom;
    private int selectedSpinnerItem;

    LinearLayoutCompat firstPage, secondPage, thirdPage, customGroup, codeSelectGroup;

    Button increasePage, decreasePage, returnToList;
    View success_icon;
    Spinner spinner;
    EditText getPlanName, getCustomURL;

    CheckBox checkMN2000191, checkMN2000194, checkMN2000195, checkMN2000196, checkMN2000197, checkMN2000198;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newplan);

//        UI 관련
        firstPage = findViewById(R.id.newPlan_innerLayout_first);
        secondPage = findViewById(R.id.newPlan_innerLayout_second);
        thirdPage = findViewById(R.id.newPlan_innerLayout_third);
        customGroup = findViewById(R.id.set_customUrl_layout);
        codeSelectGroup = findViewById(R.id.codeSelectGroup);

        getPlanName = findViewById(R.id.edit_planName);
        getCustomURL = findViewById(R.id.edit_customURL);

        decreasePage = findViewById(R.id.button_previous);
        increasePage = findViewById(R.id.button_next);
        returnToList = findViewById(R.id.return_to_list);

//      채크박스
        checkMN2000191 = findViewById(R.id.MN2000191);
        checkMN2000194 = findViewById(R.id.MN2000194);
        checkMN2000195 = findViewById(R.id.MN2000195);
        checkMN2000196 = findViewById(R.id.MN2000196);
        checkMN2000197 = findViewById(R.id.MN2000197);
        checkMN2000198 = findViewById(R.id.MN2000198);

        success_icon = findViewById(R.id.success_icon);


        spinner = findViewById(R.id.spinner);

        // 기타 변수
        currentPage = 1;
        isCustom = false;

        String[] kind = getResources().getStringArray(R.array.spinnerList);
        ArrayAdapter adapter = new ArrayAdapter(getBaseContext(), R.layout.spinner_item, kind);

        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(adapter);

        decreasePage.setOnClickListener(this);
        increasePage.setOnClickListener(this);
        returnToList.setOnClickListener(this);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSpinnerItem = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(MyApplication.ApplicationContext(), "아이템을 선택해주십시오.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_next:
                if (currentPage == 1) {
                    boolean ok = false;

                    if (selectedSpinnerItem == 0) {
                        Toast.makeText(MyApplication.ApplicationContext(), "아이템을 선택해주십시오.", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    if (selectedSpinnerItem == 1) {
                        ok = true;
                    }

                    if (selectedSpinnerItem == 6) {
                        isCustom = true;
                        ok = true;
                    }

                    if (ok) {
                        // ok
                    } else {
                        Toast.makeText(MyApplication.ApplicationContext(), "아이템을 선택해주십시오.", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }


                if (currentPage == 2) {
                    boolean ok = false;

                    //체크박스에서 체크한 값 저장....
                    Plan newPlan = new Plan();
                    ArrayList<String> subjectList = new ArrayList<String>();

                    if (getPlanName.getText().toString().length() <= 0) {
                        Toast.makeText(MyApplication.ApplicationContext(), "한 글자 이상 입력해 주십시오.", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    if (isCustom) {
                        if (getCustomURL.getText().toString().length() <= 0) {
                            Toast.makeText(MyApplication.ApplicationContext(), "url을 입력해주십시오.", Toast.LENGTH_SHORT).show();
                            break;
                        }

                        ok = true;
                        newPlan.setCustom();
                        newPlan.setCustomURL(getCustomURL.getText().toString());
                        ArrayList<String> subjects= new ArrayList<>();
                        subjects.add("custom");
                        newPlan.setSubjects(subjects);

                    } else {
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

                        if (subjectList == null) {
                            Toast.makeText(MyApplication.ApplicationContext(), "체크박스를 하나 이상 선택해주세요.", Toast.LENGTH_SHORT).show();
                            break;
                        }

                        if (subjectList.size() == 0) {
                            Toast.makeText(MyApplication.ApplicationContext(), "체크박스를 하나 이상 선택해주세요.", Toast.LENGTH_SHORT).show();
                            break;
                        }

                        ok = true;
                        newPlan.setSubjects(subjectList);
                    }

                    if (ok == false) {
                        Log.e("NewPlan", "에러!!!");
                        break;
                    }

                    MainModel mainModel = new MainModel();

                    newPlan.setPlanName(getPlanName.getText().toString());
                    newPlan.setPlanID();

                    mainModel.makeNewPlan(newPlan);
                    Toast.makeText(MyApplication.ApplicationContext(), "생성이 완료됬습니다.", Toast.LENGTH_SHORT).show();
                    decreasePage.setVisibility(View.GONE);
                    increasePage.setVisibility(View.GONE);

                    if (mainModel.getPlan(newPlan.getPlanID()) == null) {
                        Toast.makeText(MyApplication.ApplicationContext(), "생성이 완료되지 않았습니다. 다시 시도해 주시겠습니까?", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                currentPage++;
                break;
            case R.id.button_previous:
                currentPage--;
                break;
            case R.id.return_to_list:
                finish();
                break;
            default:
                Toast.makeText(MyApplication.ApplicationContext(), "현재 개발중입니다!", Toast.LENGTH_SHORT).show();
                break;
        }

        if (currentPage <= 0) {
            finish();
        }

        if (currentPage == 1) {
            firstPage.setVisibility(View.VISIBLE);
            secondPage.setVisibility(View.GONE);
            thirdPage.setVisibility(View.GONE);
        }

        if (currentPage == 2) {
            firstPage.setVisibility(View.GONE);
            secondPage.setVisibility(View.VISIBLE);
//           커스텀 선택시

            if (isCustom) {
                codeSelectGroup.setVisibility(View.GONE);
                customGroup.setVisibility(View.VISIBLE);
            }
            thirdPage.setVisibility(View.GONE);
        }

        if (currentPage == 3) {
            firstPage.setVisibility(View.GONE);
            secondPage.setVisibility(View.GONE);
            thirdPage.setVisibility(View.VISIBLE);
        }

        if (currentPage == 4) {
            currentPage = 3;
        }


    }


}
