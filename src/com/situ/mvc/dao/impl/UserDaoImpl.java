package com.situ.mvc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.situ.mvc.dao.IUserDao;
import com.situ.mvc.entity.User;
import com.situ.mvc.util.JDBCUtil;

public class UserDaoImpl implements IUserDao {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	@Override
	public User findLogin(String name, String password) {
		User user = null;
		try {
			connection = JDBCUtil.getConnection();
			String sql = "select * from user where name = ? and password = ?";
			// String sql = "SELECT id,name,age,gender FROM student";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, password);
			// 查询(没有改变表里面数据)：select executeQuery() 返回结果集：ResultSet
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String fname = resultSet.getString("name");
				String fpassword = resultSet.getString("password");
				user = new User(fname, fpassword);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, resultSet);
		}
		return user;
	}

	@Override
	public int findCountByName(String name) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int count = 0;
		try {
			connection = JDBCUtil.getConnection();
			String sql = "select count(id) from user where name=?";
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

	@Override
	public int insert(User user) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int count = 0;
		try {
			connection = JDBCUtil.getConnection();
			String sql = "insert into user(name,password) values(?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getPassword());
			count = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

}
