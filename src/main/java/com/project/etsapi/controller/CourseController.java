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

    /**
     * @description: 根据课程名称、老师id创建新课程，课程id随机数生成
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
        course.setCourse_ID(String.valueOf((int) (10000000+Math.random()*(100000000 - 10000000))));
        return String.valueOf(courseService.addCourse(course));
    }

    /**
     * @description: 根据课程id删除课程
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


    /**
     * @description: 添加一位老师到一个课程
     * @path: "/course/addTeachCourse"
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
        return String.valueOf(courseService.addTeachCourse(teachCourse));
    }


    /**
     * @description: 根据课程id获得教师列表
     * @path: "/course/getTeacherListByCourseId"
     * @param: course_ID 课程id
     * @return: java.util.List<com.project.etsapi.entity.Teacher>
     * @date: 2021/11/26 15:06
     */
    @GetMapping("/getTeacherListByCourseId")
    @ResponseBody
    public List<Teacher> getTeacherListByCourseId(@RequestParam("course_ID") String course_ID){
        return courseService.getTeacherListByCourseId(course_ID);
    }


    /**
     * @description: 根据课程id获得老师信息列表，包含id，姓名，邮箱
     * @path: "/course/getListTeacherInfoByCourseId"
     * @param: course_ID
     * @return: java.util.List<com.project.etsapi.vo.TeacherInfo>
     * @date: 2021/11/26 15:26
     */
    @GetMapping("/getListTeacherInfoByCourseId")
    @ResponseBody
    public List<TeacherInfo> getListTeacherInfo(@RequestParam("course_ID") String course_ID){
        return courseService.getListTeacherInfo(course_ID);
    }


    /**
     * @description: 添加一位学生或助教到一个课程
     * @path: "/course/addTakeCourse"
     * @param: takeCourse 包含student_ID,course_ID,is_student
     * @return: java.lang.String
     * 返回1：成功
     * 返回-1：学生id不存在
     * 返回-2：助教id不存在
     * 返回-3：课程id不存在
     * 返回-4：助教已参与该课程
     * 返回-5：学生已参与该课程
     * @date: 2021/11/26 15:13
     */
    @PostMapping( "/addTakeCourse")
    public String addTakeCourse(TakeCourse takeCourse) {
        return String.valueOf(courseService.addTakeCourse(takeCourse));
    }


    /**
     * @description: 根据课程ID和权限参与身份获得学生名单或助教名单
     * @path: "/course/getStudentListByCourseId
     * @param: course_ID 课程id
     * @param: is_student 是否是学生
     * @return: java.util.List<com.project.etsapi.entity.Student>
     * @date: 2021/11/26 15:07
     */
    @GetMapping("/getStudentListByCourseId")
    @ResponseBody
    public List<Student> getStudentListByCourseId(@RequestParam("course_ID")String course_ID, @RequestParam("is_student")String is_student){
        return courseService.getStudentListByCourseId(course_ID, is_student);
    }


    /**
     * @description: 根据课程id获得学生或助教信息列表，包含id，姓名，邮箱
     * @path: "/course/getListStudentInfoByCourseId"
     * @param: course_ID
     * @param: is_student
     * @return: java.util.List<com.project.etsapi.vo.StudentInfo>
     * @date: 2021/11/26 15:24
     */
    @GetMapping("/getListStudentInfoByCourseId")
    @ResponseBody
    public List<StudentInfo> getListStudentInfo(@RequestParam("course_ID") String course_ID,
                                                @RequestParam("is_student") String is_student){
        return courseService.getListStudentInfo(course_ID, is_student);
    }


    /**
     * @description: 根据课程ID获得项目列表
     * @path: "/course/getProjectListByCourseId"
     * @param: course_ID 课程id
     * @return: java.util.List<com.project.etsapi.entity.Project>
     * @date: 2021/11/26 15:08
     */
    @GetMapping("/getProjectListByCourseId")
    @ResponseBody
    public List<Project> getProjectListByCourseId(String course_ID){
        return courseService.getProjectListByCourseId(course_ID);
    }
}
