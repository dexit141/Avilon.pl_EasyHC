package pl.godzina.avilon.helpers;

import java.text.*;
import java.util.*;

public enum TimeHelper
{
    TICK(50, 50),
    MILLISECOND(1, 1),
    SECOND(1000, 1000),
    MINUTE(60000, 60),
    HOUR(3600000, 60),
    DAY(86400000, 24),
    WEEK(604800000, 7);

    public static final int MPT = 50;
    private final int time;
    private final int timeMulti;
    static SimpleDateFormat timeFormat;
    public static String sec;
    public static String min;
    public static String hr;
    public static String day;

    public static String getNowDate() {
        final Date banTime = new Date(System.currentTimeMillis());
        final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd");
        return dateFormat.format(banTime);
    }

    public static String getNowTime() {
        final Date banTime = new Date(System.currentTimeMillis());
        final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(banTime);
    }

    private static Integer getTimeInt() {
        final Date banTime = new Date(System.currentTimeMillis());
        final SimpleDateFormat dateFormat = new SimpleDateFormat("HHmm");
        return Integer.valueOf(dateFormat.format(banTime));
    }

    private static Integer getDayOfWeek() {
        final Calendar c = Calendar.getInstance();
        c.setTime(new Date(System.currentTimeMillis()));
        final int dayOfWeek = c.get(7);
        return dayOfWeek - 1;
    }

    public static boolean isNight() {
        return getTimeInt() < 1600 || getTimeInt() > 2200;
    }

    private TimeUtil(final int time, final int timeMulti) {
        this.time = time;
        this.timeMulti = timeMulti;
    }

    public int getMulti() {
        return this.timeMulti;
    }

    public int getTime() {
        return this.time;
    }

    public int getTick() {
        return this.time / 50;
    }

    public int getTime(final int multi) {
        return this.time * multi;
    }

    public int getTick(final int multi) {
        return this.getTick() * multi;
    }

    public static String getTime(final long time) {
        return TimeUtil.timeFormat.format(new Date(time));
    }

    public static String getTime2(final long l) {
        if (l < 60L) {
            return l + TimeUtil.sec;
        }
        final int minutes = (int)(l / 60L);
        final int s = 60 * minutes;
        final int secondsLeft = (int)(l - s);
        if (minutes < 60) {
            if (secondsLeft > 0) {
                return minutes + TimeUtil.min + " " + secondsLeft + TimeUtil.sec;
            }
            return minutes + TimeUtil.min;
        }
        else {
            if (minutes < 1440) {
                String time = "";
                final int hours = minutes / 60;
                time = hours + TimeUtil.hr;
                final int inMins = 60 * hours;
                final int left = minutes - inMins;
                if (left >= 1) {
                    time = time + " " + left + TimeUtil.min;
                }
                if (secondsLeft > 0) {
                    time = time + " " + secondsLeft + TimeUtil.sec;
                }
                return time;
            }
            String time = "";
            final int days = minutes / 1440;
            time = days + TimeUtil.day;
            final int inMins = 1440 * days;
            final int leftOver = minutes - inMins;
            if (leftOver >= 1) {
                if (leftOver < 60) {
                    time = time + " " + leftOver + TimeUtil.min;
                }
                else {
                    final int hours2 = leftOver / 60;
                    time = time + " " + hours2 + TimeUtil.hr;
                    final int hoursInMins = 60 * hours2;
                    final int minsLeft = leftOver - hoursInMins;
                    if (leftOver >= 1) {
                        time = time + " " + minsLeft + TimeUtil.min;
                    }
                }
            }
            if (secondsLeft > 0) {
                time = time + " " + secondsLeft + TimeUtil.sec;
            }
            return time;
        }
    }

    static {
        TimeUtil.timeFormat = new SimpleDateFormat("HH:mm:ss");
        TimeUtil.sec = "sek";
        TimeUtil.min = "min";
        TimeUtil.hr = "godz";
        TimeUtil.day = "d";
    }
}

