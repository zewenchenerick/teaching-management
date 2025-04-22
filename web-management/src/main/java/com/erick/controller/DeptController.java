package com.erick.controller;

import com.erick.pojo.Dept;
import com.erick.pojo.Result;
import com.erick.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
// @RequestMapping("/api")
public class DeptController {

    @Autowired
    private DeptService deptService;

    // @RequestMapping(value = "/departments", method = RequestMethod.GET)
    @GetMapping("/depts")
    public Result list(){
        System.out.println("Query all department data");
        List<Dept> departmentList = deptService.findAll();
        return Result.success(departmentList);
    }
}
