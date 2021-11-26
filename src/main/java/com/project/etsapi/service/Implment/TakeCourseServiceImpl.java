package com.project.etsapi.service.Implment;

import com.project.etsapi.entity.Student;
import com.project.etsapi.entity.TakeCourse;
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

    @Override
    public List<Student> getStudentListByCourseId(String course_ID, String is_student) {
        return takeCourseMapper.getStudentListByCourseId(course_ID,is_student);
    }

    @Override
    public List<StudentInfo> getStudentInfoListByCourseId(String course_ID, String is_student) {
        return takeCourseMapper.getStudentInfoListByCourseId(course_ID,is_student);
    }

    @Override
    public int addTakeCourse(TakeCourse takeCourse) {
        return takeCourseMapper.addTakeCourse(takeCourse);
    }

    @Override
    public TakeCourse getTakeCourse(String student_ID, String course_ID) {
        return takeCourseMapper.getTakeCourse(student_ID,course_ID);
    }
}
