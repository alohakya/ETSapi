package com.project.etsapi.vo;

import lombok.Data;

/**
 * @Created by Intellij IDEA
 * @Description
 * @Author Li Lijun
 * @Date 2021/11/19
 * @Time 20:28
 **/

@Data
public class TeacherInfo {
    private String teacher_ID;
    private String name;
    private String email;

    public TeacherInfo() {
    }

    public TeacherInfo(String teacher_ID, String name, String email) {
        this.teacher_ID = teacher_ID;
        this.name = name;
        this.email = email;
    }
}
