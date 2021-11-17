package com.project.etsapi.controller;

import com.project.etsapi.entity.TeachCourse;
import com.project.etsapi.service.TeachCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName TeachCourseController
 * @Description
 * @Author llj
 * @Date 2021/11/17 9:19
 **/

@RestController
@RequestMapping("/teach")
public class TeachCourseController {
    @Autowired
    private TeachCourseService teachCourseService;

    // 添加一位老师到一个课程
    // http://localhost:8888/teach/add?teacher_ID=10104&course_ID=42024405
    @PostMapping( "/add")
    @ResponseBody
    public String addTeachCourse(TeachCourse teachCourse) {
        int result = teachCourseService.addTeachCourse(teachCourse);
        if(result >= 1){
            return "添加成功";
        }
        else if(result == -1){
            return "添加失败！该老师不存在！";
        }
        else{
            return "添加失败！该课程不存在！";
        }
    }

//    // 插入新用户
//    // http://localhost:8888/teach/get?teacher_ID=10104
//    @GetMapping( "/get")
//    @ResponseBody
//    public TeachCourse addTeachCourse(TeachCourse teachCourse) {
//        return teachCourseService.addTeachCourse(teachCourse);
//    }

}
