package com.erick.service;


import com.erick.dto.EmpDTO;
import com.erick.pojo.Emp;
import com.erick.pojo.EmpQueryParam;
import com.erick.pojo.PageResult;
import com.erick.vo.EmpVO;

import java.util.List;

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

    /**
     * Save New  employee information and Work Experience
     * @param emp encapsulated employee object
     */
    void saveEmployeeAndExperienceInfo(Emp emp);

    /**
     * Delete employees based on id
     * @param ids List of id to be deleted
     */
    void deleteEmployees(List<Integer> ids);

    /**
     * Query employee information based on id
     * @param id Employee id to be queried
     * @return Queried Employee Object
     */
    Emp getInfoById(Integer id);

    /**
     * Update current selected employee information
     * @param emp employee object to be updated
     */
    void updateEmployee(Emp emp);

    /**
     * Get all employees information
     *
     * @return List of all employees
     */
    List<EmpVO> getAllEmp();

    /**
     * Authenticates an employee based on their credentials and returns their login information.
     *
     * @param emp Employee object containing the login credentials (e.g., username and password).
     * @return An EmpDTO object encapsulating the login information if authentication is successful;
     *         otherwise, null if authentication fails.
     */
    EmpDTO login(Emp emp);
}
