package com.project.etsapi.service.Implment;

import com.project.etsapi.entity.Project;
import com.project.etsapi.entity.Teacher;
import com.project.etsapi.mapper.ProjectMapper;
import com.project.etsapi.mapper.TeacherMapper;
import com.project.etsapi.service.ProjectService;
import com.project.etsapi.vo.ProjectInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public Boolean addProject(Project project) {
        try{
            return projectMapper.addProject(project) == 1;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Project getProject(String course_ID, String name) {
        return projectMapper.getProject(course_ID,name);
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
            ProjectInfo info = project.projectToProjectInfo(teacher.getName());
            infos.add(info);
        }
        return infos;
    }

    @Override
    public List<ProjectInfo> getToDoProjectInfoListByCourseId(String course_ID) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        // new Date()为获取当前系统时间
        String now_time = df.format(new Date());
        List<ProjectInfo> infos = new ArrayList<>();
        List<Project> projects = projectMapper.getProjectListByCourseId(course_ID);
        for(Project project: projects){
            // 截止时间在当前时间之后,开始时间在当前时间之前
            if(project.getEnd_time().compareTo(now_time) > 0 && project.getStart_time().compareTo(now_time) < 0){
                Teacher teacher = teacherMapper.getTeacher(project.getTeacher_ID());
                ProjectInfo info = project.projectToProjectInfo(teacher.getName());
                infos.add(info);
            }
        }
        return infos;
    }

    @Override
    public int deleteProject(String course_ID,String name) {
        return projectMapper.deleteProject(course_ID,name);
    }
}
