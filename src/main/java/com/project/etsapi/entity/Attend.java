package com.project.etsapi.entity;

import lombok.Data;

@Data
public class Attend {
    private String course_ID;
    private String start_time;
    private String student_ID;
    private String attend_time;

    public Attend(String course_ID, String start_time, String student_ID, String attend_time) {
        this.course_ID = course_ID;
        this.start_time = start_time;
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

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
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
