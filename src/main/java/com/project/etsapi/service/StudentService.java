package com.project.etsapi.service;

import com.project.etsapi.entity.Student;

import java.util.List;

public interface StudentService {
    int addStudent(Student student);

    int deleteStudent(String student_ID);

    int setStudent(Student student);

    Student getStudent(String student_ID);

    List<Student> getAll();
}
