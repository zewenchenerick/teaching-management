package com.erick.service.imp;

import com.erick.mapper.ClassMapper;
import com.erick.mapper.EmpMapper;
import com.erick.pojo.ClassQueryParam;
import com.erick.pojo.Clazz;
import com.erick.pojo.PageResult;
import com.erick.service.ClassService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassMapper classMapper;
    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageResult<Clazz> getClassListByPage(ClassQueryParam classQueryParam) {

        // 1. set up pagination parameters (using PageHelper)
        PageHelper.startPage(classQueryParam.getPage(), classQueryParam.getPageSize());

        // 2. execute query
        List<Clazz> classList =  classMapper.getClassList(classQueryParam);

        // set some additional information to class
        classList.forEach(clazz -> {
            // set master name
            String name = empMapper.getByID(clazz.getMasterId()).getName();
            clazz.setMasterName(name);

            // set class status
            LocalDate startDate = clazz.getBeginDate();
            LocalDate endDate = clazz.getEndDate();
            if (LocalDate.now().isAfter(endDate)){
                clazz.setStatus("已结课");
            } else if (LocalDate.now().isBefore(startDate)) {
                clazz.setStatus("未开班");
            } else {
                clazz.setStatus("在读中");
            }
        });

        // encapsulate page to PageReslt
        // to page type to count total number of classes to send
        Page<Clazz> list = (Page<Clazz>) classList;

        return new PageResult<>(list.getTotal(), classList);
    }
}
