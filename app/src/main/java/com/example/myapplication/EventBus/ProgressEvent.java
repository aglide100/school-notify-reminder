package com.example.myapplication.EventBus;

public class ProgressEvent {
    String msg;
    boolean done = false;

    public Boolean getFished() {
        return done;
    }

    public void setStart() {
        this.done = false;
    }

    public void setFinished() {
        this.done = true;
    }

    public void setMessage(String msg) {
        this.msg = msg;
    }

    public String getMessage() {
        return this.msg;
    }
}
