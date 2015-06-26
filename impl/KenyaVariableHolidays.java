/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package impl;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import support.Holiday;
import support.VariableHolidays;

/**
 *
 * @author afro
 */
public class KenyaVariableHolidays extends VariableHolidays {

    @Override
    protected void addHolidays(int year, Set<Holiday> holidays) {
        Date easterSunday = getEasterSunday(year);
        holidays.add(new Holiday(getGoodFriday(easterSunday)));
        holidays.add(new Holiday(getEasterMonday(easterSunday)));
        holidays.add(new Holiday(get(Index.FIRST, Calendar.MONDAY, Calendar.MAY, year)));
        holidays.add(new Holiday(get(Index.LAST, Calendar.MONDAY, Calendar.MAY, year)));
        holidays.add(new Holiday(get(Index.LAST, Calendar.MONDAY, Calendar.AUGUST, year)));
        Holiday labourDay = new Holiday(Calendar.MAY, 1);
        if (labourDay.isWeekend(year)) {
            holidays.add(new Holiday(Calendar.MAY, 3));
        }
        Holiday madarakaDay = new Holiday(Calendar.JUNE, 1);
        if (madarakaDay.isWeekend(year)) {
            holidays.add(new Holiday(Calendar.JUNE, 3));
        }
        Holiday mashujaaDay = new Holiday(Calendar.OCTOBER, 20);
        if (mashujaaDay.isWeekend(year)) {
            holidays.add(new Holiday(Calendar.OCTOBER, 22));
        }
        Holiday jamuhuriDay = new Holiday(Calendar.DECEMBER, 12);
        if (jamuhuriDay.isWeekend(year)) {
            holidays.add(new Holiday(Calendar.DECEMBER, 14));
        }
        Holiday christmasDay = new Holiday(Calendar.DECEMBER, 25);
        if (christmasDay.isWeekend(year)) {
            holidays.add(new Holiday(Calendar.DECEMBER, 27));
        }
        Holiday boxingDay = new Holiday(Calendar.DECEMBER, 26);
        if (boxingDay.isWeekend(year)) {
            holidays.add(new Holiday(Calendar.DECEMBER, 28));
        }
        
    }

}
