package com.project.etsapi.vo;

public class ProjectScoreInfo {
    private String project_name;
    private Double avg_score;
    private Double max_score;
    private Double min_score;

    public String getProject_name() {
        return project_name;
    }

    public Double getAvg_score() {
        return avg_score;
    }

    public Double getMax_score() {
        return max_score;
    }

    public Double getMin_score() {
        return min_score;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public void setAvg_score(Double avg_score) {
        this.avg_score = avg_score;
    }

    public void setMax_score(Double max_score) {
        this.max_score = max_score;
    }

    public void setMin_score(Double min_score) {
        this.min_score = min_score;
    }
}
