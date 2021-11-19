package com.project.etsapi.service.Implment;

import com.project.etsapi.entity.*;
import com.project.etsapi.mapper.*;
import com.project.etsapi.service.CourseService;
import com.project.etsapi.vo.StudentInfo;
import com.project.etsapi.vo.TeacherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName CourseServiceImpl
 * @Description
 * @Author llj
 * @Date 2021/11/16 20:46
 **/

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    TeachCourseMapper teachCourseMapper;
    @Autowired
    TakeCourseMapper takeCourseMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    ProjectMapper projectMapper;

    @Override
    public Course getCourse(String course_ID) {
        return courseMapper.getCourse(course_ID);
    }

    @Override
    public List<Teacher> getTeacherListByCourseId(String course_ID) {
        return teachCourseMapper.getTeacherListByCourseId(course_ID);
    }

    @Override
    public List<Student> getStudentListByCourseId(String course_ID, String authority) {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("course_ID", course_ID);
        parameters.put("authority", authority);
        return takeCourseMapper.getStudentListByCourseId(parameters);
    }

    @Override
    public List<Project> getProjectListByCourseId(String course_ID) {
        return projectMapper.getProjectListByCourseId(course_ID);
    }

    @Override
    public int addTeachCourse(TeachCourse teachCourse) {
        if(this.getTeachCourse(teachCourse.getTeacher_ID(), teachCourse.getCourse_ID()) != null){
            // 该老师已经任教该课程，返回-3
            return -3;
        }
        Teacher teacher = teacherMapper.getTeacher(teachCourse.getTeacher_ID());
        Course course = courseMapper.getCourse(teachCourse.getCourse_ID());
        if(teacher!=null && course!=null){
            return teachCourseMapper.addTeachCourse(teachCourse);
        }
        else if(teacher == null){
            // 老师不存在，返回-1
            return -1;
        }
        else{
            // 课程不存在，返回-2
            return -2;
        }
    }

    @Override
    public TeachCourse getTeachCourse(String teacher_ID, String course_ID) {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("teacher_ID", teacher_ID);
        parameters.put("course_ID", course_ID);
        return teachCourseMapper.getTeachCourse(parameters);
    }

    @Override
    public TakeCourse getTakeCourse(String student_ID, String course_ID) {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("student_ID", student_ID);
        parameters.put("course_ID", course_ID);
        return takeCourseMapper.getTakeCourse(parameters);
    }

    @Override
    public List<Course> getAllCourse() {
        return courseMapper.getAll();
    }

    @Override
    public ArrayList<String> getAllCourseId(){
        ArrayList<String> courses = new ArrayList<>();
        for(Course course : getAllCourse()){
            courses.add(course.getCourse_ID());
        }
        return courses;
    }

    @Override
    public List<StudentInfo> getListStudentInfo(String course_ID, String authority) {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("course_ID", course_ID);
        parameters.put("authority", authority);
        return courseMapper.getListStudentInfo(parameters);
    }

    @Override
    public List<TeacherInfo> getListTeacherInfo(String course_ID) {
        return courseMapper.getListTeacherInfo(course_ID);
    }

    @Override
    public int addTakeCourse(TakeCourse takeCourse) {
        if(this.getTakeCourse(takeCourse.getStudent_ID(), takeCourse.getCourse_ID()) != null){
            if(this.getTakeCourse(takeCourse.getStudent_ID(),takeCourse.getCourse_ID()).getAuthority().equals("1")){
                // 该助教已经参与该课程，返回-4
                return -4;
            }
            else{
                // 该学生已经参与该课程，返回-5
                return -5;
            }
        }
        Student student = studentMapper.getStudent(takeCourse.getStudent_ID());
        Course course = courseMapper.getCourse(takeCourse.getCourse_ID());
        if(student != null && course != null){
            return takeCourseMapper.addTakeCourse(takeCourse);
        }
        else if(student == null && takeCourse.getAuthority().equals("0")){
            // 学生不存在，返回-1
            return -1;
        }
        else if(student == null && takeCourse.getAuthority().equals("1")){
            // 助教不存在，返回-2
            return -2;
        }
        else{
            // 课程不存在，返回-3
            return -3;
        }
    }
}
