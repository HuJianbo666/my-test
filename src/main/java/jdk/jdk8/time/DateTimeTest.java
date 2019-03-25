package jdk.jdk8.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @author HuJianbo
 */
public class DateTimeTest {
    public static void main(String[] args) {
        dateTest();
        timeTest();
        localDateTimeTest();
        instantTest();
        dateTimeUtilsTest();
        dateParseFormatTest();
        dateAPILegacySupport();
        dateToLocalDate();
        dateToLocalTime();
        dateToLocalDateTime();
        localDateToDate();
        localTimeToDate();
        localDateTimeToDate();
        dateFormatTest();
        parseDateTest();
    }

    private static void dateTest() {
        LocalDate today = LocalDate.now();
        System.out.println("当前日期" + today);

        LocalDate of = LocalDate.of(2019, Month.MARCH, 25);
        System.out.println("2019年三月25:" + of);

        LocalDate todayAsia = LocalDate.now(ZoneId.of("Asia/Kolkata"));
        System.out.println("亚洲时区的当前日期：" + todayAsia);

        LocalDate ofEpochDay = LocalDate.ofEpochDay(365);
        System.out.println("基准日期点" + ofEpochDay);

        LocalDate ofYearDay = LocalDate.ofYearDay(2018, 2);
        System.out.println("2018年第2天的日期" + ofYearDay);
    }


    private static void timeTest() {
        LocalTime now = LocalTime.now();
        System.out.println(now);

        LocalTime of = LocalTime.of(11, 24, 40);
        System.out.println(of);

        LocalTime timeOfAsia = LocalTime.now(ZoneId.of("Asia/Kolkata"));
        System.out.println(timeOfAsia);

        LocalTime ofSecondOfDay = LocalTime.ofSecondOfDay(10000);
        System.out.println(ofSecondOfDay);
    }

    private static void localDateTimeTest() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        LocalDateTime now2 = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        System.out.println(now2);

        LocalDateTime of = LocalDateTime.of(2019, Month.MARCH, 25, 13, 53, 30);
        System.out.println(of);

        LocalDateTime todayKolkata = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
        System.out.println(todayKolkata);

        System.out.println("=============");
        System.out.println(now.toEpochSecond(ZoneOffset.of("+8")));
        System.out.println(now.toInstant(ZoneOffset.of("+8")).toEpochMilli());
    }

    private static void instantTest() {
        Instant now = Instant.now();
        System.out.println(now);
    }

    private static void dateTimeUtilsTest() {
        LocalDate today = LocalDate.now();
        System.out.println("Year " + today.getYear() + " is Leap Year? " + today.isLeapYear());
        System.out.println("Today is before 01/01/2015? " + today.isBefore(LocalDate.of(2015, 1, 1)));
        System.out.println("Current Time=" + today.atTime(LocalTime.now()));
        System.out.println("10 days after today will be " + today.plusDays(10));
        System.out.println("3 weeks after today will be " + today.plusWeeks(3));
        System.out.println("20 months after today will be " + today.plusMonths(20));
        System.out.println("10 days before today will be " + today.minusDays(10));
        System.out.println("3 weeks before today will be " + today.minusWeeks(3));
        System.out.println("20 months before today will be " + today.minusMonths(20));

        System.out.println("First date of this month= " + today.with(TemporalAdjusters.firstDayOfMonth()));

        LocalDate lastDayOfYear = today.with(TemporalAdjusters.lastDayOfYear());
        System.out.println("Last date of this year= " + lastDayOfYear);

        Period period = today.until(lastDayOfYear);
        System.out.println("Period Format= " + period);
        System.out.println("Months remaining in the year= " + period.getMonths());
    }

    private static void dateParseFormatTest() {
        LocalDate today = LocalDate.now();
        System.out.println("today:" + today);

        System.out.println(today.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")));
        System.out.println(today.format(DateTimeFormatter.BASIC_ISO_DATE));

        Instant timestamp = Instant.now();
        System.out.println("Default format of Instant=" + timestamp);
    }

    private static void dateAPILegacySupport() {
        Instant timestamp = new Date().toInstant();
        LocalDateTime date = LocalDateTime.ofInstant(timestamp,
                ZoneId.of(ZoneId.SHORT_IDS.get("PST")));
        System.out.println("Date = " + date);

        Instant time = Calendar.getInstance().toInstant();
        System.out.println(time);

        ZoneId defaultZone = TimeZone.getDefault().toZoneId();
        System.out.println(defaultZone);

        ZonedDateTime gregorianCalendarDateTime = new GregorianCalendar().toZonedDateTime();
        System.out.println(gregorianCalendarDateTime);

        Date dt = Date.from(Instant.now());
        System.out.println(dt);

        TimeZone tz = TimeZone.getTimeZone(defaultZone);
        System.out.println(tz);

        GregorianCalendar gc = GregorianCalendar.from(gregorianCalendarDateTime);
        System.out.println(gc);
    }

    private static void dateToLocalDate() {
        LocalDate localDate = dateToLocalDateTime().toLocalDate();
        System.out.println(localDate);
    }

    private static LocalDateTime dateToLocalDateTime() {
        Date date = new Date();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
        return localDateTime;
    }

    private static void dateToLocalTime() {
        LocalTime localTime = dateToLocalDateTime().toLocalTime();
        System.out.println(localTime);
    }

    private static void localDateToDate() {
        LocalDate now = LocalDate.now();
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = now.atStartOfDay().atZone(zoneId).toInstant();
        Date date = Date.from(instant);
        System.out.println(date);
    }

    private static void localTimeToDate() {
        LocalTime localTime = LocalTime.now();
        LocalDate localDate = LocalDate.now();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        Instant instant = localDateTime.atZone(zoneId).toInstant();
        Date date = Date.from(instant);
        System.out.println(date);
    }

    private static void localDateTimeToDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        Date date = Date.from(instant);
        System.out.println(date);
    }

    private static void dateFormatTest() {
        Date date = new Date();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = formatter.format(localDateTime);
        System.out.println(format);
    }

    private static void parseDateTest() {
        String time = "2018-03-25 19:26:23";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parse = LocalDateTime.parse(time, formatter);
        Date date = Date.from(parse.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(date);
    }
}
