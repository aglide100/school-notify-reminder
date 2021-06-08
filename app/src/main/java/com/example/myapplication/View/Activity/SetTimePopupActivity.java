package com.example.myapplication.View.Activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.myapplication.CalendarAPI.CalendarAPI;
import com.example.myapplication.CalendarAPI.Exceptions.CalendarCantNotUseException;
import com.example.myapplication.CalendarAPI.Exceptions.CalendarNeedUpdateGoogleServiceException;
import com.example.myapplication.CalendarAPI.Exceptions.CalendarNetworkException;
import com.example.myapplication.CalendarAPI.Exceptions.CalendarNotYetFinishBringDataException;
import com.example.myapplication.CalendarAPI.Interfaces.CalenderResultInterface;
import com.example.myapplication.CalendarAPI.Models.CalendarActivityRequestCode;
import com.example.myapplication.CalendarAPI.Models.CalendarInputEvent;
import com.example.myapplication.CalendarAPI.Models.CalendarResponseData;
import com.example.myapplication.MyApplication;
import com.example.myapplication.R;

import java.util.Calendar;
import java.util.Date;

public class SetTimePopupActivity extends Activity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {
    private Button callDatepicker, callTimePickerStart, callTimePickerEnd, endUpSetting;

    int setYear, setMonth, setDayOfMonth;
    int setHourStart, setMinuteEnd, setHourEnd, setMinuteStart;
    String title;

    TextView showSetDate, showSetTime;
    EditText setDescription;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.time_popup_activity);

        Intent intent = getIntent();
        title = intent.getStringExtra("postTitle");

        callDatepicker = findViewById(R.id.call_datepicker);
        callTimePickerStart = findViewById(R.id.call_timepicker_start);
        callTimePickerEnd = findViewById(R.id.call_timepicker_end);
        endUpSetting = findViewById(R.id.end_up_time_setting);

        showSetDate = findViewById(R.id.show_set_day);
        showSetTime = findViewById(R.id.show_set_time);

        setDescription = findViewById(R.id.edit_calender_description);

        callTimePickerStart.setOnClickListener(this);
        callTimePickerEnd.setOnClickListener(this);
        callDatepicker.setOnClickListener(this);
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.call_datepicker:
                DatePickerDialog datePickerDialog = new DatePickerDialog(SetTimePopupActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        setYear = year;
                        setDayOfMonth = dayOfMonth;
                        setMonth = month;

                        showSetDate.setText(Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(dayOfMonth));
                    }
                }, 2021, 06, 1);
                datePickerDialog.show();
                break;
            case R.id.call_timepicker_start:
                TimePickerDialog timePickerDialogStart = new TimePickerDialog(SetTimePopupActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        setHourStart = hourOfDay;
                        setMinuteStart = minute;

                        showSetTime.setText(Integer.toString(setHourStart) + ":" + Integer.toString(setMinuteStart) + "--"+ Integer.toString(setHourEnd) + ":" + Integer.toString(setMinuteEnd));
                    }
                }, 11,11,true);
                timePickerDialogStart.show();

                break;
            case R.id.call_timepicker_end:
                TimePickerDialog timePickerDialogEnd = new TimePickerDialog(SetTimePopupActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        setHourEnd = hourOfDay;
                        setMinuteEnd = minute;

                        showSetTime.setText(Integer.toString(setHourStart) + ":" + Integer.toString(setMinuteStart) + "--"+ Integer.toString(setHourEnd) + ":" + Integer.toString(setMinuteEnd));
                    }
                }, 11,11,true);
                timePickerDialogEnd.show();

                break;
            case R.id.end_up_time_setting:

                try {
                    Calendar startTime = Calendar.getInstance();
                    Calendar endTime = Calendar.getInstance();
                    startTime.set(setYear, setMonth, setDayOfMonth, setHourStart, setMinuteStart);
                    endTime.set(setYear, setMonth, setDayOfMonth, setHourStart, setMinuteEnd);

                    long hour1 = 3600 * 1000;
                    CalendarAPI.getInstance().addEvent(this, new CalenderResultInterface() {
                        @Override
                        public void getResult(CalendarResponseData responseData) {
                            Toast.makeText(MyApplication.ApplicationContext(), "캘린더에 일정을 추가했습니다.", Toast.LENGTH_SHORT).show();
//                        Toast.makeText(MyApplication.ApplicationContext(), responseData.toString(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void failedWithActivityResult(CalendarActivityRequestCode reason) {
                            Toast.makeText(MyApplication.ApplicationContext(), "error : "+reason.getCode(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void permissionRevoked() {
                            Toast.makeText(MyApplication.ApplicationContext(), "권한이 없습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }, new CalendarInputEvent(title, "", setDescription.getText().toString(), startTime.getTime(), endTime.getTime()));
                } catch (CalendarNeedUpdateGoogleServiceException e) {
                    e.printStackTrace();
                } catch (CalendarCantNotUseException e) {
                    e.printStackTrace();
                } catch (CalendarNetworkException e) {
                    e.printStackTrace();
                } catch (CalendarNotYetFinishBringDataException e) {
                    e.printStackTrace();
                }

                break;
        }
    }


}
