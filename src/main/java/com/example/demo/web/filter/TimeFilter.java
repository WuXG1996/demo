package com.example.demo.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * 配置方法一直接用@Component注解
 * 配置方法二写在WebConfig里
 * @author void
 *
 */
@Slf4j
//@Component
@WebFilter(filterName = "timeFilter", urlPatterns = "/*")
//https://blog.csdn.net/fxbin123/article/details/82558174
public class TimeFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.debug("=======初始化过滤器=========");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.debug("TimeFilter.doFilter,url:{}", ((HttpServletRequest) request).getRequestURI());
		long start = System.currentTimeMillis();
		chain.doFilter(request, response);
		log.debug("filter 耗时：" + (System.currentTimeMillis() - start));
	}

	@Override
	public void destroy() {
		log.debug("=======销毁过滤器=========");
	}
	
}
