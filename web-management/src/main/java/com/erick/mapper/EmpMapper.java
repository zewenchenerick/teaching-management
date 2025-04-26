package com.erick.mapper;


import com.erick.pojo.Emp;
import com.erick.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

    // --------------------------------original method----------------------------------------------------
    // /**
    //  * Query total number of employee stored in data
    //  * @return number of employee
    //  */
    // @Select("select count(*) from emp e left join tlias.dept d on d.id = e.dept_id")
    // Long count();


    // /**
    //  * page query
    //  * @return List of employees to display
    //  */
    // @Select("select e.*, d.name as deptName from emp e left join tlias.dept d on d.id = e.dept_id " +
    //         "order by e.update_time desc limit #{startIndex},#{pageSize}")
    // List<Emp> list(Integer startIndex, Integer pageSize);


    // ----------------------------------page helper----------------------------------------------------------

    // @Select("select e.*, d.name as deptName from emp e left join tlias.dept d on d.id = e.dept_id order by e.update_time desc")
    // @Result(column = "begin", property = "startDate")
    // List<Emp> list(String name, Integer gender, LocalDate startDate, LocalDate endDate);

    /**
     * Page query - get list of employees to display
     * @param empQueryParam conditional parameters object
     * @return List of employees to display
     */
    List<Emp> list(EmpQueryParam empQueryParam);

    /**
     * Insert new employee basic information
     * @param emp employee object
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            "values (#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}," +
            "#{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    void saveEmployee(Emp emp);

    /**
     * Delete batch of employees basic information based on IDs
     * @param ids list of employees id
     */
    void deleteByIds(List<Integer> ids);

    /**
     * Query employee information (basic information and working experience) based on id
     * @param id Employee id to be queried
     * @return Employee id to be queried
     */
    Emp getByID(Integer id);

    /**
     * Update current selected employee basic information
     * @param emp employee object to be updated
     */
    void updateById(Emp emp);

    /**
     * Count the number of employees in each position
     * @return List of map, (each map is position -> number)
     */
    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();

    /**
     * Count the number of employees in each gender
     * @return List of map (Map name: values) for example 男:24
     */
    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();

    /**
     * Get all employees information
     * @return List of all employees
     */
    List<Emp> getAllEmp();
}
