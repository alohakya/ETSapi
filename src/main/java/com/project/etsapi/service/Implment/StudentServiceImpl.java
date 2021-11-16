package com.project.etsapi.service.Implment;

import com.project.etsapi.mapper.StudentMapper;
import com.project.etsapi.entity.Student;
import com.project.etsapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;

    public Student insertStudent(Student student){
        int result = studentMapper.insertStudent(student);
        return student;
    }

    public int deleteStudent(String student_ID){
        return studentMapper.deleteStudent(student_ID);
    }

    public int updateStudent(Student student){
        return studentMapper.updateStudent(student);
    }

    @Override
    public Student selectStudent(String student_ID){
        return studentMapper.selectStudent(student_ID);
    }

    @Override
    public List<Student> selectAll(){
        return studentMapper.selectAll();
    }
}
