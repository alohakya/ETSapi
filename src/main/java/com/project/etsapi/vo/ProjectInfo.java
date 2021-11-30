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
    private String project_ID;
    private String name;
    private String start_time;
//    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//注意月和小时的格式为两个大写字母
//    java.util.Date date = new Date();//获得当前时间
//    String birthday = df.format(date);//将当前时间转换成特定格式的时间字符串，这样便可以插入到数据库中
    private String end_time;
    private String description;
    private int path_number;
    private String teacher_name;
    private String course_ID;
    private double percentage;

    public ProjectInfo() {

    }

    public ProjectInfo(String project_ID, String name, String start_time, String end_time, String description, int path_number, String teacher_name, String course_ID, double percentage) {
        this.project_ID = project_ID;
        this.name = name;
        this.start_time = start_time;
        this.end_time = end_time;
        this.description = description;
        this.path_number = path_number;
        this.teacher_name = teacher_name;
        this.course_ID = course_ID;
        this.percentage = percentage;
    }
}
