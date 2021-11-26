package com.project.etsapi.service;

import com.project.etsapi.entity.*;
import com.project.etsapi.vo.StudentInfo;
import com.project.etsapi.vo.TeacherInfo;

import java.util.List;

public interface CourseService {
    int addCourse(Course course);

    int deleteCourse(String course_ID);

    int setCourseInfo(String course_ID,String name,String description);

    int setCourseGrade(String course_ID,double attend_percentage,double project_percentage);

    Course getCourse(String course_ID);

    List<Teacher> getTeacherListByCourseId(String course_ID);

    List<Student> getStudentListByCourseId(String course_ID, String is_student);

    List<Project>  getProjectListByCourseId(String course_ID);

    int addTakeCourse(TakeCourse takeCourse);

    int addTeachCourse(TeachCourse teachCourse);

    TeachCourse getTeachCourse(String teacher_ID, String course_ID);

    TakeCourse getTakeCourse(String student_ID, String course_ID);

    List<Course> getAllCourse();

    List<String> getAllCourseId();

    List<StudentInfo> getListStudentInfo(String course_ID, String is_student);

    List<TeacherInfo> getListTeacherInfo(String course_ID);
}
