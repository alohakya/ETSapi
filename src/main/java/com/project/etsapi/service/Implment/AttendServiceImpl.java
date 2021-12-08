package com.project.etsapi.service.Implment;

import com.project.etsapi.entity.Attendance;
import com.project.etsapi.mapper.AttendMapper;
import com.project.etsapi.service.AttendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @Override
    public List<Attendance> getOnGoingAttendanceListByCourseId(String course_ID) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        // new Date()为获取当前系统时间
        String now_time = df.format(new Date());
        List<Attendance> attendanceList = attendMapper.getAttendanceListByCourseId(course_ID);
        List<Attendance> result = new ArrayList<>();
        for(Attendance attendance: attendanceList){
            if(attendance.getStart_time().compareTo(now_time)<0 && attendance.getEnd_time().compareTo(now_time)>0){
                result.add(attendance);
            }
        }
        return result;
    }
}
