package time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 基本概念
 *
 * 时刻：所有计算机系统内部都用一个整数表示时刻，这个整数是距离格林尼治标准时间1970年1月1日0时0分0秒的毫秒数，可以理解时刻就是绝对时间，它与时区无关，不同时区对同一时刻的解读，即年月日时分秒是不一样的；
 *
 * 时区：同一时刻，世界上各个地区的时间可能是不一样的，具体时间与时区有关，一共有24个时区，英国格林尼治是0时区，北京是东八区，也就是说格林尼治凌晨1点，北京是早上9点；
 *
 * 年历：我们都知道，中国有公历和农历之分，公历和农历都是年历，不同的年历，一年有多少月，每月有多少天，甚至一天有多少小时，这些可能都是不一样的，我们主要讨论公历。
 *
 * Java 8中表示日期和时间的类有多个，主要的有：
 *
 * Instant：表示时刻，不直接对应年月日信息，需要通过时区转换
 *
 * LocalDateTime: 表示与时区无关的日期和时间信息，不直接对应时刻，需要通过时区转换
 *
 * LocalDate：表示与时区无关的日期，与LocalDateTime相比，只有日期信息，没有时间信息
 *
 * LocalTime：表示与时区无关的时间，与LocalDateTime相比，只有时间信息，没有日期信息
 *
 * ZonedDateTime： 表示特定时区的日期和时间
 *
 * ZoneId/ZoneOffset：表示时区
 *
 */
public class LocalDateUtil {


    private final static String FORMAT_PATTERN1 = "yyyy-MM-dd HH:mm:ss";
    private final static String FORMAT_PATTERN2 = "yyyyMMddHHmmss";
    private final static String FORMAT_PATTERN3 = "yyyy-MM-dd";
    private final static String FORMAT_PATTERN4 = "yyyyMMdd";

    public static void main(String[] args) throws InterruptedException {
        Date date1 = new Date();

        TimeUnit.MILLISECONDS.sleep(100);

        System.out.println(localDateFormat(LocalDate.now(), FORMAT_PATTERN3));
        System.out.println(localDateTimeFormat(LocalDateTime.now(), FORMAT_PATTERN1));
        System.out.println(localDateTimeFormat(LocalDateTime.now(), FORMAT_PATTERN2));
        System.out.println(localDateTimeToDate(LocalDateTime.now()));
        System.out.println(dateFormat(new Date(), FORMAT_PATTERN4));
        System.out.println(minusToMillsLocalDateTime(LocalDateTime.now(), LocalDateTime.now().minusSeconds(1)));
        System.out.println(minusToMillsDate(date1, new Date()));
        System.out.println(localDateParse("2018-06-12", FORMAT_PATTERN3));
        System.out.println(localDateTimeParse("2018-06-12 16:04:43", FORMAT_PATTERN1));
        Period p = periodDate(date1, new Date());
        System.out.println("year:" + p.getYears() + "month:" + p.getMonths() + "day:" + p.getDays());
        System.out.println("----------------------------------------------------------------");
        Date date2 = localDateToDate(LocalDate.now().minusMonths(1).minusDays(2));
        Date date3 = localDateToDate(LocalDate.now());
        Period p2 = periodDate(date2, date3);
        System.out.println("year:" + p2.getYears() + "month:" + p2.getMonths() + "day:" + p2.getDays());
        System.out.println("----------------------------------------------------------------");
        Period p1 = periodLocalDate(LocalDate.now().minusDays(2), LocalDate.now());
        System.out.println("year:" + p1.getYears() + "month:" + p1.getMonths() + "day:" + p1.getDays());
    }

    /**
     * 将localDate 按照一定的格式转换成String
     *
     * @param localDate
     * @param pattern
     * @return
     */
    public static String localDateFormat(LocalDate localDate, String pattern) {
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 将localDateTime 按照一定的格式转换成String
     *
     * @param localDateTime
     * @param pattern
     * @return
     */
    public static String localDateTimeFormat(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 将String 按照pattern 转换成LocalDate
     *
     * @param time
     * @param pattern
     * @return
     */
    public static LocalDate localDateParse(String time, String pattern) {
        return LocalDate.parse(time, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 将String 按照pattern 转换成LocalDateTime
     *
     * @param time
     * @param pattern
     * @return
     */
    public static LocalDateTime localDateTimeParse(String time, String pattern) {
        return LocalDateTime.parse(time, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 将date转换成String
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String dateFormat(Date date, String pattern) {
        return localDateTimeFormat(dateToLocalDateTime(date), pattern);
    }

    /**
     * 将LocalDate 转换成 Date
     *
     * @param localDate
     * @return
     */
    public static Date localDateToDate(LocalDate localDate) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 将LocalDateTime 转换成 Date
     *
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 将 Date 转换成LocalDate
     * atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
     *
     * @param date
     * @return
     */
    public static LocalDate dateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDate();
    }

    /**
     * 将 Date 转换成LocalDateTime
     * atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
     *
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     * 计算两个LocalDateTime 之间的毫秒数
     *
     * @param time1
     * @param time2
     * @return
     */
    public static Long minusToMillsLocalDateTime(LocalDateTime time1, LocalDateTime time2) {
        return Duration.between(time1, time2).toMillis();
    }

    /**
     * 计算两个LocalTime 之间的毫秒数
     *
     * @param time1
     * @param time2
     * @return
     */
    public static Long minusToMillsLocalTime(LocalTime time1, LocalTime time2) {
        return Duration.between(time1, time2).toMillis();
    }

    /**
     * 计算两个LocalDate 之间的毫秒数
     *
     * @param time1
     * @param time2
     * @return
     */
    public static Long minusToMillsLocalDate(LocalDate time1, LocalDate time2) {
        return Duration.between(time1, time2).toMillis();
    }

    /**
     * 计算两个LocalDate 之间的Period
     *
     * @param time1
     * @param time2
     * @return
     */
    public static Period periodLocalDate(LocalDate time1, LocalDate time2) {
        return Period.between(time1, time2);
    }

    /**
     * 计算两个Date 之间的Period
     *
     * @param date1
     * @param date2
     * @return
     */
    public static Period periodDate(Date date1, Date date2) {
        return periodLocalDate(dateToLocalDate(date1), dateToLocalDate(date2));
    }

    /**
     * 计算两个Date之间的 Period
     *
     * @param time1
     * @param time2
     * @return
     */
    public static Long minusToMillsDate(Date time1, Date time2) {
        return minusToMillsLocalDateTime(dateToLocalDateTime(time1), dateToLocalDateTime(time2));
    }
}
