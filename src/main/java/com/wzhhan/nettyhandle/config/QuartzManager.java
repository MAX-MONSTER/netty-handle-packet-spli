package com.wzhhan.nettyhandle.config;

import com.wzhhan.nettyhandle.bean.JobConfig;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;


/**
 * @author:monsterHan
 * @date:2019/9/16-10:47
 * @description:@TODO
 */

@Configuration

public class QuartzManager {
    private static Logger LOG = LoggerFactory.getLogger(QuartzManager.class);
    @Autowired
    SchedulerFactoryBean schedulerFactory;
    @Autowired
    private Scheduler scheduler;
    /**
     * 新建一个任务
     */
    public String addJob(JobConfig appQuartz) throws Exception {


        if (!CronExpression.isValidExpression(appQuartz.corn)) {
            return "Illegal cron expression";   //表达式格式不正确
        }
        JobDetail jobDetail = null;
        //构建job信息
        jobDetail = JobBuilder.newJob((Class<? extends Job>) Class.forName(appQuartz.clazz)).withIdentity(appQuartz.name, appQuartz.group).build();


        //表达式调度构建器(即任务执行的时间,不立即执行)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(appQuartz.corn).withMisfireHandlingInstructionDoNothing();

        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(appQuartz.name, appQuartz.group).usingJobData(new JobDataMap(appQuartz.dataMap))
                .withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, trigger);
        return "success";
    }


    /**
     * 修改一个任务的触发时间
     *
     * @param jobConfig
     */
    public void modifyJobTime(JobConfig jobConfig) {
        try {
            scheduler = schedulerFactory.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobConfig.name, jobConfig.group);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                return;
            }
            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(jobConfig.corn)) {
                //方式一 ：调用 rescheduleJob 开始
                TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
                triggerBuilder.withIdentity(jobConfig.name, jobConfig.group).usingJobData(new JobDataMap(jobConfig.dataMap));
                triggerBuilder.startNow();
                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(jobConfig.corn));
                trigger = (CronTrigger) triggerBuilder.build();
                // 方式一 ：修改一个任务的触发时间
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        } catch (Exception e) {
            LOG.info("修改任务出现异常！,{}", e);
        }
    }
}
