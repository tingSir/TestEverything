package com.resoft.mySpring.designPatterns.proxy;

import com.google.common.collect.Lists;
import com.resoft.mySpring.designPatterns.proxy.cglib.BookFacadeCglib;
import com.resoft.mySpring.designPatterns.proxy.cglib.BookFacadeImpl;
import com.resoft.mySpring.designPatterns.proxy.cglib.CglibProxy;
import com.resoft.mySpring.designPatterns.proxy.jdk.ProxyLogDemo;
import com.resoft.mySpring.designPatterns.proxy.jdk.service.StudentImp;
import com.resoft.mySpring.designPatterns.proxy.jdk.serviceI.Student;
import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;


/**
 * aop实现的原理是通过动态代理，日志、事物
 * 动态代理是一种设计模式以后自己写代码也可以用上
 * 动态代理可以实现非侵入式编程，不修改原代码的情况实现很多功能
 * 解耦的作用
 */
public class Test {
    private ClassPathXmlApplicationContext cpxac = null;

    @Before
    public void setUp() throws Exception {
        cpxac = new ClassPathXmlApplicationContext("spring-aop.xml");
    }

    //手动使用动态代理
    @org.junit.Test
    public void test() {
        StudentImp std = new StudentImp("xixi","女");
//		std.addStudent();
        Student proStu = (Student) Proxy.newProxyInstance(Student.class.getClassLoader(), new Class[]{Student.class}, new ProxyLogDemo(std));
        proStu.toStringMy();
        //彻底搞清楚动态代理原理
        //	byte[] proxies = ProxyGenerator.generateProxyClass("proxy", new Class[]{Student.class});动态代理生成的类proStu对象的类

    }
    @org.junit.Test
    public void cglibProxy() {
        StudentImp std = new StudentImp("longting","男");
//		std.addStudent();
        StudentImp instance = (StudentImp)new CglibProxy().getInstance(std);
        instance.setName("ting");
        /**
         * instance对象继承自std，会调用父对象的方法,但是有个问题它的属性值不会自动赋值
         */
        instance.toStringMy();
        //彻底搞清楚动态代理原理

    }
/**
 * cglib实现动态代理+222222233365482352
 */

    /**
     * 配置文件实现spring aop
     */
    @org.junit.Test
    public void test1() {
        //因为动态代理，通过studentService获取的实际不是StudentImp对象
        Student st = (Student) cpxac.getBean("studentService");
        System.out.println(st.getClass());
        st.addStudent();
        st.exceptionDemo();
    }
    @org.junit.Test
    public void test2() {
        BookFacadeImpl bookFacade=new BookFacadeImpl("long","nan");
        BookFacadeCglib cglib=new BookFacadeCglib();
        BookFacadeImpl bookCglib=(BookFacadeImpl)cglib.getInstance(bookFacade);
        bookCglib.addBook();
    }

    @org.junit.Test
    public void test3() {
        List<String> list = Lists.newArrayList();
        list.add("as");
        list.add("qwe");
        list.add("weq");
        list.add("xz");
        list.add("abc");
        Collections.sort(list, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });
        System.out.println(list.get(4));
    }



    public static void main(String[] args) throws InterruptedException {
        Callable<String> callable = new Callable<String>() {
            public String call() throws Exception {
                while (true){
                    TimeUnit.MILLISECONDS.sleep(1);
                    System.out.println(1);
                }
            }
        };
        FutureTask<String> stringFutureTask = new FutureTask<String>(callable);
        Thread thread = new Thread(stringFutureTask);
        thread.start();
        TimeUnit.MILLISECONDS.sleep(10);
        stringFutureTask.cancel(true);
        System.out.println("over");

       new Thread() {
            @Override
            public void run() {
                System.out.println(1233212);
                super.run();
            }
        }.start();

    }
}
