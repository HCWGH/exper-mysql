package com.exper.mysql.aop;

import com.exper.mysql.config.DataSourceConfig;
import com.exper.mysql.config.DataSourceSelector;
import com.exper.mysql.config.DynamicDataSource;
import com.exper.mysql.enums.DataBaseType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author 1E7753
 * @date 2022/8/13 16:17
 * @todo
 */
@Aspect
@Component
public class DataSourceAop {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Pointcut("@annotation(com.exper.mysql.config.DynamicDataSource)")
    public void dataSourcePointcut() {
    }

    @Around("dataSourcePointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        DynamicDataSource annotation = method.getAnnotation(DynamicDataSource.class);
        Object proceed = null;
        if (annotation != null) {
            DataBaseType dataSource = annotation.name();
            DataSourceSelector.setDataSource(dataSource.getName());
        }
        try {
            proceed = point.proceed();
        } catch (Exception e) {
            logger.error("方法执行异常", e);
        } finally {
            DataSourceSelector.releaseDataSource();
        }
        return proceed;
    }


}
