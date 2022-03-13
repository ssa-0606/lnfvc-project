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
        return "<h1>this is a test</h1>";
    }

    // TODO: 2022/3/10 潜在bug,测试接口时，可能会发送同名用户的请求，但是程序会上传完图片，再去验证是否同名，前端有校验机制，必须控制所有条件均符合要求 
    //注册用户接口
    @PostMapping("/student")
    public CommonResult<Student> register(Student student, @RequestParam("file") MultipartFile file){
        // 设置ID ,并获取图片链接
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        student.setStuNo(uuid);
        student = registerService.initStudent(student, file);
        log.info("register info:"+student);
        return registerService.registerStudent(student);
    }

    //校验用户名是否已经注册
    @GetMapping("/checkName/{name}")
    public CommonResult<Boolean> checkName(@PathVariable String name){
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
