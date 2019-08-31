package com.example.demo.config;

import com.example.demo.web.filter.TimeFilter;
import com.example.demo.web.interceptor.TimeInterceptor;
import com.example.demo.web.listener.ListenerTest;
import com.example.demo.web.servlet.ServletTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 利用注解声明这个类是配置文件
 * @author void
 *
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
	
	@Autowired
	private Environment environment;
	
	@Autowired
    private TimeInterceptor timeInterceptor;

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
	    return new ServletListenerRegistrationBean<>(new ListenerTest());
	}
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
    }
}
