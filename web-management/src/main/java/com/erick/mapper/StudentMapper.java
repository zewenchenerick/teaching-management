package com.erick.mapper;

import com.erick.pojo.Student;
import com.erick.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {

    /**
     * Get list of student with conditional
     * @param studentQueryParam conditional parameters object
     * @return List of students
     */
    List<Student> getStudentsByPage(StudentQueryParam studentQueryParam);
}
