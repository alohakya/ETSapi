package com.project.etsapi.mapper;

import com.project.etsapi.entity.File;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
    int addFile(File file);

    File getFile(String course_ID,String file_name,String path);

    File getPhoto(String course_ID,String path);

    void deleteFilesByPath(String course_ID, String path);

    List<File> getFileList(String course_ID, String path);

    int deleteFile(String course_ID,String path,String file_name);
}
