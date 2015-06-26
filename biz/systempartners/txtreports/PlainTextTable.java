/*
 * PlainTextTableModel.java
 *
 * Created on August 28, 2005, 1:57 PM
 *
 */

package biz.systempartners.txtreports;

/**
 *
 * @author Charles Waweru <cwaweru@systempartners.biz>
 *
 */
public class PlainTextTable extends javax.swing.table.DefaultTableModel {
    
    public int currentColumn = 1;
    
    public int lineCount = 0;
   
    public java.util.Vector rowVector = new java.util.Vector(1,1);
    
    public int columnCount = 0;
    
    public int colMatrix[] = null;
    
    public String colHeaderModel[] = null;
    
    public int columnHorizontalAlignments[] = null;
    
    java.lang.String emptyString = null;
    /** Creates a new instance of PlainTextTableModel */
    public PlainTextTable(int currentColumnCount) {
        
        super(0, currentColumnCount);
        
        columnCount = currentColumnCount;
        
    }
    
    public void addCell(Object phrase){
        
        if (currentColumn < columnCount){
            
            rowVector.addElement(phrase);
            
            currentColumn++;
            
        } else if(currentColumn == columnCount){
            
            rowVector.addElement(phrase);
            
            this.addRow(rowVector);
            
            currentColumn = 1;
            
            rowVector = new java.util.Vector(1,1);
            
        }
        
    }
    
    public int getLineCount(){
        
        return this.getRowCount();
        
    }
    
    public void setParameters(int tableColMatrix[], String newColHeaderModel[], int newColumnHorizontalAlignments[]){
        
        colMatrix = tableColMatrix;
        
        colHeaderModel = newColHeaderModel;
        
        columnHorizontalAlignments = newColumnHorizontalAlignments;
        
        
    }
    
    public int[] getColMatrix(){
       
        return colMatrix;
        
    }
    
    public String[] getColHeaderModel(){
       
        return colHeaderModel;
        
    }
    
    public int[] getColumnHorizontalAlignments(){
       
        return columnHorizontalAlignments;
    
    }
}
