package com.example.myapplication.CalendarAPI.Interfaces;

import com.example.myapplication.CalendarAPI.Models.CalendarActivityRequestCode;
import com.example.myapplication.CalendarAPI.Models.CalendarResponseData;

public interface CalenderResultInterface {
    void getResult(CalendarResponseData responseData);
    void failedWithActivityResult(CalendarActivityRequestCode code);
    void permissionRevoked();
}
