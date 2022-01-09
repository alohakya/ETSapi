package com.project.etsapi.service;

import com.project.etsapi.entity.Feedback;

import java.util.List;

/**
 * @Created by Intellij IDEA
 * @Description
 * @Author Li Lijun
 * @Date
 * @Time 23:54
 **/


public interface FeedbackService {
    List<Feedback> getAll(String course_ID);

    int addFeedback (Feedback feedback);

    int deleteFeedback(Feedback feedback);

    Feedback get(String course_ID,String student_ID,String submit_time);
}
