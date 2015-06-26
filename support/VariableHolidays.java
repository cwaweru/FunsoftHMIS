/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package support;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author afro
 */
public abstract class VariableHolidays implements Holidays {

    protected static Date getAscensionThursday(Date easterSunday) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(easterSunday);
        calendar.add(Calendar.DAY_OF_MONTH, 39);
        return calendar.getTime();
    }

    protected static Date getEasterMonday(Date easterSunday) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(easterSunday);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    protected static Date getGoodFriday(Date easterSunday) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(easterSunday);
        calendar.add(Calendar.DAY_OF_MONTH, -2);
        return calendar.getTime();
    }

    protected static Date getEasterSunday(int year) {
        Calendar calendar = Calendar.getInstance();
        int initialYear = year;
        if (year < 1900) {
            year += 1900;
        }
        int a = year % 19;
        int b = year / 100;
        int c = year % 100;
        int d = b / 4;
        int e = b % 4;
        int f = (b + 8) / 25;
        int g = (b - f + 1) / 3;
        int h = (19 * a + b - d - g + 15) % 30;
        int i = c / 4;
        int j = c % 4;
        int k = (32 + 2 * e + 2 * i - h - j) % 7;
        int l = (a + 11 * h + 22 * k) / 451;
        int m = (h + k - 7 * l + 114) % 31;
        int month = (h + k - 7 * l + 114) / 31 - 1;
        int day = m + 1;
        calendar.set(initialYear, month, day);
        return calendar.getTime();
    }

    protected enum Index {

        FIRST(1),
        SECOND(2),
        THIRD(3),
        FOURTH(4),
        LAST(null);

        private Integer value;

        private Index(Integer value) {
            this.value = value;
        }

        private boolean is(int count) {
            return value != null && value == count;
        }

    }

    protected static Date get(Index index, int dayOfWeek, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        int count = 0;
        Date last = null;
        do {
            if (calendar.get(Calendar.DAY_OF_WEEK) == dayOfWeek) {
                count++;
                last = calendar.getTime();
                if (index.is(count)) {
                    return last;
                }
            }
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        } while (calendar.get(Calendar.MONTH) == month);
        if (index.equals(Index.LAST)) {
            return last;
        }
        return null;
    }

    protected static Date getPentecostMonday(Date easterSunday) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(easterSunday);
        calendar.add(Calendar.DAY_OF_MONTH, 50);
        return calendar.getTime();
    }

    private final Map<Integer, Set<Holiday>> holidays;

    public VariableHolidays() {
        holidays = new HashMap<Integer, Set<Holiday>>();
    }

    protected abstract void addHolidays(int year, Set<Holiday> holidays);

    @Override
    public final boolean contains(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        if (!holidays.containsKey(year)) {
            Set<Holiday> yearHolidays = new HashSet<Holiday>();
            addHolidays(year, yearHolidays);
            holidays.put(year, Collections.unmodifiableSet(yearHolidays));
        }
        Set<Holiday> yearHolidays = holidays.get(year);
        Holiday holiday = new Holiday(date);
        return yearHolidays.contains(holiday);
    }

}
