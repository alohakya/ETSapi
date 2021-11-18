package com.project.etsapi.entity;

import lombok.Data;

@Data
public class Attendance {
    private String course_ID;
    private int number;
    private String start_time;
    private String end_time;
    private double percentage;

    public Attendance(String course_ID, int number, String start_time,
                      String end_time, double percentage) {
        this.course_ID = course_ID;
        this.number = number;
        this.start_time = start_time;
        this.end_time = end_time;
        this.percentage = percentage;
    }

    public Attendance() {
    }

    public String getCourse_ID() {
        return course_ID;
    }

    public void setCourse_ID(String course_ID) {
        this.course_ID = course_ID;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
