package com.project.etsapi.service.Implment;

import com.project.etsapi.entity.Course;
import com.project.etsapi.mapper.CourseMapper;
import com.project.etsapi.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName CourseServiceImpl
 * @Description
 * @Author llj
 * @Date 2021/11/16 20:46
 **/

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseMapper courseMapper;

    @Override
    public Course getCourse(String course_ID) {
        return courseMapper.getCourse(course_ID);
    }
}
