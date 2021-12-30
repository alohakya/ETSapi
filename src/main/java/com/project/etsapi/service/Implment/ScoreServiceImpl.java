package com.project.etsapi.service.Implment;

import com.project.etsapi.mapper.AttendMapper;
import com.project.etsapi.mapper.ReportMapper;
import com.project.etsapi.mapper.TakeCourseMapper;
import com.project.etsapi.service.ScoreService;
import com.project.etsapi.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    ReportMapper reportMapper;

    @Autowired
    TakeCourseMapper takeCourseMapper;

    @Autowired
    AttendMapper attendMapper;


    @Override
    public List<ProjectScoreInfo> getProjectScoreInfoList(String course_ID) {
        return reportMapper.getProjectScoreInfoList(course_ID);
    }

    @Override
    public List<StuPartScore> getStuProScoreList(String course_ID, String student_ID) {
        return reportMapper.getStuProScoreList(course_ID,student_ID);
    }

    @Override
    public PartScore getPartScore(String course_ID, String student_ID) {
        return takeCourseMapper.getPartScore(course_ID,student_ID);
    }

    @Override
    public List<List<StuPartScore>> getStuTotalScore(String course_ID, String student_ID) {
        List<List<StuPartScore>> result = new ArrayList<>();
        result.add(reportMapper.getStuTotalScore(course_ID,student_ID));
        result.add(attendMapper.getStuTotalScore(course_ID,student_ID));
        return result;
    }

    @Override
    public List<ScoreInfo> getTotalScoreList(String course_ID) {
        return takeCourseMapper.getTotalScoreList(course_ID);
    }
}
