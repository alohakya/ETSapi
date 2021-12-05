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
import java.io.*;
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
     * @date: 2021/11/26 15:41
     */
    @PostMapping( "/add")
    @ResponseBody
    public String addProject(HttpServletRequest request,Project project){
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        project.setPercentage(0);
        if (!projectService.addProject(project)) {
            return "false";
        }
        //写文件到本地
        for(MultipartFile file:files){
            //写文件
            File filePath = new File("E:/PC/Desktop/" +
                    project.getCourse_ID() + "/实验项目/" + project.getName());
            if(!filePath.exists()) {
                filePath.mkdirs();
            }
            try {
                //写文件到目标路径
                String fileName = file.getOriginalFilename();
                file.transferTo(new File(filePath + "/" + fileName));
                //将文件记录写到数据库
                com.project.etsapi.entity.File dbFile = new com.project.etsapi.entity.File(
                        project.getCourse_ID(),fileName,filePath.toString(),project.getName());
                fileService.addFile(dbFile);
            } catch (Exception e) {
                //写数据库出错或写文件出错，手动回滚
                e.printStackTrace();
                projectService.deleteProject(project);
                fileService.deleteFileByProject(project);
                return "false";
            }
        }
        return "true";
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

    /**
     * @description: 根据课程ID获得待完成项目信息列表
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
