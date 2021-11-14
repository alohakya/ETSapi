package com.project.etsapi.entity;

public class Project {
    private String project_ID;
    private String name;
    private String release_time;
    private String deadline;
    private String description;
    private int path_number;
    private String teacher_ID;
    private double percentage;

    public Project() {

    }


    public Project(String project_ID, String name, String release_time,
                   String deadline, String description, int path_number,
                   String teacher_ID, double percentage) {
        this.project_ID = project_ID;
        this.name = name;
        this.release_time = release_time;
        this.deadline = deadline;
        this.description = description;
        this.path_number = path_number;
        this.teacher_ID = teacher_ID;
        this.percentage = percentage;
    }

    public String getProject_ID() {
        return project_ID;
    }

    public void setProject_ID(String project_ID) {
        this.project_ID = project_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelease_time() {
        return release_time;
    }

    public void setRelease_time(String release_time) {
        this.release_time = release_time;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPath_number() {
        return path_number;
    }

    public void setPath_number(int path_number) {
        this.path_number = path_number;
    }

    public String getTeacher_ID() {
        return teacher_ID;
    }

    public void setTeacher_ID(String teacher_ID) {
        this.teacher_ID = teacher_ID;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
