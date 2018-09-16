package com.situ.mvc.controller;

import javax.servlet.http.HttpServlet;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.situ.mvc.entity.Course;
import com.situ.mvc.entity.PageBean;
import com.situ.mvc.service.ICourseService;
import com.situ.mvc.service.impl.CourseServiceImpl;

/**
 * Servlet implementation class CourseController
 */
public class CourseController extends HttpServlet {
	private ICourseService courseService = new CourseServiceImpl();

	/**
	 * 分页展示信息
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param id
	 * @param name
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/pageList")
	public String pageList(Integer pageNo, Integer pageSize, Integer id, String name, Model model) {
		// 从浏览器获得参数
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 3;
		}
		// 调用service层业务处理
		PageBean pageBean = courseService.getPageBean(pageNo, pageSize);
		System.out.println(pageBean);
		model.addAttribute("pageBean", pageBean);
		// 转发到course_list界面
		return "/course/course_list";
	}

	/**
	 * 添加信息
	 * 
	 * @param name
	 * @param credit
	 * @return
	 */
	@RequestMapping(value = "/insert")
	public String insert(String name, Integer credit) {
		// 得到浏览器传来的参数
		// 调用service层处理
		Course course = new Course(name, credit);
		boolean result = courseService.insert(course);
		System.out.println(result == true ? "添加成功" : "添加失败");
		// 重定向到界面
		return "redirect:/course/pageList.action";
	}

	/**
	 * 根据id删除信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteById")
	public String deleteById(Integer id) {
		// 得到浏览器传来的参数
		// 调用service层业务处理
		boolean result = courseService.deleteById(id);
		System.out.println(result == true ? "删除成功" : "删除失败");
		// 重定向到界面
		return "redirect:/course/pageList.action";
	}

	/**
	 * 查找要修改的信息
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toUpdate")
	public String toUpdate(Integer id, Model model) {
		// 得到浏览器传来的参数
		// 调用service层参数
		Course course = courseService.findById(id);
		model.addAttribute("course", course);
		// 转发到界面
		return "/course/course_update";
	}

	/**
	 * 修改信息
	 * 
	 * @param id
	 * @param name
	 * @param credit
	 * @return
	 */
	@RequestMapping(value = "/update")
	public String update(Integer id, String name, Integer credit) {
		// 得到浏览器传来的参数
		// 调用service层业务处理
		Course course = new Course(id, name, credit);
		boolean result = courseService.updateCourse(course);
		System.out.println(result == true ? "修改成功" : "修改失败");
		// 重定向到界面
		return "redirect:/course/pageList.action";
	}

	/**
	 * 得到添加界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getInsertPage")
	public String getInsertPage() {
		return "/course/course_add";
	}

	/**
	 * 得到修改界面
	 */
	@RequestMapping(value = "/getUpdatePage")
	public String getUpdatePage() {
		return "/course/course_update";
	}
}
