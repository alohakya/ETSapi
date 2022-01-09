package com.project.etsapi.controller;

import com.project.etsapi.entity.Feedback;
import com.project.etsapi.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Created by Intellij IDEA
 * @Description
 * @Author Li Lijun
 * @Date
 * @Time 23:56
 **/

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

    @GetMapping("/getAll")
    List<Feedback> getAll(String course_ID){
        return feedbackService.getAll(course_ID);
    }

//  /feedback/addFeedback
    @PostMapping("/addFeedback")
    String addFeedback (Feedback feedback){
        return String.valueOf(feedbackService.addFeedback(feedback));
    }

    @PostMapping("/deleteFeedback")
    String deleteFeedback (Feedback feedback){
        return String.valueOf(feedbackService.deleteFeedback(feedback));
    }

}
