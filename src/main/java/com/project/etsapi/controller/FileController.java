package com.project.etsapi.controller;

import com.project.etsapi.entity.File;
import com.project.etsapi.service.FileService;
import com.project.etsapi.service.ProjectService;
import com.project.etsapi.vo.DirectoryFileInfo;
import com.project.etsapi.vo.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;
    @Autowired
    private ProjectService projectService;

    /**
     * @description: 获得二级目录文件名
     * @path: "/file/getTotalFolders
     * @type: get
     * @param: course_ID
     * @return: java.util.List<java.util.List < java.lang.String>>
     * 返回1：成功
     * 返回-1：失败
     * @date: 2021/12/11 9:34
     */
    @GetMapping("/getTotalFolders")
    @ResponseBody
    public List<List<String>> getTotalFolders(@RequestParam("course_ID") String course_ID){
        List<String> list1 = fileService.getFolderNameListByType(course_ID,false);
        List<String> list2 = fileService.getFolderNameListByType(course_ID,true);
        List<List<String>> result = new ArrayList<>();
        result.add(list1);
        result.add(list2);
        return result;
    }

    /**
     * @description: 返回指定课程指定路径的文件信息列表
     * @path: "/file/getFileList"
     * @type: get
     * @param: course_ID
     * @param: path
     * @return: java.util.List<com.project.etsapi.vo.FileInfo>
     * @date: 2021/12/11 9:35
     */
    @GetMapping("/getFileList")
    public List<FileInfo> getFiles(@RequestParam("course_ID") String course_ID,
                                   @RequestParam("path") String path){
        return fileService.getFileInfoListByPath(course_ID, path);
    }

    /**
     * @description: 获得所有二级目录和各目录下的文件信息
     * @path: "/file/getDirectoryFiles"
     * @type: get
     * @param: course_ID
     * @param: isProject
     * @return: java.util.List<com.project.etsapi.vo.DirectoryFileInfo>
     * @date: 2021/12/11 9:49
     */
    @GetMapping("/getDirectoryFiles")
    public List<DirectoryFileInfo> getDirectoryFiles(String course_ID, String isProject){
        List<DirectoryFileInfo> result = new ArrayList<>();
        //查询所有二级文件夹
        List<String> folders = fileService.getFolderNameListByType(course_ID,isProject.equals("1"));
        for (String folder : folders) {
            DirectoryFileInfo tmp = new DirectoryFileInfo();
            tmp.setFolderName(folder);
            tmp.setFileName(fileService.getFileNameListByPath(course_ID,"/" + folder,isProject.equals("1")));
            result.add(tmp);
        }
        return result;
    }

    /**
     * @description: 删除文件
     * @path: "/file/deleteFile"
     * @type: post
     * @param: course_ID
     * @param: path
     * @param: file_name
     * @return: java.lang.String
     * @date: 2021/12/11 9:44
     */
    @PostMapping("/deleteFile")
    public String deleteFile(String course_ID,String path,String file_name){
        return fileService.deleteFile(course_ID,path,file_name);
    }

    /**
     * @description: 上传文件,支持多文件上传
     * @path: "/file/uploadFile"
     * @type: post
     * @param: request
     * @return: java.lang.String
     * 返回1：成功
     * 返回-1：失败
     * @date: 2021/12/11 9:40
     */
    @PostMapping("/uploadFile")
    public String uploadFile(HttpServletRequest request){
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        String course_ID = request.getParameter("course_ID");
        String path = request.getParameter("path");
        try {
            for(MultipartFile file:files){
                fileService.saveFile(file,course_ID,path);
                fileService.addFile(new File(course_ID,file.getOriginalFilename(),path,file.getSize()));
            }
            return "1";
        } catch (Exception e){
            return "-1";
        }
    }

    /**
     * @description: 上传照片
     * @path: "file/uploadPhot"
     * @type: post
     * @param: request
     * @param: course_ID
     * @return: java.lang.String
     * 返回1：成功
     * 返回-1：失败
     * @date: 2021/12/11 9:42
     */
    @PostMapping("/uploadPhoto")
    public String uploadPhoto(HttpServletRequest request,String course_ID){
        MultipartFile file = ((MultipartHttpServletRequest) request).getFile("photo");
        return fileService.savePhoto(file,course_ID);
    }

    /**
     * @description: 下载文件
     * @path: "file/downloadFile
     * @type:
     * @param: request 表单上传，需要 course_ID path file_name
     * @param: response
     * @return: java.lang.String
     * @date: 2021/12/11 9:45
     */
    @PostMapping("/downloadFile")
    public String downloadFile(HttpServletRequest request,HttpServletResponse response){
        String course_ID = request.getParameter("course_ID");
        String path = request.getParameter("path");
        String file_name = request.getParameter("file_name");
        return fileService.downloadFile(response,course_ID,path,file_name);
    }
}
