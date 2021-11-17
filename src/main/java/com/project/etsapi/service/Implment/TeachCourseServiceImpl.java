package com.project.etsapi.service.Implment;

import com.project.etsapi.entity.Course;
import com.project.etsapi.entity.TeachCourse;
import com.project.etsapi.entity.Teacher;
import com.project.etsapi.mapper.CourseMapper;
import com.project.etsapi.mapper.TeachCourseMapper;
import com.project.etsapi.mapper.TeacherMapper;
import com.project.etsapi.service.TeachCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName TeachCourseImpl
 * @Description
 * @Author llj
 * @Date 2021/11/17 9:16
 **/

@Service
public class TeachCourseServiceImpl implements TeachCourseService {
    @Autowired
    TeachCourseMapper teachCourseMapper;

    @Override
    public int addTeachCourse(TeachCourse teachCourse) {
        Teacher teacher = teachCourseMapper.getTeacherById(teachCourse.getTeacher_ID());
        Course course = teachCourseMapper.getCourseById(teachCourse.getCourse_ID());
        if(teacher!=null && course!=null){
            return teachCourseMapper.addTeachCourse(teachCourse);
        }
        else if(teacher == null){
            // 老师不存在，返回-1
            return -1;
        }
        else{
            // 课程不存在，返回-2
            return -2;
        }
    }
}
