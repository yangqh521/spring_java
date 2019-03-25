package com.recover.yqh.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

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
	
	@Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        /*
         * 1、需要先定义一个 convert 转换消息的对象;
         * 2、添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
         * 3、在convert中添加配置信息.
         * 4、将convert添加到converters当中.
         */
        // 1、需要先定义一个 convert 转换消息的对象;
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //2、添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat );
        //3、在convert中添加配置信息.
        fastConverter.setFastJsonConfig(fastJsonConfig);
        //4、将convert添加到converters当中.
        converters.add(fastConverter);
    }

}
