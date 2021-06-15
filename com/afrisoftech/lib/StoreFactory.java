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
    
    public static double getSchemeDrugsMarkup(java.sql.Connection connectDB){
        
        double markup = 1;
        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT scheme_drugs_markup FROM pb_patient_names ");
            
            java.sql.ResultSet rset = pstmt.executeQuery();
           
            while(rset.next()){
                
                markup = rset.getDouble(1);
                
            }
        
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(StoreFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return markup;
        
    } 
    
    public static boolean getDrugsSource(java.sql.Connection connectDB){
        
        boolean source = true;
        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT use_main_store_items FROM pb_patient_names ");
            
            java.sql.ResultSet rset = pstmt.executeQuery();
           
            while(rset.next()){
                
                source = rset.getBoolean(1);
                
            }
        
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(StoreFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return source;
        
    } 
    
    
    public static boolean checkPrintPrescription(java.sql.Connection connectDB){
        
        boolean print = false;
        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT print_prescription FROM pb_patient_names ");
            
            java.sql.ResultSet rset = pstmt.executeQuery();
           
            while(rset.next()){
                
                print = rset.getBoolean(1);
                
            }
        
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(StoreFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return print;
        
    } 
    
    public static String getMainStoreClassificationCode(java.sql.Connection connectDB,String store){
        
        String classification = "";
        String code = "";
        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement(" SELECT classification FROM st_stores WHERE upper(store_name) = '"+store.toUpperCase()+"' ");
            
            java.sql.ResultSet rset = pstmt.executeQuery();
           
            while(rset.next()){
                
                code = rset.getString(1);
                
            }
            if(code != null )
                classification = "'"+code.replace(",", "','")+"'";
            else
                classification = "''";
            System.err.println("------"+classification);
        
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(StoreFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return classification;
        
    } 
}
