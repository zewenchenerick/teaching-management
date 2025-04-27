package com.erick.mapper;

import com.erick.pojo.Student;
import com.erick.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

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

    /**
     * Add new student
     * @param student Student entity to be added
     */
    void addStudent(Student student);

    /**
     * Update student information
     * @param student student entity to be updated
     */
    void update(Student student);

    /**
     * Delete student by id
     * @param ids student id need to be deleted
     */
    void deleteById(List<Integer> ids);

    /**
     * Count number of student in this class
     * @param id clazz id
     * @return number of student
     */
    int countByClassId(Integer id);


    /**
     * Count number of student in each class
     * @return List of map <name, value>
     */
    @MapKey("clazz")
    List<Map<String, Object>> countStudentData();

    /**
     * Count number of student in each degree
     * @return List of map (Map name: values) for exmaple "初中"：10
     */
    @MapKey("degree")
    List<Map<String, Object>> countStudentDegreeData();
}
