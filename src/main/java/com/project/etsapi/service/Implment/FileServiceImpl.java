package com.project.etsapi.service.Implment;

import com.project.etsapi.entity.File;
import com.project.etsapi.entity.Project;
import com.project.etsapi.mapper.FileMapper;
import com.project.etsapi.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;

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
    public int deleteFileByProject(Project project) {
        return fileMapper.deleteFileByProject(project.getCourse_ID(),project.getName());
    }
}
