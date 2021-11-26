package com.project.etsapi.mapper;

import com.project.etsapi.entity.Course;
import com.project.etsapi.vo.StudentInfo;
import com.project.etsapi.vo.TeacherInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface CourseMapper {
    /**
     * 新增课程
     * @param course
     * @return
     */
    int addCourse(Course course);

    /**
     * 删除课程
     * @param course_ID
     * @return
     */
    int deleteCourseById(String course_ID);

    int updateCourseInfo(String course_ID,String name,String description);

    int updateCourseGrade(String course_ID,double attend_percentage,double project_percentage);

    /**
     * 根据id查询课程信息
     * @param course_ID
     * @return Course
     */
    Course getCourse(String course_ID);

    /**
     * 获得所有课程
     * @param
     * @return
     */
    List<Course> getAll();

    /**
     * 根据课程ID获得列表，列表内容：学生ID，学生名字，账号邮箱
     * @param
     * @return
     */
    List<StudentInfo> getListStudentInfo(HashMap<String, String> parameter);

    /**
     * 根据课程ID获得列表，列表内容：教师ID，教师名字，账号邮箱
     * @param
     * @return
     */
    List<TeacherInfo> getListTeacherInfo(String course_ID);
}
