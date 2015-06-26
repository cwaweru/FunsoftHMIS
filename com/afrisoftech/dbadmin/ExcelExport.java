/*
 * ExcelExport.java
 *
 * Created on March 26, 2004, 9:35 AM
 */

package com.afrisoftech.dbadmin;

/**
 *
 * @author  root
 */
public class ExcelExport implements java.lang.Runnable {
    
    java.lang.String tableName;
    
    boolean export2EXCEL = true;
    
    
    java.lang.String fileString = null;
    
    org.w3c.dom.Document initEXCELDoc = null;
    
    java.io.BufferedOutputStream excelOutputStream = null;
    
    java.lang.Thread threadExport2EXCEL = null;
    
    javax.swing.JTable export2EXCELTable = null;
    
    int dataTypeModel[];
    
    
    javax.swing.table.DefaultTableModel export2EXCELTableModel = null;
    /** Creates a new instance of ExcelExport */
    public ExcelExport(javax.swing.JTable excelTable2Export, java.lang.String name2ExcelTable) {
        
        fileString = name2ExcelTable;
        
        tableName = name2ExcelTable;
        
        export2EXCELTable = excelTable2Export;
        
        javax.swing.table.TableModel tableModel2EXCEL = export2EXCELTable.getModel();
        
        threadExport2EXCEL = new java.lang.Thread(this, "Export2EXCEL");
        
        threadExport2EXCEL.run();
        
    }
     public ExcelExport(javax.swing.JTable excelTable2Export, java.lang.String name2ExcelTable, int dataType[]) {
        
        fileString = name2ExcelTable;
        
        tableName = name2ExcelTable;
        
        dataTypeModel = dataType;
        
        export2EXCELTable = excelTable2Export;
        
        javax.swing.table.TableModel tableModel2EXCEL = export2EXCELTable.getModel();
        
        threadExport2EXCEL = new java.lang.Thread(this, "Export2EXCEL");
        
        threadExport2EXCEL.run();
        
    }   
    public ExcelExport(javax.swing.table.DefaultTableModel excelTable2Export, java.lang.String name2ExcelTable) {
        
        tableName = name2ExcelTable;
        
        export2EXCELTableModel = excelTable2Export;
        
        javax.swing.table.TableModel tableModel2EXCEL = export2EXCELTableModel;
        
        threadExport2EXCEL = new java.lang.Thread(this, "Export2EXCEL");
        
        threadExport2EXCEL.run();
        
    }
    
    public void run() {
        
        while (export2EXCEL) {
            
            createEXCELDoc();
            
            try {
                
                Thread.currentThread().sleep(100);
                
            } catch(java.lang.InterruptedException IntExec){ System.out.println(IntExec.getMessage());}
            
            export2EXCEL = false;
            
        }
        
    }
    public void createEXCELDoc() {
        
        java.io.File excelDocFile =  null;
        
        org.w3c.dom.Document excelDocument = null;
        
        System.out.println("Started EXCEL business ...");
        
        try {
            
           // excelDocFile = java.io.File.createTempFile(fileString, ".xls", new java.io.File(System.getProperty("docsdir")));
            
            excelDocFile = new java.io.File(System.getProperty("docsdir"),fileString+".xls" );
            
           // excelDocFile.deleteOnExit();
            
            excelOutputStream = new java.io.BufferedOutputStream(new java.io.FileOutputStream(excelDocFile));
            
            System.out.println("File name ["+excelDocFile.getAbsolutePath()+"]");
            
            fileString = excelDocFile.getAbsolutePath();
            
            
        } catch(java.io.IOException ioExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioExec.getMessage());
            
        }
        
        workonExcelDocument(excelOutputStream);
        
        javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "Successfully exported table ["+tableName.toUpperCase()+"] to file ["+excelDocFile.getAbsolutePath()+"]");
        
    }
    
    public void workonExcelDocument(java.io.BufferedOutputStream buffOutputStream) {
        
        java.lang.Object[] tableColumns = this.getTableColumns(export2EXCELTable);
        
        int rowCount = export2EXCELTable.getRowCount();
        
        int columnCount = export2EXCELTable.getColumnCount();
        
        org.apache.poi.hssf.usermodel.HSSFWorkbook excelWorkBook = new org.apache.poi.hssf.usermodel.HSSFWorkbook();
        
        org.apache.poi.hssf.usermodel.HSSFSheet excelSheet = excelWorkBook.createSheet(tableName);
        
        org.apache.poi.hssf.usermodel.HSSFRow sheetRow = excelSheet.createRow((short)0);
        
        for (int i = 0; i < tableColumns.length; i++) {
            
            javax.swing.table.TableColumn excelTableColumn = (javax.swing.table.TableColumn)tableColumns[i];
            
            sheetRow.createCell((short)i).setCellValue(excelTableColumn.getHeaderValue().toString());
            
        }
        
        for (int j = 0; j < rowCount; j++) {
            
            sheetRow = excelSheet.createRow((short)j+1);
            
            for (int k = 0; k < columnCount; k++) {
                
                if (export2EXCELTable.getValueAt(j, k) != null) {
                    
                    if (dataTypeModel != null){
                    
                    sheetRow.createCell((short)k, dataTypeModel[k]).setCellValue(export2EXCELTable.getValueAt(j,k).toString());
                    
                    } else {
                        
                    sheetRow.createCell((short)k).setCellValue(export2EXCELTable.getValueAt(j,k).toString());
                        
                    }
                } else {
                    if (dataTypeModel != null){
                    sheetRow.createCell((short)k, dataTypeModel[k]).setCellValue("-");
                    } else {
                      sheetRow.createCell((short)k).setCellValue("-");  
                    }
                    
                    
                }
                
            }
            
        }
        
        try {
            
            excelWorkBook.write(buffOutputStream);
            
            buffOutputStream.close();
            
        } catch(java.io.IOException IOExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), IOExec.getMessage());
            
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
