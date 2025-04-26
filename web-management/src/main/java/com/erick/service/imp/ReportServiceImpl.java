package com.erick.service.imp;

import com.erick.mapper.EmpMapper;
import com.erick.pojo.JobOption;
import com.erick.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public JobOption getEmpJobData() {

        List<Map<String, Object>> mapList = empMapper.countEmpJobData();

        List<Object> jobList = mapList.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = mapList.stream().map(dataMap -> dataMap.get("total")).toList();

        return new JobOption(jobList, dataList);

    }

    @Override
    public List<Map<String, Object>> getGenderData() {

        return empMapper.countEmpGenderData();

    }
}
