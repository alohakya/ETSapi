package com.project.etsapi.vo;

import lombok.Data;

/**
 * @Created by Intellij IDEA
 * @Description
 * @Author Li Lijun
 * @Date 2021/11/29
 * @Time 19:18
 **/

@Data
public class ProjectInfo {
    private String name;
    private String course_ID;
    private String start_time;
    private String end_time;
    private String description;
    private String teacher_name;
    private int path_number;
    private double percentage;

    public ProjectInfo() {

    }
}
