package com.resoft.Quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.DateFormat;
import java.util.Date;

public class JobImp implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("现在的时间"+ DateFormat.getDateTimeInstance().format(new Date()));
    }
}
