package com.project.etsapi.controller;

import com.project.etsapi.service.ReportService;
import com.project.etsapi.service.TakeCourseService;
import com.project.etsapi.vo.ProjectScoreInfo;
import com.project.etsapi.vo.ScoreInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    TakeCourseService takeCourseService;

    @Autowired
    ReportService reportService;

    @GetMapping("/getTotalScore")
    public List<ScoreInfo> getTotalScoreList(@RequestParam("course_ID") String course_ID){
        return takeCourseService.getTotalScoreList(course_ID);
    }

    @GetMapping("/getProjectScoreInfoList")
    public List<ProjectScoreInfo> getProjectScoreInfoList(@RequestParam("course_ID") String course_ID){
        return reportService.getProjectScoreInfoList(course_ID);
    }
}
