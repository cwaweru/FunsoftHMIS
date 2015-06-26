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
public class SQLTimeFormat {
    
    /**
     * Creates a new instance of SQLDateFormat
     */
    
    public void SQLDateFormat(){
        
    }
    public static java.sql.Time getSQLTime(java.lang.Object date2Format) {
        
        return(java.sql.Time.valueOf(java.lang.String.format("%1$tH:%1$tM:%1$tS",date2Format)));
        
    }
    
  
}
