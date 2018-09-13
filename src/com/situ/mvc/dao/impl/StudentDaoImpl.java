package com.situ.mvc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.situ.mvc.dao.IStudentDao;
import com.situ.mvc.entity.Student;
import com.situ.mvc.util.JDBCUtil;
import com.situ.mvc.util.ModelConvertUtil;

/**
 * 用JDBC的方式实现数据的增删改查
 */
public class StudentDaoImpl implements IStudentDao {
	/**
	 * 插入学生
	 */
	@Override
	public int insert(Student student) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int count = 0;
		try {
			connection = JDBCUtil.getConnection();
			String sql = "insert into student(name,age,gender,banji_id) values(?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, student.getName());
			preparedStatement.setInt(2, student.getAge());
			preparedStatement.setString(3, student.getGender());
			preparedStatement.setInt(4, student.getBanjiId());
			count = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * list集合显示出所有学生信息
	 */
	@Override
	public List<Student> list() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Student> list = new ArrayList<>();
		try {
			connection = JDBCUtil.getConnection();
			String sql = "SELECT id,name,age,gender FROM student";
			// 预编译sql
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Integer age = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
				Student student = new Student(id, name, age, gender);
				list.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, resultSet);
		}

		return list;
	}

	/**
	 * 通过id删除学生
	 */
	@Override
	public int deleteById(Integer id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int count = 0;
		try {
			connection = JDBCUtil.getConnection();
			String sql = "delete from student where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			count = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * 通过姓名模糊查找学生
	 */
	@Override
	public List<Student> findByName(String name) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Student> list = new ArrayList<>();
		try {
			connection = JDBCUtil.getConnection();
			String sql = "select * from student where name like ?";
			// String sql = "SELECT id,name,age,gender FROM student";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + name + "%");
			// 查询(没有改变表里面数据)：select executeQuery() 返回结果集：ResultSet
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String fbname = resultSet.getString("name");
				Integer age = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
				Student student = new Student(id, fbname, age, gender);
				list.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	/**
	 * 修改学生
	 */
	@Override
	public int updateStudent(Student student) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int count = 0;
		try {
			String sql = "update student set name = ?, age = ?,gender = ? ,banji_id=? where id= ?";
			connection = JDBCUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			// 设置SQL语句的值
			preparedStatement.setString(1, student.getName());
			preparedStatement.setInt(2, student.getAge());
			preparedStatement.setString(3, student.getGender());
			preparedStatement.setInt(4, student.getBanjiId());
			preparedStatement.setInt(5, student.getId());
			// 处理结果
			count = preparedStatement.executeUpdate();
			if (count == 1) {
				System.out.println("修改成功");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * 根据id查找学生,修改时用于查找学生信息来展示
	 */
	@Override
	public Student findById(Integer id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Student student = null;
		try {
			connection = JDBCUtil.getConnection();
			String sql = "SELECT * FROM student where id=?";
			// 预编译sql
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Integer stuId = resultSet.getInt("id");// Duplicate local
														// variable id
				String name = resultSet.getString("name");
				Integer age = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
				Integer banjiId = resultSet.getInt("banji_id");
				student = new Student(stuId, name, age, gender, banjiId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, resultSet);
		}
		return student;
	}

	/**
	 * 
	 */
	@Override
	public int getTotalCount() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int count = 0;
		try {
			connection = JDBCUtil.getConnection();
			String sql = "SELECT count(id) FROM student";
			preparedStatement = connection.prepareStatement(sql);
			System.out.println(preparedStatement);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				count = resultSet.getInt(1);// 第一列
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, resultSet);
		}

		return count;
	}

	/**
	 * 分页展示
	 */

	// @Override
	// public List<Student> pageList2(int offset, int pageSize) {
	// Connection connection = null;
	// PreparedStatement preparedStatement = null;
	// ResultSet resultSet = null;
	// List<Student> list = new ArrayList<>();
	// try {
	// connection = JDBCUtil.getConnection(); // 分页sql语句：
	// String sql = "SELECT id,name,age,gender FROM student limit ?,?"; //
	// 预编译sql
	// preparedStatement = connection.prepareStatement(sql);
	// preparedStatement.setInt(1, offset);
	// preparedStatement.setInt(2, pageSize);
	// System.out.println(preparedStatement);
	// resultSet = preparedStatement.executeQuery();
	// while (resultSet.next()) {
	// Integer id = resultSet.getInt("id");
	// String name = resultSet.getString("name");
	// Integer age = resultSet.getInt("age");
	// String gender = resultSet.getString("gender");
	// Student student = new Student(id, name, age, gender);
	// list.add(student);
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// } finally {
	// JDBCUtil.close(connection, preparedStatement, resultSet);
	// }
	//
	// return list;
	// }

	/**
	 * 批量删除
	 */
	@Override
	public int deleteAll(String[] selectIds) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int count = 0;
		try {
			connection = JDBCUtil.getConnection();
			String sql = "delete from student where id=?";
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
	public List<Map<String, Object>> pageList(int offset, int pageSize) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Map<String, Object>> list = new ArrayList<>();
		try {
			connection = JDBCUtil.getConnection();
			String sql = "SELECT s.id AS s_id,s.name AS s_name,s.age AS s_age,s.gender AS s_gender,b.name AS b_name FROM student AS s INNER JOIN banji AS b ON s.banji_id = b.id ORDER BY s.id limit ?,? ";
			// 预编译sql
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, offset);
			preparedStatement.setInt(2, pageSize);
			System.out.println(preparedStatement);
			resultSet = preparedStatement.executeQuery();
			list = ModelConvertUtil.convertList(resultSet);
			// while (resultSet.next()) {
			// Integer id = resultSet.getInt("s_id");
			// String name = resultSet.getString("s_name");
			// Integer age = resultSet.getInt("s_age");
			// String gender = resultSet.getString("s_gender");
			// String banjiName = resultSet.getString("b_name");
			// Student student = new Student();
			// Map<String, Object> map = new HashMap<>();
			// // Map是一个key-value结构，就相当于实体类里面：student.age=23 属性-值
			// // 借助Map结构封装不同表里面的数据
			// // s_id 1
			// // s_name 张三
			// // b_name Java180703班
			// list.add(map);
			// }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}
}
