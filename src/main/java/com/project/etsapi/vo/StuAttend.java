package com.project.etsapi.vo;

public class StuAttend {
    private String student_ID;
    private String name;
    private String attend_type;

    public StuAttend() {
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

    public String getAttend_type() {
        return attend_type;
    }

    public void setAttend_type(String attend_type) {
        this.attend_type = attend_type;
    }
}
