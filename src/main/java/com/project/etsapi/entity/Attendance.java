package com.project.etsapi.entity;

import com.project.etsapi.vo.AttendInfo;
import lombok.Data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Attendance {
    private String course_ID;
    private String start_time;
    private String end_time;

    public Attendance(String course_ID,String start_time, String end_time) {
        this.course_ID = course_ID;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public Attendance() {
    }

    public AttendInfo toAttendInfo(String student_ID,Boolean type){
        AttendInfo attendInfo = new AttendInfo();
        attendInfo.setCourse_ID(this.course_ID);
        attendInfo.setStart_time(this.start_time);
        attendInfo.setEnd_time(this.end_time);
        attendInfo.setStudent_ID(student_ID);
        attendInfo.setAttend_type(type?"出勤":toBeAttended());
        return attendInfo;
    }

    //判断是否为待考勤
    public String toBeAttended(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date end_time = sdf.parse(this.end_time);
            return end_time.compareTo(new Date()) >= 0 ?"待考勤" : "缺勤";
        } catch (ParseException e) {
            e.printStackTrace();
            return "缺勤";
        }
    }

    public String getCourse_ID() {
        return course_ID;
    }

    public void setCourse_ID(String course_ID) {
        this.course_ID = course_ID;
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
}
