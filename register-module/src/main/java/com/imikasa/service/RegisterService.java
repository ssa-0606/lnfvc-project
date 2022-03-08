package com.imikasa.service;

import com.imikasa.pojo.CommonResult;
import com.imikasa.pojo.Student;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RegisterService {
    CommonResult registerStudent(Student student);
    Student initStudent(Student student, MultipartFile file);
    Boolean checkName(String name);
}
