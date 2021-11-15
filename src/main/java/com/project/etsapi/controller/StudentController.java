package com.project.etsapi.controller;

import com.project.etsapi.entity.Student;
import com.project.etsapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("/student")
    public List<Student> selectAll(){
        return studentService.selectAll();
    }
}
