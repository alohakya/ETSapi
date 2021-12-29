package com.project.etsapi.controller;

import com.project.etsapi.entity.*;
import com.project.etsapi.service.CourseService;
import com.project.etsapi.service.TakeCourseService;
import com.project.etsapi.service.TeachCourseService;
import com.project.etsapi.vo.CourseInfo;
import com.project.etsapi.vo.StudentInfo;
import com.project.etsapi.vo.TeacherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @Autowired
    TakeCourseService takeCourseService;

    @Autowired
    TeachCourseService teachCourseService;

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
        course.setIs_active("1");
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
     * @path: "/course/get"
     * @param: course_ID 课程id
     * @return: com.project.etsapi.entity.Course
     * @date: 2021/11/26 15:03
     */
    @GetMapping( "/get")
    @ResponseBody
    public Course getCourse(@RequestParam("course_ID") String course_ID){
        return courseService.getCourse(course_ID);
    }


    @GetMapping("/getTotalCourse")
    public List<CourseInfo> getTotalCourse(String account_ID){
        if(account_ID.length() == 7) {
            return courseService.getTotalCourse(account_ID, true);
        }
        else{
            return courseService.getTotalCourse(account_ID,false);
        }
    }

    @GetMapping("/getTotalEndCourse")
    public List<CourseInfo> getTotalEndCourse(String account_ID){
        if(account_ID.length() == 7) {
            return courseService.getTotalEndCourse(account_ID, true);
        }
        else{
            return courseService.getTotalEndCourse(account_ID,false);
        }
    }



    /**
     * @description: 添加一位学生或助教到一个课程
     * @type: post
     * @path: "/take/addTakeCourse"
     * @param: takeCourse 包含student_ID,course_ID,is_student
     * @return: java.lang.String
     * 返回1：成功
     * 返回-1：学生id不存在
     * 返回-2：课程id不存在
     * 返回-3：已参与该课程
     * @date: 2021/11/26 15:13
     */
    @PostMapping( "/addTake")
    public String addTakeCourse(TakeCourse takeCourse) {
        return String.valueOf(takeCourseService.addTakeCourse(takeCourse));
    }

    /**
     * @description: 删除指定课程的一名学生或助教
     * @type: post
     * @path: "/take/deleteTakeCourse"
     * @param: takeCourse
     * @return: java.lang.String
     * @date: 2021/11/27 11:31
     */
    @PostMapping("/deleteTake")
    public String deleteTakeCourse(TakeCourse takeCourse){
        return String.valueOf(takeCourseService.deleteTakeCourse(takeCourse));
    }

    /**
     * @description: 根据课程ID和权限参与身份获得学生名单或助教名单
     * @type: get
     * @path: "/take/getStudentList"
     * @param: course_ID 课程id
     * @param: is_student 是否是学生
     * @return: java.util.List<com.project.etsapi.entity.Student>
     * @date: 2021/11/26 15:07
     */
    @GetMapping("/getStudentList")
    @ResponseBody
    public List<Student> getStudentList(@RequestParam("course_ID")String course_ID,
                                        @RequestParam("is_student")String is_student){
        return takeCourseService.getStudentListByCourseId(course_ID, is_student);
    }

    /**
     * @description: 根据课程id获得学生或助教信息列表，包含id，姓名，邮箱
     * @type: get
     * @path: "/take/getStudentInfoList"
     * @param: course_ID
     * @param: is_student
     * @return: java.util.List<com.project.etsapi.vo.StudentInfo>
     * @date: 2021/11/26 15:24
     */
    @GetMapping("/getStudentInfoList")
    @ResponseBody
    public List<StudentInfo> getStudentInfoList(@RequestParam("course_ID") String course_ID,
                                                @RequestParam("is_student") String is_student){
        return takeCourseService.getStudentInfoListByCourseId(course_ID, is_student);
    }


    /**
     * @description: 添加一位老师到一个课程
     * @type: post
     * @path: "/teach/addTeachCourse"
     * @param: teachCourse 包含teacher_ID 与 course_ID
     * @return: java.lang.String
     * 返回1：成功
     * 返回-1：老师id不存在
     * 返回-2：课程id不存在
     * 返回-3：课程中已有这个老师
     * @date: 2021/11/26 15:11
     */
    @PostMapping( "/addTeach")
    public String addTeachCourse(TeachCourse teachCourse) {
        return String.valueOf(teachCourseService.addTeachCourse(teachCourse));
    }

    /**
     * @description: 删除指定id课程的一位老师
     * @type: post
     * @path: "/teach/deletaTeachCourse"
     * @param: teachCourse
     * @return: java.lang.String
     * @date: 2021/11/27 10:36
     */
    @PostMapping("/deleteTeach")
    public String deleteTeachCourse(TeachCourse teachCourse){
        return String.valueOf(teachCourseService.deleteTeachCourse(teachCourse));
    }

    /**
     * @description: 根据课程id获得教师列表
     * @type: get
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
     * @type: get
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
