package com.situ.mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class HandlerInterceptor1 implements HandlerInterceptor {
	/**
	 * 服务器启动时并不执行。第一次发出请求时执行。
	 * 再次请求时，就直接访问里面的方法
	 */
	public HandlerInterceptor1() {
		//System.out.println("HandlerInterceptor1.HandlerInterceptor1()");
	}

	/**
	 * 调用controller前会执行这个方法，返回true表示继续执行，返回false表示终止执行。 
	 * 用户权限验证，用户登陆处理都可以写在这里
	 */
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		//System.out.println("HandlerInterceptor1.preHandle()");
		return true;
	}

	/**
	 * 执行controller方法后会执行这个方法 
	 * 但是是在返回ModelAndView之前
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		//System.out.println("HandlerInterceptor1.postHandle()");
	}

	/**
	 * 执行Controller方法之后并且ModelAndView视图已经返回时候执行这个方法
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		//System.out.println("HandlerInterceptor1.afterCompletion()");
	}

}
