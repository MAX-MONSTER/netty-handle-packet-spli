package com.wzhhan.nettyhandle.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:monsterHan
 * @date:2019/9/16-10:43
 * @description:@TODO
 */

public class JobConfig
{
    public String name;
    public String group;
    public String corn;
    public String  clazz;
    public String  nextFireTime;
    public Map<String,Object> dataMap=new HashMap<>();
}
