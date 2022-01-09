package com.project.etsapi.controller;

import com.project.etsapi.entity.Announcement;
import com.project.etsapi.service.AnnouncementService;
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
 * @Time 10:37
 **/

@RestController
@RequestMapping("/announcement")
public class AnnouncementController {
    @Autowired
    AnnouncementService announcementService;
    @GetMapping("/getAll")
    List<Announcement> getAll(String course_ID){
        return announcementService.getAll(course_ID);
    }

    @PostMapping("/addAnnouncement")
    String addAnnouncement (Announcement announcement){
        return String.valueOf(announcementService.add(announcement));
    }

}
