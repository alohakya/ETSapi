package com.project.etsapi.entity;

import lombok.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class File {
    private String course_ID;
    private String file_name;
    private String path;
    private String submit_time;

    //文件
    public File(String course_ID, String file_name, String path) {
        this.course_ID = course_ID;
        this.file_name = file_name;
        this.path = path;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.submit_time = df.format(new Date());
    }

    public String getCourse_ID() {
        return course_ID;
    }

    public void setCourse_ID(String course_ID) {
        this.course_ID = course_ID;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSubmit_time() {
        return submit_time;
    }

    public void setSubmit_time(String submit_time) {
        this.submit_time = submit_time;
    }

}
