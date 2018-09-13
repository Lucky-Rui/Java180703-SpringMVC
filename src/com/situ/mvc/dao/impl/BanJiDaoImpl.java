package com.situ.mvc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.situ.mvc.dao.IBanJiDao;
import com.situ.mvc.entity.BanJi;
import com.situ.mvc.util.JDBCUtil;

public class BanJiDaoImpl implements IBanJiDao {
	

	@Override
	public List<BanJi> list() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<BanJi> list = new ArrayList<>();
		try {
			connection = JDBCUtil.getConnection();
			String sql = "select * from  banji ";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				BanJi banJi = new BanJi(id, name);
				list.add(banJi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	@Override
	public int insert(BanJi banJi) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int count = 0;
		try {
			connection = JDBCUtil.getConnection();
			String sql = "insert into banji(name) values (?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, banJi.getName());
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
	public int deleteById(Integer id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int count = 0;
		try {
			connection = JDBCUtil.getConnection();
			String sql = "delete from banji where id = ?";
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
	public int getTotalCount() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int count = 0;
		try {
			connection = JDBCUtil.getConnection();
			String sql = "select count(id) from banji";
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
	public List<BanJi> pageList(int offset, int pageSize) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<BanJi> list = new ArrayList<>();
		try {
			connection = JDBCUtil.getConnection();
			String sql = "select id,name from banji limit ?,?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, offset);
			preparedStatement.setInt(2, pageSize);
			System.out.println(preparedStatement);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				BanJi banJi = new BanJi(id, name);
				list.add(banJi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	@Override
	public int deleteAll(String[] selectIds) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int count = 0;
		try {
			connection = JDBCUtil.getConnection();
			String sql = "DELETE from banji where id = ?;";
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
	public List<BanJi> findByName(String name) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<BanJi> list = new ArrayList<>();
		try {
			connection = JDBCUtil.getConnection();
			String sql = "SELECT * from banji WHERE name like ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + name + "%");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String name1 = resultSet.getString("name");
				BanJi banJi = new BanJi(id, name1);
				list.add(banJi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, null);
		}
		return list;
	}

	@Override
	public int updateBanJi(BanJi banJi) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int count = 0;
		try {
			connection = JDBCUtil.getConnection();
			String sql = "update banji set name = ? where id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, banJi.getName());
			preparedStatement.setInt(2, banJi.getId());
			//处理结果
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
	public BanJi findById(Integer id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		BanJi banJi = new BanJi();
		try {
			connection = JDBCUtil.getConnection();
			String sql = ("SELECT * from BanJi WHERE id = ?");
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Integer id1 = resultSet.getInt("id");
				String name = resultSet.getString("name");
				banJi = new BanJi(id1, name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, resultSet);
		}
		return banJi;
	}

	@Override
	public int findCountByName(String name) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int count = 0;
		try {
			connection = JDBCUtil.getConnection();
			String sql = "select count(id) from banji where name=?";
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
