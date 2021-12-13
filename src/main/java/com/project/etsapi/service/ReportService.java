package com.project.etsapi.service;

import com.project.etsapi.entity.Report;
import com.project.etsapi.vo.ProjectScoreInfo;

import java.util.List;

public interface ReportService {
    List<ProjectScoreInfo> getProjectScoreInfoList(String course_ID);

    void addReport(Report report) throws Exception;
}
