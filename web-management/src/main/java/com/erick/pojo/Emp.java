package com.erick.pojo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Emp {

    //ID,primary key
    private Integer id;

    private String username;
    private String password;
    private String name;

    //gender, 1:male, 2:female
    private Integer gender;
    // phone number
    private String phone;

    // Position: 1. Head Teacher, 2. Lecturer, 3. Student Affairs Supervisor, 4. Teaching and Research Supervisor, 5. Consultant
    private Integer job;
    private Integer salary;
    private String image;
    private LocalDate entryDate;

    // department id
    private Integer deptId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // department name
    private String deptName;

    // encapsulate work experience information
    private List<EmpExpr> exprList;
}
