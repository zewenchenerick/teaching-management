package com.erick.controller;

import com.erick.pojo.ClassQueryParam;
import com.erick.pojo.Clazz;
import com.erick.pojo.PageResult;
import com.erick.pojo.Result;
import com.erick.service.ClassService;
import com.erick.vo.ClazzVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        log.info("Class Page Query: {}", classQueryParam);

        PageResult<Clazz> classes = classService.getClassListByPage(classQueryParam);

        return Result.success(classes);
    }


    /**
     * Delete class by its id
     * @param id class id to be deleted
     * @return Result object back to front end
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id){

        log.info("Delete emloyee: id is {}", id);

        classService.deleteById(id);

        return Result.success();
    }


    /**
     * Add new class
     * @param clazz new class object with its information
     * @return Result object return back to front end
     */
    @PostMapping
    public Result addClazz(@RequestBody Clazz clazz){

        log.info("Add new class: {}", clazz);

        classService.addClazz(clazz);

        return Result.success();
    }


    /**
     * Get class information based on id
     * @param id class id to be queried
     * @return Result object containing class object (id) containing information
     */
    @GetMapping("/{id}")
    public Result getClazzById(@PathVariable Integer id){

        log.info("Query class information based on id: id is {}", id);

        Clazz clazz = classService.getClazzById(id);

        return Result.success(clazz);
    }


    /**
     * Update current selected class information
     * @param clazz class object to be updated (contain updated information)
     * @return Result object return back to front end
     */
    @PutMapping
    public Result updateClazz(@RequestBody Clazz clazz){

        log.info("Update class's information: class is {}", clazz);

        classService.updateClazz(clazz);

        return Result.success();
    }


    /**
     * Get all classes information supporting adding new student
     * @return Result object containing list of all classes (encapsulate in ClazzVO)
     */
    @GetMapping("/list")
    public Result getAllClazz(){

        log.info("Get all classes information");

        List<ClazzVO> clazzList = classService.getAllClazz();

        return Result.success(clazzList);
    }
}
