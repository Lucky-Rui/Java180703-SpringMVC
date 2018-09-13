package com.situ.mvc.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ModelConvertUtil {
	/*
	 * 用于将rs查询结果封装为List<Map<String, Object>>对象
	 * 
	 * @param rs数据库查询结果
	 * 
	 * @return 返回list map封装后的结果
	 */
	public static List<Map<String, Object>> convertList(ResultSet resultSet) {
		// 新建一个map list用于存放多条查询记录
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			ResultSetMetaData metaData = resultSet.getMetaData();// 结果集(ResultSet)结构信息，比如字段数，字段名
			int columnCount = metaData.getColumnCount();// 返回字段的个数
			// 迭代ResultSet
			while (resultSet.next()) {
				// 每迭代一次构造一个Map，封装这一行数据
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {// 循环所有查询出来字段
					map.put(metaData.getColumnLabel(i), resultSet.getObject(i));
					// metaData.getColumnLabel(i) 得到别名
					// metaData.getColumnName(i) 数据库字段名
				}
				list.add(map);// 将封装好的一行记录放到list里面
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/*
	 * 用于将rs查询结果封装为Map<String, Object>对象（适合于只有一条查询记录）
	 * 
	 * @param rs数据库查询结果
	 * 
	 * @return 返回map封装后 字段名：值 的键值对结果
	 */
	public static Map<String, Object> convertMap(ResultSet resultSet) {
		Map<String, Object> map = new TreeMap<String, Object>();
		try {
			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (resultSet.next()) {
				for (int i = 1; i <= columnCount; i++) {
					map.put(metaData.getColumnName(i), resultSet.getObject(i));
				}
			}
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
