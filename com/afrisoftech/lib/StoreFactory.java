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
 * @author SPL7
 */
public class StoreFactory {
    
    public static String getStoreName(java.sql.Connection connectDB){
        
        String storeName = null;
        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT store_gl_account FROM store_allocation WHERE user_name = current_user and type ilike 'bill'");
            
            java.sql.ResultSet rset = pstmt.executeQuery();
           
            while(rset.next()){
                
                storeName = rset.getString(1);
                
            }
        
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(StoreFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return storeName;
        
    }
       
    public static String getUserStoreName(java.sql.Connection connectDB){
        
        String storeName = null;
        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT stores FROM store_allocation WHERE user_name = current_user and type ilike 'bill'");
            
            java.sql.ResultSet rset = pstmt.executeQuery();
           
            while(rset.next()){
                
                storeName = rset.getString(1);
                
            }
        
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(StoreFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return storeName;
        
    } 
}
