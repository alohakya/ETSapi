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

    @GetMapping("/getTotalFiles")
    @ResponseBody
    public List<List<String>> getTotalFiles(@RequestParam("course_ID") String course_ID){
        List<String> list1 = fileService.getFileNameListByPath(course_ID,false);
        List<String> list2 = fileService.getFileNameListByPath(course_ID,true);
        List<List<String>> result = new ArrayList<>();
        result.add(list1);
        result.add(list2);
        return result;
    }

    @GetMapping("/getFileList")
    public List<FileInfo> getFiles(@RequestParam("course_ID") String course_ID,
                                   @RequestParam("path") String path){
        return fileService.getFileInfoListByPath(course_ID, path);
    }

    @GetMapping("/getDirectoryFiles")
    public List<DirectoryFileInfo> getDirectoryFiles(String course_ID, String isProject){
        //查询所有文件夹
        List<DirectoryFileInfo> result = new ArrayList<>();
        List<String> folders = fileService.getFileNameListByPath(course_ID,isProject.equals("1"));
        List<File> files = fileService.getFiles(course_ID,isProject);
        for (String folder : folders) {
            DirectoryFileInfo tmp = new DirectoryFileInfo();
            tmp.setFolderName(folder);
            tmp.setFileName(fileService.getFileNameByFolder(folder,files));
            result.add(tmp);
        }
        return result;
    }

    @PostMapping("/uploadFile")
    public String uploadFile(HttpServletRequest request){
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        String course_ID = request.getParameter("course_ID");
        String path = request.getParameter("path");
        try {
            for(MultipartFile file:files){
                fileService.saveFile(file,course_ID,path);
            }
        }
        catch (Exception e){
            return "-1";
        }
        return "1";
    }

    @PostMapping("/uploadPhoto")
    public String uploadPhoto(HttpServletRequest request,String course_ID){
        MultipartFile file = ((MultipartHttpServletRequest) request).getFile("photo");
        try {
            fileService.savePhoto(file,course_ID);
        }
        catch (Exception e){
            e.printStackTrace();
            return "-1";
        }
        return "1";
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
