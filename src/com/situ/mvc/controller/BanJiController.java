package com.situ.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.situ.mvc.entity.BanJi;
import com.situ.mvc.entity.PageBean;
import com.situ.mvc.service.IBanJiService;
import com.situ.mvc.service.impl.BanJiServiceImpl;

@Controller
@RequestMapping(value = "/banji")
public class BanJiController {
	IBanJiService banjiService = new BanJiServiceImpl();

	/**
	 * 展示信息
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/pageList")
	public String pageList(Integer pageNo, Integer pageSize, Model model) {
		// 1、从浏览器获得参数
		// 将参数封装成pageBean，调用service层业务处理
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 3;
		}
		PageBean pageBean = banjiService.getPageBean(pageNo, pageSize);
		System.out.println(pageBean);
		model.addAttribute("pageBean", pageBean);
		// 转发到banji_list.jsp
		return "/banji/banji_list";
	}

	/**
	 * 添加信息
	 * 
	 * @param banji
	 * @return
	 */
	@RequestMapping(value = "/insert")
	public String insert(BanJi banji) {
		System.out.println(banji);
		boolean result = banjiService.insert(banji);
		System.out.println(result == true ? "添加成功" : "添加失败");
		return "redirect:/banji/pageList.action";
	}

	/**
	 * 得到添加页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getInsertPage")
	public String getInsertPage() {
		return "/banji/banji_add";
	}

	/**
	 * 得到修改界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getUpdatePage")
	public String getUpdatePage() {
		return "/banji/banji_update";

	}

	/**
	 * 查找出要修改的id
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toUpdate")
	public String toUpdate(Integer id, Model model) {
		// 1、得到要查找的id
		// 2、调用service层，返回对于的banji
		BanJi banji = banjiService.findById(id);
		model.addAttribute("banji", banji);
		return "/banji/banji_update";
	}

	/**
	 * 修改
	 */
	@RequestMapping(value = "/update")
	public String update(Integer id, String name, Model model) {
		// 1、得到浏览器传来的参数（类型自动转换）
		BanJi banji = new BanJi(id, name);
		// 2、调用service层处理
		boolean result = banjiService.updateBanJi(banji);
		System.out.println(result == true ? "修改成功" : "修改失败");
		// 3、重定向
		return "redirect:/banji/pageList.action";
	}

	/**
	 * 根据id删除信息
	 */
	@RequestMapping(value = "/deleteById")
	public String deleteById(Integer id) {
		// 得到浏览器传来的id
		// 调用service层处理
		boolean result = banjiService.deleteById(id);
		System.out.println(result == true ? "删除成功" : "删除失败");
		// 重定向到界面
		return "redirect:/banji/pageList.action";
	}
}
