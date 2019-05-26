package com.resoft.Quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestMain {

    public static void main(String[] args) throws SchedulerException {
        testQuartz();
    }
    public static void testQuartz() throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(JobImp.class).withIdentity("myJob").build();
        //创建一个Trigger触发器的实例，定义该job立即执行，并且每2秒执行一次，一直执行
        Trigger trigger = getTriggerTime();
        //创建schedule实例
        StdSchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail,trigger);
        System.out.println("完成设置调度任务");
    }
    public static SimpleTrigger getTriggerRe(){
        return TriggerBuilder.newTrigger().
                withIdentity("myTrigger").startNow().
                withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever()).build();
    }
    public static Trigger getTriggerTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.SECOND, 5);// 1分钟之后的时间
        Date beforeD = beforeTime.getTime();
        Trigger build = TriggerBuilder.newTrigger().
                withIdentity("myTrigger")
                .forJob("myJob")
                .startAt(beforeD).build();

        return build;
    }
}
