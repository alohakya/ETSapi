package com.project.etsapi.controller;

import com.project.etsapi.service.AttendService;
import com.project.etsapi.service.ReportService;
import com.project.etsapi.service.ScoreService;
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
    ScoreService scoreService;

    /**
     * @description: 获得所有学生总成绩
     * @path:
     * @type:
     * @param: course_ID
     * @return: java.util.List<com.project.etsapi.vo.ScoreInfo>
     * @date: 2021/12/30 13:31
     */
    @GetMapping("/getTotalScoreList")
    public List<ScoreInfo> getTotalScoreList(@RequestParam("course_ID") String course_ID){
        return scoreService.getTotalScoreList(course_ID);
    }

    /**
     * @description: 获得课程所有实验成绩信息列表
     * @path:
     * @type:
     * @param: course_ID
     * @return: java.util.List<com.project.etsapi.vo.ProjectScoreInfo>
     * @date: 2021/12/30 13:31
     */
    @GetMapping("/getProjectScoreInfoList")
    public List<ProjectScoreInfo> getProjectScoreInfoList(@RequestParam("course_ID") String course_ID){
        return scoreService.getProjectScoreInfoList(course_ID);
    }

    /**
     * @description: 获得指定学生的各个实验成绩
     * @path: "/score/getStuProScoreList"
     * @type: get
     * @param: course_ID
     * @param: student_ID
     * @return: java.util.List<com.project.etsapi.vo.StuProScore>
     * @date: 2021/12/14 22:54
     */
    @GetMapping("/getStuProScoreList")
    public List<StuPartScore> getStuProScoreList(String course_ID, String student_ID){
        return scoreService.getStuProScoreList(course_ID,student_ID);
    }

    /**
     * @description: 获得指定学生的实验总成绩与考勤总成绩
     * @path: "/score/getPartScore"
     * @type: get
     * @param: course_ID
     * @param: student_ID
     * @return: com.project.etsapi.vo.PartScore
     * @date: 2021/12/14 23:15
     */
    @GetMapping("/getPartScore")
    public PartScore getPartScore(String course_ID,String student_ID){
        return scoreService.getPartScore(course_ID,student_ID);
    }

    /**
     * @description: 获得指定学生的各个实验与考勤成绩
     * @path:
     * @type:
     * @param: student_ID
     * @param: course_ID
     * @return: java.util.List<java.util.List < com.project.etsapi.vo.StuPartScore>>
     * @date: 2021/12/30 13:33
     */
    @GetMapping("/getStuTotalScore")
    public List<List<StuPartScore>> getStuTotalScore(String student_ID,String course_ID){
        return scoreService.getStuTotalScore(course_ID,student_ID);
    }
}
