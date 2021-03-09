package time;

import java.time.*;

/**
 * 时间戳和Date互转
 */
public class TimeStampAndDate {

    public static void main(String[] args) {
        TimeStampAndDate test = new TimeStampAndDate();
        long timeStamp = 1615274189000L;
        LocalDateTime ldt = test.timeStampToDatetime(timeStamp);
        timeStamp = test.dataTimeToTimestamp(ldt);
        System.out.println(timeStamp);
    }

    /**
     * 1、计算与1970-01-01T00:00:00Z的间隔Instant
     * 2、根据间隔与时区转为当地时间
     *
     * @param timestamp
     * @return
     */
    public LocalDateTime timeStampToDatetime(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDate ld = ldt.toLocalDate();
        LocalTime lt = ldt.toLocalTime();
        return ldt;
    }

    /**
     * 将本地时间LocalDateTime根据特定时区转化为距离1970-01-01T00:00:00Z的间隔Instant
     *
     * @param ldt
     * @return
     */
    public long dataTimeToTimestamp(LocalDateTime ldt) {
        ZoneId zone = ZoneId.systemDefault();
        long timestamp = ldt.atZone(zone).toInstant().toEpochMilli();
        long timestamp2 = ldt.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        return timestamp;
    }
}
