package com.project.etsapi.service;

import com.project.etsapi.entity.Teacher;

import java.util.List;

/**
 * @InterfaceName TeacherService
 * @Description
 * @Author llj
 * @Date 2021/11/17 12:56
 **/


public interface TeacherService {
    int addTeacher(Teacher teacher);

    int deleteTeacher(String teacher_ID);

    int setTeacher(Teacher teacher);

    Teacher getTeacher(String teacher_ID);

    List<Teacher> getAllTeacher();

    List<String> getAllTeacherId();
}
