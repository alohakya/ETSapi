package com.project.etsapi.service;

import com.project.etsapi.entity.Attendance;
import com.project.etsapi.vo.AttendInfo;

import java.util.List;

public interface AttendService {
    //增加
    int addAttendance(Attendance attendance);

    //获得指定课程指定考勤的详细信息
    List<Attendance> getAttendanceListByCourseId(String course_ID);

    List<Attendance> getOnGoingAttendanceListByCourseId(String course_ID);

    List<AttendInfo> getAttendInfoList(String course_ID, String student_ID);

//    //获得考勤列表
//    List<Attend> getAttendListByCourseId(String course_ID);
//

//
//    //获得指定学生指定课程的考勤信息
//    List<Attendance> getAttendanceListByStudent();
}
