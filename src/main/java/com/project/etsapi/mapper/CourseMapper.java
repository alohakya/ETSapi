package com.project.etsapi.mapper;

import com.project.etsapi.entity.Course;
import org.apache.ibatis.annotations.Mapper;

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

    /**
     * 更新课程介绍信息
     * @param course_ID
     * @param name
     * @param description
     * @return
     */
    int updateCourseInfo(String course_ID,String name,String description);

    /**
     * 更新课程成绩占比
     * @param course_ID
     * @param attend_percentage
     * @param project_percentage
     * @return
     */
    int updateCourseGrade(String course_ID,double attend_percentage,double project_percentage);

    /**
     * 根据id查询课程信息
     * @param course_ID
     * @return Course
     */
    Course getCourse(String course_ID);

//    /**
//     * 根据课程ID获得列表，列表内容：学生ID，学生名字，账号邮箱
//     * @param
//     * @return
//     */
//    List<StudentInfo> getListStudentInfo(HashMap<String, String> parameter);
//
//    /**
//     * 根据课程ID获得列表，列表内容：教师ID，教师名字，账号邮箱
//     * @param
//     * @return
//     */
//    List<TeacherInfo> getListTeacherInfo(String course_ID);
}
