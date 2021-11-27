package com.project.etsapi.service.Implment;

import com.project.etsapi.entity.Project;
import com.project.etsapi.mapper.ProjectMapper;
import com.project.etsapi.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ProjectService
 * @Description
 * @Author llj
 * @Date 2021/11/19 9:10
 **/

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectMapper projectMapper;

    @Override
    public int addProject(Project project) {
        if(projectMapper.getProject(project.getProject_ID()) != null){
            return -1;
        }
        return projectMapper.addProject(project);
    }

    @Override
    public Project getProject(String project_ID) {
        return projectMapper.getProject(project_ID);
    }

    @Override
    public List<Project> getProjectListByCourseId(String course_ID) {
        return projectMapper.getProjectListByCourseId(course_ID);
    }
}
