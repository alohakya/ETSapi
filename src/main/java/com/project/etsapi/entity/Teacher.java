package com.project.etsapi.entity;

public class Teacher {
    private String teacher_ID;
    private String name;

    public Teacher(String teacher_ID, String name) {
        this.teacher_ID = teacher_ID;
        this.name = name;
    }

    public Teacher() {
    }

    public String getTeacher_ID() {
        return teacher_ID;
    }

    public void setTeacher_ID(String teacher_ID) {
        this.teacher_ID = teacher_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
