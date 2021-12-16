package com.project.etsapi.controller;

import com.project.etsapi.service.AttendService;
import com.project.etsapi.service.ReportService;
import com.project.etsapi.service.TakeCourseService;
import com.project.etsapi.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    TakeCourseService takeCourseService;

    @Autowired
    ReportService reportService;

    @Autowired
    AttendService attendService;

    @GetMapping("/getTotalScore")
    public List<ScoreInfo> getTotalScoreList(@RequestParam("course_ID") String course_ID){
        return takeCourseService.getTotalScoreList(course_ID);
    }

    @GetMapping("/getProjectScoreInfoList")
    public List<ProjectScoreInfo> getProjectScoreInfoList(@RequestParam("course_ID") String course_ID){
        return reportService.getProjectScoreInfoList(course_ID);
    }


    /**
     * @description: 获得学生项目成绩列表
     * @path: "/score/getStuProScoreList"
     * @type: get
     * @param: course_ID
     * @param: student_ID
     * @return: java.util.List<com.project.etsapi.vo.StuProScore>
     * @date: 2021/12/14 22:54
     */
    @GetMapping("/getStuProScoreList")
    public List<StuProScore> getStuProScoreList(String course_ID, String student_ID){
        return reportService.getStuProScoreList(course_ID,student_ID);
    }

    /**
     * @description: 获得学生考勤成绩attend_score和实验成绩project_score
     * @path: "/score/getPartScore"
     * @type: get
     * @param: course_ID
     * @param: student_ID
     * @return: com.project.etsapi.vo.PartScore
     * @date: 2021/12/14 23:15
     */
    @GetMapping("/getPartScore")
    public PartScore getPartScore(String course_ID,String student_ID){
        return takeCourseService.getPartScore(course_ID,student_ID);
    }

    @GetMapping("/getStuTotalScore")
    public List<List<StuPartScore>> getStuTotalScore(String student_ID,String course_ID){
        List<List<StuPartScore>> result = new ArrayList<>();
        result.add(reportService.getStuTotalScore(course_ID,student_ID));
        result.add(attendService.getStuTotalScore(course_ID,student_ID));
        return result;
    }
}
