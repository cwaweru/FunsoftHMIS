/*
 * ListModel.java
 *
 * Created on October 1, 2006, 4:54 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.afrisoftech.lib;

import com.sun.star.lib.uno.environments.java.java_environment;

/**
 *
 * @author funsoft
 */
public class ListModel {
    java.sql.Connection connectDB;
    /** Creates a new instance of ListModel */
    public ListModel() {
        
    }
    
    public static javax.swing.ListModel listModel(java.sql.Connection connDB, String sqlString){
        
        javax.swing.DefaultListModel listModel = new javax.swing.DefaultListModel();
        try {
        java.sql.PreparedStatement pstmt = connDB.prepareStatement(sqlString);
        
        java.sql.ResultSet rset = pstmt.executeQuery();
        
        while(rset.next()){
            
                    listModel.addElement(rset.getObject(1));
            
        }
        } catch(java.sql.SQLException sqlEx){
            sqlEx.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), sqlEx.getMessage());
        }
        return listModel;
        
    } 
    
}
