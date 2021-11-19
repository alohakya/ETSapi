package com.project.etsapi.mapper;

import com.project.etsapi.entity.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {
    /**
     * 根据id查询课程信息
     * @param course_ID
     * @return Course
     */
    Course getCourse(String course_ID);

    /**
     * 获得所有课程
     * @param
     * @return
     */
    List<Course> getAll();
}
