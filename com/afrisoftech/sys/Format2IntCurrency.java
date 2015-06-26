// Class :  Format2Currency.java
// Author : Charles Waweru
// Revision : 1.1a
// Object : To convert a double number into currency format.
// BUGS : Has problems parsing incorrect numbers correctly.

package com.afrisoftech.sys;

public class Format2IntCurrency {
    
    java.lang.String formattedCurrNo = null;
    
    public java.lang.String Format2IntCurrency(java.lang.String number2format) {
        
        //            number2format = null;
        
        if (number2format == null || number2format.equalsIgnoreCase("0")) {
            
            formattedCurrNo = "0";
            
            return formattedCurrNo;
            
        } else {
            
            java.text.DecimalFormat nf = (java.text.DecimalFormat)java.text.NumberFormat.getInstance();
            
            nf.setMinimumFractionDigits(0);
            
            nf.setMaximumFractionDigits(0);
            
            try {
                
                java.lang.Number number = nf.parse(number2format);
                
                formattedCurrNo = nf.format(number);
                
                System.out.println(formattedCurrNo);
                
            }catch(java.text.ParseException exec) {
                
                System.out.println(exec.getMessage());
                
            }
        }
        return formattedCurrNo;
        
    }
    
    public static void main(java.lang.String args[]) {
        
        if (args.length < 1) {
            
            new Format2Currency().Format2Currency(new java.lang.String("0"));
            
        } else {
            
            
            new Format2Currency().Format2Currency(args[0]);
            
        }
        
    }
    
}
