/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package impl;

import java.util.Date;
import java.util.Set;
import support.Holiday;
import support.VariableHolidays;

/**
 *
 * @author afro
 */
public class FranceVariableHolidays extends VariableHolidays {

    @Override
    protected void addHolidays(int year, Set<Holiday> holidays) {
        Date easterSunday = getEasterSunday(year);
        holidays.add(new Holiday(getEasterMonday(easterSunday)));
        holidays.add(new Holiday(getAscensionThursday(easterSunday)));
        holidays.add(new Holiday(getPentecostMonday(easterSunday)));
    }

}