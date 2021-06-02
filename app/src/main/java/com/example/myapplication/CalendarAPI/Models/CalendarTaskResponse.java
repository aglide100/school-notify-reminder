package com.example.myapplication.CalendarAPI.Models;

public enum CalendarTaskResponse {
    RESULT_ALREADY_EXIST("RESULT_ALREADY_EXIST"),
    RESULT_OK("RESULT_OK"),
    RESULT_FAILED("RESULT_FAILED");

    private final String code;
    public String getCode() {
        return code;
    }

    CalendarTaskResponse(String code) {
        this.code = code;
    }

    public static CalendarTaskResponse getRequestCode(String code) {
        for (CalendarTaskResponse requestCode : CalendarTaskResponse.values()) {
            if (requestCode.code.equals(code)) {
                return requestCode;
            }
        }
        return null;
    }
}
