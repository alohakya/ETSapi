package com.project.etsapi.controller;

import com.project.etsapi.entity.Project;
import com.project.etsapi.service.FileService;
import com.project.etsapi.service.ProjectService;
import com.project.etsapi.vo.ProjectInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    private FileService fileService;

    /**
     * @description: 添加新项目及项目附带文件
     * @type: post
     * @path: "/project/add"
     * @param: request，实验项目信息表单，除实验项目分数占比外所有实验项目信息，及文件列表
     * @return: java.lang.String
     * 返回1：成功
     * 返回-1：实验项目添加失败
     * 返回-2：文件上传失败
     * @date: 2021/11/26 15:41
     */
    @PostMapping( "/add")
    @ResponseBody
    public String addProject(HttpServletRequest request,Project project){
        List<MultipartFile> fileList = ((MultipartHttpServletRequest) request).getFiles("file");
        project.setPercentage(0);
        //增加实验项目
        if (!projectService.addProject(project)) {
            return "-1";
        }
        //上传文件
        try{
            fileService.saveProjectFiles(project, fileList);
        }
        catch (Exception e){
            //出错，手动回滚
            e.printStackTrace();
            //数据库删除实验
            projectService.deleteProject(project.getCourse_ID(),project.getName());
            //数据库删除实验已记录的文件
            fileService.deleteFileByProject(project);
            //删除服务器上的文件存档
            fileService.removeFileByProject(project,fileList);
            return "-2";
        }
        return "1";
    }

    //TODO 待完善
    @PostMapping("/delete")
    public String deleteProject(String course_ID,String name){
        return String.valueOf(projectService.deleteProject(course_ID,name));
    }

    @PostMapping("/update")
    public String updateProject(Project project){
        return String.valueOf(projectService.updateProject(project));
    }

    /**
     * @description: 根据课程id和项目name查询项目信息
     * @type: get
     * @path: "/project/get"
     * @param: course_ID,name
     * @return: com.project.etsapi.entity.Project
     * @date: 2021/11/26 15:44
     */
    @GetMapping("/get")
    @ResponseBody
    public Project getProject(String course_ID,String name){
        return projectService.getProject(course_ID,name);
    }

    //TODO 没有用到
     /**
     * @description: 根据课程ID获得项目列表
     * @type: get
     * @path: "/project/getProjectListByCourseId"
     * @param: course_ID 课程id
     * @return: java.util.List<com.project.etsapi.entity.Project>
     * @date: 2021/11/26 15:08
     */
    @GetMapping("/getProjectListByCourseId")
    @ResponseBody
    public List<Project> getProjectListByCourseId(String course_ID){
        return projectService.getProjectListByCourseId(course_ID);
    }

    /**
     * @description: 根据课程ID获得项目信息列表
     * @type: get
     * @path: "/project/getProjectInfoListByCourseId"
     * @param: course_ID 课程id
     * @return: java.util.List<com.project.etsapi.vo.ProjectInfo>
     * @date: 2021/11/26 15:08
     */
    @GetMapping("/getProjectInfoListByCourseId")
    @ResponseBody
    public List<ProjectInfo> getProjectInfoListByCourseId(String course_ID){
        return projectService.getProjectInfoListByCourseId(course_ID);
    }

    //TODO 没有用到
    /**
     * @description: 根据课程ID获得待完成项目信息列表
     * @type: get
     * @path: "/project/getToDoProjectInfoListByCourseId"
     * @param: course_ID 课程id
     * @return: java.util.List<com.project.etsapi.vo.ProjectInfo>
     * @date: 2021/11/26 15:08
     */
    @GetMapping("/getToDoProjectInfoListByCourseId")
    @ResponseBody
    public List<ProjectInfo> getToDoProjectInfoListByCourseId(String course_ID){
        return projectService.getToDoProjectInfoListByCourseId(course_ID);
    }

}
