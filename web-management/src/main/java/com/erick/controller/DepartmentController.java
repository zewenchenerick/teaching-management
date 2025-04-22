package com.erick.controller;

import com.erick.pojo.Dept;
import com.erick.pojo.Result;
import com.erick.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // @RequestMapping(value = "/departments", method = RequestMethod.GET)
    @GetMapping("/departments")
    public Result list(){
        System.out.println("Query all department data");
        List<Dept> departmentList = departmentService.findAll();
        return Result.success(departmentList);
    }
}
