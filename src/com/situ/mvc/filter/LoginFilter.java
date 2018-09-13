package com.situ.mvc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {
	
	public LoginFilter() {
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 判断有没有登录
		// 1、如果没有登录，重定向到登录界面
		// 2、如果登录上 chaing.doFilter
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String uri = httpServletRequest.getRequestURI();
		String servletPath = httpServletRequest.getServletPath();
		//System.out.println("uri: " + uri);
		//System.out.println("servletPath: " + servletPath);
		// uri: /Java1807Web/login.jsp
		// uri: /Java1807Web/lib/jquery/jquery-1.11.1.js
		// servletPath: /login.jsp
		int lastIndex = servletPath.lastIndexOf(".");  
		String extension = "";
		if (lastIndex != -1) {
			extension = servletPath.substring(lastIndex);// js css png
		}
		/*if ("/login.jsp".equals(servletPath) // 放行
				|| "/checkImg".equals(servletPath) 
				|| "/login".equals(servletPath) 
				|| ".js".equalsIgnoreCase(extension)
				|| ".css".equalsIgnoreCase(extension) 
				|| ".png".equalsIgnoreCase(extension)) {
			chain.doFilter(request, response);
		} else {// 需要验证
			// 判断用户有没有登录
			HttpSession session = httpServletRequest.getSession();
			User user = (User) session.getAttribute("user");
			if (user == null) {
				// 用户没有登录，重定向到登录界面
				httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.jsp");
				return;
			}
			// 执行下一个过滤器或放行（访问servlet）
			chain.doFilter(request, response);
		}*/
		chain.doFilter(request, response);
	}

	public void destroy() {
	}
}
