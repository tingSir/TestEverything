package com.xiangxue.concurrent.ch4.aqs;

import com.xiangxue.tools.SleepTools;
import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *@author Mark老师   享学课堂 https://enjoy.ke.qq.com
 *
 *类说明：
 */
public class TestMyLock {
    public void test() {
        final Lock lock = new ReentrantLock();

        class Worker extends Thread {
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                    	SleepTools.second(1);
                        System.out.println(Thread.currentThread().getName());
                        SleepTools.second(1);
                    } finally {
                        lock.unlock();
                    }
                    SleepTools.second(2);
                }
            }
        }
        // 启动10个子线程
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }
        // 主线程每隔1秒换行
        for (int i = 0; i < 10; i++) {
        	SleepTools.second(1);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TestMyLock testMyLock = new TestMyLock();
        testMyLock.test();
    }



    public String test1(){
        try {
            return "1";
        }finally {
            System.out.println("2");
        }
    }
    @Test
    public void test2(){
        TestMyLock testMyLock = new TestMyLock();
        System.out.println(testMyLock.test1());
    }
}
