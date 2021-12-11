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
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;
//    private final String basePath = "C:/Users/Administrator/Desktop/ETS/";
    private final String basePath = "E:/PC/Desktop/";
    private final String projectPath = "/实验资料";
    private final String coursePath = "/课程资料";
    private final String photoPath = "/课程头像";

    @Override
    public Boolean addFile(File file) {
        try{
            return fileMapper.addFile(file) == 1;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public File getFile(String course_ID, String file_name,String path) {
        return fileMapper.getFile(course_ID,file_name,path);
    }

    @Override
    public List<FileInfo> getFileInfoListByPath(String course_ID, String path) {
        return fileMapper.getFileInfoList(course_ID,path);
    }

    @Override
    public List<String> getFolderNameListByType(String course_ID, Boolean isProject) {
        List<String> result = new ArrayList<>();
        List<FileInfo> tmpFile = this.getFileInfoListByPath(course_ID,isProject?projectPath:coursePath);
        for (FileInfo file:tmpFile) {
            result.add(file.getFile_name());
        }
        return result;
    }

    @Override
    public int deleteFileByProject(Project project) {
        return 0;
//        fileMapper.deleteFile(project.getCourse_ID(), project.getProjectPath());
//        return fileMapper.deleteFile(project.getCourse_ID(),project.getName());
    }

    @Override
    public void saveProjectFiles(Project project, List<MultipartFile> fileList) throws Exception{
        for(MultipartFile file:fileList){
            java.io.File filePath = new java.io.File(basePath +
                    project.getCourse_ID() + project.getProjectPath());
            if(!filePath.exists()) {
                filePath.mkdirs();
                //保存实验项目文件夹
                fileMapper.addFile(new File(project.getCourse_ID(), project.getName(),projectPath));
            }
            String fileName = file.getOriginalFilename();
            file.transferTo(new java.io.File(filePath + "/" + fileName));
            //保存实验附带文件
            fileMapper.addFile(new File(project.getCourse_ID(),fileName, project.getProjectPath()));
        }
    }

    @Override
    public void saveFile(MultipartFile file, String course_ID, String path) throws Exception {
        java.io.File filePath = new java.io.File(basePath + course_ID + path);
        if(!filePath.exists()) {
            filePath.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        file.transferTo(new java.io.File(filePath + "/" + fileName));
        fileMapper.addFile(new File(course_ID,fileName, path));
    }

    @Override
    public void removeFile(String course_ID, String path, String file_name) throws Exception {
        java.io.File file = new java.io.File(basePath + course_ID + path + '/' + file_name);
        if(file.exists()){
            file.delete();
        }
    }

    @Override
    public List<File> getFiles(String course_ID, String isProject) {
        return fileMapper.getFileList(course_ID,isProject.equals("1")?projectPath:coursePath);
    }

    @Override
    public List<String> getFileNameByFolder(String folder, List<File> files) {
        List<String> result = new ArrayList<>();
        for (File file : files) {
            String[] tmp = file.getPath().split("/");
            if(tmp[tmp.length-1].equals(folder)){
                result.add(file.getFile_name());
            }
        }
        return result;
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
            return "1";
        }
        catch (Exception e){
            return "-1";
        }
    }

    @Override
    public MultipartFile getPhoto(String course_ID) {
        List<File> photo = fileMapper.getFileList(course_ID,photoPath);
        if(photo == null){
            return null;
        }
        else{
            return null;
        }
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
    public String downloadFile(HttpServletResponse response,String course_ID, String path, String file_name) {
        java.io.File file = new java.io.File(basePath + course_ID + path + '/' + file_name);
        try {
            if(file.exists()){
                FileInputStream is = new FileInputStream(file);
                response.setContentType("text/plain");
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
}
