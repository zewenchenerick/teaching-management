package com.erick.mapper;

import com.erick.pojo.ClassQueryParam;
import com.erick.pojo.Clazz;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassMapper {

    /**
     * Get list of class satisfied by condition
     * @return List of class
     */
    List<Clazz> getClassList(ClassQueryParam classQueryParam);
}
