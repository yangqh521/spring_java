package com.recover.yqh.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
//@PropertySource("classpath:jdbc.properties")
@MapperScan(basePackages="com.recover.yqh.dao.site",sqlSessionFactoryRef="siteSqlSessionFactoryBean")
public class SiteDataSourceConfig {
	
	// 如果想用@Value("${app.name}") 这种占位符的方式，否则解析会被解析成${app.name} 
	// https://blog.csdn.net/lsgqjh/article/details/54973432
//	@Bean
//	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
//		return new PropertySourcesPlaceholderConfigurer();
//	}
	
//	@Value("${jdbc.site.driverClassName}")
//	private String driverClassName;
//	
//	@Value("${jdbc.site.url}")
//	private String url;
//	
//	@Value("${jdbc.site.username}")
//	private String username;
//	
//	@Value("${jdbc.site.password}")
//	private String password;

	@Autowired
	private Environment env;
	
	
	@Bean(name = "siteDataSource")
	public DataSource siteDataSource() {
		
		String driverClassName = env.getProperty("jdbc.site.driverClassName");
		String url = env.getProperty("jdbc.site.url");
		String username = env.getProperty("jdbc.site.username");
		String password = env.getProperty("jdbc.site.password");
		
		System.out.println("site driverClassName >>> " + driverClassName);
		System.out.println("site url >>> " + url);
		System.out.println("site username >>> " + username);
		System.out.println("site password >>> " + password);
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	
	}
	
	@Bean(name = "siteDataSourceTransactionManager")
    public DataSourceTransactionManager siteTransactionManager() {
        return new DataSourceTransactionManager(siteDataSource());
    }
	
	@Bean(name = "siteSqlSessionFactoryBean")
	public SqlSessionFactoryBean siteSqlSessionFactoryBean() throws IOException {
		
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver(); 
		Resource[] mapperLocations = resourcePatternResolver.getResources("classpath:com/recover/yqh/mapper/*.xml");
		
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(siteDataSource());
		sqlSessionFactoryBean.setMapperLocations(mapperLocations);
		sqlSessionFactoryBean.setTypeAliasesPackage("com.recover.yqh.entity"); //别名，让*Mpper.xml实体类映射可以不加上具体包名
		return sqlSessionFactoryBean;
	}
	
	
	

}
