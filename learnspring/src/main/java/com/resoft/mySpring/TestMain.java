package com.resoft.mySpring;

import com.resoft.mySpring.poju.People;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestMain {
	private ClassPathXmlApplicationContext cpxac=null;
	@Before
	public void setUp() throws Exception {
		cpxac = new  ClassPathXmlApplicationContext("beans.xml");
		FileSystemXmlApplicationContext sxac = new FileSystemXmlApplicationContext();

		//cpxac = new  ClassPathXmlApplicationContext("spring-context.xml");
	}

	@Test
	public void test1() {
		BeanTest bean = (BeanTest) cpxac.getBean("beanTest");
		bean.TestMethod();
	}
	@Test
	public void test2() {
		People bean = (People) cpxac.getBean("p1");
		System.out.println(bean);
	}
	@Test
	public void test3() {
		People bean = (People) cpxac.getBean("p2");
		System.out.println(bean);
	}
	@Test
	public void test4() {
		People bean = (People) cpxac.getBean("p3");
		System.out.println(bean);
	}
	@Test
	public void test5() {
		People bean = (People) cpxac.getBean("p4");
		System.out.println(bean);
	}
	@Test
	public void test6() {
		BeanTest bean1 = (BeanTest) cpxac.getBean("beanTest");
		BeanTest bean2 = (BeanTest) cpxac.getBean("beanTest");
		System.out.println(bean1==bean2);
	}
	@Test
	public void test7() {
		People bean = (People) cpxac.getBean("p3");
		bean.getBeanTest().TestMethod();
		System.out.println(bean.getBeanTest()==bean.getBeanTest());
	}
}
