package com.project.etsapi.service;

import com.project.etsapi.entity.TeachCourse;
import com.project.etsapi.entity.Teacher;
import com.project.etsapi.vo.TeacherInfo;

import java.util.List;

public interface TeachCourseService {
    int addTeachCourse(TeachCourse teachCourse);

    int deleteTeachCourse(TeachCourse teachCourse);

    TeachCourse getTeachCourse(TeachCourse teachCourse);

    List<Teacher> getTeacherListByCourseId(String course_ID);

    List<TeacherInfo> getTeacherInfoListByCourseId(String course_ID);

}
