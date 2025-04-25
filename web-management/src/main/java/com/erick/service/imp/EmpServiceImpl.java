package com.erick.service.imp;

import com.erick.mapper.EmpExprMapper;
import com.erick.mapper.EmpMapper;
import com.erick.pojo.*;
import com.erick.service.EmpLogService;
import com.erick.service.EmpService;
import com.erick.utils.AliyunOSSOperator;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;
    @Autowired
    private AliyunOSSOperator ossOperator;

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

    // all exception will roll back
    @Transactional(rollbackFor = {Exception.class}) // Transactional Management - default only runtime error will roll back
    @Override
    public void saveEmployeeAndExperienceInfo(Emp emp) {
        try {
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
        }finally {
            // record operate log
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "Add new employee: " + emp);
            empLogService.insertLog(empLog);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void deleteEmployees(List<Integer> ids) {
        // 1. 先查询要删除的员工信息，获取图片URL
        List<Emp> empList = empMapper.list(new EmpQueryParam());
        List<Emp> employeesToDelete = empList.stream()
                .filter(emp -> ids.contains(emp.getId()))
                .toList();

        // 2. 删除员工信息
        empMapper.deleteByIds(ids);

        // 3. 删除员工工作经验信息
        empExprMapper.deleteByEmpIds(ids);

        // 4. 数据库操作成功后，删除OSS上的图片
        employeesToDelete.stream()
                .filter(emp -> emp.getImage() != null && !emp.getImage().isEmpty())
                .forEach(emp -> {
                    try {
                        // 从完整的图片URL中提取objectName
                        String imageUrl = emp.getImage();
                        // 获取bucketName后面的部分作为objectName
                        String objectName = imageUrl.substring(imageUrl.indexOf(".com/") + 5);
                        // 删除OSS上的图片
                        ossOperator.delete(objectName);
                        // 记录删除成功的日志
                        log.info("Successfully deleted image from OSS for employee {}: {}", emp.getId(), objectName);
                    } catch (Exception e) {
                        // 记录错误日志，但不影响删除员工信息的操作
                        log.error("Failed to delete image from OSS for employee {}: {}", emp.getId(), e.getMessage());
                    }
                });
    }

    @Override
    public Emp getInfoById(Integer id) {
        return empMapper.getByID(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateEmployee(Emp emp) {
        // 1. 获取原员工信息，用于比较图片是否发生变化
        Emp originalEmp = empMapper.getByID(emp.getId());
        String originalImage = originalEmp.getImage();
        String newImage = emp.getImage();

        // 2. Update employee basic information based on id
        // set update current time to update time
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        // 3. Update employee working experience information based on employee id
        Integer empId = emp.getId();
        // 3.1 delete all original working experience based on id
        empExprMapper.deleteByEmpIds(Collections.singletonList(empId));

        // 3.2 add new working experiences
        List<EmpExpr> empExprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(empExprList)){
            empExprList.forEach(empExpr -> empExpr.setEmpId(empId));
            empExprMapper.saveBatch(empExprList);
        }

        // 4. 如果图片发生变化，删除原图片
        if (originalImage != null && !originalImage.isEmpty() && 
            !originalImage.equals(newImage)) {
            try {
                // 从完整的图片URL中提取objectName
                String objectName = originalImage.substring(originalImage.indexOf(".com/") + 5);
                // 删除OSS上的原图片
                ossOperator.delete(objectName);
                // 记录删除成功的日志
                log.info("Successfully deleted old image from OSS for employee {}: {}", empId, objectName);
            } catch (Exception e) {
                // 记录错误日志，但不影响更新操作
                log.error("Failed to delete old image from OSS for employee {}: {}", empId, e.getMessage());
            }
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
