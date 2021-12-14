package com.project.etsapi.service.Implment;

import com.project.etsapi.entity.File;
import com.project.etsapi.entity.Project;
import com.project.etsapi.mapper.FileMapper;
import com.project.etsapi.service.FileService;
import com.project.etsapi.vo.FileInfo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;
    private final String basePath = "C:/Users/Administrator/Desktop/ETS/";
//    private final String basePath = "E:/PC/Desktop/";
    private final String projectPath = "/实验资料";
    private final String coursePath = "/课程资料";
    private final String photoPath = "/课程头像";
    private final String reportPath = "/实验报告/";

    @Override
    public void addFile(File file){
        if(fileMapper.getFile(file.getCourse_ID(),file.getFile_name(),file.getPath()) != null){
            System.out.println("!null");
            fileMapper.updateFile(file);
        }
        else {
            fileMapper.addFile(file);
        }
    }

    @Override
    public List<FileInfo> getFileInfoListByPath(String course_ID, String path) {
        List<FileInfo> result = new ArrayList<>();
        List<File> tmp = fileMapper.getFileList(course_ID,path);
        for (File file : tmp) {
            result.add(file.toFileInfo());
        }
        return result;
    }

    @Override
    public List<String> getFileNameListByPath(String course_ID, String path, Boolean isProject) {
        return fileMapper.getFileNameListByPath(course_ID,isProject?projectPath + path:coursePath + path);
    }

    @Override
    public List<String> getFolderNameListByType(String course_ID, Boolean isProject) {
        return fileMapper.getFileNameListByPath(course_ID,isProject?projectPath:coursePath);
    }

    @Override
    public void deleteFileByProject(Project project) {
        fileMapper.deleteFilesByPath(project.getCourse_ID(),project.getProjectPath());
    }

    @Override
    public String deleteFile(String course_ID, String path, String file_name){
        try {
            removeFile(course_ID,path,file_name);
            return fileMapper.deleteFile(course_ID,path,file_name)==1?"1":"-1";
        } catch (Exception e) {
            return "-1";
        }
    }

    @Override
    public void saveProjectFiles(Project project, List<MultipartFile> fileList) throws Exception{
        java.io.File filePath = new java.io.File(basePath +
                project.getCourse_ID() + project.getProjectPath());
        if(!filePath.exists()) {
            filePath.mkdirs();
            this.addFile(new File(project.getCourse_ID(), project.getName(),projectPath));
        }
        //保存所有文件
        for(MultipartFile file:fileList){
            file.transferTo(new java.io.File(filePath + "/" + file.getOriginalFilename()));
            this.addFile(new File(project.getCourse_ID(),file.getOriginalFilename(),
                    project.getProjectPath(),file.getSize()));
        }
    }

    @Override
    public void saveFile(MultipartFile file, String course_ID, String path) throws Exception {
        String fileName = file.getOriginalFilename();
        java.io.File filePath = new java.io.File(basePath + course_ID + path);
        java.io.File fileFile = new java.io.File(filePath + "/" + fileName);
        if(!filePath.exists()) {
            filePath.mkdirs();
        }
        else if(fileFile.exists()){
            fileFile.delete();
        }
        file.transferTo(fileFile);
    }

    @Override
    public void saveReport(String course_ID, String project_name,
                           MultipartFile report) throws Exception{
        saveFile(report,course_ID,this.reportPath + project_name);
    }

    @Override
    public String savePhoto(MultipartFile file, String course_ID){
        //先删除头像记录
        List<File> tmp = fileMapper.getFileList(course_ID,photoPath);
        try {
            if (tmp.size() != 0) {
                fileMapper.deleteFile(course_ID, photoPath, tmp.get(0).getFile_name());
                removeFile(course_ID, photoPath, tmp.get(0).getFile_name());
            }
            saveFile(file, course_ID, photoPath);
            this.addFile(new File(course_ID, file.getOriginalFilename(), photoPath,file.getSize()));
            return "1";
        }
        catch (Exception e){
            return "-1";
        }
    }

    @Override
    public void removeFile(String course_ID, String path, String file_name) throws Exception {
        java.io.File file = new java.io.File(basePath + course_ID + path + '/' + file_name);
        if(file.exists()){
            file.delete();
        }
    }

    @Override
    public void removeFileByProject(Project project, List<MultipartFile> fileList) {
        try{
            for (MultipartFile file : fileList) {
                this.removeFile(project.getCourse_ID(), project.getProjectPath(), file.getOriginalFilename());
            }
            this.removeFile(project.getCourse_ID(),projectPath,project.getName());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String downloadFile(HttpServletResponse response,
                               String course_ID, String path, String file_name) {
        java.io.File file = new java.io.File(basePath + course_ID + path + '/' + file_name);
        try {
            if(file.exists()){
                FileInputStream is = new FileInputStream(file);
                response.setContentType(this.getServletContext(file_name));
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Content-Disposition", "attachment;filename="
                        + URLEncoder.encode(file_name,"UTF-8"));
                ServletOutputStream os = response.getOutputStream();
                IOUtils.copy(is, os);
                is.close();
                return "1";
            }
            return "-1";
        }
        catch (Exception e){
            e.printStackTrace();
            return "-1";
        }
    }

    private String getServletContext(String file_name) {
        String[] tmp = file_name.split("\\.");
        switch (tmp[tmp.length - 1]) {
            case "doc":
                return "application/msword";
            case "ppt":
                return "application/vnd.ms-powerpoint";
            case "pdf":
                return "application/pdf";
            case "png":
                return "image/png";
            case "txt":
                return "text/plain";
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "html":
            case "htm":
                return "text/html";
            case "xsl":
            case "xml":
                return "text/xml";
            case "bin":
            case "exe":
            case "so":
            case "dll":
                return "application/octet-stream";
            default:
                return null;
        }
    }
}
