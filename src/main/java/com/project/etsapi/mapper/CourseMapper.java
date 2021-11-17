package com.project.etsapi.mapper;

import com.project.etsapi.entity.Course;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseMapper {
    /**
     * 根据id查询课程信息
     * @param course_ID
     * @return Course
     */
    Course getCourse(String course_ID);

}
