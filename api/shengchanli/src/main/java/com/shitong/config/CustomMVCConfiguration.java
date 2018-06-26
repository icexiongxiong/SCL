package com.shitong.config;

import java.nio.charset.Charset;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.shitong.interceptor.ControllerInterceptor;

/** 
* @author  半天  
* @version 创建时间：2018年3月5日 下午1:10:21  
*/
@Configuration
public class CustomMVCConfiguration extends WebMvcConfigurerAdapter {
	private static final Logger logger =  Logger.getLogger(CustomMVCConfiguration.class);
	
    public void addInterceptors(InterceptorRegistry registry) {
    	logger.info("正在初始化uri拦截器");
        registry.addInterceptor(new ControllerInterceptor()).addPathPatterns("/**");
    }
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(responseBodyConverter());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }
    
    public void addCorsMappings(CorsRegistry registry) {
    	logger.info("cors infomation");
        registry.addMapping("/**")  
                .allowedOrigins("*")  
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS", "HEAD", "PATCH")  
                .maxAge(3600);  
    }
    
}