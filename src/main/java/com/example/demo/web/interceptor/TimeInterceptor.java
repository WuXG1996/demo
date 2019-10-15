package com.example.demo.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 拦截器在初始化程序时不会启动
 * 当直接访问servlet时不会触发拦截器，但是会触发filter
 * 访问controller时会先触发interceptor之后触发filter
 * @author void
 */
@Slf4j
@Component
public class TimeInterceptor implements HandlerInterceptor{

	//表示控制器方法执行之前调用的方法,返回结果为boolean,如果为true,表示放行,如果为false,表示拦截
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		log.debug("========preHandle=========");
		log.debug(((HandlerMethod)handler).getBean().getClass().getName());
		log.debug("方法名:"+((HandlerMethod)handler).getMethod().getName());

		if(log.isDebugEnabled()){
			req.setAttribute("startTime", System.currentTimeMillis());
		}

        return true;
	}

	//控制器执行完方法之后,视图结合之前
	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView arg3)
			throws Exception {
		log.debug("========postHandle=========");
		if(log.isDebugEnabled()){
			Long start = (Long) req.getAttribute("startTime");
			log.debug("耗时:"+(System.currentTimeMillis() - start));
		}
	}

	//视图结合完成之后调用的方法
	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception exception)
			throws Exception {
		log.debug("========afterCompletion=========");
		if(log.isDebugEnabled()){
			Long start = (Long) req.getAttribute("startTime");
			log.debug("耗时:"+(System.currentTimeMillis() - start));
		}
	}

	
}
