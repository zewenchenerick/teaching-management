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

    /**
     * Delete batch of employees working experience based on employee ids
     * @param empIds List of employee ids to be deleted
     */
    void deleteByEmpIds(List<Integer> empIds);
}
