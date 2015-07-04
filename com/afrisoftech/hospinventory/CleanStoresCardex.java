/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.afrisoftech.hospinventory;

import java.sql.SQLException;
import org.openide.util.Exceptions;

/**
 *
 * @author Charles
 */
public class CleanStoresCardex {
    
    public static void cleanStoresCardex(java.sql.Connection connectDB, java.util.Date resetDate, String storeName){
        
        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT description FROM st_sub_stores WHERE store_name = ?");
        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        
    }
    
}
