package com.project.etsapi.service.Implment;

import com.project.etsapi.entity.Course;
import com.project.etsapi.mapper.*;
import com.project.etsapi.service.CourseService;
import com.project.etsapi.vo.CourseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    TeachCourseMapper teachCourseMapper;
    @Autowired
    TakeCourseMapper takeCourseMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    ProjectMapper projectMapper;

    @Override
    public int addCourse(Course course) {
        if(courseMapper.getCourse(course.getCourse_ID()) != null){
            // 如果该课程已经存在，返回-1
            return -1;
        }
        if(teacherMapper.getTeacher(course.getTeacher_ID()) == null){
            // 该教师不存在，返回-2
            return -2;
        }
        return courseMapper.addCourse(course);
    }

    @Override
    public int deleteCourse(String course_ID) {
        Course course = courseMapper.getCourse(course_ID);
        if(course == null)
            //课程不存在
        {
            return -1;
        }
        return courseMapper.deleteCourseById(course_ID);
    }

    @Override
    public int setCourseInfo(String course_ID, String name, String description) {
        Course course = courseMapper.getCourse(course_ID);
        if(course == null){
            //课程不存在
            return -1;
        }
        return courseMapper.updateCourseInfo(course_ID,name,description);
    }

    @Override
    public int setCourseGrade(String course_ID, double attend_percentage, double project_percentage) {
        Course course = courseMapper.getCourse(course_ID);
        if(course == null){
            //课程不存在
            return -1;
        }
        return courseMapper.updateCourseGrade(course_ID,attend_percentage,project_percentage);
    }

    @Override
    public Course getCourse(String course_ID) {
        return courseMapper.getCourse(course_ID);
    }

    @Override
    public List<List<CourseInfo>> getTotalCourse(String account_ID, boolean isStudent) {
        List<List<CourseInfo>> result = new ArrayList<>();
        List<CourseInfo> tmp1;
        List<CourseInfo> tmp2;
        if(isStudent){
            tmp1 = courseMapper.getStuCourse(account_ID,"1");
            tmp2 = courseMapper.getStuCourse(account_ID,"0");
        }
        else{
            tmp1 = courseMapper.getTeaCourse(account_ID,"1");
            tmp2 = courseMapper.getTeaCourse(account_ID,"0");
        }
        result.add(tmp1);
        result.add(tmp2);
        return result;
    }
}
