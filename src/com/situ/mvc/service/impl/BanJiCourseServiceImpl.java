package com.situ.mvc.service.impl;

import com.situ.mvc.dao.IBanJiCourseDao;
import com.situ.mvc.dao.impl.BanJiCourseDaoImpl;
import com.situ.mvc.service.IBanJiCourseService;

public class BanJiCourseServiceImpl implements IBanJiCourseService{
	private IBanJiCourseDao banJiCourseDao = new BanJiCourseDaoImpl();
	
}
