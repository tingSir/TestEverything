package com.resoft.mySpring.designPatterns.proxy.jdk;

import com.resoft.mySpring.designPatterns.proxy.jdk.serviceI.Student;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Date;

/**
 * 动态代理类
 * StudentImp 是被代理类，
 * 用动态代理方式创建出来的对象执行方法时会执行动态代理类的invoke方法
 * 实现原理：最底层原理（字节码重组）
 * 这个过程会创建一个类和一个对象。
 * (Student) Proxy.newProxyInstance(Student.class.getClassLoader(),new Class[] { Student.class }, new ProxyLogDemo(std));
 * 返回的对象的类是Proxy新建的，它实现Student.class 接口
 * 这个对象执行方法时其实就是调用动态代理类的invoke，再利用被代理类的引用反射去执行方法
 */
public class ProxyLogDemo implements InvocationHandler{
	private Student proxyed;

	public ProxyLogDemo(Student proxyed) {
		this.proxyed = proxyed;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("开始执行时间"+new Date().toLocaleString());
		//利用反射实际去执行方法，其实最终执行方法的还是proxyed对象

		/**
		 * 日志不是代理模式的最好例子，
		 * 这个地方能获取被代理对象proxy
		 * 可以根据proxy对象的信息做很多事情
		 * 例如：租房中介、火车票黄牛、媒人、经纪人。
		 * 根据proxy属性不同做出不同的处理
		 */
		method.invoke(proxyed, args);
//		proxyed.addStudent();
		System.out.println("执行结束时间"+new Date().toLocaleString());
        Comparator<String> comparator = new Comparator<String>() {
            public int compare(String o1, String o2) {
                return 0;
            }
        };
        return null;
	}
}
