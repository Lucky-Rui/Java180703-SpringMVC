package com.situ.mvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.situ.mvc.entity.Student;

@Controller
@RequestMapping(value = "/student")
public class StudentController {
	/**
	 * 视图解析器Demo
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getInsertPage")
	public String getInsertPage() {
		// return "/WEB-INF/jsp/student_insert.jsp";
		return "student_insert";
	}

	/**
	 * 跳转的视图是通过方法返回的String来实现的
	 * 
	 * @param student:这个值是放入request域对象中的
	 * @param model:只能放数据
	 * @return
	 */
	@RequestMapping(value = "/insert")
	public String insert(Student student, Model model) {
		//System.out.println(student);
		// request.setAttribute("student", student);
		model.addAttribute("student", student);
		// request.getRequestDespatcher("/student_info.jsp").forwar(req,resp);
		return "student_info";
	}

	/**
	 * ModelAndView既能够放数据又能够放跳转的视图
	 * 
	 * @param student
	 * @return
	 */
	@RequestMapping(value = "/insert2")
	public ModelAndView insert2(Student student) {
		//System.out.println(student);
		// request.setAttribute("student", student);
		// Model域对象放数据，View处理转发和重定向界面
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("student", student);
		// request.getRequestDespatcher("/student_info.jsp").forwar(req,resp);
		// modelAndView.setViewName("/WEB-INF/jsp/student_info.jsp");
		modelAndView.setViewName("student_info");
		return modelAndView;
	}

	/**
	 * 写入request、response等传统web参数
	 * 
	 * @param student
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(value = "/insert3")
	public void insert3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender");
		Student student = new Student(name, age, gender);
		// 得到Session
		HttpSession session = request.getSession();
		request.getRequestDispatcher("/WEB-INF/jsp/student_info.jsp").forward(request, response);
	}

	/**
	 * 在业务控制方法中写入普通变量参数
	 * 
	 * @param name
	 * @param age
	 * @param gender
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/insert4", method = RequestMethod.POST)
	public String insert4(String name, Integer age, String gender, Model model) {
		//System.out.println("name: " + name);
		//System.out.println("age: " + age);
		//System.out.println("gender: " + gender);

		model.addAttribute("name", name);
		model.addAttribute("age", age);
		model.addAttribute("gender", gender);

		return "student_info";
	}
}
