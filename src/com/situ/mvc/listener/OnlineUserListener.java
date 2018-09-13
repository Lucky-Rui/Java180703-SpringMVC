package com.situ.mvc.listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.situ.mvc.entity.User;

/**
 * 监听对象创建/销毁的监听器接口:
 * 
 * 监听servletContext对象的创建,初始化集合onLineUserList
 * 
 * @author WANGRUI
 *
 */
public class OnlineUserListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 监听用户的登录行为,如果用户登录就把用户的信息User对象放到
		List<User> onLineUserList = new ArrayList<User>();
		// 放入ServletContext中
		sce.getServletContext().setAttribute("onLineUserList", onLineUserList);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// 获取ServletContext
		ServletContext servletContext = sce.getServletContext();
		// 获取列表onLineUserList
		Object object = servletContext.getAttribute("onLineUserList");
		// 移除在线列表集合
		if (object != null) {
			servletContext.removeAttribute("onLineUserList");
		}
	}

}
