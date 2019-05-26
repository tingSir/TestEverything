package com.enjoy.cap10.aop;

public class Calculator {
	//业务逻辑方法
	public int div(int i, int j)  {
		System.out.println("--------");
		return i/j;
	}
	public int add(int i, int j){
	    return i+j;
	}

}
