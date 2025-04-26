package com.erick.service.imp;

import com.erick.exception.ClassHasStudentsException;
import com.erick.mapper.ClassMapper;
import com.erick.mapper.EmpMapper;
import com.erick.mapper.StudentMapper;
import com.erick.pojo.ClassQueryParam;
import com.erick.pojo.Clazz;
import com.erick.pojo.PageResult;
import com.erick.service.ClassService;
import com.erick.vo.ClazzVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassMapper classMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Clazz> getClassListByPage(ClassQueryParam classQueryParam) {

        // 1. set up pagination parameters (using PageHelper)
        PageHelper.startPage(classQueryParam.getPage(), classQueryParam.getPageSize());

        // 2. execute query
        List<Clazz> classList =  classMapper.getClassList(classQueryParam);

        // set some additional information to class
        classList.forEach(clazz -> {
            // set master name
            if (clazz.getMasterId() != null){
                String name = empMapper.getByID(clazz.getMasterId()).getName();
                clazz.setMasterName(name);
            }

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

    @Override
    public void deleteById(Integer id) {

        // Check if class has associated students before deleting
        int count = studentMapper.countByClassId(id);
        if (count > 0) {
            throw new ClassHasStudentsException("对不起, 该班级下有学生, 不能直接删除");
        }

        classMapper.deleteById(id);

    }

    @Override
    public void addClazz(Clazz clazz) {
        // 1. set some additional information for class object
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());

        classMapper.addClazz(clazz);

    }

    @Override
    public Clazz getClazzById(Integer id) {

        return classMapper.getClazzById(id);

    }

    @Override
    public void updateClazz(Clazz clazz) {

        // 1. set update time to current time
        clazz.setUpdateTime(LocalDateTime.now());

        classMapper.updateClazzById(clazz);

    }

    @Override
    public List<ClazzVO> getAllClazz() {

        // 1. get list of classes
        List<Clazz> clazzList = classMapper.getAllClazz();

        // 2. encapsulated into list of ClassOV
        return clazzList.stream().map(clazz -> {
            ClazzVO clazzVO = new ClazzVO();
            BeanUtils.copyProperties(clazz, clazzVO);
            return clazzVO;
        }).collect(Collectors.toList());

    }
}
