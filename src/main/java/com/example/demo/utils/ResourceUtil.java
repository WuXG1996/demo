package com.example.demo.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;


public class ResourceUtil {
	/**
	 * 获得请求路径
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestPath(HttpServletRequest request) {
		String queryString = request.getQueryString();
		String requestPath = request.getRequestURI();
		if(StringUtils.isNotEmpty(queryString)){
			requestPath += "?" + queryString;
		}

		if (requestPath.indexOf("&") > -1) {// 去掉其他参数
			requestPath = requestPath.substring(0, requestPath.indexOf("&"));
		}
		requestPath = requestPath.substring(request.getContextPath().length() + 1);// 去掉项目路径
		return requestPath;
	}
}
