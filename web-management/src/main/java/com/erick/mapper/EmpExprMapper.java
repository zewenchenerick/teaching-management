package com.erick.mapper;

import com.erick.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Employee work experience
 */
@Mapper
public interface EmpExprMapper {

    /**
     * Save the working experiences for new employee
     * @param exprList  list of working experience object
     */
    void saveBatch(List<EmpExpr> exprList);

}
