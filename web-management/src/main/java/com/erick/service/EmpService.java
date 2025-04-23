package com.erick.service;


import com.erick.pojo.Emp;
import com.erick.pojo.PageResult;

public interface EmpService {

    /**
     * Get employees by page
     *
     * @param page current page number
     * @param pageSize number of employee display in one page
     * @return page result object
     */
    PageResult<Emp> getEmployeesByPage(Integer page, Integer pageSize);
}
