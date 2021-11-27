package com.project.etsapi.service.Implment;

import com.project.etsapi.entity.Attendance;
import com.project.etsapi.mapper.AttendMapper;
import com.project.etsapi.service.AttendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendServiceImpl implements AttendService {
    @Autowired
    AttendMapper attendMapper;

    @Override
    public int addAttendance(Attendance attendance) {
        Attendance a = attendMapper.getAttendance(attendance.getCourse_ID(),attendance.getStart_time());
        if(a != null){
            return -1;
        }
        return attendMapper.addAttendance(attendance);
    }

    @Override
    public List<Attendance> getAttendanceListByCourseId(String course_ID) {
        return attendMapper.getAttendanceListByCourseId(course_ID);
    }
}
