/*
 * TextReport.java
 *
 * Created on August 20, 2005, 9:48 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package biz.systempartners.txtreports;

/**
 *
 * @author Charles Waweru (cwaweru@systempartners.biz)
 */
public class TextReport {
    
    
    public static int TOTAL_NUM_LINES_PER_PAGE;// = java.lang.Integer.parseInt(System.getProperty("linesperpage"));
    
    public static int TOTAL_NUM_CHAR_PER_LINE;// = java.lang.Integer.parseInt(System.getProperty("charactersperline"));
    
    public static int pageNumber = 0;
    
    public static int remainingLines = 0;
    
    public static String LINE_DRAW_CHARACTER = System.getProperty("line.character");
    
    public static String NEW_LINE_CHARACTER = System.getProperty("new.line.character");
    
    public static String DOTTED_LINE_CHARACTER = System.getProperty("dotted.line.character");
    
    private java.lang.String simpleReportHeader = null;
    
    private java.lang.String simpleReportFooter = null;
    
    private java.lang.String simplePageHeader = null;
    
    private java.lang.String simplePageFooter = null;
    
    private java.lang.String reportFile = null;
    
    private java.io.RandomAccessFile reportAccessFile = null;
    
    private int phrasesTableSizes[] = null;
    
    public static int currentLine = 0;
    
    private int bandOffsetLine = 0;
    
    private int highestLine = 0;
    
    private int bandHeaderLine = 0;
    
    private int currentColumn = 0;
    
    private int columnHorizontalAlignments[] = null;
    
    private biz.systempartners.txtreports.ReportHeader reportHeader = null;
    
    private biz.systempartners.txtreports.ReportFooter reportFooter = null;
    
    private biz.systempartners.txtreports.PageHeader pageHeader = null;
    
    private biz.systempartners.txtreports.PageFooter pageFooter = null;
    
    public int pageCount;
    
    public int reportWidth = 0;
    
    public int rptColumnTitleMatrix[] = null;
    
    /** Creates a new instance of TextReport */
    public TextReport(int maxPageLines, int maxLineChars, java.io.RandomAccessFile randomAccessFile) {
        
        // try{
        
        TOTAL_NUM_LINES_PER_PAGE = maxPageLines;
        
        TOTAL_NUM_CHAR_PER_LINE = maxLineChars;
       /* } catch(java.lang.NumberFormatException nfEx){
        
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), nfEx.getMessage());
        
        }*/
        reportAccessFile = randomAccessFile;
        
        
        
    }
    
    public int getLinesPerPage(){
        
        return TOTAL_NUM_LINES_PER_PAGE;
        
    }
    
    public int getCharPerLine(){
        
        return TOTAL_NUM_CHAR_PER_LINE;
        
    }
    
    public void addReportHeader(biz.systempartners.txtreports.ReportHeader newReportHeader, int tableColMatrix[], String colHeaderModel[], int newColumnHorizontalAlignments[]){
        
        reportHeader = newReportHeader;
        
        reportHeader.setParameters(tableColMatrix, colHeaderModel, newColumnHorizontalAlignments);
        
        columnHorizontalAlignments = newColumnHorizontalAlignments;
        
        new biz.systempartners.txtreports.TextReportTable(newReportHeader, tableColMatrix, colHeaderModel, this, 0, columnHorizontalAlignments);
        
    }
    
    public void addReportFooter(biz.systempartners.txtreports.ReportFooter newReportFooter,  int tableColMatrix[], String colHeaderModel[], int newColumnHorizontalAlignments[]){
        
        reportFooter = newReportFooter;
        
        reportFooter.setParameters(tableColMatrix, colHeaderModel, newColumnHorizontalAlignments);
        
        
        columnHorizontalAlignments = newColumnHorizontalAlignments;
        
        new biz.systempartners.txtreports.TextReportTable(newReportFooter, tableColMatrix, colHeaderModel, this, 0, columnHorizontalAlignments);
        
        
    }
    
    public void addPageHeader(biz.systempartners.txtreports.PageHeader newPageHeader,  int tableColMatrix[], String colHeaderModel[], int newColumnHorizontalAlignments[]){
        
        pageHeader = newPageHeader;
        
        pageHeader.setParameters(tableColMatrix, colHeaderModel, newColumnHorizontalAlignments);
        
    }
    
    public void writePageFooter(biz.systempartners.txtreports.PageFooter newPageFooter, int tableColMatrix[], String colHeaderModel[], int newColumnHorizontalAlignments[]){
        
        pageFooter = newPageFooter;
        
        pageFooter.setParameters(tableColMatrix, colHeaderModel, newColumnHorizontalAlignments);
        
    }
    
    public int[] createTableHeader(int cols, double sizeArrayPercent[]){
        
        phrasesTableSizes = new int[cols];
        
        if (cols != sizeArrayPercent.length || !colSizePercentsOk(sizeArrayPercent)){
            
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "The number of columns specfied must equal \n to the number of elements in the size array.\n The total of percentage column sizes must be 100.00", "Error computing column sizes.", javax.swing.JOptionPane.ERROR_MESSAGE);
            
        } else {
            
            for (cols = 0; cols < sizeArrayPercent.length; cols++){
                
                phrasesTableSizes[cols] = (int)java.lang.Math.round(java.lang.Math.floor((sizeArrayPercent[cols]/100 * TOTAL_NUM_CHAR_PER_LINE))) - 1;
                
            }
            
            
        }
        
        return phrasesTableSizes;
    }
    
    public int[] createTableFooter(int cols, double sizeArrayPercent[]){
        
        phrasesTableSizes = new int[cols];
        
        if (cols != sizeArrayPercent.length || !colSizePercentsOk(sizeArrayPercent)){
            
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "The number of columns specfied must equal \n to the number of elements in the size array.\n The total of percentage column sizes must be 100.00", "Error computing column sizes.", javax.swing.JOptionPane.ERROR_MESSAGE);
            
        } else {
            
            for (cols = 0; cols < sizeArrayPercent.length; cols++){
                
                phrasesTableSizes[cols] = (int)java.lang.Math.round(java.lang.Math.floor((sizeArrayPercent[cols]/100 * rptColumnTitleMatrix.length))) - 1;
                
            }
            
            
        }
        
        return phrasesTableSizes;
    }
    
    
    public void closeReport(){
        
        try{
          /*  for(int i = 0; i < 7; i++){
           
                this.setNextLine();
           
            }
           */
            reportAccessFile.close();
            
        } catch(java.io.IOException ioEx){
            
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioEx.getMessage());
            
        }
        
    }
    
    public java.io.RandomAccessFile createReportAccessFile(java.lang.String targetFile){
        
        try {
            
            reportAccessFile = new java.io.RandomAccessFile(targetFile,  "rw");
            
        } catch (java.io.FileNotFoundException fnfEx){
            
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), fnfEx.getMessage());
        }
        
        return reportAccessFile;
    }
    
    public java.io.RandomAccessFile createReportAccessFile(java.io.File targetFile){
        
        try {
            
            reportAccessFile = new java.io.RandomAccessFile(targetFile,  "rw");
            
        } catch (java.io.FileNotFoundException fnfEx){
            
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), fnfEx.getMessage());
        }
        
        return reportAccessFile;
    }
    
    public boolean colSizePercentsOk(double colSizes[]){
        
        double percentSize = 0.00;
        
        for (int i = 0; i < colSizes.length; i++){
            percentSize = percentSize + colSizes[i];
        }
        
        if(percentSize == 100.00){
            System.out.println("Returned true");
            return true;
        } else {
            System.out.println("Returned false");
            return false;
        }
    }
    
    public void createTable(int colHeaderMatrix[]){
        
        
        
    }
    
    public int getCurrentLine(){
        
        return currentLine;
    }
    
    public void setCurrentLine(int newCurrentLine){
        
        currentLine = newCurrentLine;
    }
    
    public int getHighestLine(){
        
        return highestLine;
        
    }
    
    public void setHighestLine(int newHighestLine){
        
        highestLine = newHighestLine;
        
    }
    
    public void setBandHeaderLine(int newBandHeaderLine){
        
        bandHeaderLine = newBandHeaderLine;
        
    }
    public int getBandHeaderLine(){
        
        return bandHeaderLine;
        
    }
    
    public java.io.RandomAccessFile getReportAccessFile(){
        
        return reportAccessFile;
        
    }
/*
    public void writeTableToFile(javax.swing.table.DefaultTableModel txtTableModel, int tableColMatrix[], String colHeaderModel[], int newColumnHorizontalAlignments[]){
 
        columnHorizontalAlignments = newColumnHorizontalAlignments;
 
        new biz.systempartners.txtreports.TextReportTable(txtTableModel, tableColMatrix, colHeaderModel, this, 0, columnHorizontalAlignments);
 
    }
 */
    
    public void addTable(biz.systempartners.txtreports.PlainTextTable txtTableModel, int tableColMatrix[], String colHeaderModel[], int newColumnHorizontalAlignments[]){
        
        //   rptColumnTitleMatrix = tableColMatrix;
        
        columnHorizontalAlignments = newColumnHorizontalAlignments;
        
        new biz.systempartners.txtreports.TextReportTable(txtTableModel, tableColMatrix, colHeaderModel, this, 0, columnHorizontalAlignments);
        
    }
    
    public void addTable(javax.swing.table.DefaultTableModel txtTableModel, int tableColMatrix[], String colHeaderModel[], int newColumnHorizontalAlignments[]){
        
        //   rptColumnTitleMatrix = tableColMatrix;
        
        columnHorizontalAlignments = newColumnHorizontalAlignments;
        
        new biz.systempartners.txtreports.TextReportTable(txtTableModel, tableColMatrix, colHeaderModel, this, 0, columnHorizontalAlignments);
        
    }
    
    public void drawHorizontalLine(int tableColumnMatrix[]){
        
        
        
        setNextLine();
        
        
        reportWidth = biz.systempartners.txtreports.Phrase.PHRASE_SEPARATOR_CHAR.toCharArray().length;
        
        for (int i = 0; i < tableColumnMatrix.length; i++){
            
            reportWidth = reportWidth + tableColumnMatrix[i] + biz.systempartners.txtreports.Phrase.PHRASE_SEPARATOR_CHAR.toCharArray().length;
            
        }
        
        for (int i = 0; i < reportWidth; i++) {
            
            try {
                
                this.getReportAccessFile().writeBytes(this.LINE_DRAW_CHARACTER);
                
            } catch(java.io.IOException ioEx){
                
                javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioEx.getMessage());
                
            }
        }
        
        //    this.currentLine++;
        
    }
    
    
    public void drawHorizontalLine(int phraseLegnth){
        
        
        //    setNextLine();
        
        
        //       int reportWidth = biz.systempartners.txtreports.Phrase.PHRASE_SEPARATOR_CHAR.toCharArray().length;
        
        //        for (int i = 0; i < tableColumnMatrix.length; i++){
        
        //            reportWidth = reportWidth + tableColumnMatrix[i] + biz.systempartners.txtreports.Phrase.PHRASE_SEPARATOR_CHAR.toCharArray().length;
        
        //        }
        
        for (int i = 0; i < phraseLegnth; i++) {
            
            try {
                
                this.getReportAccessFile().writeBytes(this.LINE_DRAW_CHARACTER);
                
            } catch(java.io.IOException ioEx){
                
                javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioEx.getMessage());
                
            }
        }
        
        //    this.currentLine++;
        
    }
    
    public void setNextLine(){
        
        currentLine = currentLine + 1;
       
        
        try {
            
            this.getReportAccessFile().writeBytes( " \n ");
            
            if(this.currentLine == this.TOTAL_NUM_LINES_PER_PAGE){
                
                if(currentLine < this.TOTAL_NUM_LINES_PER_PAGE){
                    
                   currentLine = currentLine + 1;
                     
                    remainingLines = this.TOTAL_NUM_LINES_PER_PAGE - currentLine;
                    
                } else {
                    
                    currentLine = 0;
                }
                System.out.println("Current line ["+currentLine+"]");
                //     this.newPage();
                
            }
            //  drawHeadersAndFooters();
            
        } catch (java.io.IOException ioEx){
            
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioEx.getMessage());
            
        }
        
        
    }
    
    
    public void newPage(){
  
   
        //        if(this.currentLine == this.TOTAL_NUM_LINES_PER_PAGE){
   
        try {
   
            this.getReportAccessFile().writeBytes( " \n ");
   
            //        drawHeadersAndFooters();
   
        } catch (java.io.IOException ioEx){
   
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioEx.getMessage());
   
            //            }
        }
   
    }
    
    private void drawHeadersAndFooters(){
        if(this.pageFooter != null){
            
            if (this.currentLine == (this.TOTAL_NUM_LINES_PER_PAGE - reportFooter.getRowCount())){
                
                addTable(pageFooter, pageFooter.colMatrix, pageFooter.colHeaderModel, pageFooter.columnHorizontalAlignments);
                
            }
        }
        
        if(this.pageHeader != null){
            
            if(this.currentLine == this.TOTAL_NUM_LINES_PER_PAGE){
                
                addTable(pageHeader, pageHeader.colMatrix, pageHeader.colHeaderModel, pageHeader.columnHorizontalAlignments);
                
            }
        }
    }
    
    public void screenNewPage(int remainingLinesOnPage){
        
        for(int i = 0; i < remainingLinesOnPage; i++){
            try {
                
                this.getReportAccessFile().writeBytes( " \n ");
                
            } catch (java.io.IOException ioEx){
                
                javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioEx.getMessage());
                
                //            }
            }
            
        }
        
    }
    
    public biz.systempartners.txtreports.PageHeader getPageHeader(){
        
        return pageHeader;
        
    }
    
    public biz.systempartners.txtreports.PageFooter getPageFooter(){
        
        return pageFooter;
        
    }
    
    public biz.systempartners.txtreports.ReportFooter getReportFooter(){
        
        return reportFooter;
        
    }
    
    public biz.systempartners.txtreports.ReportHeader getReportHeader(){
        
        return reportHeader;
        
    }
    
    public void writeSimpleReportFooter(String simpleReportFooterModel, boolean drawLine){
        
        long reportFooterLines =  java.lang.Math.round(java.lang.Math.ceil(simpleReportFooterModel.toCharArray().length/this.TOTAL_NUM_CHAR_PER_LINE));
        
        
        biz.systempartners.txtreports.ReportFooter reportFooter = new biz.systempartners.txtreports.ReportFooter(1);
        
        reportFooter.addCell(simpleReportFooterModel);
        
        String ColumnModelTitle[] = {""};
        
        double sizeArrayPercent[] = {100};
        
        int colSizeTitle[] =  this.createTableHeader(1, sizeArrayPercent);
        
        
        int horizontalAlignmentsTitle[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT};
        
        long remainingLinesOnLastPage = this.TOTAL_NUM_LINES_PER_PAGE - this.currentLine - reportFooterLines;
        
        
        for(int i = 0; i < remainingLinesOnLastPage - 4; i++){
            
            try {
                
                this.getReportAccessFile().writeBytes( " \n ");
                
            } catch (java.io.IOException ioEx){
                
                javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioEx.getMessage());
                
                //            }
            }
            
        }
        
        if (drawLine){
            
            this.drawHorizontalLine(this.TOTAL_NUM_CHAR_PER_LINE+4);
            //             this.drawHorizontalLine(rptColumnTitleMatrix.length);
        }
        
        this.addTable(reportFooter, colSizeTitle, ColumnModelTitle, horizontalAlignmentsTitle);
        //        this.addTable(reportFooter, rptColumnTitleMatrix, ColumnModelTitle, horizontalAlignmentsTitle);
        if (drawLine){
            
            this.drawHorizontalLine(this.TOTAL_NUM_CHAR_PER_LINE+4);
            //            this.drawHorizontalLine(rptColumnTitleMatrix.length);
            
        }
        
        this.newPage();
        
        this.currentLine = 0;
        
    }
    
}
