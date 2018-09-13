package com.situ.mvc.service.impl;

import java.util.List;

import com.situ.mvc.dao.IBanJiDao;
import com.situ.mvc.dao.impl.BanJiDaoImpl;
import com.situ.mvc.entity.BanJi;
import com.situ.mvc.entity.PageBean;
import com.situ.mvc.service.IBanJiService;

public class BanJiServiceImpl implements IBanJiService {
	private IBanJiDao banJiDao = new BanJiDaoImpl();

	/**
	 * 增加banji
	 */
	@Override
	public boolean insert(BanJi banji) {
		int count = banJiDao.insert(banji);
		return count == 1 ? true : false;
	}

	/**
	 * 读取所有banji
	 */
	@Override
	public List<BanJi> list() {
		return banJiDao.list();
	}

	/**
	 * 删除banji
	 */
	@Override
	public boolean deleteById(Integer id) {
		int count = banJiDao.deleteById(id);
		return count == 1 ? true : false;
	}

	/**
	 * 搜索banji
	 */
	@Override
	public List<BanJi> findByName(String name) {
		return banJiDao.findByName(name);
	}

	/**
	 * 修改banji
	 */
	@Override
	public boolean updateBanJi(BanJi banJi) {
		int count = banJiDao.updateBanJi(banJi);
		return count == 1 ? true : false;
	}

	/**
	 * 修改时用于查找banji信息来展示
	 */
	@Override
	public BanJi findById(Integer id) {
		return banJiDao.findById(id);
	}

	/**
	 * 
	 */
	@Override
	public PageBean getPageBean(int pageNo, int pageSize) {
		PageBean pageBean = new PageBean<>();
		// 当前是第几页
		pageBean.setPageNo(pageNo);
		// 一页有多少条数据
		pageBean.setPageSize(pageSize);
		// 总记录数
		int totalCount = banJiDao.getTotalCount();
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
		List<BanJi> list = banJiDao.pageList(offset, pageSize);
		pageBean.setList(list);

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
		 * banjiDao.deleteById(Integer.parseInt(id)); }
		 */

		// 批量删除
		int count = banJiDao.deleteAll(selectIds);
		return count == selectIds.length ? true : false;
	}
	
	/**
	 * 检查班级名重复问题
	 */
	@Override
	public boolean checkName(String name) {
		int count = banJiDao.findCountByName(name);
		return count > 0 ? true : false;
	}
}
