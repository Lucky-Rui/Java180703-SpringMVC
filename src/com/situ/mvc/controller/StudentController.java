package com.situ.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.situ.mvc.entity.PageBean;
import com.situ.mvc.entity.Student;
import com.situ.mvc.service.IStudentService;
import com.situ.mvc.service.impl.StudentServiceImpl;

@Controller
@RequestMapping(value = "/student")
public class StudentController {
	private IStudentService studentService = new StudentServiceImpl();

	@RequestMapping(value = "pageList")
	public String pageList(Integer pageNo, Integer pageSize, String name, Integer age, String gender, Model model) {
		// 1、接收请求参数
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 3;
		}
		// 2、封装成PageBean，调用Service层业务逻辑
		PageBean<Student> pageBean = studentService.getPageBean(pageNo, pageSize);
		System.out.println(pageBean);
		// 3、放入数据，转发
		model.addAttribute("pageBean", pageBean);
		return "/student/student_list";

	}
}
