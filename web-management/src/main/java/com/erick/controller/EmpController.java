package com.erick.controller;


import com.erick.pojo.Emp;
import com.erick.pojo.PageResult;
import com.erick.pojo.Result;
import com.erick.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * employee management controller
 */
@Slf4j
@RestController
@RequestMapping("emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * Page Query
     * @param page page number i.e. No.1 Page 1
     * @param pageSize Size of each page, number of employee to display in one page
     * @return updated result back to front end
     */
    @GetMapping
    public Result getEmployeesByPage(@RequestParam(defaultValue = "1") Integer page,
                                     @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("Page Query: Page Number is {}, Page Size is {}", page, pageSize);
        PageResult<Emp> employees = empService.getEmployeesByPage(page, pageSize);
        return Result.success(employees);
    }

}
