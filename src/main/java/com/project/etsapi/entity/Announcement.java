package com.project.etsapi.entity;

import lombok.Data;

@Data
public class Announcement {
    private String course_ID;
    private String name;
    private String teacher_ID;
    private String content;
    private String release_time;

    public Announcement(String course_ID, String name, String
            teacher_ID, String content, String release_time) {
        this.course_ID = course_ID;
        this.name = name;
        this.teacher_ID = teacher_ID;
        this.content = content;
        this.release_time = release_time;
    }

    public Announcement() {
    }

    public String getCourse_ID() {
        return course_ID;
    }

    public void setCourse_ID(String course_ID) {
        this.course_ID = course_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher_ID() {
        return teacher_ID;
    }

    public void setTeacher_ID(String teacher_ID) {
        this.teacher_ID = teacher_ID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRelease_time() {
        return release_time;
    }

    public void setRelease_time(String release_time) {
        this.release_time = release_time;
    }
}
