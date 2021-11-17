package com.project.etsapi.controller;

import com.project.etsapi.entity.Student;
import com.project.etsapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // 插入新用户
    // http://localhost:8888/student/add?student_ID=1951014&name=aji
    @PostMapping( "/add")
    @ResponseBody
    public String addStudent(Student student) {
        int result = studentService.addStudent(student);
        if( result >= 1){
            return "添加成功";
        }
        else{
            return "添加失败！该学生已经存在！";
        }
    }

    // 删除学生
    // http://localhost:8888/student/delete?student_ID=1950000
    @PostMapping( "/delete")
    @ResponseBody
    public String deleteStudent(@RequestParam("student_ID") String student_ID){
        int result = studentService.deleteStudent(student_ID);
        if(result >= 1){
            return "删除成功";
        }
        else{
            return "删除失败！未找到该学生！";
        }
    }

    //根据学号更新学生信息
    //http://localhost:8888/student/set?student_ID=1950000&name=aji
    @PostMapping("/set")
    @ResponseBody
    public String setStudent(Student student) {
        int result = studentService.setStudent(student);
        if(result >= 1){
            return "修改成功";
        }
        else{
            return "修改失败！未找到该学生！";
        }
    }

    //查找学生
    // http://localhost:8888/student/get?student_ID=1950000
    @GetMapping( "/get")
    @ResponseBody
    public Student getStudent(@RequestParam("student_ID") String student_ID){
        return studentService.getStudent(student_ID);
    }

    // 打印所有学生信息
    // http://localhost:8888/student/getAll
    @GetMapping("/getAll")
    @ResponseBody
    public List<Student> getAll(){
        return studentService.getAll();
    }
}
