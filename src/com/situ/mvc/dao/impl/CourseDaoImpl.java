package com.situ.mvc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.situ.mvc.dao.ICourseDao;
import com.situ.mvc.entity.Course;
import com.situ.mvc.util.JDBCUtil;

public class CourseDaoImpl implements ICourseDao {

	@Override
	public List<Course> list() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Course> list = new ArrayList<>();
		try {
			connection = JDBCUtil.getConnection();
			String sql = "select * from  course ";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Integer credit = resultSet.getInt("credit");
				Course course = new Course(id, name, credit);
				list.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	@Override
	public List<Course> findByName(String name) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Course> list = new ArrayList<>();
		try {
			connection = JDBCUtil.getConnection();
			String sql = "SELECT * from course WHERE name like ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + name + "%");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String name1 = resultSet.getString("name");
				Integer credit = resultSet.getInt("credit");
				Course course = new Course(id, name1, credit);
				list.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, null);
		}
		return list;
	}

	@Override
	public int getTotalCount() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int count = 0;
		try {
			connection = JDBCUtil.getConnection();
			String sql = "select count(id) from course";
			preparedStatement = connection.prepareStatement(sql);
			System.out.println(preparedStatement);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);// 代表第一列
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, resultSet);
		}
		return count;
	}

	@Override
	public List<Course> pageList(int offset, int pageSize) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Course> list = new ArrayList<>();
		try {
			connection = JDBCUtil.getConnection();
			String sql = "select id,name,credit from course limit ?,?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, offset);
			preparedStatement.setInt(2, pageSize);
			System.out.println(preparedStatement);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Integer credit = resultSet.getInt("credit");
				Course course = new Course(id, name, credit);
				list.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	@Override
	public int deleteById(Integer id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int count = 0;
		try {
			connection = JDBCUtil.getConnection();
			String sql = "delete from course where id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			count = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, null);
		}
		return count;
	}

	@Override
	public int insert(Course course) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int count = 0;
		try {
			connection = JDBCUtil.getConnection();
			String sql = "insert into course(name,credit) values (?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, course.getName());
			preparedStatement.setInt(2, course.getCredit());
			System.out.println(preparedStatement);
			count = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, null);
		}
		return count;
	}

	@Override
	public Course findById(Integer id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Course course = new Course();
		try {
			connection = JDBCUtil.getConnection();
			String sql = ("SELECT * from course WHERE id = ?");
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Integer id1 = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Integer credit = resultSet.getInt("credit");
				course = new Course(id1, name, credit);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, resultSet);
		}
		return course;
	}

	@Override
	public int updateCourse(Course course) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int count = 0;
		try {
			connection = JDBCUtil.getConnection();
			String sql = "update course set name = ? , credit=? where id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, course.getName());
			preparedStatement.setInt(2, course.getCredit());
			preparedStatement.setInt(3, course.getId());
			// 处理结果
			count = preparedStatement.executeUpdate();
			if (count == 1) {
				System.out.println("修改成功");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, null);
		}
		return count;
	}

	@Override
	public int deleteAll(String[] selectIds) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int count = 0;
		try {
			connection = JDBCUtil.getConnection();
			String sql = "DELETE from course where id = ?;";
			preparedStatement = connection.prepareStatement(sql);
			for (String id : selectIds) {
				preparedStatement.setInt(1, Integer.parseInt(id));
				preparedStatement.addBatch();
			}
			int[] counts = preparedStatement.executeBatch();
			count = counts.length;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, null);
		}
		return count;
	}

	@Override
	public int findCountByName(String name) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int count = 0;
		try {
			connection = JDBCUtil.getConnection();
			String sql = "select count(id) from course where name=?";
			// 预编译sql
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			System.out.println(preparedStatement);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				count = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, resultSet);
		}
		return count;
	}

}
