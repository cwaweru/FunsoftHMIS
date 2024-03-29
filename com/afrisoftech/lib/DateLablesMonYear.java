/*
 * DateLables.java
 *
 * Created on June 25, 2003, 3:37 PM
 */

package com.afrisoftech.lib;

/**
 *
 * @author  root
 */
public class DateLablesMonYear {
    
    java.util.Calendar calendar4Label;

    java.util.Calendar calinst; 
    
    /** Creates a new instance of DateLables */
    public DateLablesMonYear() {
        
        calendar4Label = null;
        
    }
    
    public DateLablesMonYear(java.util.Calendar calendar2Label) {
       
        calendar4Label = calendar2Label;
        
    }
    
    public java.lang.String getDateLabel() {
        
        java.lang.String date_label = null;
        
        java.lang.String month_now_strs = null;
       
        java.lang.String date_now_strs = null;
        
        java.lang.String year_now_strs = null;
               
        java.lang.String minute_now_strs = null;
        
        java.lang.String hour_now_strs = null;
        
        java.lang.Runtime rt = java.lang.Runtime.getRuntime();
        
        if (calendar4Label.equals(null)) {
        
            calinst = java.util.Calendar.getInstance();
        
        } else {
            
            calinst = calendar4Label;
            
        }
        
        java.util.Date date_now = calinst.getTime();
        
        int date_now_str = date_now.getDate();
        
        int month_now_str = date_now.getMonth();
                
        int year_now_str = date_now.getYear();
        
        int hour_now_str = date_now.getHours();
                
        int minute_now_str = date_now.getMinutes();
        
        int year_now_abs = year_now_str - 100;
        
        if (year_now_abs < 10) {
         
            year_now_strs = "200"+year_now_abs;
            
        } else {
            
            year_now_strs = "20"+year_now_abs;
            
        }
        
        switch (month_now_str) {
            
            case 0 : month_now_strs = "JAN";
            
            break;
                        
            case 1 : month_now_strs = "FEB";
            
            break;
                        
            case 2 : month_now_strs = "MAR";
            
            break;
                        
            case 3 : month_now_strs = "APR";
            
            break;
                        
            case 4 : month_now_strs = "MAY";
            
            break;
                        
            case 5 : month_now_strs = "JUN";
            
            break;
                        
            case 6 : month_now_strs = "JUL";
            
            break;
                        
            case 7 : month_now_strs = "AUG";
            
            break;
                        
            case 8 : month_now_strs = "SEP";
            
            break;
                        
            case 9 : month_now_strs = "OCT";
            
            break;
                        
            case 10 : month_now_strs = "NOV";
            
            break;
                        
            case 11 : month_now_strs = "DEC";
            
            break;
            
            default :         if (month_now_str < 10){
            
                                 month_now_strs = "0"+month_now_str;
            
                                 } else {
            
                                 month_now_strs = ""+month_now_str;
            
                             }
            
        }
        
        if (date_now_str < 10) {
            
            date_now_strs = "0"+date_now_str;
            
        } else {
            
            date_now_strs = ""+date_now_str;
            
        }
          
        if (minute_now_str < 10) {
            
            minute_now_strs = "0"+minute_now_str;
            
        } else {
            
            minute_now_strs = ""+minute_now_str;
            
        }
        
        if (hour_now_str < 10) {
            
            hour_now_strs = "0"+hour_now_str;
            
        } else {
            
            hour_now_strs = ""+hour_now_str;
            
        }

        date_label = month_now_strs+" "+year_now_strs;      
        
        return date_label;
        
    }
    
    public static java.sql.Date getMonthFirstDate(String month, int year){
        java.sql.Date firstDate = null;
        if(month.equalsIgnoreCase("January")){
            firstDate = java.sql.Date.valueOf (year+"-01-01");
        }
        if(month.equalsIgnoreCase("February")){
            firstDate = java.sql.Date.valueOf (year+"-02-01");
        }
        if(month.equalsIgnoreCase("March")){
            firstDate = java.sql.Date.valueOf (year+"-03-01");
        }
        if(month.equalsIgnoreCase("April")){
            firstDate = java.sql.Date.valueOf (year+"-04-01");
        }
        if(month.equalsIgnoreCase("May")){
            firstDate = java.sql.Date.valueOf (year+"-05-01");
        }
        if(month.equalsIgnoreCase("June")){
            firstDate = java.sql.Date.valueOf (year+"-06-01");
        }
        if(month.equalsIgnoreCase("July")){
            firstDate = java.sql.Date.valueOf (year+"-07-01");
        }
        if(month.equalsIgnoreCase("August")){
            firstDate = java.sql.Date.valueOf (year+"-08-01");
        }
        if(month.equalsIgnoreCase("September")){
            firstDate = java.sql.Date.valueOf (year+"-09-01");
        }
        if(month.equalsIgnoreCase("October")){
            firstDate = java.sql.Date.valueOf (year+"-10-01");
        }
        if(month.equalsIgnoreCase("November")){
            firstDate = java.sql.Date.valueOf (year+"-11-01");
        }
        if(month.equalsIgnoreCase("December")){
            firstDate = java.sql.Date.valueOf (year+"-12-01");
        }
        System.err.println("First Date of Month "+firstDate);
        
        return firstDate;
        
    }
    
      public static java.sql.Date getMonthLastDate(String month, int year){
        java.sql.Date lastDate = null;
        
        if(month.equalsIgnoreCase("January")){
            lastDate = java.sql.Date.valueOf (year+"-01-31");
        }
        if(month.equalsIgnoreCase("February")){
            if(year%4 == 0){
                lastDate = java.sql.Date.valueOf (year+"-02-29");
            }else{
                lastDate = java.sql.Date.valueOf (year+"-02-28");
            }
        }
        if(month.equalsIgnoreCase("March")){
            lastDate = java.sql.Date.valueOf (year+"-03-31");
        }
        if(month.equalsIgnoreCase("April")){
            lastDate = java.sql.Date.valueOf (year+"-04-30");
        }
        if(month.equalsIgnoreCase("May")){
            lastDate = java.sql.Date.valueOf (year+"-05-31");
        }
        if(month.equalsIgnoreCase("June")){
            lastDate = java.sql.Date.valueOf (year+"-06-30");
        }
        if(month.equalsIgnoreCase("July")){
            lastDate = java.sql.Date.valueOf (year+"-07-31");
        }
        if(month.equalsIgnoreCase("August")){
            lastDate = java.sql.Date.valueOf (year+"-08-31");
        }
        if(month.equalsIgnoreCase("September")){
            lastDate = java.sql.Date.valueOf (year+"-09-30");
        }
        if(month.equalsIgnoreCase("October")){
            lastDate = java.sql.Date.valueOf (year+"-10-31");
        }
        if(month.equalsIgnoreCase("November")){
            lastDate = java.sql.Date.valueOf (year+"-11-30");
        }
        if(month.equalsIgnoreCase("December")){
            lastDate = java.sql.Date.valueOf (year+"-12-31");
        }
        System.err.println("Last Date of Month "+lastDate);
        
        return lastDate;
        
    }
    
}
