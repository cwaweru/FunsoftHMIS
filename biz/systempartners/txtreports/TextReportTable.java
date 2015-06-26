/*
 * TextReportTable.java
 *
 * Created on August 21, 2005, 10:53 AM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package biz.systempartners.txtreports;

/**
 *
 * @author Charles Waweru <cwaweru@systempartners.biz>
 */
public class TextReportTable {
    
    private biz.systempartners.txtreports.TextReport txtReport = null;
    
    private java.lang.String columnHeaderLabels[];
   
    public static int remainingLines = 0;
    
    private int tableColumnMatrix[];
    
    private int bandHeaderLine;
    
    private int currentColumn = 0;
    
    private int currentRow = 0;
    
    private int currentColumnSize = 0;
    
    private int currentBandPosition = 0;
    
    private long topCaretBandPosition = 0;
    
    private long lineCaretPosition = 0;
    
    private int currentLine = 0;
    
    private int max_line_characters = 0;
    
    private long currentFileLength = 0;
    
    private boolean bandComplete = false;
    
    int columnXAlignments[] = null;
    
    
    
    /** Creates a new instance of TextReportTable */
    public TextReportTable(javax.swing.table.DefaultTableModel reportModel, int tableColMatrix[], java.lang.String colHeaderLabels[], biz.systempartners.txtreports.TextReport textReport, int currentFileLine, int columnHorizontalAlignments[]) {
        
        if (tableColMatrix.length != colHeaderLabels.length){
            
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "Confirm that the number of header elements \n are equal to the number of columns in the table");
            
        } else {
            
            tableColumnMatrix = tableColMatrix;
            
            txtReport = textReport;
            
            currentLine = currentFileLine;
            
            columnHeaderLabels = colHeaderLabels;
            
            bandHeaderLine = txtReport.getBandHeaderLine();
            
            max_line_characters = txtReport.TOTAL_NUM_CHAR_PER_LINE;
            
            currentFileLength = (currentFileLine) * max_line_characters;
            
            System.out.println("Max Chars per lien = "+ max_line_characters);
            
            columnXAlignments = columnHorizontalAlignments;
            
            System.out.println("X Alighnments Array size "+ columnXAlignments.length);
            
            
            this.writeTextTable(reportModel);
            
        }
        
    }
    
    public void addPhrase(biz.systempartners.txtreports.Phrase newPhrase, int phraseHorizontalAlignment){
        System.out.println();
        System.out.println("Starting to Printing phrases" + newPhrase.getPrintedCharacters() + "  Phase Characters " + newPhrase.getPhraseBytes()+ "Column count "+currentColumn);
        if (newPhrase.getPhraseBytes().length > 0){
            
            if (newPhrase.getRemainingCharacters() < tableColumnMatrix[currentColumn]) {
                
                try {
                    System.out.println("Printing phrases" + newPhrase.getPrintedCharacters());
                    txtReport.getReportAccessFile().write(newPhrase.getPhraseBytes(), newPhrase.getPrintedCharacters() + 1, 2);//newPhrase.getPrintedCharacters());
                    
                } catch (java.io.IOException ioEx){
                    
                    javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioEx.getMessage());
                    
                }
                
                
            } else {
                System.out.println("Printing phrases 2 " + newPhrase.getPrintedCharacters());
                try {
                    
                    txtReport.getReportAccessFile().write(newPhrase.getPhraseBytes(), newPhrase.getPrintedCharacters() + 1, currentColumnSize);
                    
                } catch (java.io.IOException ioEx){
                    
                    javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioEx.getMessage());
                    
                    moveToNextColumnRow();
                    
                }
            }
            
        } else {
            
            if(currentColumn == columnHeaderLabels.length - 1){
                
                moveCaretToNextRow();
                
            } else {
                
                moveCaretToNextColumn();
                
            }
            
        }
        
        if (currentColumn < tableColumnMatrix.length - 1){
            currentColumn++;
        } else{
            moveCaretToNextRow();
        }
    }
    
    public void setCaretToStartOfTable(){
        try {
            txtReport.getReportAccessFile().seek(((txtReport.getCurrentLine() + 1) * (txtReport.TOTAL_NUM_CHAR_PER_LINE)) + 1);
           
            topCaretBandPosition = txtReport.getReportAccessFile().getFilePointer();
        } catch (java.io.IOException ioEx){
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioEx.getMessage());
        }
        
    }
    
    public void moveCaretToNextColumn(){
        try {
            txtReport.getReportAccessFile().seek(topCaretBandPosition + currentColumnSize + 1);
        } catch(java.io.IOException ioEx){
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioEx.getMessage());
        }
    }
    
    public void moveCaretToNextRow(){
        try {
            txtReport.getReportAccessFile().seek((txtReport.getHighestLine() + 1) * txtReport.TOTAL_NUM_CHAR_PER_LINE);
        } catch(java.io.IOException ioEx){
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioEx.getMessage());
        }
        currentColumn = 0;
    }
    
    public void moveToNextColumnRow(){
        try {
            txtReport.getReportAccessFile().seek((txtReport.getReportAccessFile().getFilePointer() - currentColumnSize )+ txtReport.TOTAL_NUM_CHAR_PER_LINE);
        } catch(java.io.IOException ioEx){
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioEx.getMessage());
        }
        
    }
    
    public void writeTextTable(javax.swing.table.DefaultTableModel textTableModel){
        // We need to move the position of the caret in the report file on to a new line.
//        this.setCaretToStartOfTable();
        
        java.util.Vector bandPhrases = textTableModel.getDataVector();
        
        for (int i = 0; i < bandPhrases.size(); i++) {
            //  if (i == 1){
            java.util.Vector tempVector = (java.util.Vector)bandPhrases.elementAt(i);
            
            printRowToFile(tempVector);
            //  }
            //           setNextLine();
            
//            setCaretToNextLine();
        }
        //  this.setCaretToStartOfTable();
    }
    
    public void printRowToFile(java.util.Vector rowPhrasesVector){
        bandComplete = true;
     //   System.out.println("Current Line [ "+ currentLine+" ] ");
        
        java.lang.Object rowVectorArray[] = rowPhrasesVector.toArray();
        
        biz.systempartners.txtreports.Phrase phraseArray[] = new biz.systempartners.txtreports.Phrase[rowVectorArray.length];
//        String phraseArray[] = new String[rowVectorArray.length];
        for (int i = 0; i < rowVectorArray.length; i++){
            
//            phraseArray[i] = rowVectorArray[i].toString();
            if(rowVectorArray[i] == null){
                rowVectorArray[i] = new biz.systempartners.txtreports.Phrase(1, "");
                phraseArray[i] = new biz.systempartners.txtreports.Phrase(rowVectorArray[i].toString().toCharArray().length, rowVectorArray[i].toString());
                
            } else {
                
                phraseArray[i] = new biz.systempartners.txtreports.Phrase(rowVectorArray[i].toString().toCharArray().length, rowVectorArray[i].toString());
                
            }
        }
        
        writeTableRow(phraseArray);
//        setNextLine();
//        setNextLine();
        
    }
    
    public void writeTableRow(biz.systempartners.txtreports.Phrase rowElements[]){
        bandComplete = true;
//    public void writeTableRow(String rowElements[]){
        setNextLine();
        try {
            txtReport.getReportAccessFile().writeBytes(biz.systempartners.txtreports.Phrase.PHRASE_SEPARATOR_CHAR);
        } catch(java.io.IOException ioEx){
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioEx.getMessage());
        }
        /*
         * Write a line of table elements on the file.
         *
         */
        for(int i = 0; i < rowElements.length; i++){
            try {
                
//                System.out.println("Printing phrase [ " + rowElements[i].toString()+ " ] element length "+ rowElements[i].toString().toCharArray().length +" Chars remaining "+rowElements[i].getRemainingCharacters()+ " Chars printed "+rowElements[i].getPrintedCharacters());
                /*
                 * Write current chunk of the current phrase to the report file
                 *
                 */
                if(rowElements[i].getRemainingCharacters() > 0){
//                  System.out.println("Doing column [ "+i+" ]");
                    if (rowElements[i].getRemainingCharacters() > tableColumnMatrix[i]){
                        
                        System.out.println("Printing phrase [ " + rowElements[i].toString()+ " ] element length "+ rowElements[i].toString().toCharArray().length +" Chars remaining "+rowElements[i].getRemainingCharacters()+ " Chars printed "+rowElements[i].getPrintedCharacters() +" column matrix "+tableColumnMatrix[i]);
                        
                        System.out.println("Sub-string "+rowElements[i].toString().substring(rowElements[i].getPhraseCaretPosition(),  tableColumnMatrix[i] + rowElements[i].getPhraseCaretPosition()));
                        
                        txtReport.getReportAccessFile().writeBytes(rowElements[i].toString().substring(rowElements[i].getPhraseCaretPosition(), tableColumnMatrix[i] + rowElements[i].getPhraseCaretPosition()) + biz.systempartners.txtreports.Phrase.PHRASE_SEPARATOR_CHAR);
                        
                        rowElements[i].setPrintedCharacters(tableColumnMatrix[i]);
                        
                        rowElements[i].setRemainingCharacters(rowElements[i].getPrintedCharacters());
                        
                        if ((rowElements[i].toString().toCharArray().length - rowElements[i].getPrintedCharacters()) > 0) {
                            
                            bandComplete = false;
                            
                        }
                        
                        //  setBandComplete(rowElements[i], bandComplete);
                        
                        System.out.println("Printing phrase AFTER [ " + rowElements[i].toString()+ " ] element length "+ rowElements[i].toString().toCharArray().length +" Chars remaining "+rowElements[i].getRemainingCharacters()+ " Chars printed "+rowElements[i].getPrintedCharacters() +" column matrix "+tableColumnMatrix[i]);
                        
                    } else {
                            /*
                             * Writes phrase whose remaining charaters ar less than size of column.
                             */
                        int xAlignment = columnXAlignments[i];
                        
                        switch (xAlignment) {
                            
                            case 1:  {
                                // Write phrase to the left.
                                System.out.println("Printing phrase [ " + rowElements[i].toString()+ " ] element length "+ rowElements[i].toString().toCharArray().length +" Chars remaining "+rowElements[i].getRemainingCharacters()+ " Chars printed "+rowElements[i].getPrintedCharacters() +" column matrix "+tableColumnMatrix[i]);
                                
                                System.out.println("Sub-string 2 [ "+rowElements[i].toString().substring(rowElements[i].getPhraseCaretPosition(),  rowElements[i].toString().toCharArray().length)+" ]");
                                
                                txtReport.getReportAccessFile().writeBytes(rowElements[i].toString().substring(rowElements[i].getPhraseCaretPosition(), rowElements[i].toString().toCharArray().length));
                                
                                for (int j = 0; j < (tableColumnMatrix[i] - rowElements[i].getRemainingCharacters()); j++){
                                    
                                    txtReport.getReportAccessFile().writeBytes(" ");
                                    
                                }
                                
                                rowElements[i].setPrintedCharacters(rowElements[i].toString().toCharArray().length - rowElements[i].getPhraseCaretPosition());
                                
                                rowElements[i].setRemainingCharacters(rowElements[i].getPrintedCharacters());
                                
                                System.out.println("Printing phrase AFTER 2 [ " + rowElements[i].toString()+ " ] element length "+ rowElements[i].toString().toCharArray().length +" Chars remaining "+rowElements[i].getRemainingCharacters()+ " Chars printed "+rowElements[i].getPrintedCharacters() +" column matrix "+tableColumnMatrix[i]);
                                
                                
                                txtReport.getReportAccessFile().writeBytes(biz.systempartners.txtreports.Phrase.PHRASE_SEPARATOR_CHAR);
                                
                            }; break ;
                            
                            case 2:  {
                                int leftSpaceCount = 0;
                                
                                int rightSpaceCount = 0;
                                
                                if (((tableColumnMatrix[i] - rowElements[i].getRemainingCharacters())%2 > 0)){
                                    
                                    leftSpaceCount = (int)java.lang.Math.round(java.lang.Math.floor((tableColumnMatrix[i] - rowElements[i].getRemainingCharacters())/2));
                                    
                                    rightSpaceCount = (tableColumnMatrix[i] - rowElements[i].getRemainingCharacters()) - leftSpaceCount;
                                    
                                    System.out.println("Left Spaces count[ "+leftSpaceCount+" ], Right Spaces Count [ "+rightSpaceCount+" ]");
                                    
                                    
                                    // Write phrase centered.
                                    System.out.println("Printing phrase [ " + rowElements[i].toString()+ " ] element length "+ rowElements[i].toString().toCharArray().length +" Chars remaining "+rowElements[i].getRemainingCharacters()+ " Chars printed "+rowElements[i].getPrintedCharacters() +" column matrix "+tableColumnMatrix[i]);
                                    
                                    System.out.println("Sub-string 2 [ "+rowElements[i].toString().substring(rowElements[i].getPhraseCaretPosition(),  rowElements[i].toString().toCharArray().length)+" ]");
                                    
                                    
                                    for (int j = 0; j < leftSpaceCount; j++){
                                        
                                        txtReport.getReportAccessFile().writeBytes(" ");
                                        
                                    }
                                    
                                    txtReport.getReportAccessFile().writeBytes(rowElements[i].toString().substring(rowElements[i].getPhraseCaretPosition(), rowElements[i].toString().toCharArray().length));
                                    
                                    for (int j = 0; j < rightSpaceCount; j++){
                                        
                                        txtReport.getReportAccessFile().writeBytes(" ");
                                        
                                    }
                                    
                                } else {
                                    
                                    leftSpaceCount = (tableColumnMatrix[i] - rowElements[i].getRemainingCharacters())/2;
                                    
                                    rightSpaceCount = (tableColumnMatrix[i] - rowElements[i].getRemainingCharacters()) - leftSpaceCount;
                                    
                                    System.out.println("Left Spaces count[ "+leftSpaceCount+" ], Right Spaces Count [ "+rightSpaceCount+" ]");
                                    
                                    
                                    // Write phrase centered.
                                    System.out.println("Printing phrase [ " + rowElements[i].toString()+ " ] element length "+ rowElements[i].toString().toCharArray().length +" Chars remaining "+rowElements[i].getRemainingCharacters()+ " Chars printed "+rowElements[i].getPrintedCharacters() +" column matrix "+tableColumnMatrix[i]);
                                    
                                    System.out.println("Sub-string 2 [ "+rowElements[i].toString().substring(rowElements[i].getPhraseCaretPosition(),  rowElements[i].toString().toCharArray().length)+" ]");
                                    
                                    
                                    for (int j = 0; j < leftSpaceCount; j++){
                                        
                                        txtReport.getReportAccessFile().writeBytes(" ");
                                        
                                    }
                                    
                                    txtReport.getReportAccessFile().writeBytes(rowElements[i].toString().substring(rowElements[i].getPhraseCaretPosition(), rowElements[i].toString().toCharArray().length));
                                    
                                    for (int j = 0; j < rightSpaceCount; j++){
                                        
                                        txtReport.getReportAccessFile().writeBytes(" ");
                                    }
                                }
                                rowElements[i].setRemainingCharacters(rowElements[i].getPrintedCharacters());
                                
                                System.out.println("Printing phrase AFTER 2 [ " + rowElements[i].toString()+ " ] element length "+ rowElements[i].toString().toCharArray().length +" Chars remaining "+rowElements[i].getRemainingCharacters()+ " Chars printed "+rowElements[i].getPrintedCharacters() +" column matrix "+tableColumnMatrix[i]);
                                
                                txtReport.getReportAccessFile().writeBytes(biz.systempartners.txtreports.Phrase.PHRASE_SEPARATOR_CHAR);
                                
                            } ; break ;
                            
                            case 3:  {
                                // Write phrase to the right.
                                System.out.println("Printing phrase [ " + rowElements[i].toString()+ " ] element length "+ rowElements[i].toString().toCharArray().length +" Chars remaining "+rowElements[i].getRemainingCharacters()+ " Chars printed "+rowElements[i].getPrintedCharacters() +" column matrix "+tableColumnMatrix[i]);
                                
                                System.out.println("Sub-string 2 [ "+rowElements[i].toString().substring(rowElements[i].getPhraseCaretPosition(),  rowElements[i].toString().toCharArray().length)+" ]");
                                for (int j = 0; j < (tableColumnMatrix[i] - rowElements[i].getRemainingCharacters()); j++){
                                    
                                    txtReport.getReportAccessFile().writeBytes(" ");
                                    
                                }
                                txtReport.getReportAccessFile().writeBytes(rowElements[i].toString().substring(rowElements[i].getPhraseCaretPosition(), rowElements[i].toString().toCharArray().length));
                                
                                
                                
                                rowElements[i].setPrintedCharacters(rowElements[i].toString().toCharArray().length - rowElements[i].getPhraseCaretPosition());
                                
                                rowElements[i].setRemainingCharacters(rowElements[i].getPrintedCharacters());
                                
                                System.out.println("Printing phrase AFTER 2 [ " + rowElements[i].toString()+ " ] element length "+ rowElements[i].toString().toCharArray().length +" Chars remaining "+rowElements[i].getRemainingCharacters()+ " Chars printed "+rowElements[i].getPrintedCharacters() +" column matrix "+tableColumnMatrix[i]);
                                
                                txtReport.getReportAccessFile().writeBytes(biz.systempartners.txtreports.Phrase.PHRASE_SEPARATOR_CHAR);
                                
                            } ; break ;
                            
                        }
                        
                    }
                    
                } else {
                    
                    for (int j = 0; j < tableColumnMatrix[i]; j++){
                        
                        txtReport.getReportAccessFile().writeBytes(" ");
                        
                    }
                    
                   txtReport.getReportAccessFile().writeBytes(biz.systempartners.txtreports.Phrase.PHRASE_SEPARATOR_CHAR);
                }
                
            } catch (java.io.IOException ioEx){
                
                javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioEx.getMessage());
                
            }
            System.out.println("Status of bandComplete [ "+bandComplete+" ]");
        }
        
        if(!isBandComplete()){
            writeTableRow(rowElements);
            
        }
        
      /*
       * If we still have data on some or all of the phrases,
       * pass the row elements back onto the beginning of this method
       * in order to write all the charaters of table row elements onto the target
       * report file
       */
        
        // bandComplete = true;
    }
    
    public void setNextLine(){
        
//        currentLine = currentLine + 1;
        
        txtReport.currentLine++;
        
        System.out.println("Current line is : [ "+txtReport.currentLine+" ]");
        if (txtReport.currentLine == txtReport.TOTAL_NUM_LINES_PER_PAGE) {
           // Force a form feed and Draw page header and reset report line count. Increase page number count as well.
          
               txtReport.newPage();
               
               txtReport.currentLine = 0;
               
               System.out.println("DEBUG :: "+txtReport.getPageHeader());
               
               if (txtReport.getPageHeader() != null){
               
               txtReport.drawHorizontalLine(txtReport.getPageHeader().getColMatrix());
               
               txtReport.addTable(txtReport.getPageHeader(), txtReport.getPageHeader().colMatrix, txtReport.getPageHeader().getColHeaderModel(), txtReport.getPageHeader().columnHorizontalAlignments);
            
               txtReport.drawHorizontalLine(txtReport.getPageHeader().getColMatrix());
               
               }
        }
        remainingLines = txtReport.TOTAL_NUM_LINES_PER_PAGE - currentLine;
        
        txtReport.remainingLines = remainingLines;
        try {
            txtReport.getReportAccessFile().writeBytes(biz.systempartners.txtreports.TextReport.NEW_LINE_CHARACTER);
        } catch (java.io.IOException ioEx){
            
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioEx.getMessage());
            
        }
        
    }
    
    
    public void setBandComplete(Phrase currentPrintingPhrase, boolean immediateStatus){
        
        if (currentPrintingPhrase.getRemainingCharacters() > 0){
            
            bandComplete = false;
            
        } else {
            
            bandComplete = true;
        }
        
        
    }
    
    public boolean isBandComplete(){
        
        System.out.println("Decision to recurse is [ "+bandComplete+" ]");
        
        return bandComplete;
        
    }
    
        
    public void screenNewPage(int remainingLinesOnPage){
        
        for(int i = 0; i < remainingLinesOnPage; i++){
            try {
                
                this.txtReport.getReportAccessFile().writeBytes( " \n ");
                
            } catch (java.io.IOException ioEx){
                
                javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioEx.getMessage());
                
                //            }
            }
            
        }
        
    }
    

}
