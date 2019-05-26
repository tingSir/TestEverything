package com.enjoy.cap6.config;

import org.springframework.beans.factory.FactoryBean;

import com.enjoy.cap6.bean.Monkey;

public class JamesFactoryBean implements FactoryBean<Monkey>{

	public Monkey getObject() throws Exception {
		// TODO Auto-generated method stub
		return new Monkey();
	}

	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return Monkey.class;
	}

	public boolean isSingleton() {
		return true;
	}
}
