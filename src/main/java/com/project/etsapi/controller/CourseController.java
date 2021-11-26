package com.project.etsapi.controller;

import com.project.etsapi.entity.*;
import com.project.etsapi.service.CourseService;
import com.project.etsapi.vo.StudentInfo;
import com.project.etsapi.vo.TeacherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // 创建课程（前端输入课程信息vo，后端生成课程ID）
    public Course createCourse(){
        return null;
    }

    // 添加课程
    // http://localhost:8888/course/addCourse?course_ID=42024401&name=飞盘体育课&teacher_ID=10100&description=飞盘真好玩&attend_percentage=0.1
    @GetMapping("/addCourse")
    @ResponseBody
    public String addCourse(Course course){
        int result = courseService.addCourse(course);
        switch(result){
            case -1:
                return "-1";
            case -2:
                return "-2";
            default:
                return "1";
        }
    }

    // 根据课程ID获得课程
    // http://localhost:8888/course/get?course_ID=42024401
    @GetMapping( "/get")
    @ResponseBody
    public Course getCourse(@RequestParam("course_ID") String course_ID){
        return courseService.getCourse(course_ID);
    }

    // 根据课程ID获得教师列表
    // http://localhost:8888/course/getTeacherListByCourseId?course_ID=42024401
    @GetMapping("/getTeacherListByCourseId")
    @ResponseBody
    public List<Teacher> getTeacherListByCourseId(String course_ID){
        return courseService.getTeacherListByCourseId(course_ID);
    }

    // 根据课程ID和权限获得学生名单或助教名单
    // http://localhost:8888/course/getStudentListByCourseId?course_ID=42024401&is_student=1
    @GetMapping("/getStudentListByCourseId")
    @ResponseBody
    public List<Student> getStudentListByCourseId(@RequestParam("course_ID")String course_ID, @RequestParam("is_student")String is_student){
        return courseService.getStudentListByCourseId(course_ID, is_student);
    }

    // 根据课程ID获得项目列表
    // http://localhost:8888/course/getProjectListByCourseId?course_ID=42024401
    @GetMapping("/getProjectListByCourseId")
    @ResponseBody
    public List<Project> getProjectListByCourseId(String course_ID){
        return courseService.getProjectListByCourseId(course_ID);
    }

    // 添加一位老师到一个课程
    // http://localhost:8888/course/addTeachCourse?teacher_ID=10104&course_ID=42024405
    @PostMapping( "/addTeachCourse")
    @ResponseBody
    public String addTeachCourse(TeachCourse teachCourse) {
        int result = courseService.addTeachCourse(teachCourse);
        switch(result){
            case -1:
                return "-1";
            case -2:
                return "-2";
            case -3:
                return "-3";
            default:
                return "1";
        }
    }

    // 添加一位学生或助教到一个课程
    // http://localhost:8888/course/addTakeCourse?student_ID=1951014&course_ID=42024405&is_student=0
    @PostMapping( "/addTakeCourse")
    @ResponseBody
    public String addTakeCourse(TakeCourse takeCourse) {
        return Integer.toString(courseService.addTakeCourse(takeCourse));
//        int result = courseService.addTakeCourse(takeCourse);
//        switch(result){
//            case -1:
//                return "-1";
//            case -2:
//                return "-2";
//            case -3:
//                return "-3";
//            case -4:
//                return "-4";
//            case -5:
//                return "-5";
//            default:
//                return "1";
//        }
    }

    // 查找TeachCourse
    // http://localhost:8888/course/getTeachCourse?teacher_ID=10100&course_ID=42024401
    @GetMapping( "/getTeachCourse")
    @ResponseBody
    public TeachCourse getTeachCourse(@RequestParam("teacher_ID") String teacher_ID, @RequestParam("course_ID") String course_ID){
        return courseService.getTeachCourse(teacher_ID, course_ID);
    }

    // 获得所有课程
    // http://localhost:8888/course/getAllCourse
    @GetMapping("/getAllCourse")
    @ResponseBody
    public List<Course> getAllCourse(){
        return courseService.getAllCourse();
    }

    // 获得所有课程ID,，供外码选择
    // http://localhost:8888/course/getAllCourseId
    @GetMapping("/getAllCourseId")
    @ResponseBody
    public List<String> getAllCourseId(){
        return courseService.getAllCourseId();
    }

    // 根据课程ID获得列表，列表内容  学生ID，学生名字，账号邮箱
    // http://localhost:8888/course/getListStudentInfoByCourseId?course_ID=42024401&is_student=1
    @GetMapping("/getListStudentInfoByCourseId")
    @ResponseBody
    public List<StudentInfo> getListStudentInfo(@RequestParam("course_ID") String course_ID,
                                                @RequestParam("is_student") String is_student){
        return courseService.getListStudentInfo(course_ID, is_student);
    }

    // 根据课程ID获得列表，列表内容  教师ID，教师名字，账号邮箱
    // http://localhost:8888/course/getListTeacherInfoByCourseId?course_ID=42024401
    @GetMapping("/getListTeacherInfoByCourseId")
    @ResponseBody
    public List<TeacherInfo> getListTeacherInfo(@RequestParam("course_ID") String course_ID){
        return courseService.getListTeacherInfo(course_ID);
    }
}
