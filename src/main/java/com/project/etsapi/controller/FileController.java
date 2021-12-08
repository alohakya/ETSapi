package com.project.etsapi.controller;

import com.project.etsapi.service.FileService;
import com.project.etsapi.service.ProjectService;
import com.project.etsapi.vo.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;
    @Autowired
    private ProjectService projectService;

    @GetMapping("/getTotalFiles")
    @ResponseBody
    public List<List<String>> getTotalFiles(@RequestParam("course_ID") String course_ID){
        List<String> list1 = fileService.getFileNameList(course_ID,"/课程资料");
        List<String> list2 = projectService.getProjectNameListByCourseId(course_ID);
        List<List<String>> result = new ArrayList<>();
        result.add(list1);
        result.add(list2);
        return result;
    }


    @GetMapping("/getFileList")
    public List<FileInfo> getFiles(@RequestParam("course_ID") String course_ID,
                                   @RequestParam("path") String path){
        return fileService.getFileInfoList(course_ID, path);
    }

    @PostMapping("/upload")
    public void uploadFile(MultipartFile file){
        System.out.println("文件类型：" + file.getContentType());
        System.out.println("文件组件名称：" + file.getName());
        System.out.println("文件大小：" + file.getSize()/1024 + "KB");
        System.out.println("文件名称：" + file.getOriginalFilename());
    }

    @PostMapping("/multiUpload")
    public void multiUpload(HttpServletRequest request){
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        for(MultipartFile file:files){
            System.out.println(file.getOriginalFilename());
        }
    }
}
