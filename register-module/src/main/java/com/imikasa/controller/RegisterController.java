package com.imikasa.controller;

import com.imikasa.pojo.CommonResult;
import com.imikasa.pojo.Student;
import com.imikasa.service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/register")
public class RegisterController {

    @Resource
    private RegisterService registerService;

    @GetMapping("/test")
    public String test(){
        return "this is a test";
    }

    //注册用户接口
    @PostMapping("/student")
    public CommonResult<Student> register(Student student, @RequestParam("file") MultipartFile file){
//        System.out.println(student);
        // 设置ID ,并获取图片链接
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        student.setStuNo(uuid);
        student = registerService.initStudent(student, file);
//        System.out.println(student);
        log.info("register info:"+student);
        return registerService.registerStudent(student);
    }

    //校验用户名是否已经注册
    @GetMapping("/checkName")
    public CommonResult<Boolean> checkName(String name){
        Boolean aBoolean = registerService.checkName(name);
        CommonResult<Boolean> checked = new CommonResult<>();
        if(aBoolean){
            checked.setCode(200);
            checked.setMsg("校验通过");
            checked.setData(aBoolean);
        }else{
            checked.setCode(400);
            checked.setMsg("此用户名已占用");
            checked.setData(aBoolean);
        }
        return checked;
    }


}
