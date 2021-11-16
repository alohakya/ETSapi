package com.project.etsapi.controller;

import com.project.etsapi.entity.Student;
import com.project.etsapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
<<<<<<< Updated upstream
=======
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
>>>>>>> Stashed changes
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
<<<<<<< Updated upstream
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("/student")
    public List<Student> selectAll(){
=======
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
//
//    @RequestMapping(value = "insert/", method = RequestMethod.POST)
//    // 插入新用户
//    // http://localhost:8888/student/insert?student_ID=1&name=aji
//    public Student insertStudent(Student student) throws Exception {
//        return studentService.insertStudent(student);
//    }
//
//    @RequestMapping(value = "/delete/{student_ID}", method = RequestMethod.GET)
//    // 删除学生
//    // http://localhost:8888/student/delete?student_ID=1
//    public String deleteStudent(String student_ID) throws Exception {
//        int result = studentService.deleteStudent(student_ID);
//        if(result >= 1){
//            return "删除成功";
//        }
//        else{
//            return "删除失败";
//        }
//    }
//
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    //根据学号更新学生信息
//    //http://localhost:8888/student/update?student_ID=2&name=aji
//    public String updateStudent(Student student) throws Exception {
//        int result = studentService.updateStudent(student);
//        if(result >= 1){
//            return "修改成功";
//        }
//        else{
//            return "修改失败";
//        }
//    }
//
    @RequestMapping(value = "/select", method = RequestMethod.GET)
    // 查找学生
    // http://localhost:8888/student/select?student_ID=1950000
    public String selectStudent(String student_ID) throws Exception {
        return studentService.selectStudent(student_ID).toString();
    }

    @RequestMapping("/selectAll")
    // 打印所有学生信息
    // http://localhost:8888/student/selectAll
    @ResponseBody
    public List<Student> selectAll() throws Exception{
>>>>>>> Stashed changes
        return studentService.selectAll();
    }
}
