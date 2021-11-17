package com.project.etsapi.service.Implment;

import com.project.etsapi.entity.Teacher;
import com.project.etsapi.mapper.TeacherMapper;
import com.project.etsapi.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName TeacherServiceImpl
 * @Description
 * @Author llj
 * @Date 2021/11/17 13:00
 **/

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public int addTeacher(Teacher teacher) {
        if(teacherMapper.getTeacher(teacher.getTeacher_ID()) != null){
            // 已经存在，返回-1
            return -1;
        }
        else{
            return teacherMapper.addTeacher(teacher);
        }
    }

    @Override
    public int deleteTeacher(String teacher_ID) {
        if(teacherMapper.getTeacher(teacher_ID) != null){
            return teacherMapper.deleteTeacher(teacher_ID);
        }
        else{
            // 不存在则返回-1
            return -1;
        }
    }

    @Override
    public int setTeacher(Teacher teacher) {
        if(teacherMapper.getTeacher(teacher.getTeacher_ID()) != null){
            return teacherMapper.setTeacher(teacher);
        }
        else{
            // 不存在则返回-1
            return -1;
        }
    }

    @Override
    public Teacher getTeacher(String teacher_ID) {
        return teacherMapper.getTeacher(teacher_ID);
    }

    @Override
    public List<Teacher> getAll() {
        return teacherMapper.getAll();
    }
}
