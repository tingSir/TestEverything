package com.xiangxue.jvm.builder01.buildpattern.product;


public class Woman extends Person {

    public Woman() {
        System.out.println("create a Woman");
    }

    @Override
    public String toString() {
        return "Woman{}";
    }
}
