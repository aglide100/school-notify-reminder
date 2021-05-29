package com.example.myapplication.Model;

public class ErrorModel {
    private String code;
    private int page;

    public ErrorModel(String code, int page) {
        this.code = code;
        this.page = page;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
