package com.project.etsapi.controller;

import com.project.etsapi.entity.Course;
import com.project.etsapi.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

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

    /**
     * @description: 根据课程名称、老师id创建新课程，课程id随机数生成
     * @type: post
     * @path: "/course/addCourse"
     * @param: teacher_ID
     * @param: name
     * @return: java.lang.String
     * 返回1：创建成功
     * 返回-1：随机数生成id，课程id已存在
     * 返回-2：教师id不存在
     * @date: 2021/11/26 15:04
     */
    @PostMapping("/add")
    public String addCourse(@RequestParam("teacher_ID") String teacher_ID,
                            @RequestParam("name") String name){
        Course course = new Course();
        course.setTeacher_ID(teacher_ID);
        course.setName(name);
        course.setCourse_ID(String.valueOf(new Random().nextInt(89999999) + 10000000));
        return String.valueOf(courseService.addCourse(course));
    }

    /**
     * @description: 根据课程id删除课程
     * @type: post
     * @path: "/course/deleteCourse"
     * @param: course_ID
     * @return: java.lang.String
     * @date: 2021/11/26 20:05
     */
    @PostMapping("/delete")
    public String deleteCourse(@RequestParam("course_ID") String course_ID){
        return String.valueOf(courseService.deleteCourse(course_ID));
    }

    /**
     * @description: 设置课程介绍信息
     * @type: post
     * @path: "/course/setInfo"
     * @param: course_ID  课程id
     * @param: name  课程名称
     * @param: description  课程描述
     * @return: java.lang.String
     * @date: 2021/11/26 22:45
     */
    @PostMapping("/setInfo")
    public String setCourseInfo(String course_ID,String name,String description){
        return String.valueOf(courseService.setCourseInfo(course_ID,name,description));
    }

    /**
     * @description: 设置课程成绩占比
     * @type: post
     * @path: "/course/setGrade"
     * @param: course_ID
     * @param: attend_percentage
     * @param: project_percentage
     * @return: java.lang.String
     * @date: 2021/11/26 22:46
     */
    @PostMapping("/setGrade")
    public String setCourseGrade(@RequestParam("course_ID") String course_ID,
                                 @RequestParam("attend_percentage") double attend_percentage,
                                 @RequestParam("project_percentage") double project_percentage){
        return String.valueOf(courseService.setCourseGrade(
                course_ID,attend_percentage,project_percentage));
    }

    /**
     * @description: 根据课程id获得课程信息
     * @type: get
     * @path: "/course/getCourse"
     * @param: course_ID 课程id
     * @return: com.project.etsapi.entity.Course
     * @date: 2021/11/26 15:03
     */
    @GetMapping( "/get")
    @ResponseBody
    public Course getCourse(@RequestParam("course_ID") String course_ID){
        return courseService.getCourse(course_ID);
    }
}
