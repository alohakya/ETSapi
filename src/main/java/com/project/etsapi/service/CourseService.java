package com.project.etsapi.service;

import com.project.etsapi.entity.*;
import com.project.etsapi.vo.StudentInfo;
import com.project.etsapi.vo.TeacherInfo;

import java.util.List;

public interface CourseService {
    Course getCourse(String course_ID);

    List<Teacher> getTeacherListByCourseId(String course_ID);

    List<Student> getStudentListByCourseId(String course_ID, String authority);

    List<Project>  getProjectListByCourseId(String course_ID);

    int addTakeCourse(TakeCourse takeCourse);

    int addTeachCourse(TeachCourse teachCourse);

    TeachCourse getTeachCourse(String teacher_ID, String course_ID);

    TakeCourse getTakeCourse(String student_ID, String course_ID);

    List<Course> getAllCourse();

    List<String> getAllCourseId();

    List<StudentInfo> getListStudentInfo(String course_ID, String authority);

    List<TeacherInfo> getListTeacherInfo(String course_ID);
}
