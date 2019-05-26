package com.resoft.mySpring.designPatterns.prototype;

import org.junit.Test;

/**
 * aop实现的原理是通过动态代理，日志、事物
 * 动态代理是一种设计模式以后自己写代码也可以用上
 * 动态代理可以实现非侵入式编程，不修改原代码的情况实现很多功能
 * 解耦的作用
 */
public class TestPrototype {


    @Test
    public void test() {
        User aLong = new User("long", "24");
        try {
            User clone = (User)aLong.clone();
            System.out.println(aLong==clone);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }
}
