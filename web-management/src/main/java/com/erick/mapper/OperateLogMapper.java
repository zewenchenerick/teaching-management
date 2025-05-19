package com.erick.mapper;

import com.erick.pojo.LogQueryParam;
import com.erick.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OperateLogMapper {

    //插入日志数据
    @Insert("insert into operate_log (operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time) " +
            "values (#{operateEmpId}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime});")
    void insert(OperateLog log);

    //分页查询日志数据
    // @Select("select * from operate_log")
    List<OperateLog> getLogsByPage(LogQueryParam logQueryParam);
}
