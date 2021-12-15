package com.project.etsapi.service;

import com.project.etsapi.entity.Report;
import com.project.etsapi.vo.CorrectInfo;
import com.project.etsapi.vo.ProjectScoreInfo;
import com.project.etsapi.vo.ReportInfo;
import com.project.etsapi.vo.StuProScore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ReportService {
    List<ProjectScoreInfo> getProjectScoreInfoList(String course_ID);

    void addReport(Report report) throws Exception;

    List<ReportInfo> getTotalReportInfoList(String course_ID, String project_name);

    List<StuProScore> getStuProScoreList(String course_ID, String student_ID);

    int updateScore(CorrectInfo correctInfo);
}
