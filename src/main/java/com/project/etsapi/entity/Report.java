package com.project.etsapi.entity;

import lombok.Data;

@Data
public class Report {
    private String student_ID;
    private String project_name;
    private String path;
    private int score;
    private String submit_time;
    private String correct_time;

    public Report(String student_ID, String project_name, String path,
                  int score, String submit_time, String correct_time) {
        this.student_ID = student_ID;
        this.project_name = project_name;
        this.path = path;
        this.score = score;
        this.submit_time = submit_time;
        this.correct_time = correct_time;
    }

    public Report() {
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
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
