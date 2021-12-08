package com.project.etsapi.service.Implment;

import com.project.etsapi.entity.File;
import com.project.etsapi.entity.Project;
import com.project.etsapi.mapper.FileMapper;
import com.project.etsapi.service.FileService;
import com.project.etsapi.vo.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;
//    private final String basePath = "C:/Users/Administrator/Desktop/ETS/";
    private final String basePath = "E:/PC/Desktop/";

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
    public List<FileInfo> getFileInfoList(String course_ID, String path) {
        return fileMapper.getFiles(course_ID,path);
    }

    @Override
    public List<String> getFileNameList(String course_ID, String path) {
        List<String> result = new ArrayList<>();
        List<FileInfo> tmp = this.getFileInfoList(course_ID,path);
        for (FileInfo f:tmp) {
            result.add(f.getFile_name());
        }
        return result;
    }

    @Override
    public int deleteFileByProject(Project project) {
        return fileMapper.deleteFileByProject(project.getCourse_ID(),project.getName());
    }

    @Override
    public String saveFiles(Project project,List<MultipartFile> fileList) throws Exception{
        for(MultipartFile file:fileList){
            java.io.File filePath = new java.io.File(basePath +
                    project.getCourse_ID() + project.getProjectPath());
            if(!filePath.exists()) {
                filePath.mkdirs();
                fileMapper.addFile(new File(project.getCourse_ID(), project.getName(),
                        "/实验资料",project.getName()));
            }
            String fileName = file.getOriginalFilename();
            file.transferTo(new java.io.File(filePath + "/" + fileName));
            fileMapper.addFile(new File(project.getCourse_ID(),fileName,
                    project.getProjectPath(),project.getName()));
        }
        return "1";
    }
}
