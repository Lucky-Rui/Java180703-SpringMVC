package com.situ.mvc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.mvc.entity.Student;

@Controller
@RequestMapping(value = "/jsonDemo")
public class JsonDemo {

	@RequestMapping(value = "/list")
	@ResponseBody
	public List<Student> list() {
		List<Student> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Student student = new Student(i + 1, "王" + i, 20 + i, "男");
			list.add(student);
		}
		return list;
	}

	@RequestMapping(value = "/check1")
	@ResponseBody
	public boolean check1() {
		boolean isExist = true;
		return isExist;
	}

	@RequestMapping(value = "/check2")
	@ResponseBody
	public Map<String, Object> check2() {
		boolean isExist = true;
		Map<String, Object> map = new HashMap<>();
		map.put("isExist", isExist);
		return map;
	}
}
