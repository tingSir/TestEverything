package com.enjoy.cap7.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Bike implements InitializingBean, DisposableBean {
	public Bike(){
		System.out.println("Bike constructor..............");
	}
	public void init(){
		System.out.println("Bike .....init.....");
	}
	public void destory(){
		System.out.println("Bike.....destory");
	}

	public void destroy() throws Exception {
		System.out.println("Bike.....DisposableBean");
	}

	public void afterPropertiesSet() throws Exception {
		System.out.println("Bike.....InitializingBean");
	}

	@PostConstruct
	public void init_(){
		System.out.println("Bike.....@PostConstruct........");
	}

	@PreDestroy
	public void destory_(){
		System.out.println("Bike.....@PreDestroy......");
	}
}
