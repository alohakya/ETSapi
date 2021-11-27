package com.project.etsapi.controller;

import com.project.etsapi.entity.Student;
import com.project.etsapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * @description: 添加学生
     * @type: post
     * @path: "/student/add"
     * @param: student 包含学生id、姓名
     * @return: java.lang.String
     * 返回1：添加成功
     * 返回-1：学生id已存在
     * @date: 2021/11/26 15:48
     */
    @PostMapping( "/add")
    public String addStudent(Student student) {
        return String.valueOf(studentService.addStudent(student));
    }


    /**
     * @description: 根据学生id删除学生
     * @type: post
     * @path: "/student/delete"
     * @param: student_ID
     * @return: java.lang.String
     * 返回1：成功
     * 返回-1：学生id不存在
     * @date: 2021/11/26 15:49
     */
    @PostMapping( "/delete")
    public String deleteStudent(@RequestParam("student_ID") String student_ID){
        return String.valueOf(studentService.deleteStudent(student_ID));
    }

    /**
     * @description: 更新学生信息
     * @type: post
     * @path: "/student/set"
     * @param: student
     * @return: java.lang.String
     * 返回1：成功
     * 返回-1：学生id不存在
     * @date: 2021/11/26 15:52
     */
    @PostMapping("/set")
    public String setStudent(Student student) {
        return String.valueOf(studentService.setStudent(student));
    }

    /**
     * @description: 根据学生id查找学生
     * @type: get
     * @path: "/student/get"
     * @param: student_ID
     * @return: com.project.etsapi.entity.Student
     * @date: 2021/11/26 15:51
     */
    @GetMapping( "/get")
    @ResponseBody
    public Student getStudent(@RequestParam("student_ID") String student_ID){
        return studentService.getStudent(student_ID);
    }
}
