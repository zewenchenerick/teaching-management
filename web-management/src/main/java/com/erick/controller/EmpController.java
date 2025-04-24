package com.erick.controller;


import com.erick.pojo.Emp;
import com.erick.pojo.EmpQueryParam;
import com.erick.pojo.PageResult;
import com.erick.pojo.Result;
import com.erick.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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


    // @RequestParam(defaultValue = "1") Integer page,
    // @RequestParam(defaultValue = "10") Integer pageSize,
    // String name,
    // Integer gender,
    // @RequestParam(value = "begin")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
    // @RequestParam(value = "end")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate
    /**
     * Page Query with conditional
     * @param empQueryParam conditional parameters object
     * @return updated result back to front end
     */
    @GetMapping

    public Result getEmployeesByPage(EmpQueryParam empQueryParam){
        log.info("Page Query: {}", empQueryParam);
        PageResult<Emp> employees = empService.getEmployeesByPage(empQueryParam);
        return Result.success(employees);
    }

}
