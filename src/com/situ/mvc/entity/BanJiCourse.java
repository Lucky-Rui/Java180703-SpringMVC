package com.situ.mvc.entity;

public class BanJiCourse {
	private Integer banjiId;
	private Integer courseId;

	public BanJiCourse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BanJiCourse(Integer banjiId, Integer courseId) {
		super();
		this.banjiId = banjiId;
		this.courseId = courseId;
	}

	@Override
	public String toString() {
		return "BanJiCourse [banjiId=" + banjiId + ", courseId=" + courseId + "]";
	}

	public Integer getBanjiId() {
		return banjiId;
	}

	public void setBanjiId(Integer banjiId) {
		this.banjiId = banjiId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

}
