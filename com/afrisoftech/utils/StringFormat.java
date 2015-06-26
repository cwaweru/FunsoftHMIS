/*
 * StringFormat.java
 *
 * Created on September 13, 2006, 9:46 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.afrisoftech.utils;

/**
 *
 * @author root
 */
public class StringFormat {
    
    /** Creates a new instance of StringFormat */
    
    public void StringFormat(){
        
    }
    public static String StringFormat(java.util.Date date2Format) {
        
        return(java.lang.String.format("%1$tH:%1$tM:%1$tS",date2Format));
        
    }
    
    public static void main(String args[]){
        
        System.out.println(StringFormat.StringFormat(java.util.Calendar.getInstance().getTime()));
        
    }
  
}
