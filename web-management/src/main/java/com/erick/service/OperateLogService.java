package com.erick.service;


import com.erick.pojo.LogQueryParam;
import com.erick.pojo.OperateLog;
import com.erick.pojo.PageResult;

public interface OperateLogService {

    PageResult<OperateLog> getLogsByPage(LogQueryParam logQueryParam);
}
