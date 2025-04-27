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

import java.time.LocalDateTime;
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
            Clazz clazz = classMapper.getClazzById(clazzId);
            if (clazz != null){
                String clazzName = clazz.getName();
                student.setClazzName(clazzName);
            }
        });

        return new PageResult<>(list.getTotal(), studentList);
    }

    @Override
    public Student getInfoById(Integer id) {
        return studentMapper.getStudentById(id);
    }

    @Override
    public void AddNewStudent(Student student) {
        // 1. set create time and update time
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());

        // 2. update class name
        Integer clazzId = student.getClazzId();
        if (clazzId != null){
            Clazz clazz = classMapper.getClazzById(clazzId);
            String clazzName = clazz.getName();
            student.setClazzName(clazzName);
        }

        // 3. Add
        studentMapper.addStudent(student);
    }

    @Override
    public void update(Student student) {
        // 1. set update time
        student.setUpdateTime(LocalDateTime.now());

        // 2. update
        studentMapper.update(student);
    }

    @Override
    public void deleteById(List<Integer> ids) {
        studentMapper.deleteById(ids);
    }

    @Override
    public void handleViolation(Integer id, Integer score) {
        // 1. set student update time
        Student studentById = studentMapper.getStudentById(id);
        studentById.setUpdateTime(LocalDateTime.now());

        // 2. set violation count and score
        studentById.setViolationCount((short) (studentById.getViolationCount()+1));
        studentById.setViolationScore((short) (studentById.getViolationScore()+score));

        // 3. update student
        studentMapper.update(studentById);
    }
}
