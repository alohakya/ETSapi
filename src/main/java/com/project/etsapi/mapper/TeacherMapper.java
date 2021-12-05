package com.project.etsapi.mapper;

import com.project.etsapi.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @InterfaceName TeacherMapper
 * @Description
 * @Author llj
 * @Date 2021/11/17 12:18
 **/

@Mapper
public interface TeacherMapper {

    /**
     * 新增用户
     * @param teacher
     * @return
     */
    int addTeacher(Teacher teacher);

    /**
     * 根据id删除
     * @param teacher_ID
     * @return
     */
    int deleteTeacher(String teacher_ID);

    /**
     * 更新用户信息
     * @param teacher
     * @return
     */
    int setTeacher(Teacher teacher);

    /**
     * 根据id查询用户信息
     * @param teacher_ID
     * @return Teacher
     */
    Teacher getTeacher(String teacher_ID);

    /**
     * 查询所有用户信息
     * @return
     */
    List<Teacher> getAll();
}
