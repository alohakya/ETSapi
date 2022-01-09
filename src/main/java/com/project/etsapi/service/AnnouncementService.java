package com.project.etsapi.service;

import com.project.etsapi.entity.Announcement;

import java.util.List;

/**
 * @Created by Intellij IDEA
 * @Description
 * @Author Li Lijun
 * @Date
 * @Time 10:40
 **/


public interface AnnouncementService {
    List<Announcement> getAll(String course_ID);

    int add (Announcement announcement);

    int delete(Announcement announcement);

    Announcement get(String course_ID, String name);
}
