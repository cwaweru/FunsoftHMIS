/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package support;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author afro
 */
public final class Holiday {

    private final int day;
    private final int month;

    public Holiday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    public Holiday(int month, int day) {
        this.month = month;
        this.day = day;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Holiday)) {
            return false;
        }
        Holiday holiday = (Holiday) obj;
        return holiday.month == month && holiday.day == day;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public Date toDate(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    public boolean isWeekend(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(toDate(year));
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new int[] { month, day });
    }

}
