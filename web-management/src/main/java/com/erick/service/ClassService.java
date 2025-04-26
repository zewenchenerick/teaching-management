package com.erick.service;

import com.erick.pojo.ClassQueryParam;
import com.erick.pojo.Clazz;
import com.erick.pojo.PageResult;
import com.erick.vo.ClazzVO;

import java.util.List;

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

    /**
     * Add new class
     * @param clazz new class object with its information
     */
    void addClazz(Clazz clazz);

    /**
     * Get class information based on id
     * @param id class id to be queried
     * @return Clazz Object of the given id
     */
    Clazz getClazzById(Integer id);

    /**
     * Update current selected class information
     * @param clazz class object to be updated (contain updated information)
     */
    void updateClazz(Clazz clazz);

    /**
     * Get all classes information supporting adding new student
     * @return Result object containing list of all classes (encapsulate in ClazzVO)
     */
    List<ClazzVO> getAllClazz();
}
