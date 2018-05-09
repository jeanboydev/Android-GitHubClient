package com.jeanboy.arch.data.repository.util;

import android.content.Context;
import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by jeanboy on 2017/5/19.
 */

public class DateUtil {

    /**
     * https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
     * Letter	Date or Time Component	Presentation	Examples
     * G	Era designator	Text	AD
     * y	Year	Year	1996; 96
     * Y	Week year	Year	2009; 09
     * M	Month in year	Month	July; Jul; 07
     * w	Week in year	Number	27
     * W	Week in month	Number	2
     * D	Day in year	Number	189
     * d	Day in month	Number	10
     * F	Day of week in month	Number	2
     * E	Day name in week	Text	Tuesday; Tue
     * u	Day number of week (1 = Monday, ..., 7 = Sunday)	Number	1
     * a	Am/pm marker	Text	PM
     * H	Hour in day (0-23)	Number	0
     * k	Hour in day (1-24)	Number	24
     * K	Hour in am/pm (0-11)	Number	0
     * h	Hour in am/pm (1-12)	Number	12
     * m	Minute in hour	Number	30
     * s	Second in minute	Number	55
     * S	Millisecond	Number	978
     * z	Time zone	General time zone	Pacific Standard Time; PST; GMT-08:00
     * Z	Time zone	RFC 822 time zone	-0800
     * X	Time zone	ISO 8601 time zone	-08; -0800; -08:00
     */

    public static final String FORMAT_DATE_24_FULL = "yyyy-MM-dd HH:mm:ss";//2016-11-28 13:53:00
    public static final String FORMAT_DATE_12_FULL = "yyyy-MM-dd hh:mm:ss";//2016-11-28 11:53:00
    public static final String FORMAT_DATE_12_MARKER = "a";//上午/下午

    public static final String FORMAT_TIME_24_FULL = "HH:mm:ss";//13:53:00
    public static final String FORMAT_TIME_12_FULL = "hh:mm:ss a";//11:53:00 AM
    public static final String FORMAT_TIME_24_SIMPLE = "HH:mm";//13:53
    public static final String FORMAT_TIME_12_SIMPLE = "hh:mm a";//11:53 AM

    public static final String FORMAT_HOURS_24_HOUR = "H";//1-24
    public static final String FORMAT_HOURS_12_HOUR = "h";//1-12
    public static final String FORMAT_WEEK = "E";//星期一/Monday
    public static final String FORMAT_SHARE = "E.M.dd";//Monday.Jul.18
    public static final String FORMAT_DATE_SIMPLE = "MM/dd";//01/01

    public static final String FORMAT_UTC = "yyyy-MM-dd'T'HH:mm:ss'Z'";//2014-08-23T09:20:05Z

    public static final long SECOND = 1000;
    public static final long MINUTE = 60 * SECOND;
    public static final long HOUR = 60 * MINUTE;
    public static final long DAY = 24 * HOUR;
    public static final long WEEK = 7 * DAY;
    public static final String FORMAT_RECENT_DEFAULT = "yyyy-MM-dd HH:mm";//2016-11-28 13:53
    public static final String FORMAT_RECENT_THIS_YEAR = "MM-dd HH:mm";//11-28 13:53

    /**
     * 格式化时间戳
     *
     * @param timestamp
     * @param format
     * @param timeZoneId
     * @return
     */
    public static String format(long timestamp, String format, String timeZoneId) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        if (timeZoneId != null) {
            dateFormat.setTimeZone(TimeZone.getTimeZone(timeZoneId));
        } else {
            dateFormat.setTimeZone(TimeZone.getDefault());
        }
        return dateFormat.format(calendar.getTime());
    }

    public static String format(long timestamp, String format) {
        return format(timestamp, format, null);
    }

    /**
     * 字符串转日期
     *
     * @param dateStr
     * @param format
     * @param timeZoneId
     * @return
     */
    public static Date format(String dateStr, String format, String timeZoneId) {
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        if (timeZoneId != null) {
            dateFormat.setTimeZone(TimeZone.getTimeZone(timeZoneId));
        } else {
            dateFormat.setTimeZone(TimeZone.getDefault());
        }
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date format(String dateStr, String format) {
        return format(dateStr, format, null);
    }

    /**
     * 格式化世界标准时间
     *
     * @param dateStr
     * @return
     */
    public static Date formatUTC(String dateStr) {
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_UTC, Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 格式化为：昨天，？秒前，？分钟前，？小时前
     *
     * @param timestamp
     * @return
     */
    public static String formatRecent(long timestamp) {
        long currentTimeMillis = System.currentTimeMillis();
        long delay = currentTimeMillis - timestamp;
        if (delay < MINUTE) {
            return "just now";
        } else if (delay < HOUR) {
            int count = Math.round(delay / MINUTE);
            return count == 1 ? "a minute ago" : count + " minutes ago";
        } else if (delay < DAY) {
            Calendar formatTime = Calendar.getInstance();
            formatTime.setTimeInMillis(timestamp);
            int formatDay = formatTime.get(Calendar.DAY_OF_MONTH);

            Calendar currentTime = Calendar.getInstance();
            currentTime.setTimeInMillis(currentTimeMillis);
            int currentDay = currentTime.get(Calendar.DAY_OF_MONTH);

            int count = Math.round(delay / HOUR);
            return currentDay > formatDay ? "yesterday" : (count == 1 ? "an hour ago" : count + " hours ago");
        } else if (delay < DAY * 365) {
            int count = Math.round(delay / DAY);
            return count == 1 ? "yesterday" : format(timestamp, FORMAT_RECENT_THIS_YEAR);
        } else {
            return format(timestamp, FORMAT_RECENT_DEFAULT);
        }
    }

    /**
     * 是否是 24 小时制
     *
     * @param context
     * @return
     */
    public static boolean is24Hour(Context context) {
        return DateFormat.is24HourFormat(context);
    }

    /**
     * 根据系统设置，选择 12 或 24 小时制进行格式化日期
     *
     * @param context
     * @param timestamp
     * @param timeZoneId
     * @return
     */
    public static String formatDateUseSystem(Context context, long timestamp, String timeZoneId) {
        String format = FORMAT_DATE_12_FULL + " " + FORMAT_DATE_12_MARKER;
        if (is24Hour(context)) {
            format = FORMAT_DATE_24_FULL;
        }
        return format(timestamp, format, timeZoneId);
    }

    /**
     * 根据系统设置，选择 12 或 24 小时制进行格式化时间
     *
     * @param context
     * @param timestamp
     * @param timeZoneId
     * @return
     */
    public static String formatTimeUseSystem(Context context, long timestamp, String timeZoneId) {
        String format = FORMAT_TIME_12_SIMPLE;
        if (is24Hour(context)) {
            format = FORMAT_TIME_24_SIMPLE;
        }
        return format(timestamp, format, timeZoneId);
    }
}
