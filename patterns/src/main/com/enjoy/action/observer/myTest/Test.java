package com.enjoy.action.observer.myTest;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

public class Test {
    @org.junit.Test
    public void test() throws InterruptedException {
        ObDemo obDemo = new ObDemo();
        obDemo.addObserver(new Observer(){
            @Override
            public void update(Observable o, Object arg) {
                o=(ObDemo)obDemo;
                System.out.println("我是观察者1号"+((ObDemo) o).getName()+arg);
            }
        });
        obDemo.setName("我的锅");
        obDemo.perform();
        TimeUnit.SECONDS.sleep(5);
    }
}
