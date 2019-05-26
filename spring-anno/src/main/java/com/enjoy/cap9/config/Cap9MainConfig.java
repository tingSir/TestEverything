package com.enjoy.cap9.config;

import com.enjoy.cap9.dao.TestDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.enjoy.cap9"})
public class Cap9MainConfig {
	//spring进行自装配的时候默认首选的bean
//	@Primary //只要在这里申请Primary, 代表所有要注入TestDao的bean,
	@Bean("testDao")
	public TestDao testDao(){
		TestDao testDao = new TestDao();
		testDao.setFlag("2");
		return testDao;
	}
}
