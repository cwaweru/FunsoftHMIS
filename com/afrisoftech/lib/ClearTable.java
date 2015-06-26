/*
 * ClearTable.java
 *
 * Created on May 8, 2013, 7:24 PM
 */

package com.afrisoftech.lib;

import javax.swing.JOptionPane;






/**
 * 
 * @author  saleem
 */
public class ClearTable {
    //OceanTheme
    /** Creates a new instance of ClearTable */
    public ClearTable() {
    }
    
    public static void clearthisTable(javax.swing.JTable tableName){
        
       for (int k = 0; k < tableName.getModel().getRowCount(); k++) {
            for (int r = 0; r < tableName.getModel().getColumnCount(); r++) {
                tableName.getModel().setValueAt(null, k, r);
            }
        }
        
        
    }
    
      public static void removeSelectedRows(javax.swing.JTable tableName){
        
        int rows2Delete = tableName.getSelectedRowCount();
        
        int[] selectedRows = tableName.getSelectedRows();
        
        if (rows2Delete < 1) {
            
            java.awt.Toolkit.getDefaultToolkit().beep();
            
            javax.swing.JOptionPane.showMessageDialog(null, "There are no selected rows to delete!");
            
        } else {
            
            if (rows2Delete > 1) {
                
                for (int i = 0; i < selectedRows.length; i++) {
                    
                    
                    
                    javax.swing.table.DefaultTableModel defTableModel = (javax.swing.table.DefaultTableModel)tableName.getModel();
                    
                    defTableModel.removeRow(selectedRows[i]);
                    
                }
                
                
                
            } else {
                
                javax.swing.table.DefaultTableModel defTableModel = (javax.swing.table.DefaultTableModel)tableName.getModel();
                
                defTableModel.removeRow(tableName.getSelectedRow());
            }
        }
        
        
    }
    
    
    
}
