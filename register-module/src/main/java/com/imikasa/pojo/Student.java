package com.imikasa.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    private long stuNo;
    private String stuName;
    private String stuPassword;
    private int stuGender;
    private String stuPhone;
    private Date stuBrith;
    private String stuImg;
}
