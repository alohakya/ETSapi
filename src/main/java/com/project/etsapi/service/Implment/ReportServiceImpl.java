package com.project.etsapi.service.Implment;

import com.project.etsapi.mapper.ReportMapper;
import com.project.etsapi.service.ReportService;
import com.project.etsapi.vo.ProjectScoreInfo;
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
}
