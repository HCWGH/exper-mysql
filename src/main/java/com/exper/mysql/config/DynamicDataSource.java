package com.exper.mysql.config;

import com.exper.mysql.enums.DataBaseType;

import java.lang.annotation.*;

/**
 * @author 1E7753
 * @date 2022/8/13 15:24
 * @todo 作用于方法的注解
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicDataSource {
    DataBaseType name()  default DataBaseType.MASTER;
}
