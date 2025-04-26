package com.erick.service;

import com.erick.pojo.PageResult;
import com.erick.pojo.Student;
import com.erick.pojo.StudentQueryParam;

public interface StudentService {

    /**
     * Get Page Result of Student list with conditional by page
     * @param studentQueryParam conditional parameters object
     * @return Page Result with total count number and List of classes
     */
    PageResult<Student> getStudentsByPage(StudentQueryParam studentQueryParam);
}
