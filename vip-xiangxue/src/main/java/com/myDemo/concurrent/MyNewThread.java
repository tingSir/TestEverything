package com.myDemo.concurrent;

import com.xiangxue.tools.SleepTools;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


public class MyNewThread {
    static CountDownLatch countDownLatch = new CountDownLatch(500);

    private static AtomicInteger count=new AtomicInteger(1);

    private static int countInt;


    private class RunnableImp implements Runnable{
        private String name;
        private  Random random = new Random();

        public RunnableImp(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                count.incrementAndGet();
//                countInt++;
            }
            System.out.println(name+"的count为："+count.get());
            countDownLatch.countDown();
        }
    }

    private class CallImp implements Callable{

        @Override
        public Object call() throws Exception {
            TimeUnit.SECONDS.sleep(5);
            return this.getClass().toString();
        }
    }
    private static class ThreadExd extends Thread{
        @Override
        public void run() {
            while (!isInterrupted()){
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("ThreadExd Over Normal");
                } catch (InterruptedException e) {
                    interrupt();
                    e.printStackTrace();
                }
            }
        }
    }

    private static class ThreadExd1 extends Thread{
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("ThreadExd Over Normal");
            } catch (InterruptedException e) {
                interrupt();
                e.printStackTrace();
            }
        }
    }

    private class CallImp1 implements Callable{

        @Override
        public Object call() {
            int i=0;
            while (true){
                try {
                    i++;
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(i);
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
            return this.getClass().toString();
        }
    }

    @Test
    public void testCancel(){
        CallImp1 callImp1 = new CallImp1();
        FutureTask futureTask = new FutureTask<>(callImp1);
        Thread thread = new Thread(futureTask);
        thread.start();
        SleepTools.second(1);
        futureTask.cancel(true);
        SleepTools.second(10);
        System.out.println("over");
    }

    @Test
    public void testInterupt(){
        Thread thread = new Thread(new ThreadExd1());
        thread.start();
        thread.interrupt();
        SleepTools.second(10);
        System.out.println("over");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ThreadExd());
        thread.start();
        thread.join();
        System.out.println("over");
    }

    @Test
    public void testAtomic() throws InterruptedException {
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i <500 ; i++) {
            Thread thread = new Thread(new RunnableImp("thread" + i));
            threads.add(thread);
        }
        for (Thread thread:threads){
            thread.start();
        }
        countDownLatch.await();
    }

    @Test
    public void testCountDown(){
        ThreadExd threadExd = new ThreadExd();
        new Thread(threadExd).start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test(){
        RunnableImp runnableImp = new RunnableImp("");
        //应该使用这个方法去新建线程而不是runnableImp.run()，这个会执行init方法
        new Thread(runnableImp).start();
        ThreadExd threadExd = new ThreadExd();
        new Thread(threadExd).start();
        CallImp callImp = new CallImp();
        FutureTask<String> stringFutureTask = new FutureTask<String>(callImp);
        Thread thread = new Thread(stringFutureTask);
        thread.setName("longting--Thread");
        thread.start();
        try {
            System.out.println(stringFutureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(thread.isInterrupted());
        System.out.println("over!");
    }

}
