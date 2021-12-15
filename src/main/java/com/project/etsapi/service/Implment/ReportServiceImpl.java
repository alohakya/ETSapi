package com.project.etsapi.service.Implment;

import com.project.etsapi.entity.Report;
import com.project.etsapi.mapper.ReportMapper;
import com.project.etsapi.service.ReportService;
import com.project.etsapi.vo.ProjectScoreInfo;
import com.project.etsapi.vo.ReportInfo;
import com.project.etsapi.vo.StuProScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportMapper reportMapper;

    @Override
    public List<ProjectScoreInfo> getProjectScoreInfoList(String course_ID) {
        return reportMapper.getProjectScoreInfoList(course_ID);
    }

    @Override
    public void addReport(Report report) throws Exception {
//        System.out.println(report.getCourse_ID());
//        System.out.println(report.getProject_name());
//        System.out.println(report.getStudent_ID());

        System.out.println(reportMapper.getReport(report.getCourse_ID(),report.getProject_name(),
                report.getStudent_ID()) == null);
//        if(reportMapper.getReport(report.getCourse_ID(),report.getProject_name(),
//                report.getStudent_ID()) != null){
//            reportMapper.updateReport(report);
//        }
//        else{
//            reportMapper.addReport(report);
//        }
    }

    @Override
    public List<ReportInfo> getTotalReportInfoList(String course_ID, String project_name) {
        return reportMapper.getTotalReportList(course_ID,project_name);
    }

    @Override
    public List<StuProScore> getStuProScoreList(String course_ID, String student_ID) {
        return reportMapper.getStuProScoreList(course_ID,student_ID);
    }

}
