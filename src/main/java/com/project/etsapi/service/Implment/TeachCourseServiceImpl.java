package com.project.etsapi.service.Implment;

import com.project.etsapi.entity.Course;
import com.project.etsapi.entity.TeachCourse;
import com.project.etsapi.entity.Teacher;
import com.project.etsapi.mapper.CourseMapper;
import com.project.etsapi.mapper.TeachCourseMapper;
import com.project.etsapi.mapper.TeacherMapper;
import com.project.etsapi.service.TeachCourseService;
import com.project.etsapi.vo.TeacherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeachCourseServiceImpl implements TeachCourseService {
    @Autowired
    TeachCourseMapper teachCourseMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    CourseMapper courseMapper;

    @Override
    public int addTeachCourse(TeachCourse teachCourse) {
        if(this.getTeachCourse(teachCourse) != null){
            // 该老师已经任教该课程，返回-3
            return -3;
        }
        Teacher teacher = teacherMapper.getTeacher(teachCourse.getTeacher_ID());
        Course course = courseMapper.getCourse(teachCourse.getCourse_ID());
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

    @Override
    public int deleteTeachCourse(TeachCourse teachCourse) {
        return teachCourseMapper.getTeachCourse(teachCourse) == null?
                -1:teachCourseMapper.deleteTeachCourse(teachCourse);
    }

    @Override
    public TeachCourse getTeachCourse(TeachCourse teachCourse) {
        return teachCourseMapper.getTeachCourse(teachCourse);
    }

    @Override
    public List<Teacher> getTeacherListByCourseId(String course_ID) {
        return teachCourseMapper.getTeacherListByCourseId(course_ID);
    }

    @Override
    public List<TeacherInfo> getTeacherInfoListByCourseId(String course_ID) {
        return teachCourseMapper.getTeacherInfoListByCourseId(course_ID);
    }
}
