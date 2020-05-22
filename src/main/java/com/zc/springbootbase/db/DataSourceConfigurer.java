package com.zc.springbootbase.db;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
//import javax.sql.DataSource;


/**
 * @author zhanchang
 * @version 1.0
 * @date 2020/5/13 16:24
 */
@Configuration
@EnableConfigurationProperties(MybatisProperties.class)
public class DataSourceConfigurer {
    /**
     * @Description :日志logger句柄
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * @Description :自动注入环境类，用于获取配置文件的属性值
     */
    @Autowired
    private Environment evn;

    private MybatisProperties mybatisProperties;

    public DataSourceConfigurer(MybatisProperties properties) {
        this.mybatisProperties = properties;
    }


    /**
     * @Description :通过名称获取对应的数据源
     * @Author: zc
     * @Date : 2020/5/14 11:29
     */
    private DruidDataSource createDataSource(String name) {
        name = name.trim().isEmpty() ? "spring" : name.trim();
        DruidDataSource dataSource = new DruidDataSource();
        String prefix = name + ".datasource.";
        dataSource.setUrl(evn.getProperty(prefix + "url"));
        dataSource.setUsername(evn.getProperty(prefix + "username"));
        dataSource.setPassword(evn.getProperty(prefix + "password"));
        dataSource.setDriverClassName(evn.getProperty(prefix + "driver-class-name"));
        return dataSource;
    }

    /**
     * spring boot 启动后将自定义创建好的数据源对象放到TargetDataSources中用于后续的切换数据源用
     * (比如：DynamicDataSourceContextHolder.setDataSourceKey("biz")，手动切换到dbMall数据源
     * 同时指定默认数据源连接
     *
     * @return 动态数据源对象
     */
    @Bean
    public DynamicDataSource dynamicDataSource() {
        //获取动态数据库的实例（单例方式）
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        DruidDataSource springDataSource = createDataSource("spring");
        DruidDataSource bizDataSource = createDataSource("biz");
        Map<Object, Object> map = new HashMap<>();
        //自定义数据源key值，将创建好的数据源对象，赋值到targetDataSources中,用于切换数据源时指定对应key即可切换
        map.put("spring", springDataSource);
        map.put("biz", bizDataSource);
        dynamicDataSource.setTargetDataSources(map);
        //设置默认数据源
        dynamicDataSource.setDefaultTargetDataSource(springDataSource);
        return dynamicDataSource;
    }

    /**
     * 配置 SqlSessionFactoryBean
     *
     * @return the sql session factory bean
     * @ConfigurationProperties 在这里是为了将 MyBatis 的 mapper 位置和持久层接口的别名设置到
     * Bean 的属性中，如果没有使用 *.xml 则可以不用该配置，否则将会产生 invalid bond statement 异常
     */
    @Bean
//    @ConfigurationProperties(prefix="mybatis")
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        // 配置 MyBatis
        sqlSessionFactoryBean.setTypeAliasesPackage(evn.getProperty("mybatis.type-aliases-package"));
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(evn.getProperty("mybatis.mapper-locations")));
        // 配置数据源，此处配置为关键配置，如果没有将 dynamicDataSource 作为数据源则不能实现切换
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        return sqlSessionFactoryBean;
    }

    /**
     * 将动态数据源添加到事务管理器中，并生成新的bean
     *
     * @return the platform transaction manager
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}
