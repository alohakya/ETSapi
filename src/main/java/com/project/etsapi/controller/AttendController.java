package com.project.etsapi.controller;

import com.project.etsapi.entity.Attend;
import com.project.etsapi.entity.Attendance;
import com.project.etsapi.service.AttendService;
import com.project.etsapi.vo.AttendInfo;
import com.project.etsapi.vo.AttendanceInfo;
import com.project.etsapi.vo.StuAttend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * @description: 获得一个学生在一门课程中的考勤信息
     * @path: "/attend/getAttendInfoList"
     * @type: get
     * @param: course_ID
     * @param: student_ID
     * @return: java.util.List<com.project.etsapi.vo.AttendInfo>
     * @date: 2021/12/8 23:39
     */
    @GetMapping("/getAttendInfoList")
    public List<AttendInfo> getAttendInfoList(String course_ID, String student_ID){
        return attendService.getAttendInfoList(course_ID,student_ID);
    }

    /**
     * @description: 学生考勤
     * @path: "/attend/addAttend"
     * @type: post
     * @param: attend
     * @return: java.lang.String
     * 返回1：成功
     * 返回-1：已考勤
     * 返回-2：数据库操作失败，可能course_ID 或 start_time 或 student_ID不存在
     * @date: 2021/12/8 23:26
     */
    @PostMapping("/addAttend")
    public String addAttend(Attend attend){
        try {
            System.out.println(attend.getCourse_ID());
            System.out.println(attend.getStart_time());
            System.out.println(attend.getStudent_ID());
            return String.valueOf(attendService.addAttend(attend));
        } catch (Exception e) {
            return "-2";
        }
    }


    /**
     * @description: 获得课程所有考勤的出勤信息
     * @path:
     * @type:
     * @param: course_ID
     * @return: java.util.List<com.project.etsapi.vo.AttendanceInfo>
     * @date: 2021/12/30 14:15
     */
    @GetMapping("/getAttendanceInfoList")
    public List<AttendanceInfo> getAttendanceInfoList(@RequestParam("course_ID") String course_ID){
        return attendService.getAttendanceInfoList(course_ID);
    }


    /**
     * @description: 获得指定考勤的所有学生出勤列表
     * @path:
     * @type:
     * @param: course_ID
     * @param: start_time
     * @return: java.util.List<com.project.etsapi.vo.StuAttend>
     * @date: 2021/12/30 14:15
     */
    @GetMapping("/getStuAttendList")
    public List<StuAttend> getStuAttendList(String course_ID, String start_time){
        return attendService.getStuAttendList(course_ID,start_time);
    }
}
