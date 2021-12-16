package com.project.etsapi.mapper;

import com.project.etsapi.entity.Student;
import com.project.etsapi.entity.TakeCourse;
import com.project.etsapi.vo.PartScore;
import com.project.etsapi.vo.ScoreInfo;
import com.project.etsapi.vo.StudentInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @InterfaceName TakeCourseMapper
 * @Description
 * @Author llj
 * @Date 2021/11/18 15:58
 **/

@Mapper
public interface TakeCourseMapper {
    int addTakeCourse(TakeCourse takeCourse);

    int deleteTakeCourse(TakeCourse takeCourse);

    TakeCourse getTakeCourse(String student_ID,String course_ID);

    List<Student> getStudentListByCourseId(String course_ID,String is_student);

    List<StudentInfo> getStudentInfoListByCourseId(String course_ID, String is_student);

    List<ScoreInfo> getTotalScoreList(String course_ID);

    PartScore getPartScore(String course_ID, String student_ID);

    void updateProjectScore(String course_ID, String student_ID, double score);
}
