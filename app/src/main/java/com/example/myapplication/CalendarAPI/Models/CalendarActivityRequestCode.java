package com.example.myapplication.CalendarAPI.Models;

public enum CalendarActivityRequestCode {
    REQUEST_ACCOUNT_PICKER(1000),
    REQUEST_AUTHORIZATION(1001);

    private final int code;
    public int getCode() {
        return code;
    }

    CalendarActivityRequestCode(int code) {
        this.code = code;
    }

    public static CalendarActivityRequestCode getRequestCode(int code) {
        for (CalendarActivityRequestCode requestCode : CalendarActivityRequestCode.values()) {
            if (requestCode.code == code) {
                return requestCode;
            }
        }
        return null;
    }
}
