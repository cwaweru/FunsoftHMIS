/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

import java.sql.SQLException;
import org.openide.util.Exceptions;

/**
 *
 * @author Charles
 */
public class WardGLAccountsFactory {
      
    public static String getBedChargesGLAccount(java.sql.Connection connectDB, String wardName){
        
        String bedGLAccount = null;
        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT revcode FROM hp_wards WHERE upper(trim(ward_name)) = upper(trim(?))");
        
            pstmt.setString(1, wardName);
            
            java.sql.ResultSet rset = pstmt.executeQuery();
            
            while(rset.next()){
               
                bedGLAccount = rset.getString(1);
                
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        
        
        return bedGLAccount;
    }
      
    public static String getAdmissionChargesGLAccount(java.sql.Connection connectDB, String wardName){
        
        String admissionGLAccount = null;
        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT admission_revcode FROM hp_wards WHERE upper(trim(ward_name)) = upper(trim(?))");
        
            pstmt.setString(1, wardName);
            
            java.sql.ResultSet rset = pstmt.executeQuery();
            
            while(rset.next()){
               
                admissionGLAccount = rset.getString(1);
                
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        
        
        return admissionGLAccount;
    }
    
    public static String getNursingChargesGLAccount(java.sql.Connection connectDB, String wardName){
        
        String nursingGLAccount = null;
        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT nursing_revcode FROM hp_wards WHERE upper(trim(ward_name)) = upper(trim(?))");
        
            pstmt.setString(1, wardName);
            
            java.sql.ResultSet rset = pstmt.executeQuery();
            
            while(rset.next()){
               
                nursingGLAccount = rset.getString(1);
                
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        
        
        return nursingGLAccount;
    }
    
}
