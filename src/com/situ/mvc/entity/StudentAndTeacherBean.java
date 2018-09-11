package com.situ.mvc.entity;

/**
 * 将Student与Teacher再封装一次
 * 
 * @author WANGRUI
 *
 */
public class StudentAndTeacherBean {
	private Student student;
	private Teacher teacher;

	public StudentAndTeacherBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentAndTeacherBean(Student student, Teacher teacher) {
		super();
		this.student = student;
		this.teacher = teacher;
	}

	@Override
	public String toString() {
		return "StudentAndTeacher [student=" + student + ", teacher=" + teacher + "]";
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

}
