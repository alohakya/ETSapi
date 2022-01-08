package com.project.etsapi.mapper;

import com.project.etsapi.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    /**
     * 新增用户
     * @param student
     * @return
     */
    int addStudent(Student student);

    /**
     * 根据id删除
     * @param student_ID
     * @return
     */
    int deleteStudent(String student_ID);

    /**
     * 更新用户信息
     * @param student
     * @return
     */
    int setStudent(Student student);

    /**
     * 根据id查询用户信息
     * @param student_ID
     * @return Student
     */
    Student getStudent(String student_ID);

    /**
     * 查询所有用户信息
     * @return
     */
    List<Student> getAll();

    int addList(List<Student> list);
}
