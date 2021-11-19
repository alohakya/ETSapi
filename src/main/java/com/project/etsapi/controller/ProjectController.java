package com.project.etsapi.controller;

import com.project.etsapi.entity.Project;
import com.project.etsapi.entity.Student;
import com.project.etsapi.service.ProjectService;
import com.project.etsapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Created by Intellij IDEA
 * @Description
 * @Author Li Lijun
 * @Date 2021/11/19
 * @Time 9:24
 **/

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private StudentService studentService;

    // 教师发布新项目
    // http://localhost:8888/project/add?project_ID=4202440103&name=实验三&release_time=2021-11-18 10:14:35&deadline=&description=&path_number=0&percentage=0.2&teacher_ID=10100&course_ID=42024401
    @PostMapping( "/add")
    @ResponseBody
    public String addProject(Project project) {
        int result = projectService.addProject(project);
        if( result >= 1){
            return "发布成功";
        }
        else{
            return "发布失败！该项目已经存在！";
        }
    }
    // 教师发布新项目
    // http://localhost:8888/project/get?project_ID=4202440103
    @GetMapping("/get")
    @ResponseBody
    public Project getProject(String project_ID){
        return projectService.getProject(project_ID);
    }

}
