package com.erick.mapper;


import com.erick.pojo.Emp;
import com.erick.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

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

}
