package com.situ.mvc.dao;

import com.situ.mvc.entity.User;

public interface IUserDao {
	/**
	 * 登陆（用户名和密码）
	 * 
	 * @param name
	 * @param password
	 * @return 查到的用户
	 */
	User findLogin(String name, String password);

	/**
	 * 注册(检查重名)
	 * 
	 * @param name
	 * @return 查询到的数量
	 */
	int findCountByName(String name);

	/**
	 * 注册(添加用户的账号密码)
	 * 
	 * @param name
	 * @param password
	 * @return 
	 */
	int insert(User user);
}
