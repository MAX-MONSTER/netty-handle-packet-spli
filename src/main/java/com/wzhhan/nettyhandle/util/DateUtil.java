package com.wzhhan.nettyhandle.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author:monsterHan
 * @date:2020/5/7-11:55
 * @description:@TODO
 */
public class DateUtil {
    public static String YYYY_MM_DD = "YYYY-MM-DD HH:mm:ss:sss";

    public static String formate(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }
}
