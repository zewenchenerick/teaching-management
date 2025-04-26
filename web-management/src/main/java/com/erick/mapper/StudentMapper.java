package com.erick.mapper;

import com.erick.pojo.Student;
import com.erick.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {

    /**
     * Get list of student with conditional
     * @param studentQueryParam conditional parameters object
     * @return List of students
     */
    List<Student> getStudentsByPage(StudentQueryParam studentQueryParam);

    /**
     * Query student information based on id
     * @param id Student id to be queried
     * @return Student object contain information
     */
    @Select("select * from student where id = #{id}")
    Student getStudentById(Integer id);
}
