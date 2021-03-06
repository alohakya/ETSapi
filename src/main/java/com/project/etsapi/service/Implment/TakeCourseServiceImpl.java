package com.project.etsapi.service.Implment;

import com.project.etsapi.entity.Course;
import com.project.etsapi.entity.Student;
import com.project.etsapi.entity.TakeCourse;
import com.project.etsapi.mapper.CourseMapper;
import com.project.etsapi.mapper.StudentMapper;
import com.project.etsapi.mapper.TakeCourseMapper;
import com.project.etsapi.service.TakeCourseService;
import com.project.etsapi.vo.StudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TakeCourseServiceImpl implements TakeCourseService {
    @Autowired
    TakeCourseMapper takeCourseMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    CourseMapper courseMapper;

    @Override
    public List<StudentInfo> getStudentInfoListByCourseId(String course_ID, String is_student) {
        return takeCourseMapper.getStudentInfoListByCourseId(course_ID,is_student);
    }

    @Override
    public int addTakeCourse(TakeCourse takeCourse) {
        Student student = studentMapper.getStudent(takeCourse.getStudent_ID());
        if(student == null){
            return -1;
        }
        Course course = courseMapper.getCourse(takeCourse.getCourse_ID());
        if(course == null){
            return -2;
        }
        TakeCourse t = takeCourseMapper.getTakeCourse(takeCourse.getStudent_ID(),takeCourse.getCourse_ID());
        if(t != null){
            return -3;
        }
        return takeCourseMapper.addTakeCourse(takeCourse);
    }

    @Override
    public int deleteTakeCourse(TakeCourse takeCourse) {
        return takeCourseMapper.getTakeCourse(takeCourse.getStudent_ID(),takeCourse.getCourse_ID()) == null?
                -1:takeCourseMapper.deleteTakeCourse(takeCourse);
    }

}
