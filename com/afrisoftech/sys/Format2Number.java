// Class :  Format2Currency.java
// Author : Charles Waweru
// Revision : 1.1a
// Object : To convert a double number into currency format.
// BUGS : Has problems parsing incorrect numbers correctly.

package com.afrisoftech.sys;

public class Format2Number {
    
    java.lang.Number number;
    
    java.lang.Number formattedCurrNo = null;
    
    public java.lang.Number Format2Number(java.lang.String number2format) {
        
        //            number2format = null;
        
        if (number2format == null) {
            
            //formattedCurrNo = new java.lang.Number(0.00);
            
            return formattedCurrNo;
            
        } else {
            
            java.text.DecimalFormat nf = (java.text.DecimalFormat)java.text.NumberFormat.getInstance();
            
            nf.setMinimumFractionDigits(2);
            
            nf.setMaximumFractionDigits(2);
            
            try {
                
                number = nf.parse(number2format);
                
                //         formattedCurrNo = nf.format(number);
                
                System.out.println(formattedCurrNo);
                
            }catch(java.text.ParseException exec) {
                
                System.out.println(exec.getMessage());
                
            }
        }
        return number;
        
    }
    
    public static void main(java.lang.String args[]) {
        
        if (args.length < 1) {
            
            new Format2Number().Format2Number(new java.lang.String("0"));
            
        } else {
            
            
            new Format2Number().Format2Number(args[0]);
            
        }
        
    }
    
}
