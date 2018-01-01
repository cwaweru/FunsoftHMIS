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
public class TransactionNumber {
    
    public static String getTransactionNumber(java.sql.Connection connectDB){
        
        String transactionNumber = null;
        
        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT nextval('transaction_no_seq')");
        
            java.sql.ResultSet rset = pstmt.executeQuery();
            
            while(rset.next()){
                
                transactionNumber = rset.getString(1);
                
            }
            
        } catch (SQLException ex) {
           
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
           
                        ex.printStackTrace();             //Exceptions.printStackTrace(ex);
            
        }
        
        return transactionNumber;
        
    }
    
}
