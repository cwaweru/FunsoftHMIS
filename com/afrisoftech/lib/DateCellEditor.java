/*
 * DateCellEditor.java
 *
 * Created on January 1, 2005, 6:14 PM
 */

package com.afrisoftech.lib;

/**
 *
 * @author Charles Waweru
 */
public class DateCellEditor extends javax.swing.DefaultCellEditor {
    
    java.util.Date currentDate = null;
    
    java.text.DateFormat dateFormat = null;
    
    java.util.Calendar calendarInstance = java.util.Calendar.getInstance();
    
    javax.swing.JTable table2Edit = null;
    /**
     * @param datePicker : This is the date picker component passed to the DateCellEditor as super class for the editor.
     * @param table : The table to which an column editor has been set as a DateCellEditor.
     *
     * Creates a new instance of DateCellEditor 
     */
    public DateCellEditor(kiwi.ui.DateChooserField datePicker, javax.swing.JTable table) {
        
        super(new javax.swing.JCheckBox());
        
        currentDate = calendarInstance.getTime();
        
        datePicker.setDate(currentDate);
        
        datePicker.setFont(datePicker.getFont().deriveFont(java.awt.Font.PLAIN));
        
        editorComponent = datePicker;
        
        table2Edit = table;
        
        setClickCountToStart(1); //This is usually 1 or 2.
        
        //Must do this so that editing stops when appropriate.
        
        datePicker.addFocusListener(new java.awt.event.FocusAdapter(){
            
            public void lostFocus(java.awt.event.FocusEvent evt) {
                
                fireEditingStopped();
                
            }
            
        });
    }
        public DateCellEditor(com.afrisoftech.lib.DatePicker datePicker, javax.swing.JTable table) {
        
        super(new javax.swing.JCheckBox());
        
        currentDate = calendarInstance.getTime();
        
        datePicker.setDate(currentDate);
        
        datePicker.setFont(datePicker.getFont().deriveFont(java.awt.Font.PLAIN));
        
        editorComponent = datePicker;
        
        table2Edit = table;
        
        setClickCountToStart(1); //This is usually 1 or 2.
        
        //Must do this so that editing stops when appropriate.
        
        datePicker.addFocusListener(new java.awt.event.FocusAdapter(){
            
            public void lostFocus(java.awt.event.FocusEvent evt) {
                
                fireEditingStopped();
                
            }
            
        });
    }
    /**
     * This method is reponsible for firing a time out notice for cell editing.
     * It notifies the table that editing on the cell has actually stopped and the resultant value on the editor
     * is to be returned to the table cell.
     *
     */
    protected void fireEditingStopped(){
        
        super.fireEditingStopped();
        
        assignEditorValue(table2Edit);
        
        System.out.println("Editing stopped!");
        
    }
    /**
     * The method is responsible for assigning the value returned by the date editor
     * component to the respective cell whose value is being edited.
     */
    protected void assignEditorValue(javax.swing.JTable table){

            dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.LONG);//MEDIUM);
    
            java.util.Date dateReturned = ((com.toedter.calendar.JDateChooser)editorComponent).getDate();
            
            java.lang.String dateString = dateFormat.format(dateReturned);
            
            table.setValueAt(dateString,  table.getSelectedRow(),  table.getSelectedColumn());

//        System.out.println("Editing stopped!");
        
    }
    
    /**
     * Method to return the value of the CellEditor component.
     * The value is assigned to the cell on whose DateCellEditor is derived.
     */
    public Object getCellEditorValue() {
        
        return currentDate;
        
    }
    
    public java.awt.Component getTableCellEditorComponent(javax.swing.JTable table,
            Object value,
            boolean isSelected,
            int row,
            int column) {
        //  editorComponent.
        return editorComponent;
    }
    
}

//}
