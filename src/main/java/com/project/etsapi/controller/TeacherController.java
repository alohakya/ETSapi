package com.project.etsapi.controller;

import com.project.etsapi.entity.Teacher;
import com.project.etsapi.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName TeacherController
 * @Description
 * @Author llj
 * @Date 2021/11/17 14:09
 **/

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    /**
     * @description: 添加老师
     * @type: post
     * @path: "/teacher/add"
     * @param: teacher 包含老师id，姓名
     * @return: java.lang.String
     * 返回1：成功
     * 返回-1：老师id已存在
     * @date: 2021/11/26 15:57
     */
    @PostMapping( "/add")
    public String addTeacher(Teacher teacher) {
        return String.valueOf(teacherService.addTeacher(teacher));
    }


    /**
     * @description: 根据老师id删除老师
     * @type: post
     * @path: "/teacher/delete"
     * @param: teacher_ID
     * @return: java.lang.String
     * 返回1：成功
     * 返回-1：老师id不存在
     * @date: 2021/11/26 15:59
     */
    @PostMapping( "/delete")
    @ResponseBody
    public String deleteTeacher(@RequestParam("teacher_ID") String teacher_ID){
        return String.valueOf(teacherService.deleteTeacher(teacher_ID));
    }


    /**
     * @description: 根据老师id更新老师信息
     * @type: post
     * @path: "/teacher/set"
     * @param: teacher
     * @return: java.lang.String
     * 返回1：成功
     * 返回-1：老师id不存在
     * @date: 2021/11/26 16:00
     */
    @PostMapping("/set")
    @ResponseBody
    public String setTeacher(Teacher teacher) {
        return String.valueOf(teacherService.setTeacher(teacher));
    }


    /**
     * @description: 根据老师id查询老师信息
     * @type: get
     * @path: "/teacher/get"
     * @param: teacher_ID
     * @return: com.project.etsapi.entity.Teacher
     * @date: 2021/11/26 16:01
     */
    @GetMapping( "/get")
    @ResponseBody
    public Teacher getTeacher(@RequestParam("teacher_ID") String teacher_ID){
        return teacherService.getTeacher(teacher_ID);
    }
}
