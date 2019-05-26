package com.enjoy.cap7.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class Train implements InitializingBean, DisposableBean{

	public Train(){
		System.out.println("Train......constructor............");
	}
	//当我们bean销毁时,调用此方法
	public void destroy() throws Exception {
		System.out.println("Train......destory......");
		//logger.error
	}
	public void afterPropertiesSet() throws Exception {
		System.out.println("Train.......afterPropertiesSet()... 这个方法应该是容器创建完之后执行的！！");

	}

}
