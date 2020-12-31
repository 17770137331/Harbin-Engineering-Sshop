package org.wumbuk.utils;
import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @auther PeihaoYang
 * @date 2020/12/17 - 22:28
 */

public class DateToUnixTimeUtil {
    /**
     * 将时间变成时间戳
     * @param date
     * @return
     */
    public static Long Date2TimeStamp(Date date) {
        try {
            DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String t=df.format(date);
            return df.parse(t).getTime()/1000;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
