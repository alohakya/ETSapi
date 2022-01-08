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

    @PostMapping("/addFeedback")
    String addFeedback (Feedback feedback){
        return String.valueOf(feedbackService.addFeedback(feedback));
//        try{
//            fileService.addReport(reportTemplate);
//            reportService.addReport(reportTemplate.toReport());
//            return "1";
//        }catch (Exception e){
//            e.printStackTrace();
//            return "-1";
//        }
    }
    /**
     * @description: 学生考勤
     * @path: "/attend/addAttend"
     * @type: post
     * @param: attend
     * @return: java.lang.String
     * 返回1：成功
     * 返回-1：已考勤
     * 返回-2：数据库操作失败，可能course_ID 或 start_time 或 student_ID不存在
     * @date: 2021/12/8 23:26
     */
//    @PostMapping("/addAttend")
//    public String addAttend(Attend attend){
//        try {
//            System.out.println(attend.getCourse_ID());
//            System.out.println(attend.getStart_time());
//            System.out.println(attend.getStudent_ID());
//            return String.valueOf(attendService.addAttend(attend));
//        } catch (Exception e) {
//            return "-2";
//        }
//    }
}
