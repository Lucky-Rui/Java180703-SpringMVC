package com.situ.mvc.service.impl;

import java.util.List;

import com.situ.mvc.dao.ICourseDao;
import com.situ.mvc.dao.impl.CourseDaoImpl;
import com.situ.mvc.entity.Course;
import com.situ.mvc.entity.PageBean;
import com.situ.mvc.service.ICourseService;

public class CourseServiceImpl implements ICourseService {
	private ICourseDao courseDao = new CourseDaoImpl();

	@Override
	public List<Course> list() {
		return courseDao.list();
	}

	@Override
	public List<Course> findByName(String name) {
		return courseDao.findByName(name);
	}

	@Override
	public PageBean getPageBean(int pageNo, int pageSize) {
		PageBean pageBean = new PageBean<>();
		// 当前是第几页
		pageBean.setPageNo(pageNo);
		// 一页有多少条数据
		pageBean.setPageSize(pageSize);
		// 总记录数
		int totalCount = courseDao.getTotalCount();
		pageBean.setTotalCount(totalCount);
		// 一共有多少页
		// 总数量 每页数量 总页数
		// 10 3 4 10/3=3...1
		// 11 3 4 11/3=3...2
		// 12 3 4 12/3=4
		// 13 3 5 13/3=4...1
		int totalPage = (int) Math.ceil((double) totalCount / pageSize);
		pageBean.setTotalPage(totalPage);
		// 当前页的数据
		int offset = (pageNo - 1) * pageSize;
		List<Course> list = courseDao.pageList(offset, pageSize);
		pageBean.setList(list);

		return pageBean;
	}

	@Override
	public boolean deleteById(Integer id) {
		int count = courseDao.deleteById(id);
		return count == 1 ? true : false;
	}

	@Override
	public boolean insert(Course course) {
		int count = courseDao.insert(course);
		return count == 1 ? true : false;
	}

	@Override
	public Course findById(Integer id) {
		return courseDao.findById(id);
	}

	@Override
	public boolean updateCourse(Course course) {
		int count = courseDao.updateCourse(course);
		return count == 1 ? true : false;
	}

	@Override
	public boolean deleteAll(String[] selectIds) {
		int count = courseDao.deleteAll(selectIds);
		return count == selectIds.length ? true : false;
	}

	@Override
	public boolean checkName(String name) {
		int count = courseDao.findCountByName(name);
		return count > 0 ? true : false;
	}

}
