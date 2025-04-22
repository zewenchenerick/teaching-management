package com.erick.service.imp;

import com.erick.mapper.DepartmentMapper;
import com.erick.pojo.Dept;
import com.erick.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Dept> findAll() {
        return departmentMapper.findAll();
    }

}
