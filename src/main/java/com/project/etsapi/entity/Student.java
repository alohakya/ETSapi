package com.project.etsapi.entity;


public class Student {
    private String student_ID;
    private String name;

    public Student(String student_ID, String name) {
        this.student_ID = student_ID;
        this.name = name;
    }

    public Student() {

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
}
