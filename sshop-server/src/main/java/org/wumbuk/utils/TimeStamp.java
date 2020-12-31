package org.wumbuk.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @auther PeihaoYang
 * @date 2020/7/24 - 22:08
 */

public class TimeStamp {


    /**
     * 自动获得时间戳加标题头flag
     *
     * @param flag
     * @return 字符串类型的时间戳
     */
    public static String getTimeStamp(String flag) {

        Date date = new Date();
        long time = date.getTime();
        //mysq 时间戳只有10位 要做处理
        String dateline = time + "";
        dateline = dateline.substring(0, 9);
        System.out.println(dateline);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmssSSSSS");
//        System.out.println(sdf1.format(date));
        String rs = flag + dateline;
        return rs;
    }


}
