/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.afrisoftech.lib;

import java.sql.SQLException;
//import org.openide.util.Exceptions;

/**
 *
 * @author Charles
 */
public class VisitIDFactory {
    
    public static String getVisitID(java.sql.Connection connectDB, String invoiceNo){
       
        String visitID = null;
        
        java.sql.PreparedStatement pstmtVisitID;
        try {
            pstmtVisitID = connectDB.prepareStatement("SELECT visit_id FROM hp_patient_card WHERE invoice_no = ? AND visit_id is not null ORDER BY 1 LIMIT 1");
        
            pstmtVisitID.setString(1, invoiceNo);
            
            java.sql.ResultSet rsetVisitID = pstmtVisitID.executeQuery();
            
            while(rsetVisitID.next()){
                
            visitID = rsetVisitID.getString(1);
                
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //Exceptions.printStackTrace(ex);
        }
        
        return visitID;
        
    } 
    
}
