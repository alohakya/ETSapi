package com.project.etsapi.service.Implment;

import com.project.etsapi.entity.Attend;
import com.project.etsapi.entity.Attendance;
import com.project.etsapi.mapper.AttendMapper;
import com.project.etsapi.service.AttendService;
import com.project.etsapi.vo.AttendInfo;
import com.project.etsapi.vo.AttendanceInfo;
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

    @Override
    public List<AttendInfo> getAttendInfoList(String course_ID, String student_ID) {
        List<Attendance> totalTmp = attendMapper.getAttendanceListByCourseId(course_ID);
        List<Attend> stuTmp = attendMapper.getAttendList(course_ID,student_ID);
        List<AttendInfo> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < totalTmp.size() && j < stuTmp.size()){
            //出勤
            if(totalTmp.get(i).getStart_time().equals(stuTmp.get(j).getStart_time())){
                result.add(totalTmp.get(i).toAttendInfo(student_ID,Boolean.TRUE));
                i++;
                j++;
            }
            //缺勤
            else{
                result.add(totalTmp.get(i).toAttendInfo(student_ID,Boolean.FALSE));
                i++;
            }
        }
        //在数据库不出错的情况下，totalTmp的长度一定 ≥ stuTmp，所以下述为缺勤
        while (i < totalTmp.size()){
            result.add(totalTmp.get(i).toAttendInfo(student_ID,Boolean.FALSE));
            i++;
        }
        return result;
    }

    @Override
    public int addAttend(Attend attend) throws Exception {
        if (attendMapper.getAttend(attend) != null){
            return -1;
        }
        return attendMapper.addAttend(attend);
    }

    @Override
    public List<AttendanceInfo> getAttendanceInfoList(String course_ID) {
        return attendMapper.getAttendanceInfoList(course_ID);
    }
}
