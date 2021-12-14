package com.project.etsapi.controller;

import com.project.etsapi.entity.Report;
import com.project.etsapi.service.FileService;
import com.project.etsapi.service.ReportService;
import com.project.etsapi.vo.ReportInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    ReportService reportService;

    @Autowired
    FileService fileService;

    @PostMapping("/add")
    public String uploadReport(HttpServletRequest request){
        String course_ID = request.getParameter("course_ID");
        String student_ID = request.getParameter("student_ID");
        String project_name = request.getParameter("project_name");
        MultipartFile report = ((MultipartHttpServletRequest)request).getFile("file");
        if(report == null || report.isEmpty()){
            return "-2";
        }
        try {
            fileService.saveReport(course_ID,project_name,report);
            reportService.addReport(new Report(course_ID,student_ID, project_name,report.getOriginalFilename()));
            return "1";
        }
        catch (Exception e){
            e.printStackTrace();
            return "-1";
        }
    }

    @GetMapping("/getTotalReport")
    public List<ReportInfo> getTotalReportList(@RequestParam("course_ID") String course_ID,
                                               @RequestParam("project_name") String project_name){
        return reportService.getTotalReportInfoList(course_ID,project_name);
    }
}
