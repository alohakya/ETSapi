package com.project.etsapi.service;

import com.project.etsapi.entity.File;
import com.project.etsapi.entity.Project;

public interface FileService {
    Boolean addFile(File file);

    File getFile(String course_ID,String file_name,String path);

    int deleteFileByProject(Project project);
}
