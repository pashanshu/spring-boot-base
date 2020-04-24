//package com.zc.springbootbase.config;
//
//import javax.sql.DataSource;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import com.alibaba.druid.pool.DruidDataSource;
//
//@Configuration // 扫描 Mapper 接口并容器管理
//@MapperScan(basePackages = "com.zc.springbootbase.model", sqlSessionFactoryRef = "sqlSessionFactory")
//public class DataSourceConfig {
//
//	@Value("${spring.datasource.type}")
//	private Class<? extends DataSource> dataSourceType;
//
//	@Bean(name = "dataSource")
//	@ConfigurationProperties(prefix = "spring.datasource")
//	public DataSource dataSource() {
//		return DataSourceBuilder.create().type(dataSourceType).build();
//	}
//
//	@Bean(name = "sqlSessionFactory")
//	@ConditionalOnMissingBean
//	public SqlSessionFactory sqlSessionFactory() throws Exception {
//		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//		sqlSessionFactoryBean.setDataSource(dataSource());
//		sqlSessionFactoryBean.setTypeAliasesPackage("com.zc.springbootbase.dao");
//		sqlSessionFactoryBean.setMapperLocations(
//				new PathMatchingResourcePatternResolver().getResources("classpath:com/zc/dao*//*.xml"));
//		sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
//		return sqlSessionFactoryBean.getObject();
//	}
//}
