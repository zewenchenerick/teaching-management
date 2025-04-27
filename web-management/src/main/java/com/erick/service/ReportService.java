package com.erick.service;

import com.erick.pojo.ClazzData;
import com.erick.pojo.JobData;

import java.util.List;
import java.util.Map;

public interface ReportService {

    /**
     * Count the number of employees in each position
     * @return Job option object (list of position and list of number)
     */
    JobData getEmpJobData();

    /**
     * Count the number of employees in each gender
     * @return List of map (Map name: values) for example 男:24
     */
    List<Map<String, Object>> getGenderData();

    /**
     * Count the number of students in each class
     * @return Data object contain datalist and namelist
     */
    ClazzData getStudentData();

    /**
     * Count the number of students in each degree
     * @return List of map (Map name: values) for exmaple "初中"：10
     */
    List<Map<String, Object>> getStudentDegreeData();
}
