package com.erick.service;

import com.erick.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {

    /**
     * Count the number of employees in each position
     * @return Job option object (list of position and list of number)
     */
    JobOption getEmpJobData();

    /**
     * Count the number of employees in each gender
     * @return List of map (Map name: values) for example 男:24
     */
    List<Map<String, Object>> getGenderData();
}
