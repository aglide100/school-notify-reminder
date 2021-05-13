package com.example.myapplication.EventBus;

public class BusEvent {
    boolean flag;

    public BusEvent(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }
}
