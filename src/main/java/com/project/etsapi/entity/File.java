package com.project.etsapi.entity;

import com.project.etsapi.vo.FileInfo;
import lombok.Data;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class File {
    private String course_ID;
    private String file_name;
    private String path;
    private String submit_time;
    private double file_size;

    public File(){

    }

    //文件
    public File(String course_ID, String file_name, String path,double file_size) {
        this.course_ID = course_ID;
        this.file_name = file_name;
        this.path = path;
        this.file_size = new BigDecimal(file_size / 1024.0).setScale(1, BigDecimal.ROUND_UP).doubleValue();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.submit_time = df.format(new Date());
    }

    public File(String course_ID, String name, String path) {
        this.course_ID = course_ID;
        this.file_name = file_name;
        this.path = path;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.submit_time = df.format(new Date());
    }

    public FileInfo toFileInfo() {
        FileInfo result = new FileInfo();
        result.setFile_name(this.file_name);
        result.setSubmit_time(this.submit_time);
        result.setFile_size(this.file_size == 0.0 ? null:String.valueOf(this.file_size) + " KB");
        return result;
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

    public double getFile_size() {
        return file_size;
    }

    public void setFile_size(double file_size) {
        this.file_size = file_size;
    }


}
