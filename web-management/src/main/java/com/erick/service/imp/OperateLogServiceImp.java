package com.erick.service.imp;

import com.erick.mapper.OperateLogMapper;
import com.erick.pojo.LogQueryParam;
import com.erick.pojo.OperateLog;
import com.erick.pojo.PageResult;
import com.erick.service.OperateLogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperateLogServiceImp implements OperateLogService {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public PageResult<OperateLog> getLogsByPage(LogQueryParam logQueryParam) {

        // 1. configure page helper(page and page size)
        PageHelper.startPage(logQueryParam.getPage(), logQueryParam.getPageSize());

        List<OperateLog> logList = operateLogMapper.getLogsByPage(logQueryParam);

        Page<OperateLog> list = (Page<OperateLog>) logList;

        return new PageResult<>(list.getTotal(), logList);
    }

}
