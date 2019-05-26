package com.enjoy.cap7.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 相当于定义一个切片，只要是config文件中定义的bean，在执行bean的init方法前后执行以下切片，注意扫描添加到容器中的bean不会增加此切片
 *  * Bike constructor..............
 * postProcessBeforeInitialization....bike...com.enjoy.cap7.bean.Bike@2b6faea6
 * Bike .....init.....
 * postProcessAfterInitialization....bike...com.enjoy.cap7.bean.Bike@2b6faea6
 */
@Component
public class JamesBeanPostProcessor implements BeanPostProcessor{
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		//返回一个的对象(传过来的对象)
		//在初始化方法调用之前进行后置处理工作,
		//什么时候调用它: init-method=init之前调用
		System.out.println("postProcessBeforeInitialization...."+beanName+"..."+bean);
		return bean;
	}
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("postProcessAfterInitialization...."+beanName+"..."+bean);
		return bean;
	}
}
