package com.situ.mvc.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 乱码问题的过滤器
 * @author WANGRUI
 *
 */
public class EncodingFilter implements Filter {

	public EncodingFilter() {
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		// 处理POST编码问题 表单里面method="get" method="post"
		String method = httpServletRequest.getMethod();
		if ("post".equalsIgnoreCase(method)) {
			request.setCharacterEncoding("utf-8");
		}
		// 执行下一个过滤器或者放行
		chain.doFilter(request, response);
	}

	public void destroy() {
	}

}
