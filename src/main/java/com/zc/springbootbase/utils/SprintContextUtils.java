package com.zc.springbootbase.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author zhanchang
 * @version 1.0
 * @date 2020/5/13 10:20
 */
@Component
public class SprintContextUtils {
    @Autowired
    private ConfigurableApplicationContext applicationContext;

    /**
     * @Description :通过类获取bean
     * @Author: zc
     * @Date : 2020/5/13 10:22
    */
    public <T> T getBean(Class<T> tClass){
        return applicationContext.getBean(tClass);
    }
    /**
     * @Description :通过bean名称获取bean
     * @Author: zc
     * @Date : 2020/5/13 10:24
    */
    public <T> T getBean(String beanName){
        return (T) applicationContext.getBean(beanName);
    }
}
