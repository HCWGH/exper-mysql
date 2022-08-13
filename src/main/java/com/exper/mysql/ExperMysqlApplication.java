package com.exper.mysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"com.exper.mysql"},exclude = DataSourceAutoConfiguration.class)
public class ExperMysqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExperMysqlApplication.class, args);
    }

}
