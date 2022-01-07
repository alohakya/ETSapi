package com.project.etsapi.service;

import com.project.etsapi.entity.File;
import com.project.etsapi.entity.Project;
import com.project.etsapi.vo.FileInfo;
import com.project.etsapi.vo.ReportTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface FileService {

    void addFile(File file);

    List<FileInfo> getFileInfoListByPath(String course_ID, String path);

    List<String> getFileNameListByPath(String course_ID,String path,Boolean isProject);

    List<String> getFolderNameListByType(String course_ID, Boolean isProject);

    String deleteFile(String course_ID, String path, String file_name);

    void downloadFile(HttpServletResponse response, String course_ID, String path, String file_name) throws Exception;

    void downloadReport(HttpServletResponse response, String course_ID, String project_name, String student_ID) throws Exception;

    void saveProjectFiles(Project project, List<MultipartFile> fileList) throws Exception;

    void saveFile(MultipartFile file,String course_ID,String path) throws Exception;

    String savePhoto(MultipartFile file, String course_ID);

    void saveReport(String course_ID, String project_name, MultipartFile report) throws Exception;

    void removeDirFile(String path);

    void removeFile(String course_ID,String path,String file_name) throws Exception;

    void removeFileByProject(String course_ID,String name);

    void removeReportByProject(String course_ID,String name);

    void addReport(ReportTemplate reportTemplate) throws IOException;

    ReportTemplate getReportContent(String course_ID, String student_ID, String project_name);
}
