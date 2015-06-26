/*
 * DatePatterns.java
 *
 * Created on January 8, 2005, 9:30 AM
 */

package com.afrisoftech.tests;

/**
 *
 * @author  root
 */
public class DatePatterns {
    
    /** Creates a new instance of DatePatterns */
    public DatePatterns() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        
        java.util.Date date2Format = calendar.getTime();
        
        com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(date2Format, "MMM/yy");
        
       System.out.println("Formatted Date : ["+ dateFormatter.getDateString()+"]");
        
    }
    
}
