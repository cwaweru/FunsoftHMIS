package biz.systempartners.txtreports;

/*
 * SampleTxtReport.java
 *
 * Created on August 20, 2005, 4:39 PM


package biz.systempartners.txtreports;

/**
 *
 * @author Charles Waweru <cwaweru@systempartners.biz>
 */
public class SampleTxtReport {
   
    java.io.RandomAccessFile txtReportFile = null;
    /** Creates a new instance of SampleTxtReport */
    
    public SampleTxtReport(java.lang.String targetReportFile) {
        
        try {
           
            txtReportFile= new java.io.RandomAccessFile(targetReportFile,"rw");
            
            writeReport(txtReportFile);
            
        
        } catch (java.io.FileNotFoundException fnf){
            
            fnf.printStackTrace();
            
        }
        
    }
    
    private void writeReport(java.io.RandomAccessFile txtRandomAccessFile){
        
        System.setProperty("phrase.separator", "  ");
        
        System.setProperty("line.character", "_");
        
        System.setProperty("new.line.character", " \n ");
        
        int horizontalAlignments[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
                biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
                biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
                biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT};
                
                int horizontalAlignments1[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
                        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
                        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
                        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT};
                        biz.systempartners.txtreports.TextReport textReport = new biz.systempartners.txtreports.TextReport(80, 80, txtReportFile);
                        
                        double floats[] = {22,28,25,25};
                        
                        int colSizes[] = textReport.createTableHeader(4, floats);
                        
                        for (int i = 0; i < colSizes.length; i++) {
                            System.out.println(colSizes[i]);
                        }
                        
                        biz.systempartners.txtreports.TableModelPanel tablePanel = new biz.systempartners.txtreports.TableModelPanel();
                        Object companyName[] = { "Funsoft Demo Hospitals Limited"};
                        
                        double sizeArrayPercent[] = {100};
                        
                        int colSizeTitle[] = textReport.createTableHeader(1, sizeArrayPercent);
                        
                        int horizontalAlignmentsTitle[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_CENTER};
                        
                        
                        javax.swing.table.DefaultTableModel headerCompany = new javax.swing.table.DefaultTableModel(companyName,1);
                        
                        for (int i = 0; i < companyName.length; i++){
                            headerCompany.setValueAt(companyName[i], 0, i);
                        }
                        
                        Object columnModel1[] = {"Item ID.", "Description", "Unit Cost (KSh)", "Total Cost (KSh)"};
                        
                        
                        
                        String columnModel[] = {"This", "That", "Then", "when"};
                        
                        String ColumnModelTitle[] = {""};
                        
                        javax.swing.table.DefaultTableModel headerTableModel = new javax.swing.table.DefaultTableModel(columnModel1,1);
                        for (int i = 0; i < columnModel1.length; i++){
                            headerTableModel.setValueAt(columnModel1[i], 0, i);
                        }
                        
                        int integers[] = colSizes;
                        
                        biz.systempartners.txtreports.PlainTextTable table1 = new biz.systempartners.txtreports.PlainTextTable(4);
                        
                        for (int n = 0; n < 1; n++){
                            
                            table1.addCell("Papa is on holiday");
                            
                            table1.addCell("Mama is getting late to go back home");
                            
                            table1.addCell("Muthoni is a good girl");
                            
                            table1.addCell("Joe is a big playful boy.");
                            
                        }
                        
                        for (int i = 0; i < 1; i++) {
                            
                            textReport.addTable(headerCompany, colSizeTitle, ColumnModelTitle, horizontalAlignmentsTitle);
                            
                            textReport.drawHorizontalLine(integers);
                            
                            textReport.addTable(table1, integers, columnModel, horizontalAlignments1);
                            
                            textReport.drawHorizontalLine(integers);
                            
                            textReport.addTable(headerTableModel, integers, columnModel, horizontalAlignments);
                            
                            textReport.drawHorizontalLine(integers);
                            
                            textReport.addTable(tablePanel.getTableModel(), integers, columnModel, horizontalAlignments);
                            
                            textReport.drawHorizontalLine(integers);
                        
                        }
                        
    }
    
}
