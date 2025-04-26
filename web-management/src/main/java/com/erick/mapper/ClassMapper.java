package com.erick.mapper;

import com.erick.pojo.ClassQueryParam;
import com.erick.pojo.Clazz;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClassMapper {

    /**
     * Get list of class satisfied by condition
     * @return List of class
     */
    List<Clazz> getClassList(ClassQueryParam classQueryParam);

    /**
     * Delete class by its id
     * @param id class id to be deleted
     */
    @Delete("delete from clazz where id = #{id}")
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
    @Select("select * from clazz where id = #{id}")
    Clazz getClazzById(Integer id);
}
