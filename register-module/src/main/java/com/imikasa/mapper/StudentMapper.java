package com.imikasa.mapper;

import com.imikasa.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    int registerStudent(Student student);
    Student findStudentByName(String name);
}
