package com.project.etsapi.mapper;

import com.project.etsapi.entity.Attend;
import com.project.etsapi.entity.Attendance;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttendMapper {

    /**
     * 课程添加一次考勤
     * @param attendance
     * @return
     */
    int addAttendance(Attendance attendance);

    /**
     * 根据课程id与考勤开始时间获得课程的一次考勤
     * @param course_ID
     * @param start_time
     * @return
     */
    Attendance getAttendance(String course_ID,String start_time);

    /**
     * 查询课程所有考勤
     * @param course_ID
     * @return
     */
    List<Attendance> getAttendanceListByCourseId(String course_ID);

    /**
     * 查询学生某门课的所有考勤记录
     * @param course_ID
     * @param student_ID
     * @return
     */
    List<Attend> getAttendList(String course_ID, String student_ID);

    /**
     * 添加考勤
     * @param attend
     * @return
     */
    int addAttend(Attend attend);

    /**
     * 查询一条考勤记录
     * @param attend
     * @return
     */
    Attend getAttend(Attend attend);
}
