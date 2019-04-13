package com.example.demo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.example.demo.web.filter.TimeFilter;
import com.example.demo.web.interceptor.TimeInterceptor;
import com.example.demo.web.listener.ListenerTest;
import com.example.demo.web.servlet.ServletTest;

/**
 * 利用注解声明这个类是配置文件
 * @author void
 *
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
	
	/*@Value("${ds.username}")
	private String userName;*/
	
	@Autowired
	private Environment environment;
	
	@Autowired
    private TimeInterceptor timeInterceptor;
	
	/**
	 * 解决跨域问题
	 */
	/*@Override
	public void addCorsMappings(CorsRegistry registry) {
		 registry.addMapping("/fastjson/**")
         	.allowedOrigins("http://localhost:8020");// 允许 8020 端口访问
	}*/
	
	/*public void show(){
		System.out.println("ds.username:"+this.userName);
		System.out.println("ds.password:"+this.environment.getProperty("ds.password"));
	}*/
	
	/**
	 * 整合FastJson
	 * @return
	 */
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters(){
		FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        
        HttpMessageConverter<?> converter = fastJsonHttpMessageConverter;
        
        return new HttpMessageConverters(converter);
	}
	
	/**
	 * 替代了原先web.xml的作用
	 * 直接在java中注册servlet并且设置路径
	 * @return
	 */
	@Bean
	public ServletRegistrationBean servletRegistrationBean(){
		return new ServletRegistrationBean(new ServletTest(),"/servletTest");
	}
	
	@Bean
	public FilterRegistrationBean timeFilter() {
	    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
	    
	    TimeFilter timeFilter = new TimeFilter();
	    registrationBean.setFilter(timeFilter);
	    
	    List<String> urls = new ArrayList<>();
	    urls.add("/*");
	    registrationBean.setUrlPatterns(urls);
	    
	    return registrationBean;
	}
	
	@Bean
	public ServletListenerRegistrationBean<ListenerTest> servletListenerRegistrationBean() {
	    return new ServletListenerRegistrationBean<ListenerTest>(new ListenerTest());
	}
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
    }
}
