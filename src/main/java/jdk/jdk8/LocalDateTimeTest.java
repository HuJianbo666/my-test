package jdk.jdk8;

import com.sun.org.apache.xpath.internal.SourceTree;

import javax.sound.midi.Soundbank;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * http://blog.csdn.net/KevinDai007/article/details/77774285
 * <p>
 * Created by hujianbo on 2018/2/7.
 */
public class LocalDateTimeTest {

    public static void main(String[] args) {

        //获取当前时间2018-02-07T13:22:33.793
        System.out.println(LocalDateTime.now());
        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());

        // LocalDateTime
        System.out.println(LocalDateTime.of(2018, 2, 7, 13, 25));

        LocalDate parse = LocalDate.parse("2018-02-07");
        System.out.println(parse);
        System.out.println(parse.plusDays(1));
        System.out.println(parse.minusDays(1));
        System.out.println(parse.getDayOfYear());
        System.out.println(parse.getDayOfMonth());
        //是否是闰年
        System.out.println(parse.isLeapYear());

        LocalDate now = LocalDate.now();
        System.out.println(now.isAfter(parse));
        System.out.println(now.with(TemporalAdjusters.firstDayOfMonth()));
        // 获取这个月的第2天
        System.out.println(now.withDayOfMonth(2));

        MonthDay birth = MonthDay.of(2, 7);
        MonthDay nowMonth = MonthDay.now();
        System.out.println(birth.equals(nowMonth));


        //LocalTime
        LocalTime time = LocalTime.now();
        System.out.println(LocalTime.parse("13:38"));
        System.out.println(LocalTime.of(13, 39, 22));
        System.out.println(LocalTime.parse("13:38").plusHours(1));


        // LocalDateTime
        LocalDateTime today = LocalDateTime.of(2018, 2, 20, 15, 18);

        LocalDateTime.parse("2017-07-20T15:18:00");

        //日期转换
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = today.format(formatter);
        System.out.println(format);

        //计算
        LocalDate initialDate = LocalDate.parse("2017-07-20");
        LocalDate finalDate = initialDate.plus(Period.ofDays(5));

        long between = ChronoUnit.DAYS.between(initialDate, finalDate);
        System.out.println("差距天数: " + between);

        //Date和Instant互相转换
        Date from = Date.from(Instant.now());
        System.out.println(from);
        Instant instant = from.toInstant();
        System.out.println(instant);

        //Date和LocalDateTime互换
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        System.out.println(localDateTime);

        ZoneId zone = ZoneId.systemDefault();
        Date date = Date.from(localDateTime.atZone(zone).toInstant());
        System.out.println(date);
        LocalDate localDate = localDateTime.toLocalDate();
        LocalTime time1 = localDateTime.toLocalTime();

    }
}
