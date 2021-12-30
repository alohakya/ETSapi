package com.project.etsapi.service;

import com.project.etsapi.entity.Course;
import com.project.etsapi.vo.CourseInfo;

import java.util.List;

public interface CourseService {
    int addCourse(Course course);

    int deleteCourse(String course_ID);

    int setCourseInfo(String course_ID,String name,String description);

    int setCourseGrade(String course_ID,double attend_percentage,double project_percentage);

    Course getCourse(String course_ID);

    List<CourseInfo> getTotalCourse(String teacher_ID,boolean isStudent);

    List<CourseInfo> getTotalEndCourse(String account_ID, boolean isStudent);

    int endCourse(String course_ID);
}
