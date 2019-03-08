package com.recover.yqh.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
//@PropertySource("classpath:jdbc.properties")
@MapperScan(basePackages="com.recover.yqh.dao.dsp",sqlSessionFactoryRef="dspSqlSessionFactoryBean")
public class DspDataSourceConfig {
	
	@Autowired
	private Environment env;
	
	@Primary
	@Bean(name = "dspDataSource")
	public DataSource dspDataSource() {
		
		String driverClassName = env.getProperty("jdbc.dsp.driverClassName");
		String url = env.getProperty("jdbc.dsp.url");
		String username = env.getProperty("jdbc.dsp.username");
		String password = env.getProperty("jdbc.dsp.password");
		
		System.out.println("dsp driverClassName >>> " + driverClassName);
		System.out.println("dsp url >>> " + url);
		System.out.println("dsp username >>> " + username);
		System.out.println("dsp password >>> " + password);
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	
	}
	
	@Primary
	@Bean(name = "dspDataSourceTransactionManager")
    public DataSourceTransactionManager dspTransactionManager() {
        return new DataSourceTransactionManager(dspDataSource());
    }
	
	@Primary
	@Bean(name = "dspSqlSessionFactoryBean")
	public SqlSessionFactoryBean dspSqlSessionFactoryBean() throws IOException {
		
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver(); 
		Resource[] mapperLocations = resourcePatternResolver.getResources("classpath:com/recover/yqh/mapper/*.xml");
		
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dspDataSource());
		sqlSessionFactoryBean.setMapperLocations(mapperLocations);
		sqlSessionFactoryBean.setTypeAliasesPackage("com.recover.yqh.entity"); //别名，让*Mpper.xml实体类映射可以不加上具体包名
		return sqlSessionFactoryBean;
	}
	
	
	

}
