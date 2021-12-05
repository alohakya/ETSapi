package com.project.etsapi.service;

import com.project.etsapi.entity.File;
import com.project.etsapi.entity.Project;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    Boolean addFile(File file);

    File getFile(String course_ID,String file_name,String path);

    int deleteFileByProject(Project project);

    String saveFiles(Project project,List<MultipartFile> fileList) throws Exception;
}
