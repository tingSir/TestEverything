package com.resoft.mySpring.poju;

import java.util.ArrayList;
import java.util.List;

import com.resoft.mySpring.BeanTest;

public abstract class People {
	private String name;
	private int age;
	private Dog dog;
	private List<String> friends=new ArrayList<String>();

	private BeanTest beanTest;


	public abstract BeanTest getBeanTest();

	public void  setBeanTest(BeanTest beanTest) {
		this.beanTest = beanTest;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Dog getDog() {
		return dog;
	}

	public void setDog(Dog dog) {
		this.dog = dog;
	}

	public List<String> getFriends() {
		return friends;
	}

	public void setFriends(List<String> friends) {
		this.friends = friends;
	}

	@Override
	public String toString() {
		if(dog!=null){
			return "Peple [name=" + name + ", age=" + age + ", dog=" + dog.getName() + "]";
		}{
			return "Peple [name=" + name + ", friends=" + friends + ", dog="+ ", age=" + age + ", dog=û��]";
		}
	}

}
