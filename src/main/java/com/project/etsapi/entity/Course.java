package com.project.etsapi.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Course implements Serializable {
    private String course_ID;
    private String name;
    private String teacher_ID;
    private String description;
    private double attend_percentage;
    private double project_percentage;
    private String is_active;


    public Course() {

    }

    public Course(String course_ID, String name, String teacher_ID, String description, double attend_percentage, double project_percentage) {
        this.course_ID = course_ID;
        this.name = name;
        this.teacher_ID = teacher_ID;
        this.description = description;
        this.attend_percentage = attend_percentage;
        this.project_percentage = project_percentage;
        this.is_active = "1";
    }

    public String getCourse_ID() {
        return course_ID;
    }

    public void setCourse_ID(String course_ID) {
        this.course_ID = course_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher_ID() {
        return teacher_ID;
    }

    public void setTeacher_ID(String teacher_ID) {
        this.teacher_ID = teacher_ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAttend_percentage() {
        return attend_percentage;
    }

    public void setAttend_percentage(double attend_percentage) {
        this.attend_percentage = attend_percentage;
    }

    public double getProject_percentage() {
        return project_percentage;
    }

    public void setProject_percentage(double project_percentage) {
        this.project_percentage = project_percentage;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }
}
