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
 * @author root
 */
public class StaffDetails {
    
    public static String getStaffName(java.sql.Connection connectDB, String staffNo){
        
        java.lang.String staffNames = null;
        
        try {
            java.sql.PreparedStatement pstmtStaffName = connectDB.prepareStatement("SELECT first_name, middle_name, last_name FROM master_file WHERE employee_no = ?");
            
            pstmtStaffName.setString(1, staffNo);
            
            java.sql.ResultSet rsetStaffName = pstmtStaffName.executeQuery();
            
            while(rsetStaffName.next()){
                
                staffNames = rsetStaffName.getString(1)+" "+rsetStaffName.getString(2)+" "+rsetStaffName.getString(3);
                
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        return staffNames;
        
    }
    
        public static String getStaffEmail(java.sql.Connection connectDB, String staffNo){
        
        java.lang.String staffEmails = null;
        
        try {
            java.sql.PreparedStatement pstmtStaffEmail = connectDB.prepareStatement("SELECT email_address FROM master_file WHERE employee_no = ? AND email_address IS NOT NULL ORDER BY 1");
            
            pstmtStaffEmail.setString(1, staffNo);
            
            java.sql.ResultSet rsetStaffEmail = pstmtStaffEmail.executeQuery();
            
            while(rsetStaffEmail.next()){
                
                staffEmails = rsetStaffEmail.getString(1); //+" "+rsetStaffEmail.getString(2)+" "+rsetStaffEmail.getString(3);
                
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        return staffEmails;
        
    }
        
        public static String logEmailSent(java.sql.Connection connectDB, String email,String party,String type, String outcome){
        
        java.lang.String staffEmails = null;
        
        try {
            java.sql.PreparedStatement pstmtStaffEmail = connectDB.prepareStatement("INSERT INTO pb_emails( email_address, email_type, out_come,counter_party)\n" +
                "    VALUES (?, ?, ?,?)");
            
            pstmtStaffEmail.setString(1, email);
            pstmtStaffEmail.setString(2, type);
            pstmtStaffEmail.setString(3, outcome);
            pstmtStaffEmail.setString(4, party);
            
            //pstmtStaffEmail.executeQuery();
              pstmtStaffEmail.executeUpdate();
            
           
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        return staffEmails;
        
    }
    
}
