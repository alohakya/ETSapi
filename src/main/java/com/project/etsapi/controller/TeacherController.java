package com.project.etsapi.controller;

import com.project.etsapi.entity.Teacher;
import com.project.etsapi.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName TeacherController
 * @Description
 * @Author llj
 * @Date 2021/11/17 14:09
 **/

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    // 添加老师
    // http://localhost:8888/teacher/add?teacher_ID=10100&name=aji
    @PostMapping( "/add")
    @ResponseBody
    public String addTeacher(Teacher teacher) {
        int result = teacherService.addTeacher(teacher);
        if( result >= 1){
            return "添加成功";
        }
        else{
            return "添加失败！该老师已经存在！";
        }
    }

    // 删除老师
    // http://localhost:8888/teacher/delete?teacher_ID=10100
    @PostMapping( "/delete")
    @ResponseBody
    public String deleteTeacher(@RequestParam("teacher_ID") String teacher_ID){
        int result = teacherService.deleteTeacher(teacher_ID);
        if(result >= 1){
            return "删除成功";
        }
        else{
            return "删除失败！未找到该老师！";
        }
    }

    //根据工号更新老师信息
    //http://localhost:8888/teacher/set?teacher_ID=10100&name=aji
    @PostMapping("/set")
    @ResponseBody
    public String setTeacher(Teacher teacher) {
        int result = teacherService.setTeacher(teacher);
        if(result >= 1){
            return "修改成功";
        }
        else{
            return "修改失败！未找到该老师！";
        }
    }

    //查找老师
    // http://localhost:8888/teacher/get?teacher_ID=10100
    @GetMapping( "/get")
    @ResponseBody
    public Teacher getTeacher(@RequestParam("teacher_ID") String teacher_ID){
        return teacherService.getTeacher(teacher_ID);
    }

    // 打印所有老师信息
    // http://localhost:8888/teacher/getAllTeacher
    @GetMapping("/getAllTeacher")
    @ResponseBody
    public List<Teacher> getAllTeacher(){
        return teacherService.getAllTeacher();
    }

    // 获得所有教师ID，供外码选择
    // http://localhost:8888/teacher/getAllTeacherId
    @GetMapping("/getAllTeacherId")
    @ResponseBody
    public List<String> getAllTeacherId(){
        return teacherService.getAllTeacherId();
    }

}
