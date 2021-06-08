package com.example.myapplication.View.Activity;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.annotation.Nullable;

import com.example.myapplication.R;

public class SetTimePopupActivity extends Activity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {
    private Button callCalender, callTimePicker, endUpSetting;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.time_popup_activity);

        callCalender = findViewById(R.id.call_calender);
        callTimePicker = findViewById(R.id.call_timepicker);
        endUpSetting = findViewById(R.id.end_up_time_setting);

        callTimePicker.setOnClickListener(this);
        callCalender.setOnClickListener(this);
        endUpSetting.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.call_calender:
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
