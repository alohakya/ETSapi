package com.project.etsapi.entity;

public class Attend {
    private String course_ID;
    private int number;
    private String student_ID;
    private String attend_time;

    public Attend(String course_ID, int number, String student_ID, String attend_time) {
        this.course_ID = course_ID;
        this.number = number;
        this.student_ID = student_ID;
        this.attend_time = attend_time;
    }

    public Attend() {
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

    public String getStudent_ID() {
        return student_ID;
    }

    public void setStudent_ID(String student_ID) {
        this.student_ID = student_ID;
    }

    public String getAttend_time() {
        return attend_time;
    }

    public void setAttend_time(String attend_time) {
        this.attend_time = attend_time;
    }
}
