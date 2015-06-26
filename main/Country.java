/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import impl.KenyaFixedHolidays;
import impl.KenyaVariableHolidays;
import impl.KenyaBFixedHolidays;
import impl.KenyaBVariableHolidays;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import support.FixedHolidays;
import support.HolidayException;
import support.Holidays;
import support.VariableHolidays;

/**
 *
 * @author afro
 */
public enum Country {

    KENYAB(new KenyaBFixedHolidays(), new KenyaBVariableHolidays()),

    KENYA(new KenyaFixedHolidays(), new KenyaVariableHolidays());

    private Holidays fixedHolidays;

    private Holidays variableHolidays;

    private Country(FixedHolidays fixedHolidays, VariableHolidays variableHolidays) {
        this.fixedHolidays = fixedHolidays;
        this.variableHolidays = variableHolidays;
    }

    public int getBusinessDayCount(Date d1, Date d2) throws HolidayException {
        Calendar calendar = Calendar.getInstance();
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            d1 = formatter.parse(formatter.format(d1));
            d2 = formatter.parse(formatter.format(d2));
        } catch (ParseException ignore) {
        }
        if (!isBusinessDay(d1) || !isBusinessDay(d2)) {
            throw new HolidayException("Dates must both be business days");
        }
        int businessDayCount = 0;
        Date min = d1.before(d2) ? d1 : d2;
        Date max = min.equals(d2) ? d1 : d2;
        calendar.setTime(min);
        while (calendar.getTime().before(max)) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            if (isBusinessDay(calendar.getTime())) {
                businessDayCount++;
            }
        }
        return businessDayCount;
    }

    public boolean isBusinessDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
            return false;
        } else if (fixedHolidays.contains(date)) {
            return false;
        } else if (variableHolidays.contains(date)) {
            return false;
        }
        return true;
    }
    
    
}
