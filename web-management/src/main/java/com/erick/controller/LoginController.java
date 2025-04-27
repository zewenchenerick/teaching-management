package com.erick.controller;

import com.erick.dto.EmpDTO;
import com.erick.pojo.Emp;
import com.erick.pojo.Result;
import com.erick.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for login
 */
@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    /**
     * Handles the login request. This method processes the login logic
     * and returns a unified result indicating the success of the operation.
     *
     * @return Result encapsulating the success state and any relevant data
     */
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("Log in employee: {}", emp);
        EmpDTO empDTO = empService.login(emp);
        if (empDTO != null) {
            return Result.success(empDTO);
        } else {
            return Result.error("Username or password is incorrect.");
        }
    }
}
