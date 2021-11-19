package com.project.etsapi.mapper;

import com.project.etsapi.entity.Project;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @InterfaceName ProjectMapper
 * @Description
 * @Author llj
 * @Date 2021/11/19 1:31
 **/

@Mapper
public interface ProjectMapper {
    /**
     * 根据课程编号获取项目列表
     * @param course_ID
     * @return
     */
    List<Project> getProjectListByCourseId(String course_ID);

    /**
     * 教师发布新项目
     * @param project
     * @return
     */
    int addProject(Project project);

    /**
     * 根据课程编号获取项目列表
     * @param project_ID
     * @return
     */

    Project getProject(String project_ID);
}
