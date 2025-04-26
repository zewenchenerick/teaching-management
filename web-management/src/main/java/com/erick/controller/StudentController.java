package com.erick.controller;

import com.erick.pojo.PageResult;
import com.erick.pojo.Result;
import com.erick.pojo.Student;
import com.erick.pojo.StudentQueryParam;
import com.erick.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * Get Page Result of Student list with conditional by page
     * @param studentQueryParam conditional parameters object
     * @return Result Object contain Page result
     */
    @GetMapping
    public Result getStudentsByPage(StudentQueryParam studentQueryParam){
        log.info("Student Page Query: {}", studentQueryParam);
        PageResult<Student> studentPageResult = studentService.getStudentsByPage(studentQueryParam);
        return Result.success(studentPageResult);
    }

    /**
     * Query student information based on id
     * @param id student id to be queried
     * @return Result object contain student information
     */
    @GetMapping("/{id}")
    public Result getStudentById(@PathVariable Integer id){
        log.info("Query student information based on id: id is {}", id);
        Student student = studentService.getInfoById(id);
        return Result.success(student);
    }

    /**
     * Add new student
     * @param student Student entity to be added
     * @return Result Object back to front end
     */
    @PostMapping
    public Result addNewStudent(@RequestBody Student student){
        log.info("Add new student: {}", student);
        studentService.AddNewStudent(student);
        return Result.success();
    }
}
