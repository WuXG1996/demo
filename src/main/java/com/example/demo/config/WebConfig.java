package com.example.demo.config;

import com.example.demo.web.listener.ListenerTest;
import com.example.demo.web.servlet.ServletTest;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 利用注解声明这个类是配置文件
 * @author void
 *
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

//	@Autowired
//    private TimeInterceptor timeInterceptor;

	/**
	 * 注册自定义interceptor
	 * @param registry
	 */
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(timeInterceptor);
////				.addPathPatterns("/**").excludePathPatterns("/test");
//	}

	/**
	 * 注册自定义filter
	 * @return
	 */
//	@Bean
//	public FilterRegistrationBean timeFilter() {
//	    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//	    registrationBean.setFilter(new TimeFilter());
//	    registrationBean.setUrlPatterns(new ArrayList<String>(){{
//	    	this.add("/*");
//		}});
//	    return registrationBean;
//	}

	/**
	 * 注册servlet
	 * @return
	 */
	@Bean
	public ServletRegistrationBean servletRegistrationBean(){
		return new ServletRegistrationBean(new ServletTest(),"/servletTest");
	}

	/**
	 * 注册listener
	 * @return
	 */
	@Bean
	public ServletListenerRegistrationBean<ListenerTest> servletListenerRegistrationBean() {
	    return new ServletListenerRegistrationBean<>(new ListenerTest());
	}


}
