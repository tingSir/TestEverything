package com.xiangxue.jvm.ch03.deencrpt;

/**
 *@author Mark老师   享学课堂 https://enjoy.ke.qq.com
 *
 *类说明：我们要处理的业务类
 */
public class DemoUser {

	private int id = 1;
	private String name = "mark";

	private void MyTestPrivate(){
		System.out.println("MyTestPrivate");
	}
	@Override
	public String toString() {
		return "DemoUser [id=" + id + ", name=" + name + "]";
	}


}
