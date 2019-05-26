package com.myDemo.concurrent;

import com.xiangxue.tools.SleepTools;
import org.junit.Test;

import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLockDemo {
    private Lock lock=new ReentrantLock();

    private class ThreadExt  extends  Thread{
        public ThreadExt(String name) {
            super(name);
        }

        @Override
        public void run() {
            lock.lock();
            try {
                SleepTools.second(2);
                System.out.println(getName()+"线程，占用了锁2秒");
            } finally {
                lock.unlock();
            }
        }
    }
    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new ThreadExt("thread"+i));
            thread.start();
            thread.join();
        }
        System.out.println("over");

    }
    @Test
    public void test2(){
        Vector vector = new Vector();
        vector.add(1);
    }
}
