package com.project.etsapi.service;

import com.project.etsapi.entity.*;

import java.util.List;

public interface CourseService {
    Course getCourse(String course_ID);

    List<Teacher> getTeacherListByCourseId(String course_ID);

//    List<Student> getStudentListByCourseId(String course_ID, String authority);

    List<Student> getStudentListByCourseId(String course_ID);

    int addTakeCourse(TakeCourse takeCourse);

    int addTeachCourse(TeachCourse teachCourse);

    TeachCourse getTeachCourse(String teacher_ID, String course_ID);

    TakeCourse getTakeCourse(String student_ID, String course_ID);
}
