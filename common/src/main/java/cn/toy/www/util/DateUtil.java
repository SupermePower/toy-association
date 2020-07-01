package cn.toy.www.util;


import cn.toy.www.Constant;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.*;

/**
 * @version 1.0.0
 * @Author hsl
 * @Description 时间格式转换工具类
 * @Date: 17:22 2018/7/13
 */
public class DateUtil<main> {

    public static final String YYMMDD1 = "yy/MM/dd";
    public static final String YYYYMM = "yyyyMM";
    public static final String YYYYMM1 = "yyyy-MM";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYYMMDD1 = "yyyy_MM_dd";
    public static final String YYYYMMDD2 = "yyyy-MM-dd";
    public static final String YYYYMMDD3 = "yyyy/MM/dd";

    public static final String YYYYMMDDHHMM = "yyyyMMddHHmm";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static final String HHMM = "HHmm";
    public static final String HHMM1 = "HH:mm";

    /**
     * 将yyyy-MM-dd的时间格式转化为时间
     * @param time
     * @return
     */
    public static Date stringToDate(String time){
        if(Objects.isNull(time)){
            return null;
        }
        LocalDate beginDateTime = new LocalDate(time);

        return beginDateTime.toDate();
    }

    /**
     * 将时间戳转换为yyyy/MM/dd的时间格式
     * @param time
     * @return
     */
    public static String linuxTimeToFormat(Timestamp time){
        if(Objects.equals(null, time)){
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        return format.format(time);
    }

    /**
     * 将yyyy-MM-dd的时间字符串转换为时间
     * @param time
     * @return
     */
    public static String linuxTimeToFormat(Long time){
        if(Objects.isNull(time)){
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        return format.format(new Timestamp(time));
    }

    /**
     * 将时间戳转换为yyyy-MM-dd的时间格式
     * @param time
     * @return
     */
    public static String timeToFormat(Timestamp time){
        if(Objects.equals(null, time)){
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(time);
    }

    public static String timeToFormat(Timestamp time, String pattern){
        if(Objects.equals(null, time)){
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(time);
    }



    public static LocalDate getLocalDate(String dateStr) {
        LocalDate localDate = new LocalDate();
        if(!Objects.equals(null, dateStr)){
            localDate = new LocalDate(Long.valueOf(dateStr));
        }
        return localDate;
    }


    /**
     * long类型的时间撮转LocalDate
     * @param date
     * @return
     */
    public static LocalDate getLocalDate(long date) {
        return new LocalDate(date);
    }

    /**
     * 获取当天的Date(去掉时分秒，只有年月日)
     * @return
     */
    public static Date getToday() {
        DateTime dateTime = new DateTime();
        LocalDate date = new LocalDate(dateTime);
        return date.toDate();
    }

    /**
     * 获取昨天的Date(去掉时分秒，只有年月日)
      * @return
     */
    public static Date getYesterday() {
        DateTime dateTime = new DateTime();
        LocalDate date = new LocalDate(dateTime.minusDays(1));
        return date.toDate();
    }

    /**
     * 获取指定时间昨天的Date(去掉时分秒，只有年月日)
     * @return
     */
    public static Date getDateYesterday(Date date) {
        LocalDate localDate =  new LocalDate(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().minusDays(1));
        return localDate.toDate();
    }

    /**
     * 获取明天的Date(去掉时分秒，只有年月日)
     * @return
     */
    public static Date getTomorrow() {
        DateTime dateTime = new DateTime();
        LocalDate date = new LocalDate(dateTime.plusDays(1));
        return date.toDate();
    }

    /**
     * 获取这周开始的星期一的时间
     * @return
     */
    public static Date getThisWeekStartday() {
        DateTime dateTime = new DateTime();
        LocalDate date = new LocalDate(dateTime);
        return date.dayOfWeek().withMinimumValue().toDate();
    }

    /**
     * 获取上周开始的星期一的时间
     * @return
     */
    public static Date getLastWeekStartday() {
        DateTime dateTime = new DateTime();
        LocalDate date = new LocalDate(dateTime.minusWeeks(1));
        return date.dayOfWeek().withMinimumValue().toDate();
    }

    /**
     * 获取这个月开始的星期一的时间
     * @return
     */
    public static Date getThisMonthStartday() {
        DateTime dateTime = new DateTime();
        LocalDate date = new LocalDate(dateTime);
        return date.dayOfMonth().withMinimumValue().toDate();
    }

    /**
     * 获取上个月开始的星期一的时间
     * @return
     */
    public static Date getLastMonthStartday() {
        DateTime dateTime = new DateTime();
        LocalDate date = new LocalDate(dateTime.minusMonths(1));
        return date.dayOfMonth().withMinimumValue().toDate();
    }
    /**
     * 如果传递的days大于0，表示得到boundary之后的days的日期
     * 如果传递的days小于0，表示得到boundary之前的days的日期
     */
    public static Date getSpecificDay(Date boundary, int days) {
        DateTime dateTime = new DateTime(boundary);
        LocalDate date = null;
        if (days < 0) {
            date = new LocalDate(dateTime.minusDays(-days));
        } else {
            date = new LocalDate(dateTime.plusDays(days));
        }
        return date.toDate();
    }


    /**
     * 两个日期之间差多少年月天
     * @param date1
     * @return 字符串
     */
        public static String differentYMD(Date date1)
    {
        Calendar foretime = Calendar.getInstance();
        foretime.setTime(date1);
        Calendar nowCalendar = Calendar.getInstance();
        int day = nowCalendar.get(Calendar.DAY_OF_MONTH) - foretime.get(Calendar.DAY_OF_MONTH);
        int month = nowCalendar.get(Calendar.MONTH) - foretime.get(Calendar.MONTH);
        int year = nowCalendar.get(Calendar.YEAR) - foretime.get(Calendar.YEAR);
        // 按照减法原理，先day相减，不够向month借；然后month相减，不够向year借；最后year相减。
        if(day < 0) {
            month -= 1;
            nowCalendar.add(Calendar.MONTH, -1);// 得到上一个月，用来得到上个月的天数。

            day = day + nowCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        if(month < 0) {
            month = (month + 12) % 12;
            year--;
        }
        if(year == 0){
            if(month == 0){
                return (day+1)+"天";
            }else{
                return  month + "月"+(day+1)+"天";
            }
        }else{
            return   year + "年" + month + "月"+(day+1)+"天";
        }
    }

    /**
     * 立即数：相当于高级语言中的常量（常数），它是直接出现在指令中的数，不用存储在寄存器或存储器中的数，如指令ADD AL,06H中的06H即为立即数
     *  获取两个时间的差距的天数
     * @param date1
     * @param date2
     * @return
     */
    public static int diffDays(Date date1, Date date2)
    {
        Date bigDay = null;
        Date smallDay = null;
        if (date1.after(date2)) {
            bigDay = date1;
            smallDay = date2;
        } else {
            bigDay = date2;
            smallDay = date1;
        }
        // 用立即数，减少乘法计算的开销
        return (int) (bigDay.getTime() / 86400000 - smallDay.getTime() / 86400000);
    }


    public static int diffHours(Date date1, Date date2) {
        Date bigDate = null;
        Date smallDate = null;
        if (date1.after(date2)) {
            bigDate = date1;
            smallDate = date2;
        } else {
            bigDate = date2;
            smallDate = date1;
        }
        return (int) (bigDate.getTime() / 3600000 - smallDate.getTime() / 3600000);
    }
    /**
     * 时间戳转换小时:分:秒
     *
     * @author:hwts
     * @date:2018-07-19 19:16
     */
    public static String getHMS(long timeStamp) {
        // 小时
        Long companyHour = 3600 * 1000L;
        // 分
        Long companyMin = 60 * 1000L;
        // 秒
        Long companySecond = 1000L;
        Long disposable = 0L;
        Long hour = timeStamp / companyHour;
        disposable = timeStamp % companyHour;
        Long min = disposable / companyMin;
        disposable = disposable % companyMin;
        Long second = disposable / companySecond;
        String result = "" ;
        if (hour < Constant.MIN_TWO_DIGITS) {
            result += "0" + hour ;
        }else{
            result += hour ;
        }
        if (min < Constant.MIN_TWO_DIGITS) {
            result += ":0" + min ;
        }else{
            result += ":" + min ;
        }
        if (second < Constant.MIN_TWO_DIGITS) {
            result += ":0" + second ;
        }else{
            result += ":" + second ;
        }
        return result;
    }

    /**
     * 获取两个北京时间的天数差，按自然天算(如：23:59:59距离00:00:00也算一天)
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 天数差
     */
    public static int getDiscrepantDays(Date startDate, Date endDate){
        // 由于北京时间在东八区，提前了8个小时
        return (int)((endDate.getTime()+ Constant.EAST_EIGHT_MILLISECOND)/Constant.DAY_MILLISECOND
                - (startDate.getTime()+ Constant.EAST_EIGHT_MILLISECOND)/Constant.DAY_MILLISECOND);
    }

    /**
     * date2比date1多的天数+1
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1,Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)   //同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2-day1) + 1 ;
        }
        else    //不同年
        {
            return day2-day1+1;
        }
    }

    /**
     * date2比date1多的天数
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDay(Date date1,Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)   //同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2-day1) ;
        }
        else    //不同年
        {
            return day2-day1;
        }
    }

    /**
     * 日期增加天数
     * @param date
     * @param days
     * @return
     */
    public static Date dateAddTimeByDay(Date date,Integer days){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,days);
        return calendar.getTime();
    }

    /**
     * 日期增加天数
     * @param date
     * @param days
     * @return
     */
    public static Date dateBeforeTimeByDay(Date date,Integer days){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,-days);
        return calendar.getTime();
    }

    /**
     * 获取时间轴数据
     * @param day 查看数据天数
     * @return 时间轴数据,时间格式为yyyyMMdd
     */
    public static List<String> getTimeAxisData(Integer day){
        // 时间天数小于1天的话，返回空
        if(Objects.isNull(day) || day <= 0){
            return null;
        }
        List<String> timeAxisDataList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH,-day);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        for(int i = 0; i < day; i++){
            calendar.add(Calendar.DAY_OF_MONTH,i);
            timeAxisDataList.add(sdf.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_MONTH,-i);
        }
        return timeAxisDataList;
    }
    /**
     * 获取时间轴数据
     * @param day 查看数据天数
     * @return 时间轴数据,时间格式为yyyyMMdd
     */
    public static List<String> getTimeAxisDataMonthDay(Integer day){
        // 时间天数小于1天的话，返回空
        if(Objects.isNull(day) || day <= 0){
            return null;
        }
        List<String> timeAxisDataList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH,-day);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
        for(int i = 0; i < day; i++){
            calendar.add(Calendar.DAY_OF_MONTH,i);
            timeAxisDataList.add(sdf.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_MONTH,-i);
        }
        return timeAxisDataList;
    }

    /**
     * 获取时间轴数据
     * @param date 开始时间
     * @param day 查看数据天数
     * @param format YY/MM/dd
     * @return 时间轴数据,时间格式为YY/MM/dd
     */
    public static List<String> getDataMonthDayList(Date date,Integer day,String format){
        // 时间天数小于1天的话，返回空
        if(Objects.isNull(day) || day <= 0){
            return null;
        }
        List<String> timeAxisDataList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateAddTimeByDay(date,1));
        calendar.add(Calendar.DAY_OF_MONTH,-day);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        //timeAxisDataList.add(sdf.format(date));
        for(int i = 0; i < day; i++){
            calendar.add(Calendar.DAY_OF_MONTH,i);
            timeAxisDataList.add(sdf.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_MONTH,-i);
        }
        return timeAxisDataList;
    }

    /**
     * 获得入参日期下个月的第一天
     *
     * @param date 入参日期
     * @return 入参日期下个月的第一天
     */
    public static Date firstDayOfNextMonth(Date date) {
        //获得入参的日期
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        //获取下个月第一天：
        cd.add(Calendar.MONTH, 1);
        //设置为1号,当前日期既为次月第一天
        cd.set(Calendar.DAY_OF_MONTH,1);
        return cd.getTime();
    }

    public static Date parseToDate(String dateStr, String patten) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patten);
        return simpleDateFormat.parse(dateStr);
    }

    public static String format(String patten){
        return format(Calendar.getInstance().getTime(), patten);
    }

    public static String format(Date date, String patten){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patten);
        return simpleDateFormat.format(date);
    }








    public static void main(String[] args) {

    }

}
