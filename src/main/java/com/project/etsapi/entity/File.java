package com.project.etsapi.entity;

import lombok.Data;

@Data
public class File {
    private String course_ID;
    private int number;
    private String name;
    private String path;
    private String submit_time;
    private String project_ID;

    public File(String course_ID, int number, String name,
                String path, String submit_time, String project_ID) {
        this.course_ID = course_ID;
        this.number = number;
        this.name = name;
        this.path = path;
        this.submit_time = submit_time;
        this.project_ID = project_ID;
    }

    public File() {
    }

    public String getCourse_ID() {
        return course_ID;
    }

    public void setCourse_ID(String course_ID) {
        this.course_ID = course_ID;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSubmit_time() {
        return submit_time;
    }

    public void setSubmit_time(String submit_time) {
        this.submit_time = submit_time;
    }

    public String getProject_ID() {
        return project_ID;
    }

    public void setProject_ID(String project_ID) {
        this.project_ID = project_ID;
    }
}
