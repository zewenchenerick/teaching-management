package com.erick.service;

import com.erick.pojo.ClassQueryParam;
import com.erick.pojo.Clazz;
import com.erick.pojo.PageResult;

public interface ClassService {

    /**
     * Get Oage result of Classes list with conditional by page
     *
     * @param classQueryParam conditional parameters object
     * @return Page Result with total count number and List of Classes
     */
    PageResult<Clazz> getClassListByPage(ClassQueryParam classQueryParam);

    /**
     * Delete class by its id
     * @param id class id to be deleted
     */
    void deleteById(Integer id);
}
