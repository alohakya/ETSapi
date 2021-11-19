package com.project.etsapi.mapper;

import com.project.etsapi.entity.Student;
import com.project.etsapi.entity.TakeCourse;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @InterfaceName TakeCourseMapper
 * @Description
 * @Author llj
 * @Date 2021/11/18 15:58
 **/

@Mapper
public interface TakeCourseMapper {
    List<Student> getStudentListByCourseId(HashMap<String, String> parameter);

    int addTakeCourse(TakeCourse takeCourse);

    TakeCourse getTakeCourse(HashMap<String, String> parameters);
}
