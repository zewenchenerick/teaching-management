package com.erick.controller;

import com.erick.pojo.ClazzData;
import com.erick.pojo.JobData;
import com.erick.pojo.Result;
import com.erick.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * Count the number of employees in each position
     * @return Result containing job option object (list of position and list of number)
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("Count the number of employees in each position");

        JobData jobData = reportService.getEmpJobData();

        return Result.success(jobData);
    }


    /**
     * Count the number of employees in each gender
     * @return Result containing List of Map (Map name: values) for example 男:24
     */
    @GetMapping("empGenderData")
    public Result getGenderData(){

        log.info("Count the number of employees in each gender");

        List<Map<String, Object>> genderList = reportService.getGenderData();

        return Result.success(genderList);
    }


    /**
     * Count the number of students in each class
     * @return Result object containing clazz data
     */
    @GetMapping("studentCountData")
    public Result getStudentData(){
        log.info("Count the number of students in each class");
        ClazzData clazzData = reportService.getStudentData();
        return Result.success(clazzData);
    }


    /**
     * Count number of student for each degree
     * @return Result object containing degree data
     */
    @GetMapping("studentDegreeData")
    public Result getDegreeData(){
        log.info("Count the number of students in each degree");
        List<Map<String, Object>> degreeList = reportService.getStudentDegreeData();
        return Result.success(degreeList);
    }

}
