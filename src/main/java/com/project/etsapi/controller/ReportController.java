package com.project.etsapi.controller;

import com.project.etsapi.entity.Report;
import com.project.etsapi.entity.TakeCourse;
import com.project.etsapi.service.FileService;
import com.project.etsapi.service.ReportService;
import com.project.etsapi.service.TakeCourseService;
import com.project.etsapi.vo.CorrectInfo;
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

    @Autowired
    TakeCourseService takeCourseService;

    /**
     * @description: 上传报告
     * @path: "/report/add"
     * @type: post
     * @param: request
     * @return: java.lang.String
     * 返回1：成功
     * 返回-1：文件上传失败
     * 返回-2：没有报告文件
     * @date: 2021/12/15 19:40
     */
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

    /**
     * @description: 获得指定课程的某一实验的所有学生实验信息
     * @path: "/report/getTotalReport
     * @type: get
     * @param: course_ID
     * @param: project_name
     * @return: java.util.List<com.project.etsapi.vo.ReportInfo>
     * @date: 2021/12/15 19:43
     */
    @GetMapping("/getTotalReport")
    public List<ReportInfo> getTotalReportList(@RequestParam("course_ID") String course_ID,
                                               @RequestParam("project_name") String project_name){
        return reportService.getTotalReportInfoList(course_ID,project_name);
    }

    /**
     * @description: 批改实验报告
     * @path: "/report/correct"
     * @type: post
     * @param: course_ID
     * @param: project_name
     * @param: student_ID
     * @param: score
     * @return: java.lang.String
     * 返回1：成功
     * 返回-1：批改失败
     * @date: 2021/12/15 19:42
     */
    @PostMapping("/correct")
    public String correctReport(String course_ID,String project_name,String student_ID,Integer old_score,Integer score){
        System.out.println(course_ID);
        System.out.println(project_name);
        System.out.println(student_ID);
        System.out.println(score);
        CorrectInfo correctInfo = new CorrectInfo(course_ID,project_name,student_ID,score);
        if (reportService.updateScore(correctInfo) == 1){
            takeCourseService.updateProjectScore(course_ID,student_ID,old_score==null?score:score-old_score);
            return "1";
        }
        return "-1";
    }

    @GetMapping("/getName")
    public String getReportName(String course_ID,String project_name,String student_ID){
        return reportService.getReportName(course_ID,student_ID,project_name);
    }
}
