package com.project.etsapi.vo;

public class AttendInfo {
    private String course_ID;
    private String student_ID;
    private String start_time;
    private String end_time;
    private String attend_type;

    public String getStart_time() {
        return start_time;
    }

    public String getCourse_ID() {
        return course_ID;
    }

    public String getAttend_type() {
        return attend_type;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getStudent_ID() {
        return student_ID;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setCourse_ID(String course_ID) {
        this.course_ID = course_ID;
    }

    public void setAttend_type(String attend_type) {
        this.attend_type = attend_type;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public void setStudent_ID(String student_ID) {
        this.student_ID = student_ID;
    }
}
