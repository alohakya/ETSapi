package com.project.etsapi.service;

import com.project.etsapi.entity.Report;
import com.project.etsapi.vo.*;

import java.util.List;

public interface ReportService {
    List<ProjectScoreInfo> getProjectScoreInfoList(String course_ID);

    void addReport(Report report) throws Exception;

    List<ReportInfo> getTotalReportInfoList(String course_ID, String project_name);

    List<StuProScore> getStuProScoreList(String course_ID, String student_ID);

    int updateScore(CorrectInfo correctInfo);

    String getReportName(String course_ID, String student_ID, String project_name);

    List<StuPartScore> getStuTotalScore(String course_ID, String student_ID);
}
