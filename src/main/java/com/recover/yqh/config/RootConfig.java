package com.recover.yqh.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@PropertySource("classpath:jdbc.properties")
@EnableTransactionManagement
@Import({DspDataSourceConfig.class, SiteDataSourceConfig.class, RedisConfig.class})
@ComponentScan(basePackages={"com.recover.yqh.service"},
	excludeFilters={
	    @Filter(type=FilterType.ANNOTATION, value=EnableWebMvc.class)
	})
public class RootConfig {
	
	

}
