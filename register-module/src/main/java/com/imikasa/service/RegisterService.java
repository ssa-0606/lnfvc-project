package com.imikasa.service;

import com.imikasa.pojo.CommonResult;
import com.imikasa.pojo.Student;
import org.springframework.web.multipart.MultipartFile;

public interface RegisterService {
    CommonResult registerStudent(Student student);
    Student initStudent(Student student, MultipartFile file);
}
