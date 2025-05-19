// src/main/java/com/Erick/aspect/OperateLogAspect.java
package com.erick.aop;

import com.erick.mapper.OperateLogMapper;
import com.erick.pojo.OperateLog;
import com.erick.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class OperateLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.erick.annotation.Loggable)")
    public Object logOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        // 记录开始时间
        long startTime = System.currentTimeMillis();

        // 获取目标方法信息
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        String methodParams = Arrays.toString(joinPoint.getArgs());

        // 初始化操作日志对象
        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(getCurrentUserId()); // 这里需要根据实际情况获取当前操作人ID
        operateLog.setOperateTime(LocalDateTime.now());
        operateLog.setClassName(className);
        operateLog.setMethodName(methodName);
        operateLog.setMethodParams(methodParams);

        Object result;
        try {
            // 执行目标方法
            result = joinPoint.proceed();
            operateLog.setReturnValue(result != null ? result.toString() : "void");
        } catch (Throwable throwable) {
            operateLog.setReturnValue("Exception: " + throwable.getMessage());
            throw throwable;
        } finally {
            // 记录执行耗时
            long costTime = System.currentTimeMillis() - startTime;
            operateLog.setCostTime(costTime);

            log.info("Record OperateLog: {}", operateLog);
            // 保存日志
            operateLogMapper.insert(operateLog);
        }

        return result;
    }

    // 获取当前用户ID，从Token中获取
    private Integer getCurrentUserId() {
        return CurrentHolder.getCurrentId();
    }
}