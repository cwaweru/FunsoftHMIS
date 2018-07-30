/*
 * TableColumnTotal.java
 *
 * Created on January 13, 2006, 5:04 PM
 */

package com.afrisoftech.lib;

/**
 *
 * @author  root
 */
public class TableColumnTotal {
    
    private static double columnTotal;
    
    /** Creates a new instance of TableColumnTotal */
    public static double getTableColumnTotal(javax.swing.JTable table, int column2Total) {
        
        columnTotal = 0.00;
        
        for(int i = 0; i < table.getRowCount(); i++){
            
            if(table.getValueAt(i, column2Total) != null){
                
                columnTotal = columnTotal + java.lang.Double.parseDouble(table.getValueAt(i, column2Total).toString().replace(",", ""));
                
            }
        }
        
        return columnTotal;
        
    }
     public static double getGrossTableColumnTotal(javax.swing.JTable table, int column2Total) {
        
        columnTotal = 0.00;
        
        for(int i = 0; i < table.getRowCount(); i++){
            
            if(table.getValueAt(i, column2Total) != null){
                
                if(java.lang.Double.parseDouble(table.getValueAt(i, column2Total).toString().replace(",", "")) > 0.00){
                    
                columnTotal = columnTotal + java.lang.Double.parseDouble(table.getValueAt(i, column2Total).toString().replace(",", ""));
                
                }
            }
        }
        
        return columnTotal;
        
    }  
//      public static int getCheckedTableColumnTotal(javax.swing.JTable table, int column2Total) {
//        
//        int columnTotalChecked = 0;
//        
//        for(int i = 0; i < table.getRowCount(); i++){
//            
//            if(table.getValueAt(i, column2Total) != null){
//                
//                if(java.lang.Double.parseDouble(table.getValueAt(i, column2Total).toString().replace(",", "")) > 0.00){
//                    
//               // columnTotalChecked = columnTotalChecked + java.lang.Double.parseDouble(table.getValueAt(i, column2Total).toString().replace(",", ""));
//                
//                }
//            }
//        }
//        
//        return columnTotalChecked;
//        
//    }     
     
}
