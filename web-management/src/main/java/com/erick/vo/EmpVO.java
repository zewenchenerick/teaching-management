package com.erick.vo;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class EmpVO {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Integer gender;
    private String image;
    private Integer job;
    private Integer salary;
    private LocalDate entryDate;
    private Integer deptId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
