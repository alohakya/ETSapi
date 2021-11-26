package com.project.etsapi.controller;

import com.project.etsapi.entity.Project;
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

    /**
     * @description: 添加新项目
     * @path: "/project/add"
     * @param: project
     * @return: java.lang.String
     * @date: 2021/11/26 15:41
     */
    @PostMapping( "/add")
    @ResponseBody
    public String addProject(Project project) {
        return String.valueOf(projectService.addProject(project));
    }


    /**
     * @description: 根据项目id查询项目信息
     * @path: "/project/get"
     * @param: project_ID
     * @return: com.project.etsapi.entity.Project
     * @date: 2021/11/26 15:44
     */
    @GetMapping("/get")
    @ResponseBody
    public Project getProject(String project_ID){
        return projectService.getProject(project_ID);
    }
}
