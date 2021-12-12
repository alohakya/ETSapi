package com.project.etsapi.entity;

import lombok.Data;

@Data
public class TakeCourse {
    private String student_ID;
    private String course_ID;
    private String is_student;
    private String project_score;
    private String attend_score;

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

    public String getAttend_score() {
        return attend_score;
    }

    public String getProject_score() {
        return project_score;
    }

    public void setProject_score(String project_score) {
        this.project_score = project_score;
    }

    public void setAttend_score(String attend_score) {
        this.attend_score = attend_score;
    }
}
