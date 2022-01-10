package com.project.etsapi.vo;

import com.project.etsapi.entity.Report;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportTemplate {
    private String course_ID;
    private String student_ID;
    private String project_name;
    private String purpose;
    private String principle;
    private String device;
    private String steps;
    private String conclusion;
    private String isSubmit;

    public ReportTemplate() {

    }

    public ReportTemplate(String course_ID, String student_ID, String project_name) {
        this.course_ID = course_ID;
        this.student_ID = student_ID;
        this.project_name = project_name;
        this.purpose = "";
        this.principle = "";
        this.device = "";
        this.steps = "";
        this.conclusion = "";
        this.isSubmit = "0";
    }

    public String getIsSubmit() {
        return isSubmit;
    }

    public void setIsSubmit(String isSubmit) {
        this.isSubmit = isSubmit;
    }

    public String getCourse_ID() {
        return course_ID;
    }

    public void setCourse_ID(String course_ID) {
        this.course_ID = course_ID;
    }

    public String getStudent_ID() {
        return student_ID;
    }

    public void setStudent_ID(String student_ID) {
        this.student_ID = student_ID;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getPrinciple() {
        return principle;
    }

    public void setPrinciple(String principle) {
        this.principle = principle;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public void setContent(String content) {
        String[] tmp = content.split("\n\n0000000000\n\n");
        this.purpose = tmp.length == 5?tmp[0]:"";
        this.principle = tmp.length == 5?tmp[1]:"";
        this.device = tmp.length == 5?tmp[2]:"";
        this.steps = tmp.length == 5?tmp[3]:"";
        this.conclusion = tmp.length == 5?tmp[4]:"";
    }

    @Override
    public String toString() {
        return (this.purpose.equals("") ?" ":this.purpose) + "\n\n0000000000\n\n" +
                (this.principle.equals("")?" ":this.principle) + "\n\n0000000000\n\n" +
                (this.device.equals("")?" ":this.device) + "\n\n0000000000\n\n" +
                (this.steps.equals("")?" ":this.steps) + "\n\n0000000000\n\n" +
                (this.conclusion.equals("")?" ":this.conclusion);
    }

    public Report toReport() {
        Report result = new Report();
        result.setCourse_ID(this.course_ID);
        result.setStudent_ID(this.student_ID);
        result.setProject_name(this.project_name);
        result.setReport_name(this.student_ID + "_" +this.project_name);
        result.setScore(0);
        result.setCorrect_time(null);
        if(this.isSubmit.equals("1")){
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            result.setSubmit_time(df.format(new Date()));
        }
        else{
            result.setSubmit_time(null);
        }
        return result;
    }

}
