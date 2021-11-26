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

//    List<Project>  getProjectListByCourseId(String course_ID);

}
