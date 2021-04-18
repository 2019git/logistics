package com.company.logistics.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

/**
 * @author wzj
 * @date 2021/4/18 15:36
 */
@Configuration
@AutoConfigureAfter(MybatisConfiguration.class)
public class MapperConfiguration implements EnvironmentAware {

    /**
     * basePackage
     */
    private String basePackage;
    private String mappers;
    private String identity;
    private String order;

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(Environment environment) {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage(basePackage);

        Properties properties = new Properties();
        properties.setProperty("mappers", mappers);
        properties.setProperty("IDENTITY", identity);
        properties.setProperty("ORDER", order);
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.basePackage = environment.getProperty("mybatis-plus.basepackage");
        this.mappers = environment.getProperty("mybatis-plus.mappers");
        this.identity = environment.getProperty("mybatis-plus.identity");
        this.order = environment.getProperty("mybatis-plus.order");
    }
}
