package com.project.etsapi.entity;

import lombok.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Report {
    private String course_ID;
    private String project_name;
    private String student_ID;
    private String report_name;
    private Double score;
    private String submit_time;
    private String correct_time;

    public Report(String course_ID, String project_name,
                  String student_ID, String report_name,
                  Double score, String submit_time, String correct_time) {
        this.course_ID = course_ID;
        this.project_name = project_name;
        this.student_ID = student_ID;
        this.report_name = report_name;
        this.score = score;
        this.submit_time = submit_time;
        this.correct_time = correct_time;
    }

    public Report() {
    }

    public Report(String course_ID, String student_ID, String project_name,String report_name) {
        this.course_ID = course_ID;
        this.student_ID = student_ID;
        this.project_name = project_name;
        this.report_name = report_name;
        this.score = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.submit_time = df.format(new Date());
        this.correct_time = null;
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

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getReport_name() {
        return report_name;
    }

    public void setReport_name(String report_name) {
        this.report_name = report_name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getSubmit_time() {
        return submit_time;
    }

    public void setSubmit_time(String submit_time) {
        this.submit_time = submit_time;
    }

    public String getCorrect_time() {
        return correct_time;
    }

    public void setCorrect_time(String correct_time) {
        this.correct_time = correct_time;
    }
}
