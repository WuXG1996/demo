package com.example.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
@Component
public class TimeInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		/*System.out.println("========preHandle=========");
        System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
        System.out.println("方法名:"+((HandlerMethod)handler).getMethod().getName());
        
        req.setAttribute("startTime", System.currentTimeMillis());*/
        
        return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView arg3)
			throws Exception {
		//System.out.println("========postHandle=========");
        //Long start = (Long) req.getAttribute("startTime");
        //System.out.println("耗时:"+(System.currentTimeMillis() - start));
	}
	
	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception exception)
			throws Exception {
		//System.out.println("========afterCompletion=========");
		//Long start = (Long) req.getAttribute("startTime");
        //System.out.println("耗时:"+(System.currentTimeMillis() - start));
        
        //System.out.println(exception);
	}

	
}
