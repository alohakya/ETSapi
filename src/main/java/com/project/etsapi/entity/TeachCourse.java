package com.project.etsapi.entity;

import lombok.Data;

@Data
public class TeachCourse {
    private String teacher_ID;
    private String course_ID;

    public TeachCourse(String teacher_ID, String course_ID) {
        this.teacher_ID = teacher_ID;
        this.course_ID = course_ID;
    }

    public TeachCourse() {
    }

    public String getTeacher_ID() {
        return teacher_ID;
    }

    public void setTeacher_ID(String teacher_ID) {
        this.teacher_ID = teacher_ID;
    }

    public String getCourse_ID() {
        return course_ID;
    }

    public void setCourse_ID(String course_ID) {
        this.course_ID = course_ID;
    }
}
