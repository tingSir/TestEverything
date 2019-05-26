package com.resoft.mySpring.designPatterns.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Date;

public class CglibProxy implements MethodInterceptor {
    private Object proxyed;

    public Object getInstance(Object obj){
        this.proxyed=obj;
        //创建一个对象继承自被代理对象
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallback(this);
        return  enhancer.create();
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("开始执行时间"+new Date().toLocaleString());
        //利用反射实际去执行方法，其实最终执行方法的还是proxyed对象

        /**
         * methodProxy:代理对象（自动生成的）
         * o:原对象
         */

        methodProxy.invokeSuper(o, objects);
//		proxyed.addStudent();
        System.out.println("执行结束时间"+new Date().toLocaleString());
        return null;
    }
}
