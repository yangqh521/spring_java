package com.recover.yqh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc	//启动springMVC注解驱动 等价于xml 配置中的<mvc:annotation-driven/>
@ComponentScan("com.recover.yqh.controller")
public class WebConfig extends WebMvcConfigurerAdapter {

	
	// 定义试图解析器
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/view/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
	}
	
	// 静态资源交给默认的servlet
	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();	//将静态资源的请求转发到servlet容器中默认的servlet上
    }

	// 访问静态资源
    @Override  
    public void addResourceHandlers(ResourceHandlerRegistry registry) {  
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }
    
    // 文件上传处理器 
    @Bean(name="multipartResolver")  
    public CommonsMultipartResolver commonsMultipartResolver(){  
    	CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver();
        multipartResolver.setResolveLazily(true);
        multipartResolver.setMaxUploadSize(1048576000L);
        multipartResolver.setDefaultEncoding("UTF-8");
        return multipartResolver;  
     }  
	
    // 跳转主页面
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        super.addViewControllers(registry);
    }

}
