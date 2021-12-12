package com.project.etsapi.vo;

public class ScoreInfo {
    private String student_ID;
    private String name;
    private Double project_score;
    private Double attend_score;

    public ScoreInfo(){

    }

    public void setStudent_ID(String student_ID) {
        this.student_ID = student_ID;
    }

    public void setAttend_score(Double attend_score) {
        this.attend_score = attend_score;
    }

    public void setProject_score(Double project_score) {
        this.project_score = project_score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudent_ID() {
        return student_ID;
    }

    public Double getAttend_score() {
        return attend_score;
    }

    public Double getProject_score() {
        return project_score;
    }

    public String getName() {
        return name;
    }
}
