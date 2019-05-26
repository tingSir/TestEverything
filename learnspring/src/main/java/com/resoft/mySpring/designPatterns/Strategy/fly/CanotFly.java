package com.resoft.mySpring.designPatterns.Strategy.fly;

public class CanotFly implements  Flyable {
    public void fly() {
        System.out.println("我们这种鸭子不会飞！");
    }
}
