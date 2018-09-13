package com.situ.mvc.dao;

import java.util.List;

import com.situ.mvc.entity.Course;

public interface ICourseDao {

	List<Course> list();

	List<Course> findByName(String name);

	int getTotalCount();

	List<Course> pageList(int offset, int pageSize);

	int deleteById(Integer id);

	int insert(Course course);

	Course findById(Integer id);

	int updateCourse(Course course);

	int deleteAll(String[] selectIds);

	int findCountByName(String name);

}
