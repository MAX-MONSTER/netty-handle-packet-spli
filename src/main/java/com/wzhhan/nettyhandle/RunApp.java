package com.wzhhan.nettyhandle;

import com.wzhhan.nettyhandle.config.QuartzManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author:monsterHan
 * @date:2019/9/16-11:02
 * @description:@TODO
 */
@Component
public class RunApp implements ApplicationRunner {
    @Autowired
    private QuartzManager quartzManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        JobConfig jf = new JobConfig();
//        jf.clazz = "com.wzhhan.distriquartz.executeclasss.SysoutJob";
//        jf.corn = "0/5 * * * * ?";
//        jf.name = "51bf762a-203d-49e3-99c9-d09791490467";
//        jf.dataMap.put("data",jf.name );
//        jf.group = "hh";
//        quartzManager.addJob(jf);
    }
}
