package com.project.etsapi.vo;

/**
 * @Created by Intellij IDEA
 * @Description
 * @Author Li Lijun
 * @Date 2021/11/19
 * @Time 18:50
 **/


public class StudentEmail {
    private String student_ID;
    private String name;
    private String email;

    public StudentEmail() {
    }

    public StudentEmail(String student_ID, String name, String email) {
        this.student_ID = student_ID;
        this.name = name;
        this.email = email;
    }
}
