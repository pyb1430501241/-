package com.pdsu.banmeng.utils;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间格式化工具
 * @author 半梦
 * @create 2021-05-10 18:59
 */
@SuppressWarnings("all")
public abstract class DateUtils {

    private static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    protected static final DateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_PATTERN);

    public static final long NEWS_TIME_MINUTE = 60;

    public static final long NEWS_TIME_HOUR = NEWS_TIME_MINUTE * 60;

    public static final long NEWS_TIME_DAY = NEWS_TIME_HOUR * 24;

    public static final long NEWS_TIME_WEEK = NEWS_TIME_DAY * 7;

    @NonNull
    public static Date nowDate() {
        return create(System.currentTimeMillis());
    }

    @NonNull
    public static Date create(Long time) {
        return new Date(time);
    }

    /**
     * @param date date
     */
    @NonNull
    public static String formatString(@NonNull Date date) {
        return DATE_FORMAT.format(date);
    }

    /**
     *
     * @param dateString yyyy-MM-dd HH:mm:ss
     * @return
     *  <p>当日期不符合规范时，返回<code>null</code></p>
     */
    @Nullable
    public static Date formatDate(@NonNull String dateString) {
        try {
            return DATE_FORMAT.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

}
