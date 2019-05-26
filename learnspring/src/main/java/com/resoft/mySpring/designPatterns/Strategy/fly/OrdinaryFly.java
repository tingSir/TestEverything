package com.resoft.mySpring.designPatterns.Strategy.fly;

public class OrdinaryFly implements Flyable {
    public void fly() {
        System.out.println("当然是用翅膀用力的飞呀");
    }
}
