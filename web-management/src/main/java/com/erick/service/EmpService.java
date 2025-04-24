package com.erick.service;


import com.erick.pojo.Emp;
import com.erick.pojo.EmpQueryParam;
import com.erick.pojo.PageResult;

public interface EmpService {

    // /**
    //  * Get employees by page
    //  *
    //  * @param page      current page number
    //  * @param pageSize  number of employee display in one page
    //  * @param name
    //  * @param gender
    //  * @param startDate
    //  * @param endDate
    //  * @return page result object
    //  */
    // PageResult<Emp> getEmployeesByPage(Integer page, Integer pageSize, String name, Integer gender, LocalDate startDate, LocalDate endDate);

    /**
     * Get employees by page with condition
     * @param empQueryParam conditional parameters object
     * @return page result object
     */
    PageResult<Emp> getEmployeesByPage(EmpQueryParam empQueryParam);
}
