package com.zc.springbootbase;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@MapperScan("com.zc.springbootbase.dao")
@ServletComponentScan
public class SpringBootBaseApplication {

    /**
     * @Description :*@SpringBootApplication 注解是一个复合注解,里面包含了@ComponentScan注解默认开启包扫描，扫描与主程序所在包及其子包，对于本工程而言 默认扫描 com.zc.springbootbase
     * @Author: zc
     * @Date : 2020/4/13 11:07
    */
    public static void main(String[] args) {
        SpringApplication.run(SpringBootBaseApplication.class, args);
    }

}

