package com.imikasa.service.impl;

import com.imikasa.mapper.StudentMapper;
import com.imikasa.pojo.CommonResult;
import com.imikasa.pojo.Student;
import com.imikasa.service.RegisterService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Resource
    private StudentMapper studentMapper;

    @Value("${uploadImgPath}")
    private String uploadPath;

    @Override
    public CommonResult registerStudent(Student student) {
        CommonResult commonResult = new CommonResult();

        if(student.getStuName()!=null&&student.getStuPassword()!=null&&student.getStuPhone()!=null){
            Student find = studentMapper.findStudentById(student.getStuName());
            if(find!=null){
                //用户名已经存在
                commonResult.setCode(400);
                commonResult.setMsg("用户名已经存在");
                commonResult.setData(find);
                return commonResult;
            }else{
                int i = studentMapper.registerStudent(student);
                if(i == 1){
                    commonResult.setCode(200);
                    commonResult.setMsg("注册成功");
                    commonResult.setData(student);
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
                student.setStuImg(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return student;
    }



}
