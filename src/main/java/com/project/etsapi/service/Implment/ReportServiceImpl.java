package com.project.etsapi.service.Implment;

import com.project.etsapi.entity.Report;
import com.project.etsapi.mapper.ReportMapper;
import com.project.etsapi.service.ReportService;
import com.project.etsapi.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportMapper reportMapper;

    @Override
    public void addReport(Report report) throws Exception {
        if(reportMapper.getReport(report.getCourse_ID(),report.getStudent_ID(),
                report.getProject_name()) != null){
            reportMapper.updateReport(report);
        }
        else{
            reportMapper.addReport(report);
        }
    }

    @Override
    public int addEmptyReport(Report report) {
        if(reportMapper.getReport(report.getCourse_ID(), report.getStudent_ID(), report.getProject_name())!=null){
            reportMapper.updateReport(report);
            return 2;
        }
        else{
            return reportMapper.addEmptyReport(report);
        }
    }

    @Override
    public List<ReportInfo> getTotalReportInfoList(String course_ID, String project_name) {
        return reportMapper.getTotalReportList(course_ID,project_name);
    }

    @Override
    public int updateScore(CorrectInfo correctInfo) {
        return reportMapper.updateScore(correctInfo);
    }

    @Override
    public Report getReport(String course_ID, String student_ID, String project_name) {
        return reportMapper.getReport(course_ID,student_ID,project_name);
    }

    @Override
    public List<StuPartScore> getStuTotalScore(String course_ID, String student_ID) {
        return reportMapper.getStuTotalScore(course_ID,student_ID);
    }

}
