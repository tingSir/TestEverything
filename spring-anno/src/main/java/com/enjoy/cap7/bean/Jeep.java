package com.enjoy.cap7.bean;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
@Component
public class Jeep implements EnvironmentAware {
	public Jeep(){
		System.out.println("Jeep.....constructor........");
	}
	//相当于init，创建完成bean调用。
	@PostConstruct
	public void init(){
		System.out.println("Jeep.....@PostConstruct........");
	}

	@PreDestroy
	public void destory(){
		System.out.println("Jeep.....@PreDestroy......");
	}

	public void setEnvironment(Environment environment) {

	}
}
