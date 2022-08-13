package com.exper.mysql.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author 1E7753
 * @date 2022/8/13 15:32
 * @todo
 */
public class DataSourceSelector extends AbstractRoutingDataSource {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceSelector.class);
    /**
     * ThreadLocal 用于提供线程局部变量，在多线程环境可以保证各个线程里的变量独立于其它线程里的变量。
     * 也就是说 ThreadLocal 可以为每个线程创建一个【单独的变量副本】，相当于线程的 private static 类型变量。
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    public DataSourceSelector(DataSource defaultDatasource, Map<Object, Object> targetDataSource) {
        super.setTargetDataSources(targetDataSource);
        super.setDefaultTargetDataSource(defaultDatasource);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSource = getDataSource();
        logger.info("final select dataSource name={}", dataSource);
        return dataSource;
    }

    public static String getDataSource() {
        return CONTEXT_HOLDER.get();
    }

    public static void setDataSource(String dataSourceName) {
        CONTEXT_HOLDER.set(dataSourceName);
    }

    public static void releaseDataSource() {
        CONTEXT_HOLDER.remove();
    }
}
