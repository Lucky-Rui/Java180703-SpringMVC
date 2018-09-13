package com.situ.mvc.service;

import java.util.List;

import com.situ.mvc.entity.Course;
import com.situ.mvc.entity.PageBean;

public interface ICourseService {
	/**
	 * 所有课程
	 * 
	 * @return
	 */
	List<Course> list();

	/**
	 * 查找课程
	 * 
	 * @param name
	 * @return
	 */
	List<Course> findByName(String name);

	/**
	 * 通过pageNo和pageSize封住成PageBean对象
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	PageBean getPageBean(int pageNo, int pageSize);

	/**
	 * 根据ID删除课程
	 * 
	 * @param id
	 */
	boolean deleteById(Integer id);

	/**
	 * 插入课程
	 * 
	 * @param course
	 * @return
	 */
	boolean insert(Course course);

	/**
	 * 修改时查找课程展示
	 * 
	 * @param id
	 * @return
	 */
	Course findById(Integer id);

	/**
	 * 修改课程
	 * 
	 * @param course
	 * @return
	 */
	boolean updateCourse(Course course);

	/**
	 * 删除选中的若干个课程
	 * 
	 * @param selectIds
	 */
	boolean deleteAll(String[] selectIds);

	/**
	 * 添加课程的时候查找是否重名
	 * 
	 * @param name
	 * @return
	 */
	boolean checkName(String name);

}
