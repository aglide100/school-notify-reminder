package com.example.myapplication.View.Activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.myapplication.MyApplication;
import com.example.myapplication.R;

public class SetTimePopupActivity extends Activity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {
    private Button callDatepicker, callTimePicker, endUpSetting;

    int setYear, setMonth, setDayOfMonth;
    String time;

    private DatePickerDialog.OnDateSetListener callbackMethod;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.time_popup_activity);

        callDatepicker = findViewById(R.id.call_datepicker);
        callTimePicker = findViewById(R.id.call_timepicker);
        endUpSetting = findViewById(R.id.end_up_time_setting);

        callTimePicker.setOnClickListener(this);
        callDatepicker.setOnClickListener(this);
        endUpSetting.setOnClickListener(this);

        this.InitializeListener();
    }

    public void InitializeListener() {
        callbackMethod = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            }
        };


    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);

        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
            return false;
        }
        return true;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.call_datepicker:
                DatePickerDialog dialog = new DatePickerDialog(MyApplication.ApplicationContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        setYear = year;
                        setDayOfMonth = dayOfMonth;
                        setMonth = month;
                    }
                }, 2021, 06, 1);
                dialog.show();
                break;
            case R.id.call_timepicker:

                break;
            case R.id.end_up_time_setting:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        return;
//        super.onBackPressed();
    }
}
