package com.project.etsapi.vo;

import lombok.Data;

/**
 * @Created by Intellij IDEA
 * @Description
 * @Author Li Lijun
 * @Date 2021/11/19
 * @Time 18:50
 **/

@Data
public class StudentInfo {
    private String student_ID;
    private String name;
    private String email;

    public StudentInfo() {
    }

    public StudentInfo(String student_ID, String name, String email) {
        this.student_ID = student_ID;
        this.name = name;
        this.email = email;
    }
}
