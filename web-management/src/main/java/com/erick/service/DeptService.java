package com.erick.service;

import com.erick.pojo.Dept;

import java.util.List;

public interface DeptService {

    /**
     * Query all department data
     * @return list of departments
     */
    List<Dept> findAll();

    /**
     * Delete department based on id
     * @param id department id
     */
    void deleteById(Integer id);

    /**
     * add new department
     * @param dept department object
     */
    void add(Dept dept);

    /**
     * get department by id
     * @param id department id
     * @return department object
     */
    Dept getById(Integer id);
}
