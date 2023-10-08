package com.boxtrade.akshare4j.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;


import lombok.extern.slf4j.Slf4j;

/**
 * 时间格式处理
 *
 * @author 1763113879@qq.com
 * @version V2.1
 * @since 2.1.0 2022/11/5 01:28
 */
@Slf4j
public class DateUtil {

    /**
     * yyyyMMddHHmmss
     */
    public static String FORMAT_YMDHMS = "yyyyMMddHHmmss";
    /**
     * yyyyMMdd
     */
    public static String FORMAT_YMD = "yyyyMMdd";
    /**
     * yyyy-MM-dd HH:mm
     */
    public static String FORMAT_YMDHM = "yyyy-MM-dd HH:mm";
    /**
     * yyyy年MM月dd日
     */
    public static String FORMAT_YMD_CHI = "yyyy年MM月dd日";
    /**
     * yyyy-MM-dd
     */
    public static String FORMAT_YMD_LINE = "yyyy-MM-dd";

    /**
     * yyyy-MM-dd
     */
    public static String FORMAT_MDHM_S = "MM-dd HH:mm:ss";

    /**
     * MM-dd
     */
    public static String FORMAT_M_D = "MM-dd";
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static String FORMAT_YMDHM_S = "yyyy-MM-dd HH:mm:ss";
    public static String FORMAT_HMS = "HH:mm:ss";
    public static String FORMAT_HMS_S = "HHmmss";
    /**
     * yyyyMM
     */
    public static String FORMAT_YM = "yyyyMM";

    public static Date getDate(String date, String format) {
        if (StringUtil.hasLength(date) || StringUtil.hasLength(format)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            try {
                if (date.length() == FORMAT_YMD.length()) {
                    SimpleDateFormat sdf2 = new SimpleDateFormat(FORMAT_YMD);
                    return sdf2.parse(date);
                }
            } catch (Exception e2) {
            }

            log.error("{} {} parse error", date, format);
        }
        return null;
    }

    public static String getDateString(Date date, String format) {
        if (date == null || StringUtil.hasLength(format)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 获取中国日期格式
     *
     * @param date 时间
     * @return yyyy年MM月dd日
     */
    public static String getFormatYmdChi(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YMD_CHI);
        return sdf.format(date);
    }

    public static String transferDatestr(String dateStr, String from, String to) {
        return getDateString(getDate(dateStr, from), to);
    }

    /**
     * yyyyMMddHHmmss >> yyyyMMdd235959
     *
     * @param dateStr
     * @return
     */
    public static String getDateEnd(String dateStr) {
        if (StringUtil.hasLength(dateStr)) {
            return null;
        }

        if (FORMAT_YMDHMS.length() == dateStr.length()) {
            return dateStr.substring(0, 8) + "235959";
        }

        return dateStr;
    }

    /**
     * yyyyMMddHHmmss >> yyyyMMdd00000
     *
     * @param dateStr
     * @return
     */
    public static String getDateStart(String dateStr) {
        if (StringUtil.hasLength(dateStr)) {
            return null;
        }

        if (FORMAT_YMDHMS.length() == dateStr.length()) {
            return dateStr.substring(0, 8) + "000000";
        }

        return dateStr;
    }

    /**
     * 转让日期为**-**-** 23:59:59
     *
     * @param date
     * @return
     */
    public static Date getDateEnd(Date date) {
        if (date == null) {
            return null;
        }
        return getDate(getDateEnd(getDateString(date, FORMAT_YMDHMS)), FORMAT_YMDHMS);
    }

    /**
     * 日期转换为**-**-** 00:00:00
     *
     * @param date
     * @return
     */
    public static Date getDateStart(Date date) {
        if (date == null) {
            return null;
        }
        return getDate(getDateStart(getDateString(date, FORMAT_YMDHMS)), FORMAT_YMDHMS);
    }

    /**
     * 日期类型 转换为 yyyy年MM月dd日 日期类型格式
     *
     * @param date 日期类型
     * @return 日期类型
     */
    public static Date getDate2YmdChiDate(Date date) {
        if (date == null) {
            return null;
        }
        return getDate(getDateString(date, FORMAT_YMD_CHI), FORMAT_YMD_CHI);
    }

    /**
     * 校验date1是否在date2之后
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isAfter(String date1, String date2, String format) {
        Date d1 = getDate(date1, format);
        Date d2 = getDate(date2, format);
        return isAfter(d1, d2);
    }

    /**
     * 校验date1是否在date2之后
     *
     * @param d1
     * @param d2
     * @return
     */
    public static boolean isAfter(Date d1, Date d2) {
        if (d1 == null || d2 == null) {
            return false;
        }
        return d1.getTime() - d2.getTime() > 0;
    }

    /**
     * 获取时分秒
     *
     * @param date
     * @return
     */
    public static String getTimeHMS(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        return df.format(date);
    }

    /**
     * 计算日期的间隔 d2 - d1
     *
     * @param d1 yyyy-MM-dd
     * @param d2 yyyy-MM-dd
     * @return
     */
    public static int calInterval(String d1, String d2) {
        Date day1 = getDate(d1, FORMAT_YMD);
        Date day2 = getDate(d2, FORMAT_YMD);
        if (day1 == null || day2 == null) {
            return 0;
        }
        long mill = day2.getTime() - day1.getTime();
        return (int) (mill / 1000 / 3600 / 24);
    }

    /**
     * 当天剩余分钟
     *
     * @return
     */
    public static String getTimeCountDown() {
        Date now = new Date();
        String time = DateUtil.getDateString(now, DateUtil.FORMAT_YMD) + "235959";
        long leftTime = DateUtil.getDate(time, DateUtil.FORMAT_YMDHMS)
            .getTime() - now.getTime();
        return leftTime / 1000 / 60 + 1 + "";
    }

    /**
     * 获取系统时间的后一天
     *
     * @return
     */
    public static String nextDay() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        String dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
        return dd;
    }

    /**
     * 获取系统时间的前一天
     *
     * @return
     */
    public static Date preDay() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -1);
        return c.getTime();
    }

    /**
     * 日期计算
     *
     * @return
     */
    public static Date calDate(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, days);
        return c.getTime();
    }

    /**
     * 计算两个日期的月差值
     *
     * @param bigDate 日期大的值 比如：2012-12-12
     * @param smallDate 日期小的值 比如：2018-12-12
     * @return 月差值
     */
    public static int getMonthDiff(Date bigDate, Date smallDate) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(bigDate);
        c2.setTime(smallDate);
        if (c1.getTimeInMillis() < c2.getTimeInMillis()) {
            return 0;
        }
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        //获取年的差值 假设 bigDate = 2015-8-16 smallDate = 2012-9-30
        int yearInterval = year1 - year2;
        // 如果 bigDate的 月-日 小于 smallDate的 月-日 那么 yearInterval-- 这样就得到了相差的年数
        if ((month1 < month2 || month1 == month2) && day1 < day2) {
            yearInterval--;
        }
        // 获取月数差值
        int monthInterval = (month1 + 12) - month2;
        if (day1 < day2) {
            monthInterval--;
        }
        monthInterval %= 12;
        return yearInterval * 12 + monthInterval;
    }

    public static void main(String[] args) {
        //            Date d1 = getDate("2018-09-27 00:00:00", FORMAT_YMDHM_S);
        //            Date d2 = getDate("2018-08-26 00:00:00", FORMAT_YMDHM_S);
        //            int monthDiff = getMonthDiff(d1, d2);
        //            System.out.println(monthDiff);
        //            return getDate(getDateString(date, FORMAT_YMD_LINE), FORMAT_YMD_CHI);
        //            System.out.println(getDateString(d1, FORMAT_YMD_LINE));
        System.out.println(getDate2YmdChiDate(new Date()));
    }

    /***
     * 获取业务时间
     *
     * @return
     */
    public static Date getBizTime() {
        return new Date();
    }

    /**
     * 计算日期的间隔 d2 - d1
     *
     * @param day1 yyyy-MM-dd
     * @param day2 yyyy-MM-dd
     * @return
     */
    public static int calInterval(Date day1, Date day2) {
        if (day1 == null || day2 == null) {
            return 0;
        }
        long mill = day2.getTime() - day1.getTime();
        return (int) (mill / 1000 / 3600 / 24);
    }

    public static String getCurrentDateTimeStr() {
        return DateUtil.getDateString(new Date(), DateUtil.FORMAT_YMDHM_S);
    }

    /**
     * 转化将长整型数字转换为日期格式的字符串
     *
     * @param time
     * @return
     */
    public static String convert2String(long time) {
        if (time > 0l) {
            Date date = new Date(time * 1000);
            return DateUtil.getDateTimeStr(date);
        }
        return "";
    }

    public static Date convert(ZonedDateTime time) {
        return Date.from(time.toInstant());
    }

    public static String getDateTimeStr(Date date) {
        return DateUtil.getDateString(date, DateUtil.FORMAT_YMDHM_S);
    }

    /**
     * Java将Unix时间戳转换成指定格式日期字符串
     *
     * @param timestampString 时间戳 如："1473048265";
     * formats 要格式化的格式 默认："yyyy-MM-dd HH:mm:ss";
     * @return 返回结果 如："2016-09-05 16:06:42";
     */
    public static String TimeStamp2Date(String timestampString) {
        Long timestamp = Long.parseLong(timestampString) * 1000;
        return getDateTimeStr(new Date(timestamp));
    }

}
