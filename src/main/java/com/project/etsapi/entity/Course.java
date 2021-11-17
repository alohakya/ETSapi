package com.project.etsapi.entity;

import lombok.*;

import java.io.Serializable;

@Data
public class Course implements Serializable {
    private String course_ID;
    private String name;
    private String teacher_ID;
    private String description;


    public Course() {

    }

    public Course(String course_ID, String name, String teacher_ID, String description) {
        this.course_ID = course_ID;
        this.name = name;
        this.teacher_ID = teacher_ID;
        this.description = description;
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
}
