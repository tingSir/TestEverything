package com.xiangxue.concurrent.ch6;

import com.xiangxue.tools.SleepTools;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

/**
 *@author Mark老师   享学课堂 https://enjoy.ke.qq.com
 *
 *类说明：线程池的使用
 */
public class UseThreadPool {
	//工作线程
    static class Worker implements Runnable
    {
        private String taskName;
        private Random r = new Random();

        public Worker(String taskName){
            this.taskName = taskName;
        }

        public String getName() {
            return taskName;
        }

        @Override
        public void run(){
            System.out.println(Thread.currentThread().getName()
            		+" process the task : " + taskName);
            SleepTools.ms(r.nextInt(100)*5);
        }
    }

    static class CallWorker implements Callable<String>{

        private String taskName;
        private Random r = new Random();

        public CallWorker(String taskName){
            this.taskName = taskName;
        }

        public String getName() {
            return taskName;
        }

		@Override
		public String call() throws Exception {
            System.out.println(Thread.currentThread().getName()
            		+" process the task : " + taskName);
            return Thread.currentThread().getName()+":"+r.nextInt(100)*5;
		}

    }
    @Test
    public void myThreadPool(){
        ExecutorService myPool = new ThreadPoolExecutor(2, 4, 4, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
        ExecutorService fixedPool = Executors.newFixedThreadPool(2);
        ExecutorService cachePool = Executors.newCachedThreadPool();
        ExecutorService singlePool = Executors.newSingleThreadExecutor();

        excuteByPool(singlePool);

    }

    public static void main(String[] args) {
        ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(2);
        scheduledPool.scheduleAtFixedRate(new Worker("worker_lalala"),1000,1000,TimeUnit.MILLISECONDS);
    }

    public void excuteByPool(ExecutorService pool){
        for(int i=0;i<6;i++) {
            Worker worker = new Worker("worker_"+i);
            pool.execute(worker);
        }
        ArrayList<Future<String>> futures = new ArrayList<>();
        for(int i=0;i<6;i++) {
            CallWorker callWorker = new CallWorker("callWorker_"+i);
            Future<String> result = pool.submit(callWorker);
            futures.add(result);
        }
        try {
        for (Future<String> future:futures){
                System.out.println(future.get());
        }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        pool.shutdown();
    }

}
