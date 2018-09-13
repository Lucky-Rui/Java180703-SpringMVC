package com.situ.mvc.service.impl;

import java.util.List;
import java.util.Map;

import com.situ.mvc.dao.IStudentDao;
import com.situ.mvc.dao.impl.StudentDaoImpl;
import com.situ.mvc.entity.PageBean;
import com.situ.mvc.entity.Student;
import com.situ.mvc.service.IStudentService;

public class StudentServiceImpl implements IStudentService {
	private IStudentDao studentDao = new StudentDaoImpl();

	/**
	 * 增加学生
	 */
	@Override
	public boolean insert(Student student) {
		int count = studentDao.insert(student);
		return count == 1 ? true : false;
	}

	/**
	 * 读取所有学生
	 */
	@Override
	public List<Student> list() {
		return studentDao.list();
	}

	/**
	 * 删除学生
	 */
	@Override
	public boolean deleteById(Integer id) {
		int count = studentDao.deleteById(id);
		return count == 1 ? true : false;
	}

	/**
	 * 搜索学生
	 */
	@Override
	public List<Student> findByName(String name) {
		return studentDao.findByName(name);
	}

	/**
	 * 修改学生
	 */
	@Override
	public boolean updateStudent(Student student) {
		int count = studentDao.updateStudent(student);
		return count == 1 ? true : false;
	}

	/**
	 * 修改时用于查找学生信息来展示
	 */
	@Override
	public Student findById(Integer id) {
		return studentDao.findById(id);
	}

	/**
	 * 
	 */
	@Override
	public PageBean getPageBean(int pageNo, int pageSize) {
		PageBean pageBean = new PageBean();
		// 当前是第几页
		pageBean.setPageNo(pageNo);
		// 一页有多少条数据
		pageBean.setPageSize(pageSize);
		// 总记录数
		int totalCount = studentDao.getTotalCount();
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
		List<Map<String, Object>> list = studentDao.pageList(offset, pageSize);
		pageBean.setList(list);;

		return pageBean;
	}

	/**
	 * 批量删除
	 */
	@Override
	public boolean deleteAll(String[] selectIds) {
		// 有缺点：每次打开连接，关闭连接，非常消耗性能
		/*
		 * for (String id : selectIds) {
		 * studentDao.deleteById(Integer.parseInt(id)); }
		 */

		// 批量删除
		int count = studentDao.deleteAll(selectIds);
		return count == selectIds.length ? true : false;
	}

}
