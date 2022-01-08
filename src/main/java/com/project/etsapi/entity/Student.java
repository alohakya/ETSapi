package com.project.etsapi.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable {
    private String student_ID;
    private String name;
    private String ID_number;

    public Student() {

    }

    public Student(String student_ID, String name, String ID_number) {
        this.student_ID = student_ID;
        this.name = name;
        this.ID_number = ID_number;
    }

    public String getID_number() {
        return ID_number;
    }

    public void setID_number(String ID_number) {
        this.ID_number = ID_number;
    }

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

    @Override
    public String toString() {
        return "Student{" +
                "student_ID='" + student_ID + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
