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
public class EnglandFixedHolidays extends FixedHolidays {

    @Override
    protected void addHolidays(Set<Holiday> holidays) {
        holidays.add(new Holiday(Calendar.JANUARY, 1));
        holidays.add(new Holiday(Calendar.DECEMBER, 25));
        holidays.add(new Holiday(Calendar.DECEMBER, 26));
    }

}
