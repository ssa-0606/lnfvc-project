package com.imikasa.controller;

import com.imikasa.pojo.CommonResult;
import com.imikasa.pojo.Student;
import com.imikasa.service.RegisterService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Resource
    private RegisterService registerService;

    @GetMapping("/test")
    public String test(){
        return "test1245sdka77888lfjsdklajf56";
    }

    @PostMapping("/student")
    public CommonResult<Student> register(Student student, MultipartFile file){
        //设置ID ,并获取图片链接
        student = registerService.initStudent(student, file);
        return registerService.registerStudent(student);
    }
}
