package com.project.mybatis.entity;

public class Course {
    private String courseId;

    private String name;

    private String teacherId;

    private Double attendPercentage;

    private Double projectPercentage;

    private String description;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
    }

    public Double getAttendPercentage() {
        return attendPercentage;
    }

    public void setAttendPercentage(Double attendPercentage) {
        this.attendPercentage = attendPercentage;
    }

    public Double getProjectPercentage() {
        return projectPercentage;
    }

    public void setProjectPercentage(Double projectPercentage) {
        this.projectPercentage = projectPercentage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}