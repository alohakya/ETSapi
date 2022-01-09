package com.project.etsapi.service.Implment;

import com.project.etsapi.entity.Feedback;
import com.project.etsapi.mapper.FeedbackMapper;
import com.project.etsapi.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Created by Intellij IDEA
 * @Description
 * @Author Li Lijun
 * @Date
 * @Time 23:55
 **/

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    FeedbackMapper feedbackMapper;
    @Override
    public List<Feedback> getAll(String course_ID) {
        return feedbackMapper.getAll(course_ID);
    }

    @Override
    public int addFeedback(Feedback feedback) {
        if(feedbackMapper.getAll(feedback.getCourse_ID()).contains(feedback)){
            return -1;
        }
        else{
            return feedbackMapper.addFeedback(feedback);
        }
    }

    @Override
    public int deleteFeedback(Feedback feedback) {
        if(feedbackMapper.get(feedback.getCourse_ID(), feedback.getStudent_ID(),
                feedback.getSubmit_time())!=null){
            return feedbackMapper.deleteFeedback(feedback);
        }
        else{
            return -1;
        }
    }

    @Override
    public Feedback get(String course_ID, String student_ID, String submit_time) {
        return feedbackMapper.get(course_ID,student_ID,submit_time);
    }
}
