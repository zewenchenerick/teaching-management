package com.erick.service.imp;

import com.erick.mapper.EmpMapper;
import com.erick.mapper.StudentMapper;
import com.erick.pojo.ClazzData;
import com.erick.pojo.JobData;
import com.erick.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobData getEmpJobData() {

        List<Map<String, Object>> mapList = empMapper.countEmpJobData();

        List<Object> jobList = mapList.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = mapList.stream().map(dataMap -> dataMap.get("total")).toList();

        return new JobData(jobList, dataList);

    }

    @Override
    public List<Map<String, Object>> getGenderData() {
        return empMapper.countEmpGenderData();

    }

    @Override
    public ClazzData getStudentData() {

        List<Map<String, Object>> mapList = studentMapper.countStudentData();

        List<Object> clazzList = mapList.stream().map(dataMap -> dataMap.get("clazz")).toList();
        List<Object> dataList = mapList.stream().map(dataMap -> dataMap.get("count")).toList();

        return new ClazzData(clazzList, dataList);
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        return studentMapper.countStudentDegreeData();
    }
}
