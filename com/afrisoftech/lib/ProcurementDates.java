/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author saleem
 */
public class ProcurementDates {
   
    public ProcurementDates(){

    }
    
    public static java.util.Date getTenderOpeningDate(java.sql.Connection connectDB, java.util.Date current_date, Integer days){
        java.util.Date tenderOpeningDate = null  ;
        try {
            java.sql.PreparedStatement pst = connectDB.prepareStatement("select "+current_date+"+"+days+"");
            
            System.out.println("select current_date - "+days+"");
            java.sql.ResultSet rset1 = pst.executeQuery();

                  while (rset1.next()){
                      tenderOpeningDate = SQLDateFormat.getSQLDate(rset1.getDate(1));
                      System.out.println(tenderOpeningDate);
                      }
              } 
        
             catch (SQLException ex) {
                  ex.printStackTrace();
        }   
        return SQLDateFormat.getSQLDate(tenderOpeningDate);
}
    
    
    
    
    
    
    
    
    
    
    
}
