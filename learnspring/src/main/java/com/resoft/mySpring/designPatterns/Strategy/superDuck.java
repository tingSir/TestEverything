package com.resoft.mySpring.designPatterns.Strategy;

import com.resoft.mySpring.designPatterns.Strategy.fly.Flyable;

public abstract class superDuck {

    public Flyable fly;

    public void  fly(){
        fly.fly();
    }
    public void quack(){
        System.out.println("吱吱叫");
    }
    public void display(){
        System.out.println("我就是普通鸭子的样子呀");
    }
    public void swim(){
        System.out.println("用脚划呀划的游泳");
    }

    public Flyable getFly() {
        return fly;
    }

    public void setFly(Flyable fly) {
        this.fly = fly;
    }
}
