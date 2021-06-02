package com.example.myapplication.CalendarAPI.Models;

public enum CalendarRequestCode {
    REQUEST_CREATE_CALENDAR("REQUEST_CREATE_CALENDAR"),
    REQUEST_GET_CALENDAR_ID("REQUEST_GET_CALENDAR_ID"),
    REQUEST_GET_EVENTS("REQUEST_GET_EVENTS"),
    REQUEST_ADD_EVENT("REQUEST_ADD_EVENT");

    private final String code;
    public String getCode() {
        return code;
    }

    CalendarRequestCode(String code) {
        this.code = code;
    }

    public static CalendarRequestCode getRequestCode(String code) {
        for (CalendarRequestCode requestCode : CalendarRequestCode.values()) {
            if (requestCode.code.equals(code)) {
                return requestCode;
            }
        }
        return null;
    }
}
