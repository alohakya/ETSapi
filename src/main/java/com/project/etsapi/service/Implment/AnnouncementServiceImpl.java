package com.project.etsapi.service.Implment;

import com.project.etsapi.entity.Announcement;
import com.project.etsapi.mapper.AnnouncementMapper;
import com.project.etsapi.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Created by Intellij IDEA
 * @Description
 * @Author Li Lijun
 * @Date
 * @Time 10:40
 **/

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    AnnouncementMapper announcementMapper;

    @Override
    public List<Announcement> getAll(String course_ID) {
        return announcementMapper.getAll(course_ID);
    }

    @Override
    public int add(Announcement announcement) {
        if(announcementMapper.getAll(announcement.getCourse_ID()).contains(announcement)){
            return -1;
        }
        else{
            return announcementMapper.add(announcement);
        }
    }

    @Override
    public int delete(Announcement announcement) {
        if(announcementMapper.get(announcement.getCourse_ID(), announcement.getName())!=null){
            return announcementMapper.delete(announcement);
        }
        else{
            return -1;
        }
    }

    @Override
    public Announcement get(String course_ID, String name) {
        return announcementMapper.get(course_ID,name);
    }
}
