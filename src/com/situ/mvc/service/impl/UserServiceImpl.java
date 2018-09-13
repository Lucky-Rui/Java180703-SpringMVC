package com.situ.mvc.service.impl;

import com.situ.mvc.dao.IUserDao;
import com.situ.mvc.dao.impl.UserDaoImpl;
import com.situ.mvc.entity.User;
import com.situ.mvc.service.IUserService;

public class UserServiceImpl implements IUserService {
	private IUserDao userDao = new UserDaoImpl();

	@Override
	public User login(String name, String password) {
		// 将登陆的加密;登陆时加密的密码与注册时的密码对比.
		// return userDao.findLogin(name, MD5Util.EncodeUtf8(password));
		return userDao.findLogin(name, password);
	}

	@Override
	public boolean checkName(String name) {
		int count = userDao.findCountByName(name);
		return count > 0 ? true : false;
	}

	@Override
	public boolean register(User user) {
		//注册时将密码加密
		// user.setPassword(MD5Util.EncodeUtf8(user.getPassword()));
		int count = userDao.insert(user);
		return count == 1 ? true : false;
	}

}
