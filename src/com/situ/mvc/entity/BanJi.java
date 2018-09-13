package com.situ.mvc.entity;

/**
 * 班级表
 * 
 * @author WANGRUI
 *
 */
public class BanJi {
	private Integer id;
	private String name;

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

	public BanJi() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BanJi(String name) {
		super();
		this.name = name;
	}

	public BanJi(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "BanJi [id=" + id + ", name=" + name + "]";
	}

}
