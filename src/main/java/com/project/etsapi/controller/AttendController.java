package com.project.etsapi.controller;

import com.project.etsapi.entity.Attendance;
import com.project.etsapi.service.AttendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/attend")
public class AttendController {
    @Autowired
    AttendService attendService;

    /**
     * @description: 传入课程id与开始、结束时间新建考勤
     * @path: "/attend/addAttendance
     * @param: attendance
     * @return: java.lang.String
     * 返回1：成功
     * 返回-1：已存在这个考勤
     * @date: 2021/11/27 18:56
     */
    @PostMapping("/addAttendance")
    public String addAttendance(Attendance attendance){
        return String.valueOf(attendService.addAttendance(attendance));
    }

    /**
     * @description: 传入课程id查询该课程所有考勤
     * @path: "/attend/getAttendanceListByCourseId"
     * @param: course_ID
     * @return: java.util.List<com.project.etsapi.entity.Attendance>
     * @date: 2021/11/28 0:02
     */
    @GetMapping("/getAttendanceListByCourseId")
    public List<Attendance> getAttendanceListByCourseId(String course_ID){
        return attendService.getAttendanceListByCourseId(course_ID);
    }

    /**
     * @description: 传入课程id查询该课程所有考勤
     * @path: "/attend/getOnGoingAttendanceListByCourseId"
     * @param: course_ID
     * @return: java.util.List<com.project.etsapi.entity.Attendance>
     * @date: 2021/11/28 0:02
     */
    @GetMapping("/getOnGoingAttendanceListByCourseId")
    public List<Attendance> getOnGoingAttendanceListByCourseId(String course_ID){
        return attendService.getOnGoingAttendanceListByCourseId(course_ID);
    }
}
