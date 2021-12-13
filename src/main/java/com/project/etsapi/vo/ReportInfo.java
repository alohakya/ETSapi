package com.project.etsapi.vo;

public class ReportInfo {
    private String student_ID;

    public String getStudent_ID() {
        return student_ID;
    }

    public void setStudent_ID(String student_ID) {
        this.student_ID = student_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    private String name;
    private String submit_time;
    private String correct_time;
    private String score;

    public ReportInfo(){

    }


}
