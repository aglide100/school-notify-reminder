package com.example.myapplication.View.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Model.MainModel;
import com.example.myapplication.Model.Plan;
import com.example.myapplication.MyApplication;
import com.example.myapplication.Presenter.Contract;
import com.example.myapplication.Presenter.MainPresenter;
import com.example.myapplication.R;
import com.example.myapplication.View.Basic.BasicActivity;

import java.util.ArrayList;

// 새로운 플랜을 생성하는 액티비티
public class NewPlanActivity extends BasicActivity implements View.OnClickListener {
    private MainModel mainModel;
    private Contract.Presenter presenter;
    private Plan newPlan;
    Button button_search1, button_search2, button_search3, button_previous1, button_previous2, button_previous3, button_next1, button_next2, button_next3, button_list1;
    TextView textView1, textView2, textView3, textView4;
    View image1;
    Spinner spinner1;
    EditText editText1;
    CheckBox check1, check2, check3, check4, check5, check6, check7, check8, check9, check10, check11, check12;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newplan);

        // create new Object for plan
        newPlan = new Plan();
        presenter = new MainPresenter(this);
        mainModel = new MainModel(presenter);

//        UI 관련
        button_list1 = findViewById(R.id.button_list1);
        button_search2 = findViewById(R.id.button_search2);
        button_search3 = findViewById(R.id.button_search3);
        button_previous1 = findViewById(R.id.button_previous1);
        button_previous2 = findViewById(R.id.button_previous2);
        button_previous3 = findViewById(R.id.button_previous3);
        button_next1 = findViewById(R.id.button_next1);
        button_next2 = findViewById(R.id.button_next2);
        button_next3 = findViewById(R.id.button_next3);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        image1 = findViewById(R.id.image1);
        editText1 = findViewById(R.id.editText1);
        check1 = findViewById(R.id.check1);
        check2 = findViewById(R.id.check2);
        check3 = findViewById(R.id.check3);
        check4 = findViewById(R.id.check4);
        check5 = findViewById(R.id.check5);
        check6 = findViewById(R.id.check6);
        check7 = findViewById(R.id.check7);
        check8 = findViewById(R.id.check8);
        check9 = findViewById(R.id.check9);
        check10 = findViewById(R.id.check10);
        check11 = findViewById(R.id.check11);
        check12 = findViewById(R.id.check12);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        String[] kind1 = getResources().getStringArray(R.array.전공과목);
        ArrayAdapter adapter = new ArrayAdapter(getBaseContext(), R.layout.spinner_item, kind1);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner1.setAdapter(adapter);
        button_previous1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = spinner1.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_next1:
                textView2.setVisibility(View.VISIBLE);
                button_next1.setVisibility(View.GONE);
                button_next2.setVisibility(View.VISIBLE);
                button_previous1.setVisibility(View.GONE);
                button_previous2.setVisibility(View.VISIBLE);
                check1.setVisibility(View.VISIBLE);
                check2.setVisibility(View.VISIBLE);
                check3.setVisibility(View.VISIBLE);
                check4.setVisibility(View.VISIBLE);
                check5.setVisibility(View.VISIBLE);
                check6.setVisibility(View.VISIBLE);
                spinner1.setVisibility(View.GONE);
                break;
            case R.id.button_previous2:
                check1.setVisibility(View.GONE);
                check2.setVisibility(View.GONE);
                check3.setVisibility(View.GONE);
                check4.setVisibility(View.GONE);
                check5.setVisibility(View.GONE);
                check6.setVisibility(View.GONE);
                button_previous1.setVisibility(View.VISIBLE);
                button_next1.setVisibility(View.VISIBLE);
                button_previous2.setVisibility(View.GONE);
                button_next2.setVisibility(View.GONE);
                spinner1.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.GONE);
                break;
            case R.id.button_next2:
                check1.setVisibility(View.GONE);
                check2.setVisibility(View.GONE);
                check3.setVisibility(View.GONE);
                check4.setVisibility(View.GONE);
                check5.setVisibility(View.GONE);
                check6.setVisibility(View.GONE);
                check7.setVisibility(View.VISIBLE);
                check8.setVisibility(View.VISIBLE);
                check9.setVisibility(View.VISIBLE);
                check10.setVisibility(View.VISIBLE);
                check11.setVisibility(View.VISIBLE);
                check12.setVisibility(View.VISIBLE);
                button_next2.setVisibility(View.GONE);
                button_next3.setVisibility(View.VISIBLE);
                button_previous2.setVisibility(View.GONE);
                button_previous3.setVisibility(View.VISIBLE);
                textView3.setVisibility(View.VISIBLE);
                editText1.setVisibility(View.VISIBLE);
                spinner1.setVisibility(View.GONE);
                textView2.setVisibility(View.GONE);
                break;
            case R.id.button_previous3:
                check1.setVisibility(View.VISIBLE);
                check2.setVisibility(View.VISIBLE);
                check3.setVisibility(View.VISIBLE);
                check4.setVisibility(View.VISIBLE);
                check5.setVisibility(View.VISIBLE);
                check6.setVisibility(View.VISIBLE);
                check7.setVisibility(View.GONE);
                check8.setVisibility(View.GONE);
                check9.setVisibility(View.GONE);
                check10.setVisibility(View.GONE);
                check11.setVisibility(View.GONE);
                check12.setVisibility(View.GONE);
                textView3.setVisibility(View.VISIBLE);
                editText1.setVisibility(View.VISIBLE);
                button_next3.setVisibility(View.GONE);
                button_previous3.setVisibility(View.GONE);
                button_next2.setVisibility(View.VISIBLE);
                button_previous2.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.VISIBLE);
                textView3.setVisibility(View.GONE);
                editText1.setVisibility(View.GONE);
                break;
            case R.id.button_next3:
                textView3.setVisibility(View.GONE);
                editText1.setVisibility(View.GONE);
                image1.setVisibility(View.VISIBLE);
                textView4.setVisibility(View.VISIBLE);
                button_list1.setVisibility(View.VISIBLE);
                check7.setVisibility(View.GONE);
                check8.setVisibility(View.GONE);
                check9.setVisibility(View.GONE);
                check10.setVisibility(View.GONE);
                check11.setVisibility(View.GONE);
                check12.setVisibility(View.GONE);
                button_next3.setVisibility(View.GONE);
                button_previous3.setVisibility(View.GONE);
                spinner1.setVisibility(View.GONE);

                //체크박스에서 체크한 값 저장....
                ArrayList<String> subjectList = new ArrayList<String>();

                if (check7.isChecked()) {
                    subjectList.add("MN2000191");
                }

                if (check8.isChecked()) {
                    subjectList.add("MN2000194");
                }

                if (check9.isChecked()) {
                    subjectList.add("MN2000195");
                }

                if (check10.isChecked()) {
                    subjectList.add("MN2000196");
                }

                if (check11.isChecked()) {
                    subjectList.add("MN2000197");
                }
                if (check12.isChecked()) {
                    subjectList.add("MN2000198");
                } else {
                    if (editText1.getText().toString() == "") {
                        Toast.makeText(MyApplication.ApplicationContext(), "플랜 이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    if (subjectList.size() == 0) {
                        Toast.makeText(MyApplication.ApplicationContext(), "체크박스를 하나 이상 선택해주세요.", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    MainModel mainModel = new MainModel(presenter);

                    Plan newPlan = new Plan();

                    newPlan.setSubjects(subjectList);
                    newPlan.setPlanName(editText1.getText().toString());
                    newPlan.setPlanID();

                    mainModel.makeNewPlan(newPlan);
                }
                break;
            case R.id.button_list1:
                finish();
                break;
            default:
                Toast.makeText(MyApplication.ApplicationContext(), "현재 개발중입니다!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
