package com.project.etsapi.service.Implment;

import com.project.etsapi.entity.File;
import com.project.etsapi.entity.Project;
import com.project.etsapi.mapper.FileMapper;
import com.project.etsapi.service.FileService;
import com.project.etsapi.vo.FileInfo;
import com.project.etsapi.vo.ReportTemplate;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
    public void removeDirFile(String path) {
        java.io.File dirFile = new java.io.File(path);
        if (!dirFile.exists()) {
            return;
        }
        if (dirFile.isFile()) {
            dirFile.delete();
        }
        else {
            for (java.io.File file : dirFile.listFiles()) {
                file.delete();
            }
        }
        dirFile.delete();
    }

    @Override
    public void removeFileByProject(String course_ID,String name) {
        removeDirFile(basePath + course_ID + projectPath + "/" + name);
    }

    @Override
    public void removeReportByProject(String course_ID,String name) {
        removeDirFile(basePath + course_ID + reportPath + name);
    }

    @Override
    public void addReport(ReportTemplate reportTemplate) throws IOException {
        java.io.File dir = new java.io.File(basePath
                + reportTemplate.getCourse_ID() + reportPath
                + reportTemplate.getProject_name());
        if(!dir.exists()){
            dir.mkdir();
        }
        java.io.File file = new java.io.File(dir + "/"
                + reportTemplate.getStudent_ID() + "_"
                + reportTemplate.getProject_name() + ".txt");
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(reportTemplate.toString());
        fileWriter.close();
    }

    @Override
    public ReportTemplate getReportContent(String course_ID, String student_ID, String project_name){
        ReportTemplate result = new ReportTemplate(course_ID,student_ID,project_name);
        try{
            String filePath = basePath + course_ID + reportPath + project_name
                    + "/" + student_ID +"_" + project_name + ".txt";
            java.io.File file = new java.io.File(filePath);
            byte[] bytes = new byte[(int) file.length()];
            FileInputStream fileInputStream = new FileInputStream(filePath);
            int ret  = fileInputStream.read(bytes);
            fileInputStream.close();
            result.setContent(new String(bytes,0,ret));
        }
        catch (IOException e){
            System.out.println("没有实验报告存档！");
        }
        return result;
    }

    @Override
    public String addFolder(String course_ID, String path, String fileName) {
        if(fileMapper.addFile(new File(course_ID,fileName,path)) != 1){
            return "-1";
        }
        String dirPath = basePath + course_ID + path + "/" + fileName;
        java.io.File dir = new java.io.File(dirPath);
        if(!dir.exists()){
            dir.mkdir();
        }
        return "1";
    }

    @Override
    public void downloadFile(HttpServletResponse response,
                               String course_ID, String path, String file_name) throws Exception {
        java.io.File file = new java.io.File(basePath + course_ID + path + '/' + file_name);
        if(file.exists()){
            FileInputStream is = new FileInputStream(file);
            response.setContentType(this.getServletContext(file_name));
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + URLEncoder.encode(file_name,"UTF-8"));
            ServletOutputStream os = response.getOutputStream();
            IOUtils.copy(is, os);
            is.close();
        }
        else{
            throw new Exception();
        }
    }

    @Override
    public void downloadReport(HttpServletResponse response, String course_ID, String project_name, String report_name) throws Exception {
        downloadFile(response, course_ID, reportPath + project_name, report_name);
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
            case"docx":
                return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
            case "bin":
            case "exe":
            case "so":
            case "dll":
            default:
                return "application/octet-stream";
        }
    }
}
