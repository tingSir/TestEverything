package com.resoft.mySpring.designPatterns.proxy.jdk.service;

import com.resoft.mySpring.designPatterns.proxy.jdk.serviceI.Student;

public class StudentImp implements Student{
	private String name;
	private String sex;

	public StudentImp() {
	}

	public StudentImp(String name, String sex) {
		this.name = name;
		this.sex = sex;
	}

	public void addStudent() {
		System.out.println("增加一个学生！！");

	}
	public void countDown() {
		System.out.println("减少一个学生！！");

	}
	public void exceptionDemo() {
		System.out.println(1/0);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String toStringMy() {
		System.out.println("StudentImp{" +
				"name='" + name + '\'' +
				", sex='" + sex + '\'' +
				'}');
		return "StudentImp{" +
				"name='" + name + '\'' +
				", sex='" + sex + '\'' +
				'}';
	}
}
