package com.situ.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.situ.mvc.entity.BanJi;
import com.situ.mvc.entity.PageBean;
import com.situ.mvc.entity.Student;
import com.situ.mvc.entity.StudentSearchCondition;
import com.situ.mvc.service.IBanJiService;
import com.situ.mvc.service.IStudentService;
import com.situ.mvc.service.impl.BanJiServiceImpl;
import com.situ.mvc.service.impl.StudentServiceImpl;

@Controller
@RequestMapping(value = "/student")
public class StudentController {
	private IStudentService studentService = new StudentServiceImpl();
	private IBanJiService banjiService = new BanJiServiceImpl();

	/**
	 * 分页展示
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param name
	 * @param age
	 * @param gender
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/pageList")
	public String pageList(Integer pageNo, Integer pageSize, String name, Integer age, String gender, Model model) {
		// 1、接收请求参数
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 3;
		}
		// 把所有的搜索条件封装成了StudentSearchCondition这样一个对象
		StudentSearchCondition searchCondition = new StudentSearchCondition(pageNo, pageSize, name, age, gender);
		// 2、封装成PageBean，调用Service层业务逻辑
		PageBean pageBean = studentService.getPageBean(searchCondition);
		System.out.println(pageBean);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("searchCondition", searchCondition);
		return "student_list";
	}

	/**
	 * 删除学生
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteById")
	public String deleteById(Integer id) {
		studentService.deleteById(id);
		return "redirect:/student/pageList.action";
	}

	/**
	 * 得到增添界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAddPage")
	public String getAddPage(Model model) {
		List<BanJi> list = new ArrayList<>();
		list = banjiService.list();
		model.addAttribute("list", list);
		return "/student/student_add";
	}

	/**
	 * 得到修改界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getUpdatePage")
	public String getUpdatePage() {
		return "/student/student_update";
	}

	/**
	 * 查找要修改对象的id
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toUpdate")
	public String toUpdate(Integer id, Model model) {
		// 1、得到要查找的id
		// 2、调用service层，返回对应的student
		Student student = studentService.findById(id);
		List<BanJi> list = banjiService.list();
		model.addAttribute("student", student);
		model.addAttribute("list", list);
		return "/student/student_update";
	}

	/**
	 * 修改
	 * 
	 * @param id
	 * @param name
	 * @param age
	 * @param gender
	 * @param banjiId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/update")
	public String update(Integer id, String name, Integer age, String gender, Integer banjiId, Model model) {
		// 1、得到浏览器传来的参数(类型自动转换)
		Student student = new Student(id, name, age, gender, banjiId);
		// 2、调用service处理
		boolean result = studentService.updateStudent(student);
		System.out.println(result == true ? "成功" : "失败");
		// 3、重定向到界面
		return "redirect:/student/pageList.action";
	}

}
