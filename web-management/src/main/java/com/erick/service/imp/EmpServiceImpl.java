package com.erick.service.imp;

import com.erick.mapper.EmpExprMapper;
import com.erick.mapper.EmpMapper;
import com.erick.pojo.Emp;
import com.erick.pojo.EmpExpr;
import com.erick.pojo.EmpQueryParam;
import com.erick.pojo.PageResult;
import com.erick.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;

    @Override
    public PageResult<Emp> getEmployeesByPage(EmpQueryParam empQueryParam) {
        // 1.set up pagination parameters (PageHepler)
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());

        // 2. execute query
        List<Emp> empList = empMapper.list(empQueryParam);

        // encapsulate page result and return
        Page<Emp> list = (Page<Emp>) empList;
        return new PageResult<>(list.getTotal(), empList);
    }

    @Override
    public void saveEmployeeAndExperienceInfo(Emp emp) {
        // 1. update employee information
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.saveEmployee(emp);

        // 2. update employee's work experience
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            // set the id for employee
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.saveBatch(exprList);
        }
    }

    // --------------------------------original method----------------------------------------------------
    /*@Override
    public PageResult<Emp> getEmployeesByPage(Integer page, Integer pageSize) {

        // 1. invoke Emp Mapper interface to query total number of data
        Long count = empMapper.count();

        // 2. invoke mapper interface to query list of result
        List<Emp> empList = empMapper.list((page-1)*pageSize, pageSize);

        // encapsulate page result and return
        return new PageResult<>(count, empList);
    }*/


    // ----------------------------------page helper----------------------------------------------------------
    /*@Override
    public PageResult<Emp> getEmployeesByPage(Integer page, Integer pageSize, String name, Integer gender, LocalDate startDate, LocalDate endDate) {

        // 1.set up pagination parameters (PageHepler)
        PageHelper.startPage(page, pageSize);

        // 2. execute query
        List<Emp> empList = empMapper.list(name, gender, startDate, endDate);

        // encapsulate page result and return
        Page<Emp> list = (Page<Emp>) empList;
        return new PageResult<>(list.getTotal(), empList);
    }*/


}
