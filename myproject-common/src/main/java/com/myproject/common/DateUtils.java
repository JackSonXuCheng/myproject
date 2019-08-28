package com.myproject.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * Created by hboxs001 on 2015/11/17.
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils {

    public static int getDiffYears(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
        if (a.get(Calendar.DAY_OF_YEAR) < b.get(Calendar.DAY_OF_YEAR)) {
            diff++;
        }
        return diff;
    }

    public static int getDiffMonth(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
        int month = b.get(Calendar.MONTH) - a.get(Calendar.MONTH);
        int day = b.get(Calendar.DAY_OF_MONTH) - a.get(Calendar.DAY_OF_MONTH);
        if (day > 0) {
            month++;
        }
        if (diff > 0) {
            month = month + (diff * 12);
        }
        if (month <= 0) {
            month = 1;
        }
        return month;
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.CHINESE);
        cal.setTime(date);
        return cal;
    }

    public static String dateStr(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    /**
     * 获取今天过去第几天的日期
     *
     * @param past
     * @return
     */
    public static Date getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, past);
        Date today = calendar.getTime();
        return today;
    }

    /**
     * 获取今天之前第几天的日期
     *
     * @param pre
     * @return
     */
    public static Date getPreDate(int pre) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - pre);
        Date today = calendar.getTime();
        return today;
    }


    /*
    * 毫秒转化
    */
    public static String formatTime(long ms) {

        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        StringBuffer sb = new StringBuffer();
        if (day > 0) {
            sb.append(day + "天");
        }
        if (hour > 0) {
            sb.append(hour + "小时");
        }
        if (minute > 0) {
            sb.append(minute + "分");
        }
        if (second > 0) {
            sb.append(second + "秒");
        }
//        if(milliSecond > 0) {
//            sb.append(milliSecond+"毫秒");
//        }
        return sb.toString();
    }

    /**
     * 获取一年后的日期
     *
     * @param date
     * @return
     */
    public static Date getAfterYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, +1);
        return cal.getTime();
    }

    /**
     * 获取半年后的日期
     *
     * @param date
     * @return
     */
    public static Date getAfterHalfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, +6);
        return cal.getTime();
    }

    /**
     * 获取一个季度后的日期
     *
     * @param date
     * @return
     */
    public static Date getAfterSeason(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, +3);
        return cal.getTime();
    }

    /**
     * 获取一个月后的日期
     *
     * @param date
     * @return
     */
    public static Date getAfterMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, +1);
        return cal.getTime();
    }

    public static Date startOftDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND,0);

        return calendar.getTime();
    }

    public static Date endOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }


    /**
     * 判断时间是否在时间段内
     *
     * @param date         当前时间 yyyy-MM-dd HH:mm:ss
     * @param strDateBegin 开始时间 00:00:00
     * @param strDateEnd   结束时间 00:05:00
     * @return
     */
    public static boolean isInDate(Date date, String strDateBegin, String strDateEnd) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String strDate = sdf.format(date);   //2016-12-16 11:53:54
        // 截取当前时间时分秒 转成整型
        int tempDate = Integer.parseInt(strDate.substring(11, 13) + strDate.substring(14, 16) /*+ strDate.substring(17, 19)*/);
        // 截取开始时间时分秒  转成整型
        int tempDateBegin = Integer.parseInt(strDateBegin.substring(0, 2) + strDateBegin.substring(3, 5) /*+ strDateBegin.substring(6, 8)*/);
        // 截取结束时间时分秒  转成整型
        int tempDateEnd = Integer.parseInt(strDateEnd.substring(0, 2) + strDateEnd.substring(3, 5) /*+ strDateEnd.substring(6, 8)*/);

        if ((tempDate >= tempDateBegin && tempDate <= tempDateEnd)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取计算后的时间（天为单位）
     *
     * @param amount 天数
     * @param date   比较时间
     * @param isAdd  是之前还是之后
     * @return
     */
    public static Date getDate(int amount, Date date, boolean isAdd) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        } else {
            cal.setTime(new Date());
        }
        if (isAdd) {
            cal.add(Calendar.DATE, amount);
        } else {
            cal.set(Calendar.DATE, cal.get(Calendar.DATE) - amount);
        }
        return cal.getTime();
    }

    /**
     * 获取计算后的时间
     *
     * @param amount 数量
     * @param date   比较时间
     * @param isAdd  是之前还是之后
     * @param type   类型（day-天；week-周；month-月；season-季；halfYear-半年；year-年）
     * @return
     */
    public static Date getNeedDate(int amount, Date date, boolean isAdd, String type) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        } else {
            cal.setTime(new Date());
        }
        switch (type) {
            case "day":
                if (isAdd) {
                    cal.add(Calendar.DATE, amount);
                } else {
                    cal.set(Calendar.DATE, cal.get(Calendar.DATE) - amount);
                }
                break;
            case "week":
                if (isAdd) {
                    cal.add(Calendar.DATE, 7);
                } else {
                    cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 7);
                }
                break;
            case "month":
                if (isAdd) {
                    cal.add(Calendar.MONTH, amount);
                } else {
                    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - amount);
                }
                break;
            case "season":
                if (isAdd) {
                    cal.add(Calendar.MONTH, 3);
                } else {
                    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 3);
                }
                break;
            case "halfYear":
                if (isAdd) {
                    cal.add(Calendar.MONTH, 6);
                } else {
                    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 6);
                }
                break;
            case "year":
                if (isAdd) {
                    cal.add(Calendar.YEAR, amount);
                } else {
                    cal.set(Calendar.DATE, cal.get(Calendar.YEAR) - amount);
                }
                break;
            default:
                if (isAdd) {
                    cal.add(Calendar.YEAR, 3);
                } else {
                    cal.set(Calendar.DATE, cal.get(Calendar.YEAR) - 3);
                }
                break;
        }
        return cal.getTime();
    }

    /**
     * 判断是否为同一天
     *
     * @return date为null时返回false
     */
    public static boolean checkSameDay(Date date) {
        if (date == null) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        calendar.setTime(date);
        now.setTime(new Date());
        return calendar.get(Calendar.DAY_OF_YEAR) == now.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 获取天数
     */
    public static long getDayCount(Date begin, Date end) {
        begin = setHMSMToZero(begin);
        end = setHMSMToZero(end);
        long sub = end.getTime() - begin.getTime();
        return sub / (24 * 60 * 60 * 1000);
    }

    private static Date setHMSMToZero(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private static Date setHMSMZero(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取天数
     */
    public static int getDaysCount(Date begin, Date end) {
        begin = setHMSMZero(begin);
        end = setHMSMZero(end);
        return Long.valueOf((end.getTime() - begin.getTime()) / (24 * 60 * 60 * 1000)).intValue();
    }


    /**
     * 将日期格式化为天：yy-MM-dd
     */
    public static String getFormatDay(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    /**
     * 将日期格式化为天：yy-MM-dd
     */
    public static String getFormatTime(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }


    public static void main(String[] args) {

        long time = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
//            int l = (int)(Math.random()*100+1);


            Random random = new Random();
            System.out.println(random.nextInt(100));

        }

        System.out.println(System.currentTimeMillis() - time);

    }
}
