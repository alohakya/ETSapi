package com.project.etsapi.mapper;

import com.project.etsapi.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    List<Student> selectAll();
}
