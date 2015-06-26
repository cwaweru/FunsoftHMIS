/*
 * TableColumnComboBox.java
 *
 * Created on July 11, 2007, 2:57 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.afrisoftech.cbx;
/**
 *
 * @author Dave Rono
 */
public class TableColumnComboBox extends javax.swing.DefaultCellEditor{
        javax.swing.JTable table2Edit = null;
        javax.swing.JComboBox cbx =new javax.swing.JComboBox();
    /** Creates a new instance of TableColumnComboBox */
    public TableColumnComboBox(javax.swing.ComboBoxModel cbxModel, javax.swing.JTable table){
        
        super(new javax.swing.JComboBox());
        
        cbx.setModel(cbxModel);
        
        editorComponent = cbx;

        table2Edit = table;

        setClickCountToStart(1); //This is usually 1 or 2.
    // Must do this so that editing stops when appropriate.
        cbx.addFocusListener(new java.awt.event.FocusAdapter(){
            
            public void lostFocus(java.awt.event.FocusEvent evt) {
                
                fireEditingStopped();                
            }            
        });
        
    }
    protected void fireEditingStopped(){
        
        super.fireEditingStopped();
        
        assignEditorValue(table2Edit);
        
        System.out.println("Editing stopped!");
        
    }
    protected void assignEditorValue(javax.swing.JTable table){
                
            java.lang.String selectedValue = cbx.getSelectedItem().toString();            
            
            table.setValueAt(selectedValue,  table.getSelectedRow(),  table.getSelectedColumn());
        
    }  
    /**
     * Method to return the value of the CellEditor component.
     * The value is assigned to the cell on whose DateCellEditor is derived.
     */
    public java.awt.Component getTableCellEditorComponent(javax.swing.JTable table,
            Object value,
            boolean isSelected,
            int row,
            int column) {
        return editorComponent;
    }   
}
