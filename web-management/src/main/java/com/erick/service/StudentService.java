package com.erick.service;

import com.erick.pojo.PageResult;
import com.erick.pojo.Student;
import com.erick.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {

    /**
     * Get Page Result of Student list with conditional by page
     * @param studentQueryParam conditional parameters object
     * @return Page Result with total count number and List of classes
     */
    PageResult<Student> getStudentsByPage(StudentQueryParam studentQueryParam);

    /**
     * Query student information based on id
     * @param id student id to be queried
     * @return Student Object contain all information
     */
    Student getInfoById(Integer id);

    /**
     * Add new student
     * @param student Student entity to be added
     */
    void AddNewStudent(Student student);

    /**
     * Update student information
     * @param student student entity to be updated
     */
    void update(Student student);

    /**
     * Delete Student by is
     * @param ids student id need to be deleted
     */
    void deleteById(List<Integer> ids);
}
