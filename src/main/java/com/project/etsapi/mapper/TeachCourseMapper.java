package com.project.etsapi.mapper;

import com.project.etsapi.entity.Course;
import com.project.etsapi.entity.TeachCourse;
import com.project.etsapi.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

/**
 * @InterfaceName TeachCourseMapper
 * @Description
 * @Author llj
 * @Date 2021/11/17 8:56
 **/

@Mapper
public interface TeachCourseMapper {
    /**
     * 给某门课添加教师
     * @param teachCourse
     * @return int
     */
    int addTeachCourse(TeachCourse teachCourse);

    Teacher getTeacherById(String teacher_ID);

    Course getCourseById(String course_ID);

}
