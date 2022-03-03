package com.imikasa.mapper;

import com.imikasa.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper {
    int registerStudent(Student student);
    Student findStudentById(String name);
}
