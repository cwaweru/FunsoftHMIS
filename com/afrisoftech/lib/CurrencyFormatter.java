/*
 * CurrencyFormatter.java
 *
 * Created on January 13, 2006, 3:56 PM
 */

package com.afrisoftech.lib;

/**
 *
 * @author  root
 */
public class CurrencyFormatter {
    
    private static java.text.NumberFormat decimalFormat = null;
    
    /** Creates a new instance of CurrencyFormatter */
    public CurrencyFormatter() {
        
    }
    public static java.lang.String getFormattedDouble(double double2format){
        
         
        decimalFormat = java.text.DecimalFormat.getInstance();
        
        
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);
               
        
        
        return decimalFormat.format(double2format);
        
    }
        public static java.lang.String getFormattedInteger(double integer2format){
        
         
        decimalFormat = java.text.DecimalFormat.getInstance();
        
        
        decimalFormat.setMaximumFractionDigits(0);
        decimalFormat.setMinimumFractionDigits(0);
               
        
        
        return decimalFormat.format(integer2format);
        
    }
    
    public static double parseFormattedString(java.lang.String formattedString){
        
        
        decimalFormat = java.text.DecimalFormat.getInstance();
        
        
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);
                
        
        double parsedDouble = 0.00;
        
        try {
        
        java.lang.Number parsedNumber =  decimalFormat.parse(((formattedString == null) ? "0.00" : formattedString));
        
        parsedDouble = parsedNumber.doubleValue();
        
        } catch(java.text.ParseException parseEx){
            
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), parseEx.getMessage(), "Number parse exception", javax.swing.JOptionPane.ERROR_MESSAGE);
        
            parseEx.printStackTrace();
            
        }
        
        return parsedDouble;
        
    }
    
    //    }
    
}
