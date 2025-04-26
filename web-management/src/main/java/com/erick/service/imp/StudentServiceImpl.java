package com.erick.service.imp;

import com.erick.mapper.ClassMapper;
import com.erick.mapper.StudentMapper;
import com.erick.pojo.Clazz;
import com.erick.pojo.PageResult;
import com.erick.pojo.Student;
import com.erick.pojo.StudentQueryParam;
import com.erick.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ClassMapper classMapper;

    @Override
    public PageResult<Student> getStudentsByPage(StudentQueryParam studentQueryParam) {
        // 1. configure page helper(page and page size)
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());

        // 2. execute query
        List<Student> studentList = studentMapper.getStudentsByPage(studentQueryParam);

        // 3. Encapsulate list into page result in order to get total count
        Page<Student> list = (Page<Student>) studentList;

        // 4. set clazz Name for each student
        studentList.forEach(student -> {
            Integer clazzId = student.getClazzId();
            System.out.println(clazzId);
            Clazz clazz = classMapper.getClazzById(clazzId);
            System.out.println(clazz);
            String clazzName = clazz.getName();
            student.setClazzName(clazzName);
        });

        return new PageResult<>(list.getTotal(), studentList);
    }

    @Override
    public Student getInfoById(Integer id) {

        return studentMapper.getStudentById(id);
    }
}
