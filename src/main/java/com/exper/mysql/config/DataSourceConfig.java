package com.exper.mysql.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.support.ibatis.DruidDataSourceFactory;
import com.exper.mysql.enums.DataBaseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 1E7753
 * @date 2022/8/13 13:48
 * @todo 注意：使用自定义注解进行读写分离时开启，避免与使用ShardingJDBC实现读写分离冲突
 */
@Configuration
public class DataSourceConfig {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Bean
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource masterDataSource() {
        logger.info("select master datasource");
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.slave")
    public DataSource slaveDataSource() {
        logger.info("select slave dataSource");
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DataSourceSelector dataSourceSelector(DataSource masterDataSource, DataSource slaveDataSource) {
        Map<Object, Object> map = new HashMap<>(8);
        map.put(DataBaseType.MASTER.getName(), masterDataSource);
        map.put(DataBaseType.SLAVE_FIRST.getName(), slaveDataSource);
        return new DataSourceSelector(masterDataSource, map);
    }

}
