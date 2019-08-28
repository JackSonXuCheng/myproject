package com.study.lambda;

import com.study.entity.Person;
import org.junit.Test;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.text.Format;
import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

public class DateTimeTest {

    @Test
    public void test() {

    }
    //时间日期
     @Test
     public void test1(){
         LocalDate now = LocalDate.now();
         //添加十二天
         LocalDate localDate1 = now.plusDays(12);
         //System.out.println(now); 2019-07-23
        // System.out.println(localDate1); 2019-08-04
         LocalDateTime dateTimeNow = LocalDateTime.now();
         //System.out.println(dateTimeNow); 2019-07-23T09:51:01.096
         LocalTime localTimeNow = LocalTime.now();
         //System.out.println(localTimeNow); 09:51:59.007
         LocalDate localDate = LocalDate.of(2019, 07, 23);

     }

     //时间校正器
    @Test
    public void test2() {
        LocalDateTime firstDayOfMonth = LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth());
        //本月的第二个星期五的时间
        LocalDateTime firstDayOfMonth1 = LocalDateTime.now().with(TemporalAdjusters.dayOfWeekInMonth(2,DayOfWeek.FRIDAY));

       // System.out.println(firstDayOfMonth); 2019-07-01T10:21:25.680
       // System.out.println(firstDayOfMonth1); 2019-07-12T10:36:29.724
    }

    //时间格式化
    @Test
    public void test3() throws ParseException {
        String localDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        //Timestamp -> Instant ->  Date
        Date date = Date.from(Timestamp.valueOf(localDate).toInstant());
        //计算两个日期的相差数
        long day = ChronoUnit.DAYS.between(LocalDateTime.now(), LocalDateTime.now().minusDays(12));
        System.out.println(day);
        ArrayList<String> list = new ArrayList<>();
        list.add("xiao");
        list.add("ming");
        list.add("hong");
        System.out.println(String.join(",", list));
    }




}
