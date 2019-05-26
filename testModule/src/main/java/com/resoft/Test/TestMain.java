package com.resoft.Test;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;

public class TestMain {
    @Test
    public void test(){
        ArrayList<String> strs = Lists.newArrayListWithCapacity(8);
        strs.add("a");
        strs.add("b");
        strs.add("c");
        strs.add("d");
        Iterator<String> iterator = strs.iterator();
        while (iterator.hasNext()){
            if("ab".contains(iterator.next())){
                iterator.remove();
            }
        }
        System.out.println(strs.size());
    }
//    private static CountDownLatch countDownLatch=new CountDownLatch(1);
    @Test
    public void test1() throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(10);
        new Thread(()->{
            try {
                Thread.sleep(2000);
                countDownLatch.countDown();
                System.out.println("内部线程执行完成！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
        countDownLatch.await();
        System.out.println("主线程执行完成");
    }

}
