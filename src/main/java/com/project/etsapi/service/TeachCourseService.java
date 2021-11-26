package com.project.etsapi.service;

import com.project.etsapi.entity.TeachCourse;
import com.project.etsapi.entity.Teacher;
import com.project.etsapi.vo.TeacherInfo;

import java.util.List;

public interface TeachCourseService {
    int addTeachCourse(TeachCourse teachCourse);

    TeachCourse getTeachCourse(String teacher_ID,String course_ID);

    List<Teacher> getTeacherListByCourseId(String course_ID);

    List<TeacherInfo> getTeacherInfoListByCourseId(String course_ID);

}
