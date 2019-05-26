package com.myDemo.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {
    class Animal {
        public String name = setName("animal");
        public Animal(){
            printIn();
        }
        public void printIn() {
            System.out.println(name);
        }

    }
    class Dog extends Animal{
        public String name = setName("dog");

        @Override
        public void printIn(){
            System.out.println(name);
        }
    }
    @org.junit.Test
    public  void test() {
//        Dog dog = new Dog();
        new com.myDemo.concurrent.Animal();
    }

    @org.junit.Test
    public  void test1() {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.addAndGet(1);
        ConcurrentHashMap stringConcurrentHashMap = new ConcurrentHashMap<String,Object>();
        stringConcurrentHashMap.put("","");
        ThreadLocalRandom.current();
    }

    static String setName(String str){
        System.out.println(str);
        return str;
    }
}
