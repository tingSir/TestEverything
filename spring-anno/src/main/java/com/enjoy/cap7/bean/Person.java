package com.enjoy.cap7.bean;

import org.springframework.scheduling.annotation.Async;

public class Person {
	private String name;
	private Integer age;

	public Person(){
		System.out.println("创建person");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Person(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
	public Integer getAge() {
		return age;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Async
	public void async() throws InterruptedException {
		Thread.sleep(5000l);
		System.out.println("方法执行了！！");
	}
}
