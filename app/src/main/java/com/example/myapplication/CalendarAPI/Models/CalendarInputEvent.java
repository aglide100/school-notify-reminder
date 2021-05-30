package com.example.myapplication.CalendarAPI.Models;

import java.time.LocalDateTime;
import java.util.Date;

public class CalendarInputEvent {
    private String title;
    private String location;
    private String description;
    private Date startTime;
    private Date endTime;

    public CalendarInputEvent(String title, String location, String description, Date startTime, Date endTime) {
        this.title = title;
        this.location = location;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
