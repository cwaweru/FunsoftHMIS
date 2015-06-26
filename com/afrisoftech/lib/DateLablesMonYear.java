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
    
}
