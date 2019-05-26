package com.xiangxue.concurrent.ch1.wn;

/**
 *@author Mark老师   享学课堂 https://enjoy.ke.qq.com
 *
 *类说明：测试wait/notify/notifyAll
 */
public class TestWN {
    private static Express express = new Express(0,Express.CITY);

    /*检查里程数变化的线程,不满足条件，线程一直等待*/
    private static class CheckKm extends Thread{
        public CheckKm(String name) {
            super(name);
        }
        @Override
        public void run() {
        	express.waitKm();
        }
    }

    /*检查地点变化的线程,不满足条件，线程一直等待*/
    private static class CheckSite extends Thread{
        public CheckSite(String name) {
            super(name);
        }

        @Override
        public void run() {
        	express.waitSite();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        //多线程等待对象锁时会排成一个队列，CheckSite线程先创建所以排在前面，notify会按照队列顺序进行通知
        for(int i=0;i<3;i++){//三个线程
            new CheckSite("CheckSite"+i).start();
        }
        for(int i=0;i<30;i++){//里程数的变化
            new CheckKm("CheckKm"+i).start();
        }
        //为这个对象创建6个线程去等待对象锁。
        Thread.sleep(1000);
        express.changeKm();//快递地点变化
//        express.changeSite();
    }
}
