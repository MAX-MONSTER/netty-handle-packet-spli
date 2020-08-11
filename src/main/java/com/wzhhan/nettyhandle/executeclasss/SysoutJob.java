package com.wzhhan.nettyhandle.executeclasss;

import com.alibaba.fastjson.JSON;
import com.wzhhan.nettyhandle.bean.JobConfig;
import com.wzhhan.nettyhandle.config.QuartzManager;
import com.wzhhan.nettyhandle.util.DateUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author:monsterHan
 * @date:2019/9/16-10:39
 * @description:@TODO
 */
@Component
public class SysoutJob implements Job {
    private static Logger LOG = LoggerFactory.getLogger(SysoutJob.class);
    @Autowired
    private QuartzManager quartzManager;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
       LOG.info("job_"+JSON.toJSONString(jobExecutionContext.getTrigger().getJobDataMap()));
        JobConfig jf = new JobConfig();
        jf.clazz = "com.wzhhan.distriquartz.executeclasss.SysoutJob";
        jf.corn = "0/10 * * * * ?";
        jf.name = "51bf762a-203d-49e3-99c9-d09791490467";
        jf.dataMap.put("data","updated" );
        jf.group = "hh";
        Date nextFireTime = jobExecutionContext.getNextFireTime();
        System.out.println(DateUtil.formate(nextFireTime,DateUtil.YYYY_MM_DD));
        jf.nextFireTime = DateUtil.formate(nextFireTime,DateUtil.YYYY_MM_DD);
        quartzManager.modifyJobTime(jf);
    }
}
