package com.project.etsapi.service;

import com.project.etsapi.entity.Student;

import java.util.List;

public interface StudentService {
    Student insertStudent(Student student);

//    int deleteStudent(String student_ID);
//
//    int updateStudent(Student student);
//
    Student selectStudent(String student_ID);

    List<Student> selectAll();
}
