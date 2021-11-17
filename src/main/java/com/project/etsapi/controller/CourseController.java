package com.project.etsapi.controller;

import com.project.etsapi.entity.Course;
import com.project.etsapi.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName CourseController
 * @Description
 * @Author llj
 * @Date 2021/11/16 20:48
 **/

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    //查找课程
    // http://localhost:8888/course/get?course_ID=42024401
    @GetMapping( "/get")
    @ResponseBody
    public Course getCourse(@RequestParam("course_ID") String course_ID){
        return courseService.getCourse(course_ID);
    }
}
