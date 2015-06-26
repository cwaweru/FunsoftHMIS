/*
 * XmlSerializer.java
 *
 * Created on March 27, 2004, 3:36 PM
 */

package com.afrisoftech.dbadmin;

/**
 *
 * @author  root
 */
public class XmlSerializer {
    
    java.io.BufferedOutputStream xmlIOStream = null;
    
    java.lang.String refDTDFile = null;
    
    java.lang.String serializedTable = null;
    
    javax.swing.JTable export2Table = null;
    
    java.lang.Object[] tableColumns = null;
    
    /** Creates a new instance of XmlSerializer */
    public XmlSerializer(javax.swing.JTable xmlTable2Export, java.io.BufferedOutputStream xmlioStream, java.lang.String refDtdString, java.lang.String serializedFileName) {
        
        xmlIOStream = xmlioStream;
        
        refDTDFile = refDtdString;
        
        serializedTable = serializedFileName;
        
        export2Table = xmlTable2Export;
        
        //        serializeTable2XML();
        
    }
    
    public void serializeTable2XML() {
        
        com.lowagie.text.Document document = null;
        
        com.lowagie.text.Table table = null;
        
        int columnCount;
        
        int rowCount;
        
        columnCount = export2Table.getColumnCount();
        
        rowCount = export2Table.getRowCount();
        
        java.lang.Object[] tableColumns = this.getTableColumns(export2Table);
        
        try {
            
            document = new com.lowagie.text.Document();
            
            com.lowagie.text.xml.XmlWriter.getInstance(document, xmlIOStream, refDTDFile);
            
            document.open();
            
            com.lowagie.text.Paragraph paragraph = new com.lowagie.text.Paragraph("Serialized XML file for table ["+serializedTable.toUpperCase()+"]");
            
            document.add(paragraph);
            
            table = new com.lowagie.text.Table(columnCount);
            
            table.setBorderWidth(1);
            
            table.setBackgroundColor(java.awt.Color.BLUE);
            
            table.setPadding(5);
            
            table.setCellspacing(5);
            
            for (int i = 0; i < columnCount; i++) {
                
                javax.swing.table.TableColumn xmlTableColumn = (javax.swing.table.TableColumn)tableColumns[i];
                
                com.lowagie.text.Cell cell = new com.lowagie.text.Cell(xmlTableColumn.getHeaderValue().toString());
                
                cell.setHeader(true);
                
                table.addCell(cell);
                
                table.endHeaders();
                
            }
            
            table.setBackgroundColor(java.awt.Color.WHITE);
            
            for (int j = 0; j < rowCount; j++) {
                
                for (int k = 0; k < columnCount; k++) {
                    
                    com.lowagie.text.Cell cell = null;
                    
                    if (export2Table.getValueAt(j, k) != null) {
                        
                        cell = new com.lowagie.text.Cell(export2Table.getValueAt(j, k).toString());
                        
                    } else if (export2Table.getValueAt(j, k) == "") {
                        
                        cell = new com.lowagie.text.Cell("-");
                        
                    } else {
                        
                        cell = new com.lowagie.text.Cell("-");
                        
                    }
                    
                    table.addCell(cell);
                }
                
                
            }
            
            document.add(table);
            
        } catch(com.lowagie.text.DocumentException docExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), docExec.getMessage());
            
        }
        
        document.close();
        
        try {
            
            xmlIOStream.close();
            
        } catch (java.io.IOException ioExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioExec.getMessage());
            
        }
        
    }
    
    private java.lang.Object[] getTableColumns(javax.swing.JTable exportedTable) {
        
        java.util.Vector columnVector = new java.util.Vector(1,1);
        
        java.lang.Object[] columnArray = null;
        
        java.util.Enumeration columnsEnumeration = exportedTable.getColumnModel().getColumns();
        
        while (columnsEnumeration.hasMoreElements()) {
            
            columnVector.addElement(columnsEnumeration.nextElement());
            
        }
        
        columnArray = columnVector.toArray();
        
        return columnArray;
    }
}
