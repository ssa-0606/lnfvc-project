package com.imikasa.service.impl;

import com.imikasa.mapper.StudentMapper;
import com.imikasa.pojo.CommonResult;
import com.imikasa.pojo.Student;
import com.imikasa.service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class RegisterServiceImpl implements RegisterService {

    @Resource
    private StudentMapper studentMapper;

    @Value("${uploadImgPath}")
    private String uploadPath;

    //注册学生，添加学生进数据库
    @Override
    public CommonResult<Student> registerStudent(Student student) {
        CommonResult<Student> commonResult = new CommonResult<Student>();

        if(student.getStuName()!=null&&student.getStuPassword()!=null&&student.getStuPhone()!=null){
            Student data_student = studentMapper.findStudentByName(student.getStuName());
            log.info("传入参数为:"+student.getStuName()+" - 查出数据学生为："+data_student);
            if(data_student!=null){
                //用户名已经存在
                commonResult.setCode(400);
                commonResult.setMsg("用户名已经存在");
                commonResult.setData(data_student);
                return commonResult;
            }else{
                int i = studentMapper.registerStudent(student);
                if(i == 1){
                    commonResult.setCode(200);
                    commonResult.setMsg("注册成功");
                    commonResult.setData(student);
//                    System.out.println(commonResult);
                    return commonResult;
                }else{
                    commonResult.setCode(400);
                    commonResult.setMsg("注册失败");
                    commonResult.setData(student);
                    return commonResult;
                }
            }

        }
        commonResult.setCode(400);
        commonResult.setMsg("信息不得为空");
        commonResult.setData(null);
        return commonResult; 
    }

    //学生进入数据库
    //加工（加图片url）
    public Student initStudent(Student student, MultipartFile file){

        if(file!=null){
            try {
                String fileName = System.currentTimeMillis()+file.getOriginalFilename();
                String fileDirPath = uploadPath;
                File filePath = new File(fileDirPath);
                if(!filePath.exists()){
                    filePath.mkdirs();
                }
                File target = new File(filePath,fileName);
                file.transferTo(target);
                student.setStuImg("/uploadview/temp/photos/"+fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return student;
    }

    @Override
    public Boolean checkName(String name) {
        Student stu_name = studentMapper.findStudentByName(name);
        if(stu_name!=null){
            //数据库已有此用户，返回false，表示不可以用此用户名注册
            return false;
        }else {
            //没有此用户，返回true，可继续注册
            return true;
        }

    }




}
