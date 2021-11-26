package com.project.etsapi.controller;

import com.project.etsapi.entity.TeachCourse;
import com.project.etsapi.entity.Teacher;
import com.project.etsapi.service.TeachCourseService;
import com.project.etsapi.vo.TeacherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teach")
public class TeachCourseController {
    @Autowired
    TeachCourseService teachCourseService;

    /**
     * @description: 添加一位老师到一个课程
     * @path: "/teach/addTeachCourse"
     * @param: teachCourse 包含teacher_ID 与 course_ID
     * @return: java.lang.String
     * 返回1：成功
     * 返回-1：老师id不存在
     * 返回-2：课程id不存在
     * 返回-3：课程中已有这个老师
     * @date: 2021/11/26 15:11
     */
    @PostMapping( "/addTeachCourse")
    public String addTeachCourse(TeachCourse teachCourse) {
        return String.valueOf(teachCourseService.addTeachCourse(teachCourse));
    }

    /**
     * @description: 根据课程id获得教师列表
     * @path: "/teach/getTeacherList"
     * @param: course_ID 课程id
     * @return: java.util.List<com.project.etsapi.entity.Teacher>
     * @date: 2021/11/26 15:06
     */
    @GetMapping("/getTeacherList")
    @ResponseBody
    public List<Teacher> getTeacherList(@RequestParam("course_ID") String course_ID){
        return teachCourseService.getTeacherListByCourseId(course_ID);
    }

    /**
     * @description: 根据课程id获得老师信息列表，包含id，姓名，邮箱
     * @path: "/teach/getTeacherInfoList"
     * @param: course_ID
     * @return: java.util.List<com.project.etsapi.vo.TeacherInfo>
     * @date: 2021/11/26 15:26
     */
    @GetMapping("/getTeacherInfoList")
    @ResponseBody
    public List<TeacherInfo> getTeacherInfoList(@RequestParam("course_ID") String course_ID){
        return teachCourseService.getTeacherInfoListByCourseId(course_ID);
    }
}
