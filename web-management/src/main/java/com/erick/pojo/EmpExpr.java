package com.erick.pojo;

import lombok.Data;

import java.time.LocalDate;

/**
 * work experience
 */
@Data
public class EmpExpr {

    private Integer id;

    // staff id
    private Integer empId;
    private LocalDate begin;
    private LocalDate end;

    // company name
    private String company;

    // position
    private String job;
}
