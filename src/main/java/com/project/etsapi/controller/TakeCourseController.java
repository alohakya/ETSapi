package com.project.etsapi.controller;

import com.project.etsapi.entity.Student;
import com.project.etsapi.entity.TakeCourse;
import com.project.etsapi.service.TakeCourseService;
import com.project.etsapi.vo.StudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/take")
public class TakeCourseController {
    @Autowired
    TakeCourseService takeCourseService;

    /**
     * @description: 添加一位学生或助教到一个课程
     * @path: "/take/addTakeCourse"
     * @param: takeCourse 包含student_ID,course_ID,is_student
     * @return: java.lang.String
     * 返回1：成功
     * 返回-1：学生id不存在
     * 返回-2：课程id不存在
     * 返回-3：已参与该课程
     * @date: 2021/11/26 15:13
     */
    @PostMapping( "/addTakeCourse")
    public String addTakeCourse(TakeCourse takeCourse) {
        return String.valueOf(takeCourseService.addTakeCourse(takeCourse));
    }

    /**
     * @description: 删除指定课程的一名学生或助教
     * @path: "/take/deleteTakeCourse"
     * @param: takeCourse
     * @return: java.lang.String
     * @date: 2021/11/27 11:31
     */
    @PostMapping("/deleteTakeCourse")
    public String deleteTakeCourse(TakeCourse takeCourse){
        return String.valueOf(takeCourseService.deleteTakeCourse(takeCourse));
    }

    /**
     * @description: 根据课程ID和权限参与身份获得学生名单或助教名单
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
     * @path: "/course/getStudentInfoList"
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
}
