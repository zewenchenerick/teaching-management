package com.erick.controller;


import com.erick.pojo.Emp;
import com.erick.pojo.EmpQueryParam;
import com.erick.pojo.PageResult;
import com.erick.pojo.Result;
import com.erick.service.EmpService;
import com.erick.vo.EmpVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
     * Page Query employees with conditional
     * @param empQueryParam conditional parameters object
     * @return updated result back to front end
     */
    @GetMapping
    public Result getEmployeesByPage(EmpQueryParam empQueryParam){
        log.info("Employee Page Query: {}", empQueryParam);
        PageResult<Emp> employees = empService.getEmployeesByPage(empQueryParam);
        return Result.success(employees);
    }

    /**
     * Save New  employee information and Work Experience
     * @param emp encapsulated employee object
     * @return Result back to front end
     */
    @PostMapping
    public Result saveEmployeeAndExperienceInfo(@RequestBody Emp emp){
        log.info("add new employee: {}", emp);
        empService.saveEmployeeAndExperienceInfo(emp);
        return Result.success();
    }

    /**
     * Delete batch of employees based on id
     * @param ids List of id to be deleted
     * @return Result object back to front end
     */
    @DeleteMapping
    public Result deleteEmployees(@RequestParam List<Integer> ids){
        log.info("Delete Employee: {}", ids);
        empService.deleteEmployees(ids);
        return Result.success();
    }


    /**
     * Query employee information based on id
     * @param id Employee id to be queried
     * @return Result object contain employee information
     */
    @GetMapping("/{id}")
    public Result getInfoById(@PathVariable Integer id){
        log.info("Query employee information based on id: id is {}", id);
        Emp emp =  empService.getInfoById(id);
        return Result.success(emp);
    }

    /**
     * Update current selected employee information
     * @param emp employee object to be updated
     * @return Result object return back to front end
     */
    @PutMapping
    public Result updateEmployee(@RequestBody Emp emp){
        log.info("Update employee's information: employee is {}", emp);
        empService.updateEmployee(emp);
        return Result.success();
    }


    /**
     * Get all employees information
     * @return Result object containing list of all employees
     */
    @GetMapping("/list")
    public Result getAllEmp(){

        log.info("Get all employees information");

        List<EmpVO> empList = empService.getAllEmp();

        return Result.success(empList);
    }
}
