package com.project.etsapi.mapper;

import com.project.etsapi.entity.Feedback;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Created by Intellij IDEA
 * @Description
 * @Author Li Lijun
 * @Date
 * @Time 23:52
 **/

@Mapper
public interface FeedbackMapper {
    List<Feedback> getAll(String course_ID);

    int addFeedback (Feedback feedback);

    int deleteFeedback(Feedback feedback);

    Feedback get(String course_ID,String student_ID,String submit_time);
}
