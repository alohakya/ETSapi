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
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
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
            }
            return "1";
        }
        catch (Exception e){
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

    @PostMapping("/uploadReport")
    public String uploadReport(HttpServletRequest request){
        String course_ID = request.getParameter("course_ID");
        String student_ID = request.getParameter("student_ID");
        String project_name = request.getParameter("project_name");
        MultipartFile report = ((MultipartHttpServletRequest)request).getFile("file");
        return fileService.saveReport(course_ID,student_ID,project_name,report);
    }

    @PostMapping("/getPhoto")
    public String getPhoto(HttpServletResponse response, String course_ID) throws Exception{
        //要下载的文件路径
        java.io.File file = new java.io.File("E:\\PC\\Desktop\\42024401\\课程头像\\1.jpg");
        //下载后的文件名
        String fileName = "1.jpg";
        if (file.exists()) {
            // 配置文件下载
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            // 下载文件能正常显示中文
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.setHeader("Content-Length",""+file.length());
            // 实现文件下载
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return "1";
    }
}
