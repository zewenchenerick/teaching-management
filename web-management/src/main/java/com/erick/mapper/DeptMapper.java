package com.erick.mapper;

import com.erick.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    /**
     * Query all department data
     * @return list of departments
     */
    /*@Results({
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })*/
    // @Select("select id, name, create_time as createTime, update_time as updateTime from dept order by update_time desc")
    @Select("select id, name, create_time, update_time from dept order by update_time desc")
    List<Dept> findAll();

    /**
     * delete department based on id
     * @param id department id
     */
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    /**
     * Insert department into database
     * @param dept department object
     */
    @Insert("insert into dept(name, create_time, update_time)  values(#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);


    /**
     * Find department based on id
     * @param id department id
     * @return department object
     */
    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    Dept findById(Integer id);


    /**
     * Update department information
     * @param dept department object
     */
    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);
}
