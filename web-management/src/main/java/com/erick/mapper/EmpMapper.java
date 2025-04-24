package com.erick.mapper;


import com.erick.pojo.Emp;
import com.erick.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Mapper;

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
}
