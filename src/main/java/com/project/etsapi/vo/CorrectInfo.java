package com.project.etsapi.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CorrectInfo {
    private String course_ID;
    private String project_name;
    private String student_ID;
    private Integer score;
    private String correct_time;

    public CorrectInfo() {
    }

    public CorrectInfo(String course_ID, String project_name, String student_ID, Integer score) {
        this.course_ID = course_ID;
        this.project_name = project_name;
        this.student_ID = student_ID;
        this.score = score;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.correct_time = df.format(new Date());
    }

    public String getCourse_ID() {
        return course_ID;
    }

    public void setCourse_ID(String course_ID) {
        this.course_ID = course_ID;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getStudent_ID() {
        return student_ID;
    }

    public void setStudent_ID(String student_ID) {
        this.student_ID = student_ID;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getCorrect_time() {
        return correct_time;
    }

    public void setCorrect_time(String correct_time) {
        this.correct_time = correct_time;
    }
}
