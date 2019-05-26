package com.enjoy.cap6.bean;

import org.springframework.context.annotation.Lazy;
@Lazy
public class Dog {
    public Dog() {
        System.out.println("我是dog,这是我的构造方法！！");
    }
}
