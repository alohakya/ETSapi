package com.project.etsapi.mapper;

import com.project.etsapi.entity.Announcement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Created by Intellij IDEA
 * @Description
 * @Author Li Lijun
 * @Date
 * @Time 10:38
 **/

@Mapper
public interface AnnouncementMapper {
    List<Announcement> getAll(String course_ID);

    int add (Announcement announcement);

    int delete(Announcement announcement);

    Announcement get(String course_ID, String name);
}
