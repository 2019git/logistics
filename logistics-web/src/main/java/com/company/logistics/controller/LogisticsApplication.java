package com.company.logistics.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

import javax.annotation.PostConstruct;

@EnableTransactionManagement
@SpringBootApplication
@EnableScheduling
@ComponentScan(value = "com.company")
@MapperScan(basePackages = "com.company.logistics.mapper.mapper")//用于扫描mapper接口层，指向mapper接口类的统一父级目录
public class LogisticsApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(LogisticsApplication.class,args);
    }

    public SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return super.configure(builder);
    }

}
