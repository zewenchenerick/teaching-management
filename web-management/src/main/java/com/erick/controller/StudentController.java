package com.erick.controller;

import com.erick.pojo.PageResult;
import com.erick.pojo.Result;
import com.erick.pojo.Student;
import com.erick.pojo.StudentQueryParam;
import com.erick.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
