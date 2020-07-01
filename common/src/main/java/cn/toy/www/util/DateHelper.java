package cn.toy.www.util;

import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @description 日期工具
 * @author: fly
 * @date: 2018/9/3 13:59
 */
@Slf4j
public class DateHelper {

    /**
     * 一分钟的毫秒数
     */
    private static final Long oneMinuteInMilliseconds = 60000L;

    /**
     * 获取当前时间返回Timestamp（Y-M-D）
     *
     * @param timestamp 当前时间戳
     * @return 当前时间
     */
    public static Timestamp getCurrentTime(Timestamp timestamp) {
        Instant instant = timestamp.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();
        Instant instantLocalDate = localDate.atStartOfDay().atZone(zone).toInstant();
        return new Timestamp(Date.from(instantLocalDate).getTime());
    }

    /**
     * 获取指定日期所在月份开始的时间戳
     *
     * @return 当月第一天
     */
    public static Long getCurrentMonthBegin() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        //设置为1号,当前日期既为本月第一天
        c.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0
        c.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        c.set(Calendar.MINUTE, 0);
        //将秒至0
        c.set(Calendar.SECOND, 0);
        //将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
        // 获取本月第一天的时间戳
        return c.getTimeInMillis();
    }

    /**
     * 获取指定日期所在月份结束的时间戳
     *
     * @return 当月最后一天
     */
    public static Long getCurrentMonthEnd() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        //设置为当月最后一天
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        //将小时至23
        c.set(Calendar.HOUR_OF_DAY, 23);
        //将分钟至59
        c.set(Calendar.MINUTE, 59);
        //将秒至59
        c.set(Calendar.SECOND, 59);
        //将毫秒至999
        c.set(Calendar.MILLISECOND, 999);
        // 获取本月最后一天的时间戳
        return c.getTimeInMillis();
    }

    /**
     * 获取上个月最后一天
     *
     * @return 上个月最后一天
     */
    public static Long getLastMonthEnd() {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -1);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
            int lastMonthMaxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), lastMonthMaxDay, 23, 59, 59);
            String endTime = sdf.format(calendar.getTime());
            return sdf.parse(endTime).getTime();
        } catch (ParseException e) {
            log.error("DateHelper getLastMonthEnd ParseException", e);
            //获取时间异常，返回当前时间
            return System.currentTimeMillis();
        }
    }

    /**
     * 获取上个月第一天
     *
     * @return 上个月第一天
     */
    public static Long getLastMonthBegin() {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -1);
            int lastMonthMaxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-01  00:00:00");
            String beginTime = sdf.format(calendar.getTime());
            return sdf.parse(beginTime).getTime();
        } catch (ParseException e) {
            log.error("DateHelper getLastMonthBegin ParseException", e);
            //获取时间异常，返回当前时间
            return System.currentTimeMillis();
        }
    }

    /**
     * 字符串转化为时间戳
     *
     * @param date 字符串格式日期
     * @return 时间戳
     */
    public static Timestamp strToTimestamp(String date) {
        long time = System.currentTimeMillis();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            time = simpleDateFormat.parse(date).getTime();
        } catch (ParseException e) {
            log.error("DateHelper strToTimestamp ERROR -> {}", "字符串转化日期失败，请检查格式");
            log.error("DateHelper strToTimestamp Exception", e);
        }
        return new Timestamp(time);
    }

    /**
     * 获取相距N天的数据：负数代表今天之前，整数代表今天之后
     *
     * @param n 天数
     * @return 时间戳
     */
    public static Timestamp getBeforeDate(int n) {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, n);
        date = calendar.getTime();
        return new Timestamp(date.getTime());
    }

    /**
     * 获取指定年月的开始时间
     *
     * @param year  年
     * @param month 月
     * @return 指定年月的开始时间
     */
    public static Date getBeginTime(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate localDate = yearMonth.atDay(1);
        LocalDateTime startOfDay = localDate.atStartOfDay();
        ZonedDateTime zonedDateTime = startOfDay.atZone(ZoneId.of("Asia/Shanghai"));

        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * 获取指定年月的结束时间
     *
     * @param year  年
     * @param month 月
     * @return 指定年月的结束时间
     */
    public static Date getEndTime(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();
        LocalDateTime localDateTime = endOfMonth.atTime(23, 59, 59, 999);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Asia/Shanghai"));
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * 获取时间间隔日期
     *
     * @param big 较大时间
     * @param min 较小时间
     * @return 间隔
     */
    public static Integer dateInterval(Timestamp big, Timestamp min) {
        //第二个日期
        String bigTime = big.toString();
        //第一个日期
        String minTime = min.toString();
        //算两个日期间隔多少天
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date bigDate = null;
        Date minDate = null;
        try {
            bigDate = format.parse(bigTime);
            minDate = format.parse(minTime);
        } catch (ParseException e) {
            log.error("DateHelper dateInterval ParseException", e);
        }
        return (int) ((bigDate.getTime() - minDate.getTime()) / (1000 * 3600 * 24));
    }

    /**
     * 获取今年的开始时间
     *
     * @return 今年的开始时间
     */
    public static Date getCurrentYearStart() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-01-01 00:00:00");
            return format.parse(format.format(new Date()));
        } catch (ParseException e) {
            log.error("DateHelper getCurrentYearStart ParseException", e);
            return null;
        }
    }

    /**
     * 获取今年的结束时间
     *
     * @return 今年的结束时间
     */
    public static Date getCurrentYearEnd() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-12-31 23:59:59");
            return format.parse(format.format(new Date()));
        } catch (ParseException e) {
            log.error("DateHelper getCurrentYearEnd ParseException", e);
            return null;
        }
    }


    /**
     * 时间戳转时间
     */
    public static String timeStamp2StrSSS(Long timeStamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(timeStamp);
    }

    /**
     * 判断选择的日期是否是本周
     *
     * @param time 时间戳
     * @return 是否是当天周
     */
    public static boolean isThisWeek(long time) {
        Calendar calendar = Calendar.getInstance();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.setTime(new Date(time));
        int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        return paramWeek == currentWeek;
    }

    /**
     * 判断是否是今天
     *
     * @param time 时间戳
     * @return 是否是今天
     */
    public static boolean isToday(long time) {
        return isThisTime(time, "yyyy-MM-dd");
    }

    /**
     * 获取当日的开始时间
     *
     * @return 当日的开始时间
     */
    public static Timestamp getCurrentDayStartTime() {
        Calendar currentDate = new GregorianCalendar();
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        return new Timestamp(currentDate.getTime().getTime());
    }

    public static String getStartTime(int amount) {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, amount);
        //一天的开始时间 yyyy:MM:dd 00:00:00
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date dayStart = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(dayStart);
    }

    public static String getEndTime(int amount) {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, amount);
        //一天的开始时间 yyyy:MM:dd 00:00:00
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        Date dayStart = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(dayStart);
    }

    /**
     * 判断选择的日期是否是本月
     *
     * @param time 时间戳
     * @return 是否是本月
     */
    public static boolean isThisMonth(long time) {
        return isThisTime(time, "yyyy-MM");
    }

    private static boolean isThisTime(long time, String pattern) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        //参数时间
        String param = sdf.format(date);
        //当前时间
        String now = sdf.format(new Date());
        return param.equals(now);
    }

    /**
     * 获取明天的凌晨
     *
     * @return 明天凌晨时间
     */
    public static long getNextDayZeroTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTimeInMillis();
    }

    /**
     * 获取次月的一号
     *
     * @param date 日期
     * @return 次月一号
     */
    public static Timestamp getFirstDayOfNextMonth(Timestamp date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        return new Timestamp(calendar.getTime().getTime());
    }

    /**
     * 时间戳转换分钟,不满足一分钟 按照一分钟来算
     *
     * @param time 时间戳
     * @return 次月一号
     */
    public static Long timestampConversionSeconds(Long time) {
        if (time != 0) {
            long minute = time / oneMinuteInMilliseconds;
            long l = time % oneMinuteInMilliseconds;
            if (minute == 0 || l != 0) {
                minute = minute + 1L;
            }
            return minute;
        } else {
            return 1L;
        }
    }

    /**
     * 获取指定月份以后的日期
     *
     * @param date        日期
     * @param monthNumber 月数
     * @return 日期
     */
    public static Timestamp getAfterMonthDate(Date date, int monthNumber) {
        //获得一个日历的实例
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //设置日历时间
        c.setTime(date);
        //在日历的月份上增加6个月
        c.add(Calendar.MONTH, monthNumber);
        //的到你想要得6个月后的日期
        String strDate = sdf.format(c.getTime());
        try {
            return new Timestamp(sdf.parse(strDate).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取下一个时间点的50分
     *
     * @return 下一个时间点的50分
     */
    public static Date getNextFiftyMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int minute = calendar.get(Calendar.MINUTE);
        calendar.set(Calendar.HOUR, minute > 50 ? calendar.get(Calendar.HOUR) + 1 : calendar.get(Calendar.HOUR));
        calendar.set(Calendar.MINUTE, 50);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
