package com.myDemo.concurrent;

public class Animal {
    public static String name = Test.setName("animal");
    static {
        System.out.println("Child的静态块");
    }

    {
        System.out.println("Child的构造块");
    }

    Animal() {
        System.out.println("Child的构造方法");
    }

}
