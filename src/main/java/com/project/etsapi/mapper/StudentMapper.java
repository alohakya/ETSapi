package com.project.etsapi.mapper;

import com.project.etsapi.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
<<<<<<< Updated upstream
=======
    /**
     * 新增用户
     * @param student
     * @return
     */
    int insertStudent(Student student);

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
    int updateStudent(Student student);

    /**
     * 根据id查询用户信息
     * @param student_ID
     * @return Student
     */
    Student selectStudent(String student_ID);

    /**
     * 查询所有用户信息
     * @return
     */
>>>>>>> Stashed changes
    List<Student> selectAll();
}
