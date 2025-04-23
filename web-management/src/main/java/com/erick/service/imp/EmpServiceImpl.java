package com.erick.service.imp;

import com.erick.mapper.EmpMapper;
import com.erick.pojo.Emp;
import com.erick.pojo.PageResult;
import com.erick.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;


    @Override
    public PageResult<Emp> getEmployeesByPage(Integer page, Integer pageSize) {

        // 1. invoke Emp Mapper interface to query total number of data
        Long count = empMapper.count();

        // 2. invoke mapper interface to query list of result
        List<Emp> empList = empMapper.list((page-1)*pageSize, pageSize);

        // encapsulate page result and return
        return new PageResult<>(count, empList);
    }
}
