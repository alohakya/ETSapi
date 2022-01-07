package com.project.etsapi.entity;

import lombok.Data;

@Data
public class Teacher {
    private String teacher_ID;
    private String name;
    private String ID_number;

    public Teacher(String teacher_ID, String name) {
        this.teacher_ID = teacher_ID;
        this.name = name;
    }

    public Teacher() {
    }

    public String getID_number() {
        return ID_number;
    }

    public void setID_number(String ID_number) {
        this.ID_number = ID_number;
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
