package com.project.etsapi.service.Implment;

import com.project.etsapi.entity.Project;
import com.project.etsapi.entity.Teacher;
import com.project.etsapi.mapper.ProjectMapper;
import com.project.etsapi.mapper.TeacherMapper;
import com.project.etsapi.service.ProjectService;
import com.project.etsapi.vo.ProjectInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public int addProject(Project project) {
        if(projectMapper.getProject(project.getProject_ID()) == null){
            // 新建项目的名下文件个数设为0
            project.setPath_number(0);
            return projectMapper.addProject(project);
        }
        else{
            // 返回-1，该项目已存在
            return -1;
        }
    }

    @Override
    public Project getProject(String project_ID) {
        return projectMapper.getProject(project_ID);
    }

    @Override
    public List<Project> getProjectListByCourseId(String course_ID) {
        return projectMapper.getProjectListByCourseId(course_ID);
    }

    @Override
    public List<ProjectInfo> getProjectInfoListByCourseId(String course_ID) {
        List<ProjectInfo> infos = new ArrayList<>();
        List<Project> projects = projectMapper.getProjectListByCourseId(course_ID);
        for(Project project: projects){
            Teacher teacher = teacherMapper.getTeacher(project.getTeacher_ID());
            ProjectInfo info = new ProjectInfo(project.getProject_ID(), project.getName(),
                    project.getStart_time(), project.getEnd_time(), project.getDescription(),
                    project.getPath_number(), teacher.getName(),
                    course_ID, project.getPercentage());
            infos.add(info);
        }
        return infos;
    }


}
