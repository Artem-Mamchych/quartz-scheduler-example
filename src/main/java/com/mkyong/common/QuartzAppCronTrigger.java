package com.mkyong.common;

import java.util.Map;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuartzAppCronTrigger  {
     private static final Logger log = LoggerFactory.getLogger(QuartzAppCronTrigger.class);

    public static void main( String[] args ) throws Exception {
    	RunMeTask task = new RunMeTask();
    	
    	//specify your sceduler task details
    	JobDetail job = new JobDetail();
    	job.setName("runMeJob");
    	job.setJobClass(RunMeJob.class);
    	
    	Map dataMap = job.getJobDataMap();
    	dataMap.put("runMeTask", task);
    	
    	//configure the scheduler time
    	CronTrigger trigger = new CronTrigger();
    	trigger.setName("runMeJobTesting");
    	trigger.setCronExpression("0/5 * * * * ?");
    	
    	//schedule it
    	Scheduler scheduler = new StdSchedulerFactory().getScheduler();
    	scheduler.start();
    	scheduler.scheduleJob(job, trigger);
        log.debug("scheduler started");
    }
}
