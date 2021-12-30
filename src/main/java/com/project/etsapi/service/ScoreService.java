package com.project.etsapi.service;

import com.project.etsapi.vo.*;

import java.util.List;

public interface ScoreService {

    List<ProjectScoreInfo> getProjectScoreInfoList(String course_ID);

    List<StuPartScore> getStuProScoreList(String course_ID, String student_ID);

    PartScore getPartScore(String course_ID, String student_ID);

    List<List<StuPartScore>> getStuTotalScore(String course_ID, String student_ID);

    List<ScoreInfo> getTotalScoreList(String course_ID);
}
