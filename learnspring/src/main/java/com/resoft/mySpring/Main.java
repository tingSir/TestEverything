package com.resoft.mySpring;

import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ClassPathXmlApplicationContext classPathXmlApplicationContext = new  ClassPathXmlApplicationContext("spring-context.xml");
			BeanTest bean1 = (BeanTest) classPathXmlApplicationContext.getBean("beanTest");
			DoThings doTh = (DoThings) classPathXmlApplicationContext.getBean("doTh");
			doTh.doTh();
			System.out.println(bean1==doTh.getTest());
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void sameBean() {

		// TODO Auto-generated method stub
		try {
			ClassPathXmlApplicationContext classPathXmlApplicationContext = new  ClassPathXmlApplicationContext("spring-context.xml");
			BeanTest bean1 = (BeanTest) classPathXmlApplicationContext.getBean("beanTest");
			BeanTest bean2=bean1;
			BeanTest bean3 = (BeanTest) classPathXmlApplicationContext.getBean("beanTest");
			System.out.println(bean1);
			System.out.println(bean2==bean3);
			bean1.ttt();
			bean1.name();
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
