package com.resoft.database;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDataTest {
	private ClassPathXmlApplicationContext cpxac=null;
	private TempTestDao tempTestDao =null;
	@Before
	public void setUp() throws Exception {
		cpxac = new  ClassPathXmlApplicationContext("spring-database.xml");
		tempTestDao=(TempTestDao) cpxac.getBean("tempTestDao");
	}

	@Test
	public void test() {
		Map<String, Object> select = tempTestDao.select("SMITH");
		System.out.println(select);
	}
	@Test
	public void test1() {
		//fail("Not yet implemented");
		//tempTestDao
		int insert = tempTestDao.insert("SMITH2");
		if(insert==1){
			System.out.println(tempTestDao.select("SMITH2"));
		}
	}
}
