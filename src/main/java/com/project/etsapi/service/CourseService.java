package com.project.etsapi.service;

import com.project.etsapi.entity.Course;

public interface CourseService {
    int addCourse(Course course);

    int deleteCourse(String course_ID);

    int setCourseInfo(String course_ID,String name,String description);

    int setCourseGrade(String course_ID,double attend_percentage,double project_percentage);

    Course getCourse(String course_ID);

//    List<Project>  getProjectListByCourseId(String course_ID);

}
