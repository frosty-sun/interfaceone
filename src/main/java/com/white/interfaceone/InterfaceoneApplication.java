package com.white.interfaceone;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.white.interfaceone.dao")
public class InterfaceoneApplication extends SpringBootServletInitializer {

    /**
     * Spring Boot打包初始化入口类
     *
     * @param application spring boot的入口application class
     * @return 入口类class
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(InterfaceoneApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(InterfaceoneApplication.class, args);
    }

}
