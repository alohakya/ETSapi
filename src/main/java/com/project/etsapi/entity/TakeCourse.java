package com.project.etsapi.entity;

import lombok.Data;

@Data
public class TakeCourse {
    private String student_ID;
    private String course_ID;
    private String authority;

    public TakeCourse(String student_ID, String course_ID, String authority) {
        this.student_ID = student_ID;
        this.course_ID = course_ID;
        this.authority = authority;
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


    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
