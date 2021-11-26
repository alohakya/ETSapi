package com.project.etsapi.entity;

import lombok.Data;

@Data
public class TakeCourse {
    private String student_ID;
    private String course_ID;
    private String is_student;

    public TakeCourse(String student_ID, String course_ID, String is_student) {
        this.student_ID = student_ID;
        this.course_ID = course_ID;
        this.is_student = is_student;
    }

    public TakeCourse() {

    }

    public String getStudent_ID() {
        return student_ID;
    }

    public void setStudent_ID(String student_ID) {
        this.student_ID = student_ID;
    }

    public String getCourse_ID() {
        return course_ID;
    }

    public void setCourse_ID(String course_ID) {
        this.course_ID = course_ID;
    }


    public String getIs_student() {
        return is_student;
    }

    public void setIs_student(String is_student) {
        this.is_student = is_student;
    }
}
