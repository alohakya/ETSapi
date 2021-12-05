package com.project.etsapi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

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
