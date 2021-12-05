package com.project.etsapi.entity;

import com.project.etsapi.vo.ProjectInfo;
import lombok.Data;

@Data
public class Project {
    private String course_ID;
    private String name;
    private String start_time;
    private String end_time;
    private String description;
    private String teacher_ID;
    private int path_number;
    private double percentage;
//    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//注意月和小时的格式为两个大写字母
//    java.util.Date date = new Date();//获得当前时间
//    String birthday = df.format(date);//将当前时间转换成特定格式的时间字符串，这样便可以插入到数据库中

    public Project() {

    }

    public Project(String course_ID,String name, String start_time, String end_time,
                   String description, int path_number, String teacher_ID, double percentage) {
        this.course_ID = course_ID;
        this.name = name;
        this.start_time = start_time;
        this.end_time = end_time;
        this.description = description;
        this.path_number = path_number;
        this.teacher_ID = teacher_ID;
        this.percentage = percentage;
    }

    public ProjectInfo projectToProjectInfo(String teacherName){
        ProjectInfo projectInfo= new ProjectInfo();
        projectInfo.setName(this.name);
        projectInfo.setCourse_ID(this.getCourse_ID());
        projectInfo.setTeacher_name(teacherName);
        projectInfo.setPercentage(this.percentage);
        projectInfo.setPath_number(this.path_number);
        projectInfo.setStart_time(this.start_time);
        projectInfo.setEnd_time(this.end_time);
        return projectInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPath_number() {
        return path_number;
    }

    public void setPath_number(int path_number) {
        this.path_number = path_number;
    }

    public String getTeacher_ID() {
        return teacher_ID;
    }

    public void setTeacher_ID(String teacher_ID) {
        this.teacher_ID = teacher_ID;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public String getCourse_ID() {
        return course_ID;
    }

    public void setCourse_ID(String course_ID) {
        this.course_ID = course_ID;
    }
}
