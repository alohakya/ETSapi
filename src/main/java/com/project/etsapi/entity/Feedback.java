package com.project.etsapi.entity;

import lombok.Data;

@Data
public class Feedback {
    private String course_ID;
    private String student_ID;
    private String submit_time;
    private String content;

    public Feedback(String course_ID, String student_ID, String submit_time, String content) {
        this.course_ID = course_ID;
        this.student_ID = student_ID;
        this.submit_time = submit_time;
        this.content = content;
    }

    public Feedback() {
    }

    public String getCourse_ID() {
        return course_ID;
    }

    public void setCourse_ID(String course_ID) {
        this.course_ID = course_ID;
    }

    public String getStudent_ID() {
        return student_ID;
    }

    public void setStudent_ID(String student_ID) {
        this.student_ID = student_ID;
    }

    public String getSubmit_time() {
        return submit_time;
    }

    public void setSubmit_time(String submit_time) {
        this.submit_time = submit_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
