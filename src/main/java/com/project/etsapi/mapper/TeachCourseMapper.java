package com.project.etsapi.mapper;

import com.project.etsapi.entity.TeachCourse;
import com.project.etsapi.entity.Teacher;
import com.project.etsapi.vo.TeacherInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @InterfaceName TeachCourseMapper
 * @Description
 * @Author llj
 * @Date 2021/11/17 8:56
 **/

@Mapper
public interface TeachCourseMapper {
    /**
     * 给某门课添加教师
     * @param teachCourse
     * @return int
     */
    int addTeachCourse(TeachCourse teachCourse);

    /**
     * 给某门课删除教师
     * @param teachCourse
     * @return
     */
    int deleteTeachCourse(TeachCourse teachCourse);

    /**
     * 获取指定行信息
     */
    TeachCourse getTeachCourse(TeachCourse teachCourse);

    /**
     * 根据课程id获得某门课的教师列表
     * @param course_ID
     * @return
     */
    List<Teacher> getTeacherListByCourseId(String course_ID);

    /**
     * 根据课程id获得某门课的教师信息列表，包含邮箱
     * @param course_ID
     * @return
     */
    List<TeacherInfo> getTeacherInfoListByCourseId(String course_ID);
}
