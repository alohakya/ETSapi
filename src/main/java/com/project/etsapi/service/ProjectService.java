package com.project.etsapi.service;

import com.project.etsapi.entity.Project;
import com.project.etsapi.vo.ProjectInfo;

import java.util.List;

/**
 * @InterfaceName ProjectService
 * @Description
 * @Author llj
 * @Date 2021/11/19 9:09
 **/


public interface ProjectService {
    Boolean addProject(Project project);

    Project getProject(String course_ID,String name);

    List<String> getProjectNameListByCourseId(String course_ID);

    List<Project> getProjectListByCourseId(String course_ID);

    List<ProjectInfo> getProjectInfoListByCourseId(String course_ID);

    List<ProjectInfo> getToDoProjectInfoListByCourseId(String course_ID);

    int deleteProject(Project project);
}
