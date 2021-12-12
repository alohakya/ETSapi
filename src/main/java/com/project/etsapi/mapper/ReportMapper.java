package com.project.etsapi.mapper;

import com.project.etsapi.vo.ProjectScoreInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReportMapper {
    List<ProjectScoreInfo> getProjectScoreInfoList(String course_ID);
}
