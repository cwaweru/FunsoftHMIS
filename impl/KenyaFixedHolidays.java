/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package impl;

import java.util.Calendar;
import java.util.Set;
import support.FixedHolidays;
import support.Holiday;

/**
 *
 * @author afro
 */
public class KenyaFixedHolidays extends FixedHolidays {

    @Override
    protected void addHolidays(Set<Holiday> holidays) {
        holidays.add(new Holiday(Calendar.JANUARY, 1));
        holidays.add(new Holiday(Calendar.MAY, 1));
        holidays.add(new Holiday(Calendar.JUNE, 1));
        holidays.add(new Holiday(Calendar.OCTOBER, 20));
        holidays.add(new Holiday(Calendar.DECEMBER, 12));
        holidays.add(new Holiday(Calendar.DECEMBER, 25));
        holidays.add(new Holiday(Calendar.DECEMBER, 26));
    }

}
