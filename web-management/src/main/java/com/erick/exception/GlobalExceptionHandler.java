package com.erick.exception;

import com.erick.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // handle exception
    @ExceptionHandler
    public Result handleException(Exception e){ // set the type of exception

        log.error("Error: " , e);

        // After catch an exception, return a standard Result
        return Result.error("对不起,操作失败,请联系管理员");
    }

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException dke){
        log.error("Error: " , dke);
        String message = dke.getMessage();
        int index = message.indexOf("Duplicate entry");
        String errorMessage = message.substring(index);
        String[] arr = errorMessage.split(" ");

        // After catch an exception, return a standard Result
        return Result.error("对不起, " + arr[2] + " 已存在");

    }
}
