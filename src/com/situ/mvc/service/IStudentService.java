package com.situ.mvc.service;

import java.util.List;

import com.situ.mvc.entity.PageBean;
import com.situ.mvc.entity.Student;

public interface IStudentService {
	/**
	 * 插入学生信息
	 * 
	 * @param student
	 * @return 插入成功返回true，失败返回false
	 */
	boolean insert(Student student);

	/**
	 * 返回所有学生的集合
	 * 
	 * @return
	 */
	List<Student> list();

	/**
	 * 根据id删除
	 * 
	 * @param id
	 * @return 删除成功返回true 失败返回false
	 */
	boolean deleteById(Integer id);

	/**
	 * 根据名字查找学生信息
	 * 
	 * @param name
	 * @return
	 */
	List<Student> findByName(String name);

	/**
	 * 修改学生信息
	 * 
	 * @param student
	 */
	boolean updateStudent(Student student);

	/**
	 * 修改时用于查找学生信息来展示
	 * 
	 * @param id
	 * @return
	 */
	Student findById(Integer id);

	/**
	 * 通过pageNo和pageSize封住成PageBean对象
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	PageBean getPageBean(int pageNo, int pageSize);

	/**
	 * 批量删除
	 * 
	 * @param selectIds
	 * @return
	 */
	boolean deleteAll(String[] selectIds);

}
