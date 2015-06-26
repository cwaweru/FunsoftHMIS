/*
 * SQLDateFormat.java
 *
 * Created on September 13, 2006, 9:46 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.afrisoftech.lib;

/**
 *
 * @author root
 */
public class SQLDateFormat {
    
    /**
     * Creates a new instance of SQLDateFormat
     */
    
    public void SQLDateFormat(){
        
    }
    public static java.sql.Date getSQLDate(java.util.Date date2Format) {
        
        return(java.sql.Date.valueOf(java.lang.String.format("%1$tY-%1$tm-%1$te",date2Format)));
        
    }
    
    
    public static java.sql.Date getSQLDate(java.util.Date date2Format, java.lang.String dateFormat) {
        
        return(java.sql.Date.valueOf(java.lang.String.format(dateFormat,date2Format)));
        
    }    
  
}
