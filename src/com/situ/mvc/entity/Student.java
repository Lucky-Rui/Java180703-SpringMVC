package com.situ.mvc.entity;

/**
 * 学生表
 * 
 * @author WANGRUI
 *
 */
public class Student {
	private Integer id;
	private String name;
	private Integer age;
	private String gender;
	private Integer banjiId;

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(String name, Integer age, String gender) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	public Student(String name, Integer age, String gender, Integer banjiId) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.banjiId = banjiId;
	}

	public Student(Integer id, String name, Integer age, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	public Student(Integer id, String name, Integer age, String gender, Integer banjiId) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.banjiId = banjiId;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", banjiId=" + banjiId
				+ "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getBanjiId() {
		return banjiId;
	}

	public void setBanjiId(Integer banjiId) {
		this.banjiId = banjiId;
	}

}
