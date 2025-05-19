package com.erick.controller;

import com.erick.pojo.LogQueryParam;
import com.erick.pojo.OperateLog;
import com.erick.pojo.PageResult;
import com.erick.pojo.Result;
import com.erick.service.OperateLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private OperateLogService operateLogService;

    @RequestMapping("/page")
    public Result  getLogsByPage(LogQueryParam logQueryParam){

        log.info("Get Log List");

        PageResult<OperateLog> logList = operateLogService.getLogsByPage(logQueryParam);

        return Result.success(logList);
    }
}
