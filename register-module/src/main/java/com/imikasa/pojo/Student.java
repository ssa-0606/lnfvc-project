package com.imikasa.pojo;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    private String stuNo;
    private String stuName;
    private String stuPassword;
    private int stuGender;
    private String stuPhone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date stuBrith;
    private String stuImg;
}
