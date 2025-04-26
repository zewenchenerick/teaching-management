package com.erick.controller;

import com.erick.pojo.ClassQueryParam;
import com.erick.pojo.Clazz;
import com.erick.pojo.PageResult;
import com.erick.pojo.Result;
import com.erick.service.ClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClassController {

    @Autowired
    private ClassService classService;

    /**
     * Query Classes with conditional by page
     * @param classQueryParam conditional parameters object
     * @return result object Page result (total count result, with filtered list of classes) to front end
     */
    @GetMapping
    public Result getClassListByPage(ClassQueryParam classQueryParam){

        log.info("Page Query: {}", classQueryParam);

        PageResult<Clazz> classes = classService.getClassListByPage(classQueryParam);

        return Result.success(classes);
    }
}
