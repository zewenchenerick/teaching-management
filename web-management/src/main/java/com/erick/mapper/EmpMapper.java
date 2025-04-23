package com.erick.mapper;


import com.erick.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmpMapper {

    /**
     * Query total number of employee stored in data
     * @return number of employee
     */
    @Select("select count(*) from emp e left join tlias.dept d on d.id = e.dept_id")
    Long count();


    /**
     * page query
     * @return List of employees to display
     */
    @Select("select e.*, d.name as deptName from emp e left join tlias.dept d on d.id = e.dept_id " +
            "order by e.update_time desc limit #{startIndex},#{pageSize}")
    List<Emp> list(Integer startIndex, Integer pageSize);

}
