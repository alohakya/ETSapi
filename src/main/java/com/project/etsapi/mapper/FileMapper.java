package com.project.etsapi.mapper;

import com.project.etsapi.entity.File;
import com.project.etsapi.vo.FileInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
    int addFile(File file);

    File getFile(String course_ID,String file_name,String path);

    int deleteFileByProject(String course_ID,String project_name);

    List<FileInfo> getFiles(String course_ID, String path);
}
