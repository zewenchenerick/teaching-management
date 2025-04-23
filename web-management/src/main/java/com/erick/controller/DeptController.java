package com.erick.controller;

import com.erick.pojo.Dept;
import com.erick.pojo.Result;
import com.erick.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * delete department information
     * @return result
     */
    /*@DeleteMapping("/depts")
    public Result delete(HttpServletRequest request){
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);
        System.out.println("delete department based on id: " + id);
        return Result.success();
    }*/
    /*@DeleteMapping("/depts")
    public Result delete(@RequestParam(value = "id", required = false) Integer deptId) {
        System.out.println("delete department based on id: " + deptId);
        return Result.success();
    }*/
    @DeleteMapping("/depts")
    public Result delete(Integer id) {
        System.out.println("delete department based on id: " + id);
        deptService.deleteById(id);
        return Result.success();
    }


    /**
     * Add new department
     * @param dept department object
     * @return result back to front end
     */
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept){
        System.out.println("add new department: " + dept);
        deptService.add(dept);
        return Result.success();
    }


    @GetMapping("/depts/{id}")
    public Result getDeptById(@PathVariable Integer id){
        System.out.println("get department based on id " + id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }
}
