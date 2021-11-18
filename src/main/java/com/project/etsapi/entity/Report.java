package com.project.etsapi.entity;

import lombok.Data;

@Data
public class Report {
    private String student_ID;
    private String project_ID;
    private String path;
    private int score;
    private String submit_time;
    private String correct_time;

    public Report(String student_ID, String project_ID, String path,
                  int score, String submit_time, String correct_time) {
        this.student_ID = student_ID;
        this.project_ID = project_ID;
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

    public String getProject_ID() {
        return project_ID;
    }

    public void setProject_ID(String project_ID) {
        this.project_ID = project_ID;
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
