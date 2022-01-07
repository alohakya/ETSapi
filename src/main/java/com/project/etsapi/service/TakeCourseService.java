package com.project.etsapi.service;

import com.project.etsapi.entity.Student;
import com.project.etsapi.entity.TakeCourse;
import com.project.etsapi.vo.PartScore;
import com.project.etsapi.vo.ScoreInfo;
import com.project.etsapi.vo.StudentInfo;

import java.util.List;

public interface TakeCourseService {
    int addTakeCourse(TakeCourse takeCourse);

    int deleteTakeCourse(TakeCourse takeCourse);

    List<StudentInfo> getStudentInfoListByCourseId(String course_ID, String is_student);

}
