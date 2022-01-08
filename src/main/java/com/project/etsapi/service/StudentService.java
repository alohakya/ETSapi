package com.project.etsapi.service;

import com.project.etsapi.entity.Student;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StudentService {
    int addStudent(Student student);

    int deleteStudent(String student_ID);

    int setStudent(Student student);

    Student getStudent(String student_ID);

    List<Student> getAll();

    int addList(MultipartFile file) throws IOException;
}
