//Author Charles Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;

package com.afrisoftech.hr;
import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;


public class EmployeeProfPdf implements java.lang.Runnable {
    
    public static java.sql.Connection connectDB = null;
    
    com.afrisoftech.lib.DBObject dbObject;
    
    java.lang.String names = null;
    
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 9,Font.ITALIC);
    com.lowagie.text.Font pFontHeader3 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader4 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 13, Font.NORMAL);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void EmployeeProfPdf(java.sql.Connection connDb,java.lang.String textfield) {
        
        names = textfield;
        
        connectDB = connDb;
        
        dbObject = new com.afrisoftech.lib.DBObject();
        
        // beginDate = begindate;
        
        //  endDate = endate;
        
        threadSample = new java.lang.Thread(this,"SampleThread");
        
        System.out.println("threadSample created");
        
        threadSample.start();
        
        System.out.println("threadSample fired");
        
    }
    
    public static void main(java.lang.String[] args) {
        
        //	new CashBookListPdf().CashBookListPdf();
        
    }
    
    
    public void run() {
        
        System.out.println("System has entered running mode");
        
        while (threadCheck) {
            
            System.out.println("O.K. see how we execute target program");
            
            this.generatePdf();
            
            try {
                
                System.out.println("Right, let's wait for task to complete of fail");
                
                java.lang.Thread.currentThread().sleep(200);
                
                System.out.println("It's time for us threads to get back to work after the nap");
                
            } catch(java.lang.InterruptedException IntExec) {
                
                System.out.println(IntExec.getMessage());
                
            }
            
            threadCheck = false;
            
            
            System.out.println("We shall be lucky to get back to start in one piece");
            
        }
        
        if (!threadCheck) {
            
            
            
            Thread.currentThread().stop();
            
        }
        
    }
    
    public java.lang.String getDateLable() {
        
        java.lang.String date_label = null;
        
        java.lang.String month_now_strs = null;
        
        java.lang.String date_now_strs = null;
        
        java.lang.String year_now_strs = null;
        
        java.lang.String minute_now_strs = null;
        
        java.lang.String hour_now_strs = null;
        
        java.lang.Runtime rt = java.lang.Runtime.getRuntime();
        
        java.util.Calendar calinst = java.util.Calendar.getInstance();
        
        java.util.Date date_now = calinst.getTime();
        
        int date_now_str = date_now.getDate();
        
        int month_now_str = date_now.getMonth();
        
        int year_now_str = date_now.getYear();
        
        int hour_now_str = date_now.getHours();
        
        int minute_now_str = date_now.getMinutes();
        
        int year_now_abs = year_now_str - 100;
        
        if (year_now_abs < 10) {
            
            year_now_strs = "200"+year_now_abs;
            
        } else {
            
            year_now_strs = "20"+year_now_abs;
            
        }
        
        switch (month_now_str) {
            
            case 0 : month_now_strs = "JAN";
            
            break;
            
            case 1 : month_now_strs = "FEB";
            
            break;
            
            case 2 : month_now_strs = "MAR";
            
            break;
            
            case 3 : month_now_strs = "APR";
            
            break;
            
            case 4 : month_now_strs = "MAY";
            
            break;
            
            case 5 : month_now_strs = "JUN";
            
            break;
            
            case 6 : month_now_strs = "JUL";
            
            break;
            
            case 7 : month_now_strs = "AUG";
            
            break;
            
            case 8 : month_now_strs = "SEP";
            
            break;
            
            case 9 : month_now_strs = "OCT";
            
            break;
            
            case 10 : month_now_strs = "NOV";
            
            break;
            
            case 11 : month_now_strs = "DEC";
            
            break;
            
            default :         if (month_now_str < 10){
                
                month_now_strs = "0"+month_now_str;
                
            } else {
                
                month_now_strs = ""+month_now_str;
                
            }
            
        }
        
        if (date_now_str < 10) {
            
            date_now_strs = "0"+date_now_str;
            
        } else {
            
            date_now_strs = ""+date_now_str;
            
        }
        
        if (minute_now_str < 10) {
            
            minute_now_strs = "0"+minute_now_str;
            
        } else {
            
            minute_now_strs = ""+minute_now_str;
            
        }
        
        if (hour_now_str < 10) {
            
            hour_now_strs = "0"+hour_now_str;
            
        } else {
            
            hour_now_strs = ""+hour_now_str;
            
        }
        
        date_label = date_now_strs+month_now_strs+year_now_strs+"@"+hour_now_strs+minute_now_strs;
        
        return date_label;
        
    }
    
    
    public void generatePdf() {
        
        java.lang.Process wait_for_Pdf2Show;
        
        java.util.Calendar cal = java.util.Calendar.getInstance();
        
        java.util.Date dateStampPdf = cal.getTime();
        
        java.lang.String pdfDateStamp = dateStampPdf.toString();
        
        
        try {
            
            java.io.File tempFile = java.io.File.createTempFile("HR_REP"+this.getDateLable()+"_", ".pdf");
            
            tempFile.deleteOnExit();
            
            java.lang.Runtime rt = java.lang.Runtime.getRuntime();
            
            java.lang.String debitTotal = null;
            
            java.lang.String creditTotal = null;
            
            // com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());
            
            try{
                try {
                    
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    
                    
                    
     /*    try {
      
            java.lang.Class.forName("org.postgresql.Driver");
      
        } catch(java.lang.ClassNotFoundException cnfExec) {
      
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), cnfExec.getMessage());
      
        }
      */
                    
                    
                    String compName = null;
                    String date = null;
                    try {
                        
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                        
                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT UPPER(organisation_name) from hr.hr_company_profile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while(rset2.next()){
                            compName = rset2.getObject(1).toString();
                        }
                        while(rset4.next()){
                            date = rset4.getObject(1).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+"",pFontHeader),false);
                        
                        // com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        headerFoter.setRight(7);
                        docPdf.setHeader(headerFoter);
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase(
                    "FunSoft Human Resources Management Systems                         Printed On: "+date+"               Page:",pFontHeader2), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(5);
                        
                        int headerwidths[] = {10,15,40,15,20};
                        
                        table1.setWidths(headerwidths);
                        
                        table1.setWidthPercentage((100));
                        
                        table1.setHeaderRows(1);
                        
                        
                        Phrase phrase1 = new Phrase("");
                        
                        table1.getDefaultCell().setColspan(5);
                        table1.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        
                        try {
                            java.sql.Statement st1 = connectDB.createStatement();
                            //java.sql.Statement st2 = connectDB.createStatement();
                            //java.sql.Statement st3 = connectDB.createStatement();
                            //java.sql.Statement st4 = connectDB.createStatement();
                            //java.sql.Statement st5 = connectDB.createStatement();
                            //java.sql.Statement st6 = connectDB.createStatement();
                            
                            java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                            
                            for (int j = 0; j < listofStaffNos.length; j++) {
                                java.sql.Statement st = connectDB.createStatement();
                                
                                
                                java.sql.ResultSet rset1 = st1.executeQuery("select staff_id,empmt_date,initcap(first_name||' '||second_name||' '||others) AS staff_names from  hr.hr_staffresume_fileinfo where staff_id = '"+listofStaffNos[j]+"'");
                                //java.sql.ResultSet rset4 = st2.executeQuery("SELECT date('now') as Date");
                                
                                
                                table1.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
                                table1.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase1 = new Phrase( " APPLICANT PROFILE " , pFontHeader3);
                                
                                table1.addCell(phrase1);
                                
                                table1.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table1.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
                                table1.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                                phrase1 = new Phrase( " File Details " , pFontHeader);
                                //table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                table1.addCell(phrase1);
                                table1.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                                phrase1 = new Phrase(" " , pFontHeader2);
                                
                                table1.addCell(phrase1);
                                
                                while (rset1.next()) {
                                    
                                    table1.getDefaultCell().setColspan(1);
                                    table1.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
                                    table1.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    
                                    phrase1 = new Phrase("Staff No. : " , pFontHeader2);
                                    table1.addCell(phrase1);
                                    table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table1.getDefaultCell().setColspan(1);
                                    // table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //phrase2 = new Phrase(rset4.getObject(1).toString(), pFontHeader1);
                                    //table2.addCell(phrase2);
                                    //   table.getDefaultCell().setColspan(1);
                                    
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase1 = new Phrase("Employment date :", pFontHeader2);
                                    table1.addCell(phrase1);
                                    table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table1.getDefaultCell().setColspan(1);
                                    //table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //phrase2 = new Phrase(rset4.getObject(2).toString(), pFontHeader);
                                    //table2.addCell(phrase2);
                                    
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    phrase1 = new Phrase("Staff Names :",pFontHeader2);
                                    table1.addCell(phrase1);
                                    table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table1.getDefaultCell().setColspan(1);
                                    //table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //phrase2 = new Phrase(rset4.getObject(3).toString(), pFontHeader1);
                                    //table2.addCell(phrase2);
                                    
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase1 = new Phrase("Profile Date. : ", pFontHeader2);
                                    table1.addCell(phrase1);
                                    table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table1.getDefaultCell().setColspan(1);
                                    
                                    
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase1 = new Phrase("Status  " , pFontHeader2);
                                    table1.addCell(phrase1);
                                    table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table1.getDefaultCell().setColspan(1);
                                    
                                    
                                    
                                    
                                    
                                    table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    
                                    table1.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
                                    
                                    
                                    
                                    
                                    //table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //phrase2 = new Phrase("Staff Name :     "+rset2.getObject(1).toString(), pFontHeader1);
                                    
                                    
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase1 = new Phrase(rset1.getObject(1).toString(), pFontHeader1);
                                    table1.addCell(phrase1);
                                    table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table1.getDefaultCell().setColspan(1);
                                    //   table.getDefaultCell().setColspan(1);
                                    
                                    //table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //phrase1 = new Phrase("EMPLOYER'S NUMBER : "+rset2.getObject(2).toString(), pFontHeader1);
                                    //table1.addCell(phrase2);
                                    
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase1 = new Phrase(rset1.getObject(2).toString(), pFontHeader1);
                                    table1.addCell(phrase1);
                                    table1.getDefaultCell().setColspan(1);
                                    //table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //phrase1 = new Phrase("ADDRESS :         "+rset2.getObject(3).toString(), pFontHeader1);
                                    //table1.addCell(phrase2);
                                    
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase1 = new Phrase(rset1.getObject(3).toString(), pFontHeader1);
                                    table1.addCell(phrase1);
                                    table1.getDefaultCell().setColspan(1);
                                    //table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //phrase2 = new Phrase("TEL No. :            "+rset2.getObject(4).toString(), pFontHeader1);
                                    //table2.addCell(phrase2);
                                    
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase1 = new Phrase(date, pFontHeader1);
                                    table1.addCell(phrase1);
                                    table1.getDefaultCell().setColspan(1);
                                    
                                    
                                    
                                    table1.getDefaultCell().setColspan(1);
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase1 = new Phrase("Casual  " , pFontHeader1);
                                    
                                    
                                    table1.addCell(phrase1);
                                    
                                    table1.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                    
                                   /* table1.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                                    
                                    table1.getDefaultCell().setColspan(5);
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase1 = new Phrase( " Demographics " , pFontHeader3);
                                    
                                table1.addCell(phrase1);
                                    
                                    table1.getDefaultCell().setBorder(Rectangle.BOTTOM);*/
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    docPdf.add(table1);
                                }
                            }
                            
                            
                        } catch(java.sql.SQLException SqlExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        
                    } catch(com.lowagie.text.BadElementException BadElExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                        
                    }
                    
                    
                    
                    
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table2 = new com.lowagie.text.pdf.PdfPTable(10);
                        
                        int headerwidths[] = {8,10,10,11,11,10,10,10,10,10};
                        
                        table2.setWidths(headerwidths);
                        
                        table2.setWidthPercentage((100));
                        
                        table2.setHeaderRows(1);
                        
                        
                        Phrase phrase2 = new Phrase("");
                        
                        table2.getDefaultCell().setColspan(10);
                        
                        //table2.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        
                        
                        try {
                            java.sql.Statement st1 = connectDB.createStatement();
                            //java.sql.Statement st2 = connectDB.createStatement();
                            //java.sql.Statement st3 = connectDB.createStatement();
                            //java.sql.Statement st4 = connectDB.createStatement();
                            //java.sql.Statement st5 = connectDB.createStatement();
                            //java.sql.Statement st6 = connectDB.createStatement();
                            
                            java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                            
                            for (int j = 0; j < listofStaffNos.length; j++) {
                                java.sql.Statement st2 = connectDB.createStatement();
                                
                                
                                java.sql.ResultSet rset2 = st2.executeQuery("select gender,birth_date,marital_status,handicup_status,initcap(current_residence),initcap(ethnicity),national_id_no,nssf_no,nhif_no from  hr.hr_staffresume_fileinfo where staff_id = '"+listofStaffNos[j]+"'");
                                //java.sql.ResultSet rset4 = st2.executeQuery("SELECT date('now') as Date");
                                
                                
                                table2.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table2.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
                                table2.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                                phrase2 = new Phrase( " Demographics " , pFontHeader);
                                
                                table2.addCell(phrase2);
                                
                                
                                table2.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                                phrase2 = new Phrase(" " , pFontHeader2);
                                
                                table2.addCell(phrase2);
                                
                                while (rset2.next()) {
                                    
                                    table2.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table2.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
                                    table2.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                    table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    table2.getDefaultCell().setColspan(1);
                                    
                                    phrase2 = new Phrase("Sex. : " , pFontHeader2);
                                    table2.addCell(phrase2);
                                    table2.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table2.getDefaultCell().setColspan(1);
                                    // table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //phrase2 = new Phrase(rset4.getObject(1).toString(), pFontHeader1);
                                    //table2.addCell(phrase2);
                                    //   table.getDefaultCell().setColspan(1);
                                    
                                    table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    
                                    phrase2 = new Phrase("Date Of Birth :", pFontHeader2);
                                    table2.addCell(phrase2);
                                    table2.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table2.getDefaultCell().setColspan(1);
                                    //table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //phrase2 = new Phrase(rset4.getObject(2).toString(), pFontHeader);
                                    //table2.addCell(phrase2);
                                    
                                    table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase2 = new Phrase("Marital Status :",pFontHeader2);
                                    table2.addCell(phrase2);
                                    table2.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table2.getDefaultCell().setColspan(1);
                                    //table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //phrase2 = new Phrase(rset4.getObject(3).toString(), pFontHeader1);
                                    //table2.addCell(phrase2);
                                    table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase2 = new Phrase("Handicup status  " , pFontHeader2);
                                    table2.addCell(phrase2);
                                    table2.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table2.getDefaultCell().setColspan(1);
                                    
                                    
                                    table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase2 = new Phrase("Current Residence : ", pFontHeader2);
                                    table2.addCell(phrase2);
                                    table2.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table2.getDefaultCell().setColspan(1);
                                    
                                    
                                    
                                    table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase2 = new Phrase("Citizenship. : " , pFontHeader2);
                                    table2.addCell(phrase2);
                                    table2.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table2.getDefaultCell().setColspan(1);
                                    // table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //phrase2 = new Phrase(rset4.getObject(1).toString(), pFontHeader1);
                                    //table2.addCell(phrase2);
                                    //   table.getDefaultCell().setColspan(1);
                                    
                                    table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase2 = new Phrase("Ethnicity :", pFontHeader2);
                                    table2.getDefaultCell().setColspan(1);
                                    table2.addCell(phrase2);
                                    table2.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    //table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //phrase2 = new Phrase(rset4.getObject(2).toString(), pFontHeader);
                                    //table2.addCell(phrase2);
                                    
                                    table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase2 = new Phrase("National ID No. :",pFontHeader2);
                                    table2.addCell(phrase2);
                                    table2.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table2.getDefaultCell().setColspan(1);
                                    //table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //phrase2 = new Phrase(rset4.getObject(3).toString(), pFontHeader1);
                                    //table2.addCell(phrase2);
                                    
                                    table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase2 = new Phrase("NSSF No. : ", pFontHeader2);
                                    table2.addCell(phrase2);
                                    table2.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table2.getDefaultCell().setColspan(1);
                                    
                                    
                                    table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase2 = new Phrase("NHIF NO.  " , pFontHeader2);
                                    table2.getDefaultCell().setColspan(1);
                                    table2.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table2.addCell(phrase2);
                                    
                                    table2.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
                                    
                                    
                                    
                                    //table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //phrase2 = new Phrase("Staff Name :     "+rset2.getObject(1).toString(), pFontHeader1);
                                    
                                    
                                    table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase2 = new Phrase(dbObject.getDBObject(rset2.getObject(1), "-"), pFontHeader1);
                                    table2.addCell(phrase2);
                                    table2.getDefaultCell().setColspan(1);
                                    
                                    
                                    table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase2 = new Phrase(dbObject.getDBObject(rset2.getObject(2), "-"), pFontHeader1);
                                    table2.addCell(phrase2);
                                    table2.getDefaultCell().setColspan(1);
                                    
                                    table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase2 = new Phrase(dbObject.getDBObject(rset2.getObject(3), "-"), pFontHeader1);
                                    table2.addCell(phrase2);
                                    table2.getDefaultCell().setColspan(1);
                                    
                                    table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase2 = new Phrase(dbObject.getDBObject(rset2.getObject(4), "-"), pFontHeader1);
                                    table2.addCell(phrase2);
                                    table2.getDefaultCell().setColspan(1);
                                    
                                    
                                    table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase2 = new Phrase(dbObject.getDBObject(rset2.getObject(5), "-"), pFontHeader1);
                                    table2.addCell(phrase2);
                                    table2.getDefaultCell().setColspan(1);
                                    
                                    
                                    table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase2 = new Phrase("Kenyan ", pFontHeader1);
                                    table2.addCell(phrase2);
                                    table2.getDefaultCell().setColspan(1);
                                    
                                    
                                    
                                    table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase2 = new Phrase(dbObject.getDBObject(rset2.getObject(6), "-"), pFontHeader1);
                                    table2.addCell(phrase2);
                                    table2.getDefaultCell().setColspan(1);
                                    
                                    
                                    
                                    //table1.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                    
                                    table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase2 = new Phrase(dbObject.getDBObject(rset2.getObject(7), "-"), pFontHeader1);
                                    table2.addCell(phrase2);
                                    table2.getDefaultCell().setColspan(1);
                                    
                                    table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase2 = new Phrase(dbObject.getDBObject(rset2.getObject(8), "-"), pFontHeader1);
                                    table2.addCell(phrase2);
                                    table2.getDefaultCell().setColspan(1);
                                    
                                    
                                    table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase2 = new Phrase(dbObject.getDBObject(rset2.getObject(9), "-"), pFontHeader1);
                                    table2.addCell(phrase2);
                                    table2.getDefaultCell().setColspan(1);
                                    
                                    /* table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase2 = new Phrase(dbObject.getDBObject(rset2.getObject(8), "-"), pFontHeader1);
                                    table2.addCell(phrase2);
                                    table2.getDefaultCell().setColspan(1);*/
                                    
                                    table2.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                    
                                    
                                    docPdf.add(table2);
                                }
                            }
                            
                            
                        } catch(java.sql.SQLException SqlExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        
                    } catch(com.lowagie.text.BadElementException BadElExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                        
                    }
                    
                    
                    
                    
                    
                    
                    
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table3 = new com.lowagie.text.pdf.PdfPTable(7);
                        
                        int headerwidths[] = {15,12,13,15,15,15,15};
                        
                        table3.setWidths(headerwidths);
                        
                        table3.setWidthPercentage((100));
                        
                        table3.setHeaderRows(1);
                        
                        
                        Phrase phrase3 = new Phrase("");
                        
                        table3.getDefaultCell().setColspan(7);
                        
                        //table2.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        
                        
                        try {
                            //java.sql.Statement st3 = connectDB.createStatement();
                            //java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            //java.sql.Statement st4 = connectDB.createStatement();
                            //java.sql.Statement st5 = connectDB.createStatement();
                            //java.sql.Statement st6 = connectDB.createStatement();
                            
                            java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                            
                            for (int j = 0; j < listofStaffNos.length; j++) {
                                java.sql.Statement sst3 = connectDB.createStatement();
                                
                                
                                java.sql.ResultSet rset3 = sst3.executeQuery("select (postal_code||'-'||postal_address) as add,email,(tel_no1||','||mobile_1) as mobile,(nok_tel||','||nok_mobile) as tel2,(nok_postal_code||'-'||nok_postal_address) as add2 from hr.hr_staffresume_add  where staff_id = '"+listofStaffNos[j]+"'");
                                //java.sql.ResultSet rset4 = st2.executeQuery("SELECT date('now') as Date");
                                
                                
                                table3.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table3.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
                                table3.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                                phrase3 = new Phrase( " Contacts " , pFontHeader);
                                
                                table3.addCell(phrase3);
                                
                                table3.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                                phrase3 = new Phrase(" " , pFontHeader2);
                                
                                //phrase1 = new Phrase(" " , pFontHeader2);
                                
                                // table1.addCell(phrase1);
                                table3.addCell(phrase3);
                                
                                
                                while (rset3.next()) {
                                    
                                    table3.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table3.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
                                    table3.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                    table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    table3.getDefaultCell().setColspan(1);
                                    
                                    phrase3 = new Phrase("Postal Address : " , pFontHeader2);
                                    table3.addCell(phrase3);
                                    table3.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table3.getDefaultCell().setColspan(1);
                                    // table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //phrase2 = new Phrase(rset4.getObject(1).toString(), pFontHeader1);
                                    //table2.addCell(phrase2);
                                    //   table.getDefaultCell().setColspan(1);
                                    
                                    table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    
                                    phrase3 = new Phrase("EMail Address :", pFontHeader2);
                                    table3.addCell(phrase3);
                                    table3.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table3.getDefaultCell().setColspan(1);
                                    //table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //phrase2 = new Phrase(rset4.getObject(2).toString(), pFontHeader);
                                    //table2.addCell(phrase2);
                                    
                                    table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    
                                    phrase3 = new Phrase("Telephone No. :", pFontHeader2);
                                    table3.addCell(phrase3);
                                    table3.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table3.getDefaultCell().setColspan(1);
                                    //table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //phrase2 = new Phrase(rset4.getObject(3).toString(), pFontHeader1);
                                    //table2.addCell(phrase2);
                                    table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    
                                    phrase3 = new Phrase("Emergency Telephone No. :", pFontHeader2);
                                    table3.addCell(phrase3);
                                    table3.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table3.getDefaultCell().setColspan(1);;
                                    
                                    
                                    table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    
                                    phrase3 = new Phrase("Emergency Postal Address. :", pFontHeader2);
                                    table3.addCell(phrase3);
                                    table3.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table3.getDefaultCell().setColspan(1);
                                    
                                    
                                    
                                    table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    
                                    phrase3 = new Phrase("Spouse Names :", pFontHeader2);
                                    table3.addCell(phrase3);
                                    table3.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table3.getDefaultCell().setColspan(1);
                                    // table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //phrase2 = new Phrase(rset4.getObject(1).toString(), pFontHeader1);
                                    //table2.addCell(phrase2);
                                    //   table.getDefaultCell().setColspan(1);
                                    
                                    table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    
                                    phrase3 = new Phrase("Spouse Status :", pFontHeader2);
                                    table3.addCell(phrase3);
                                    table3.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table3.getDefaultCell().setColspan(1);
                                    //table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //phrase2 = new Phrase(rset4.getObject(2).toString(), pFontHeader);
                                    //table2.addCell(phrase2);
                                    
                                    
                                    
                                    table3.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
                                    
                                    
                                    
                                    //table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //phrase2 = new Phrase("Staff Name :     "+rset2.getObject(1).toString(), pFontHeader1);
                                    
                                    
                                    table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase3 = new Phrase(dbObject.getDBObject(rset3.getObject(1), "-"), pFontHeader1);
                                    table3.addCell(phrase3);
                                    table3.getDefaultCell().setColspan(1);
                                    
                                    
                                    table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase3 = new Phrase(dbObject.getDBObject(rset3.getObject(2), "-"), pFontHeader1);
                                    table3.addCell(phrase3);
                                    table3.getDefaultCell().setColspan(1);
                                    
                                    table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase3 = new Phrase(dbObject.getDBObject(rset3.getObject(3), "-"), pFontHeader1);
                                    table3.addCell(phrase3);
                                    table3.getDefaultCell().setColspan(1);
                                    
                                    table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase3 = new Phrase(dbObject.getDBObject(rset3.getObject(4), "-"), pFontHeader1);
                                    table3.addCell(phrase3);
                                    table3.getDefaultCell().setColspan(1);
                                    
                                    
                                    
                                    table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase3 = new Phrase(dbObject.getDBObject(rset3.getObject(5), "-"), pFontHeader1);
                                    table3.addCell(phrase3);
                                    table3.getDefaultCell().setColspan(1);
                                    
                                    
                                    table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase3 = new Phrase("-", pFontHeader1);
                                    table3.addCell(phrase3);
                                    table3.getDefaultCell().setColspan(1);
                                    
                                    
                                    table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase3 = new Phrase("-", pFontHeader1);
                                    table3.addCell(phrase3);
                                    table3.getDefaultCell().setColspan(1);
                                    
                                    
                                    
                                    
                                    
                                    table3.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                    
                                    
                                    docPdf.add(table3);
                                }
                            }
                            
                            
                        } catch(java.sql.SQLException SqlExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        
                    } catch(com.lowagie.text.BadElementException BadElExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                        
                    }
                    
                    
                    
                    
                    
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table4 = new com.lowagie.text.pdf.PdfPTable(14);
                        
                        int headerwidths[] = {8,9,9,5,5,9,9,8,5,5,8,10,5,5};
                        
                        table4.setWidths(headerwidths);
                        
                        table4.setWidthPercentage((100));
                        
                        table4.setHeaderRows(1);
                        
                        
                        Phrase phrase4 = new Phrase("");
                        
                        table4.getDefaultCell().setColspan(5);
                        
                        //table2.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        
                        
                        
                        try {
                            
                            //java.sql.Statement st1 = connectDB.createStatement();
                            
                            /// java.sql.Statement st2 = connectDB.createStatement
                            
                            //java.sql.Statement st3 = connectDB.createStatement();
                            //java.sql.Statement st2 = connectDB.createStatement();
                            // java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st4 = connectDB.createStatement();
                            //java.sql.Statement st5 = connectDB.createStatement();
                            //java.sql.Statement st6 = connectDB.createStatement();
                            
                            java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                            
                            for (int j = 0; j < listofStaffNos.length; j++) {
                                java.sql.Statement sst4 = connectDB.createStatement();
                                
                                
                                java.sql.ResultSet rset4 = sst4.executeQuery("select course,institution,date_of_qualif,grade from hr.hr_staffresume_qualf  where staff_id = '"+listofStaffNos[j]+"'");
                                //java.sql.ResultSet rset4 = st2.executeQuery("SELECT date('now') as Date");
                                
                                
                            /*     table4.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table4.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
                                table4.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                                phrase4 = new Phrase( " Contacts " , pFontHeader);
                             
                                table4.addCell(phrase4);
                                 table4.getDefaultCell().setColspan(5);*/
                                
                                table4.getDefaultCell().setColspan(5);table4.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table4.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
                                table4.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                                // table4.addCell(phrase4);
                                phrase4 = new Phrase( " Highest Education Level " , pFontHeader);
                                
                               /* phrase4 = new Phrase("Level : " , pFontHeader2);
                                    table4.addCell(phrase4);
                                     table4.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table4.getDefaultCell().setColspan(1);*/
                                
                                table4.addCell(phrase4);
                                table4.getDefaultCell().setColspan(5);
                                
                                table4.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table4.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
                                table4.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                                phrase4 = new Phrase( " Other Education Levels " , pFontHeader);
                                
                                table4.addCell(phrase4);
                                table4.getDefaultCell().setColspan(4); table4.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table4.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
                                table4.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                                phrase4 = new Phrase( " Proffesional Qualifications " , pFontHeader);
                                table4.addCell(phrase4);
                                
                                
                                table4.getDefaultCell().setColspan(14);
                                
                                
                                table4.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                                phrase4 = new Phrase(" " , pFontHeader2);
                                table4.addCell(phrase4);
                                
                                
                                while (rset4.next()) {
                                    
                                    table4.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table4.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
                                    table4.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                    table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    table4.getDefaultCell().setColspan(1);
                                    
                                    phrase4 = new Phrase("Level : " , pFontHeader2);
                                    table4.addCell(phrase4);
                                    table4.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table4.getDefaultCell().setColspan(1);
                                    // table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //phrase2 = new Phrase(rset4.getObject(1).toString(), pFontHeader1);
                                    //table2.addCell(phrase2);
                                    //   table.getDefaultCell().setColspan(1);
                                    
                                    table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    
                                    phrase4 = new Phrase("Major Subject : " , pFontHeader2);
                                    table4.addCell(phrase4);
                                    table4.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table4.getDefaultCell().setColspan(1);
                                    // table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //phrase2 = new Phrase(rset4.getObject(1).toString(), pFontHeader1);
                                    //table2.addCell(phrase2);
                                    //   table.getDefaultCell().setColspan(1);
                                    
                                    table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase4 = new Phrase("Institution : " , pFontHeader2);
                                    table4.addCell(phrase4);
                                    table4.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table4.getDefaultCell().setColspan(1);
                                    // table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //phrase2 = new Phrase(rset4.getObject(1).toString(), pFontHeader1);
                                    //table2.addCell(phrase2);
                                    //   table.getDefaultCell().setColspan(1);
                                    
                                  /* table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                   
                                 /*   phrase4 = new Phrase("Major Subject : " , pFontHeader2);
                                    table4.addCell(phrase4);
                                     table4.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table4.getDefaultCell().setColspan(1);
                                   
                                    table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase4 = new Phrase("Insitution : " , pFontHeader2);
                                    table4.addCell(phrase4);
                                     table4.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table4.getDefaultCell().setColspan(1);  */
                                    
                                    
                                    table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase4 = new Phrase("Grade : " , pFontHeader2);
                                    table4.addCell(phrase4);
                                    table4.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table4.getDefaultCell().setColspan(1);
                                    // table3.getDefaultCell().setColspan(1);
                                    
                                    
                                    table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase4 = new Phrase("Last Year : " , pFontHeader2);
                                    table4.addCell(phrase4);
                                    table4.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table4.getDefaultCell().setColspan(1);
                                    
                                    table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase4 = new Phrase("Level : " , pFontHeader2);
                                    table4.addCell(phrase4);
                                    table4.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table4.getDefaultCell().setColspan(1);
                                    table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase4 = new Phrase("Major Certificate : " , pFontHeader2);
                                    table4.addCell(phrase4);
                                    table4.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table4.getDefaultCell().setColspan(1);
                                    
                                    
                                    table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase4 = new Phrase("Institution : " , pFontHeader2);
                                    table4.addCell(phrase4);
                                    table4.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table4.getDefaultCell().setColspan(1);
                                    // table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //phrase2 = new Phrase(rset4.getObject(1).toString(), pFontHeader1);
                                    //table2.addCell(phrase2);
                                    //   table.getDefaultCell().setColspan(1);
                                    
                                    table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    
                                    phrase4 = new Phrase("Grade : " , pFontHeader2);
                                    table4.addCell(phrase4);
                                    table4.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table4.getDefaultCell().setColspan(1);
                                    
                                    table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase4 = new Phrase("Last Year: " , pFontHeader2);
                                    table4.addCell(phrase4);
                                    table4.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table4.getDefaultCell().setColspan(1);
                                    
                                    /*
                                     
                                    table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase4 = new Phrase("Grade : " , pFontHeader2);
                                    table4.addCell(phrase4);
                                     table4.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table4.getDefaultCell().setColspan(1);
                                    // table3.getDefaultCell().setColspan(1);
                                     */
                                    
                                    table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase4 = new Phrase("Course : " , pFontHeader2);
                                    table4.addCell(phrase4);
                                    table4.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table4.getDefaultCell().setColspan(1);
                                    
                                    table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase4 = new Phrase("Issuer: " , pFontHeader2);
                                    table4.addCell(phrase4);
                                    table4.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table4.getDefaultCell().setColspan(1);
                                    
                                    table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase4 = new Phrase("date Of Qualif : " , pFontHeader2);
                                    table4.addCell(phrase4);
                                    table4.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table4.getDefaultCell().setColspan(1);
                                    
                                    table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase4 = new Phrase("Grade : " , pFontHeader2);
                                    table4.addCell(phrase4);
                                    table4.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    
                                    
                                    table4.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
                                    
                                    
                                    
                                    //table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //phrase2 = new Phrase("Staff Name :     "+rset2.getObject(1).toString(), pFontHeader1);
                                    table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase4 = new Phrase("-", pFontHeader1);
                                    table4.addCell(phrase4);
                                    table4.getDefaultCell().setColspan(1);
                                    
                                    table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase4 = new Phrase("-", pFontHeader1);
                                    table4.addCell(phrase4);
                                    table4.getDefaultCell().setColspan(1);
                                    
                                    
                                    table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase4 = new Phrase("-", pFontHeader1);
                                    table4.addCell(phrase4);
                                    table4.getDefaultCell().setColspan(1);
                                    
                                    table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase4 = new Phrase("-", pFontHeader1);
                                    table4.addCell(phrase4);
                                    table4.getDefaultCell().setColspan(1);
                                    
                                    table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase4 = new Phrase("-", pFontHeader1);
                                    table4.addCell(phrase4);
                                    table4.getDefaultCell().setColspan(1);
                                    
                                    table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase4 = new Phrase("-", pFontHeader1);
                                    table4.addCell(phrase4);
                                    table4.getDefaultCell().setColspan(1);
                                    
                                    
                                    table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase4 = new Phrase("-", pFontHeader1);
                                    table4.addCell(phrase4);
                                    table4.getDefaultCell().setColspan(1);
                                    
                                    table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase4 = new Phrase("-", pFontHeader1);
                                    table4.addCell(phrase4);
                                    table4.getDefaultCell().setColspan(1);
                                    
                                    table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase4 = new Phrase("-", pFontHeader1);
                                    table4.addCell(phrase4);
                                    table4.getDefaultCell().setColspan(1);
                                    
                                    table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase4 = new Phrase("-", pFontHeader1);
                                    table4.addCell(phrase4);
                                    table4.getDefaultCell().setColspan(1);
                                    
                                    
                                    
                                    table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase4 = new Phrase(dbObject.getDBObject(rset4.getObject(1), "-"), pFontHeader1);
                                    table4.addCell(phrase4);
                                    table4.getDefaultCell().setColspan(1);
                                    
                                    
                                    table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase4 = new Phrase(dbObject.getDBObject(rset4.getObject(2), "-"), pFontHeader1);
                                    table4.addCell(phrase4);
                                    table4.getDefaultCell().setColspan(1);
                                    
                                    table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase4 = new Phrase(dbObject.getDBObject(rset4.getObject(3), "-"), pFontHeader1);
                                    table4.addCell(phrase4);
                                    table4.getDefaultCell().setColspan(1);
                                    
                                    
                                    table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase4 = new Phrase(dbObject.getDBObject(rset4.getObject(4), "-"), pFontHeader1);
                                    table4.addCell(phrase4);
                                    table4.getDefaultCell().setColspan(1);
                                    
                                    
                                    
                                    
                                    
                                    table4.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                    
                                    
                                    docPdf.add(table4);
                                }
                            }
                            
                            
                        } catch(java.sql.SQLException SqlExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        
                    } catch(com.lowagie.text.BadElementException BadElExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                        
                    }
                    
                    
                    
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table5 = new com.lowagie.text.pdf.PdfPTable(12);
                        
                        int headerwidths[] = {12,12,12,7,5,8,8,8,8,6,6,8};
                        
                        table5.setWidths(headerwidths);
                        
                        table5.setWidthPercentage((100));
                        
                        table5.setHeaderRows(1);
                        
                        
                        Phrase phrase5 = new Phrase("");
                        
                        table5.getDefaultCell().setColspan(5);
                        
                        //table2.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        
                        
                        
                        try {
                            //java.sql.Statement st3 = connectDB.createStatement();
                            //java.sql.Statement st2 = connectDB.createStatement();
                            // java.sql.Statement st3 = connectDB.createStatement();
                            //java.sql.Statement st4 = connectDB.createStatement();
                            java.sql.Statement st5 = connectDB.createStatement();
                            //java.sql.Statement st6 = connectDB.createStatement();
                            
                            java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                            
                            for (int j = 0; j < listofStaffNos.length; j++) {
                                java.sql.Statement sst5 = connectDB.createStatement();
                                
                                
                                java.sql.ResultSet rset5 = sst5.executeQuery("select dept,section,designation,job_group,jgrade from hr.hr_staffresume_fileinfo  where staff_id = '"+listofStaffNos[j]+"'");
                                //java.sql.ResultSet rset4 = st2.executeQuery("SELECT date('now') as Date");
                                
                                
                            /*     table4.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table4.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
                                table4.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                                phrase4 = new Phrase( " Contacts " , pFontHeader);
                             
                                table4.addCell(phrase4);
                                 table4.getDefaultCell().setColspan(5);*/
                                
                                table5.getDefaultCell().setColspan(5);table5.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table5.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table5.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
                                table5.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                                // table4.addCell(phrase4);
                                phrase5 = new Phrase( " Department " , pFontHeader);
                                
                               /* phrase4 = new Phrase("Level : " , pFontHeader2);
                                    table4.addCell(phrase4);
                                     table4.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table4.getDefaultCell().setColspan(1);*/
                                
                                table5.addCell(phrase5);
                                table5.getDefaultCell().setColspan(4);
                                
                                table5.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table5.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table5.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
                                table5.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                                phrase5 = new Phrase( " Compensation " , pFontHeader);
                                
                                table5.addCell(phrase5);
                                table5.getDefaultCell().setColspan(2);
                                table5.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table5.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table5.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
                                table5.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                                phrase5 = new Phrase( " Schedule " , pFontHeader);
                                
                                
                                table5.addCell(phrase5);
                                table5.getDefaultCell().setColspan(1);
                                table5.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table5.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table5.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
                                table5.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                                phrase5 = new Phrase( " Performance " , pFontHeader);
                                
                                table5.addCell(phrase5);
                                //table4.getDefaultCell().setColspan(5);
                                // table4.getDefaultCell().setColspan(1);
                                
                                table5.getDefaultCell().setColspan(14);
                                
                                
                                table5.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                                phrase5 = new Phrase(" " , pFontHeader2);
                                table5.addCell(phrase5);
                                
                                
                                while (rset5.next()) {
                                    
                                    table5.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table5.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
                                    table5.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                    table5.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    table5.getDefaultCell().setColspan(1);
                                    
                                    phrase5 = new Phrase("Dept Name : " , pFontHeader2);
                                    table5.addCell(phrase5);
                                    table5.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table5.getDefaultCell().setColspan(1);
                                    // table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //phrase2 = new Phrase(rset4.getObject(1).toString(), pFontHeader1);
                                    //table2.addCell(phrase2);
                                    //   table.getDefaultCell().setColspan(1);
                                    
                                    table5.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    
                                    phrase5 = new Phrase("Section : " , pFontHeader2);
                                    table5.addCell(phrase5);
                                    table5.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table5.getDefaultCell().setColspan(1);
                                    // table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //phrase2 = new Phrase(rset4.getObject(1).toString(), pFontHeader1);
                                    //table2.addCell(phrase2);
                                    //   table.getDefaultCell().setColspan(1);
                                    
                                    table5.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase5 = new Phrase("Designation : " , pFontHeader2);
                                    table5.addCell(phrase5);
                                    table5.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table5.getDefaultCell().setColspan(1);
                                    // table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //phrase2 = new Phrase(rset4.getObject(1).toString(), pFontHeader1);
                                    //table2.addCell(phrase2);
                                    //   table.getDefaultCell().setColspan(1);
                                    
                                  /* table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                   
                                 /*   phrase4 = new Phrase("Major Subject : " , pFontHeader2);
                                    table4.addCell(phrase4);
                                     table4.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table4.getDefaultCell().setColspan(1);
                                   
                                    table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase4 = new Phrase("Insitution : " , pFontHeader2);
                                    table4.addCell(phrase4);
                                     table4.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table4.getDefaultCell().setColspan(1);  */
                                    
                                    
                                    table5.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase5 = new Phrase("Job Group : " , pFontHeader2);
                                    table5.addCell(phrase5);
                                    table5.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table5.getDefaultCell().setColspan(1);
                                    // table3.getDefaultCell().setColspan(1);
                                    
                                    
                                    table5.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase5 = new Phrase("Job Grade : " , pFontHeader2);
                                    table5.addCell(phrase5);
                                    table5.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table5.getDefaultCell().setColspan(1);
                                    
                                    table5.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase5 = new Phrase("Basic Pay : " , pFontHeader2);
                                    table5.addCell(phrase5);
                                    table5.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table5.getDefaultCell().setColspan(1);
                                    
                                    table5.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase5 = new Phrase("Benefits : " , pFontHeader2);
                                    table5.addCell(phrase5);
                                    table5.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table5.getDefaultCell().setColspan(1);
                                    
                                    
                                    table5.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase5 = new Phrase("Net pay : " , pFontHeader2);
                                    table5.addCell(phrase5);
                                    table5.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table5.getDefaultCell().setColspan(1);
                                    // table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //phrase2 = new Phrase(rset4.getObject(1).toString(), pFontHeader1);
                                    //table2.addCell(phrase2);
                                    //   table.getDefaultCell().setColspan(1);
                                    
                                    table5.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    
                                    phrase5 = new Phrase("Annual salary : " , pFontHeader2);
                                    table5.addCell(phrase5);
                                    table5.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table5.getDefaultCell().setColspan(1);
                                    
                                    table5.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase5 = new Phrase("Shift Hrs: " , pFontHeader2);
                                    table5.addCell(phrase5);
                                    table5.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table5.getDefaultCell().setColspan(1);
                                    
                                    table5.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase5 = new Phrase("Work Hrs: " , pFontHeader2);
                                    table5.addCell(phrase5);
                                    table5.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table5.getDefaultCell().setColspan(1);
                                    
                                    
                                    
                                    table5.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase5 = new Phrase("Rating : " , pFontHeader2);
                                    table5.addCell(phrase5);
                                    table5.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table5.getDefaultCell().setColspan(1);
                                    // table3.getDefaultCell().setColspan(1);
                                    
                                    
                                    
                                    table5.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    
                                    
                                    table5.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
                                    
                                    
                                    
                                    
                                    table5.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase5 = new Phrase(dbObject.getDBObject(rset5.getObject(1), "-"), pFontHeader1);
                                    table5.addCell(phrase5);
                                    table5.getDefaultCell().setColspan(1);
                                    
                                    
                                    table5.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase5 = new Phrase(dbObject.getDBObject(rset5.getObject(2), "-"), pFontHeader1);
                                    table5.addCell(phrase5);
                                    table5.getDefaultCell().setColspan(1);
                                    
                                    table5.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase5 = new Phrase(dbObject.getDBObject(rset5.getObject(3), "-"), pFontHeader1);
                                    table5.addCell(phrase5);
                                    table5.getDefaultCell().setColspan(1);
                                    
                                    table5.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase5 = new Phrase(dbObject.getDBObject(rset5.getObject(4), "-"), pFontHeader1);
                                    table5.addCell(phrase5);
                                    table5.getDefaultCell().setColspan(1);
                                    
                                    table5.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase5 = new Phrase(dbObject.getDBObject(rset5.getObject(5), "-"), pFontHeader1);
                                    table5.addCell(phrase5);
                                    table5.getDefaultCell().setColspan(1);
                                    //table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //phrase2 = new Phrase("Staff Name :     "+rset2.getObject(1).toString(), pFontHeader1);
                                    table5.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase5 = new Phrase("-", pFontHeader1);
                                    table5.addCell(phrase5);
                                    table5.getDefaultCell().setColspan(1);
                                    
                                    table5.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase5 = new Phrase("-", pFontHeader1);
                                    table5.addCell(phrase5);
                                    table5.getDefaultCell().setColspan(1);
                                    
                                    
                                    table5.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase5 = new Phrase("-", pFontHeader1);
                                    table5.addCell(phrase5);
                                    table5.getDefaultCell().setColspan(1);
                                    
                                    table5.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase5 = new Phrase("-", pFontHeader1);
                                    table5.addCell(phrase5);
                                    table5.getDefaultCell().setColspan(1);
                                    
                                    table5.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase5 = new Phrase("-", pFontHeader1);
                                    table5.addCell(phrase5);
                                    table5.getDefaultCell().setColspan(1);
                                    
                                    table5.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase5 = new Phrase("-", pFontHeader1);
                                    table5.addCell(phrase5);
                                    table5.getDefaultCell().setColspan(1);
                                    
                                    table5.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase5 = new Phrase("-", pFontHeader1);
                                    table5.addCell(phrase5);
                                    table5.getDefaultCell().setColspan(1);
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    table5.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                    
                                    
                                    docPdf.add(table5);
                                }
                            }
                            
                            
                            
                        } catch(java.sql.SQLException SqlExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        
                    } catch(com.lowagie.text.BadElementException BadElExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                        
                    }
                    
                    
                    //HAPA JOOOOOOOO
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table6 = new com.lowagie.text.pdf.PdfPTable(11);
                     
                        
                        int headerwidths[] = {15,10,7,7,6,6,10,10,9,10,10};
                        
                        table6.setWidths(headerwidths);
                       
                        table6.setWidthPercentage((100));
                       
                        table6.setHeaderRows(1);
                       
                        
                        Phrase phrase6 = new Phrase("");
                       
                        
                        table6.getDefaultCell().setColspan(6);
                       
                        table6.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table6.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
                        table6.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        
                        phrase6 = new Phrase( "Previous Employment Data " , pFontHeader);
                        
                              
                        table6.addCell(phrase6);
                        table6.getDefaultCell().setColspan(5);
                        
                     
                        table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);                     
                        phrase6 = new Phrase( " Referees " , pFontHeader);                        
                        table6.addCell(phrase6);
                        table6.getDefaultCell().setColspan(11);
                        
                        
                        table6.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
                        phrase6 = new Phrase(" " , pFontHeader2);
                                                
                        table6.addCell(phrase6);
                        
                        
                        table6.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table6.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table6.getDefaultCell().setBorderColor(java.awt.Color.BLACK);                       
                        table6.getDefaultCell().setColspan(1);
                   
                        
                        table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase6 = new Phrase("Employer : " , pFontHeader2);
                        table6.addCell(phrase6);                    
                        
                        
                        table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);                        
                        phrase6 = new Phrase("Position : " , pFontHeader2);
                        table6.addCell(phrase6);
                       
                        
                        
                        table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);                        
                        phrase6 = new Phrase("Begin Date : " , pFontHeader2);
                        table6.addCell(phrase6);
                        
                        
                        
                        table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);                        
                        phrase6 = new Phrase("End Date : " , pFontHeader2);
                        table6.addCell(phrase6);
                        
                        
                        table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase6 = new Phrase("Annual Salary : " , pFontHeader2);
                        table6.addCell(phrase6);
                      
                        
                        table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase6 = new Phrase("Termination date : " , pFontHeader2);
                        table6.addCell(phrase6);
                        
                        
                        table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase6 = new Phrase("Name : " , pFontHeader2);
                        table6.addCell(phrase6);
                       
                        
                        table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);                        
                        phrase6 = new Phrase("Institution : " , pFontHeader2);
                        table6.addCell(phrase6);
                       
                        
                        table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase6 = new Phrase("Position:" , pFontHeader2);
                        table6.addCell(phrase6);
                      
                        
                        table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase6 = new Phrase("Postal Address: " , pFontHeader2);
                        table6.addCell(phrase6);
                       
                                                
                        table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);                        
                        phrase6 = new Phrase("Telephones : " , pFontHeader2);
                        table6.addCell(phrase6);
                      
                                               
                        table6.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
                        
                        
                        try {
                            
                            java.sql.Statement st6 = connectDB.createStatement();
                            java.sql.Statement st7 = connectDB.createStatement();
                            
                            java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                            
                            for (int j = 0; j < listofStaffNos.length; j++) {
                                java.sql.Statement sst6 = connectDB.createStatement();
                                java.sql.Statement sst7 = connectDB.createStatement();
                                
                                java.sql.ResultSet rset6 = sst6.executeQuery("select employer,positions,begin_date,end_date,annual_salary,termination_reason from hr.hr_staffresume_qualf where staff_id = '"+listofStaffNos[j]+"'");
                                java.sql.ResultSet rset7 = sst7.executeQuery("select name,org,title,postal,telephone from hr.hr_referees_xtras where staff_id = '"+listofStaffNos[j]+"'");
                               
                                
                                while (rset6.next()) {
                                    
                                    table6.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table6.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
                                    table6.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                    
                                    
                                    table6.getDefaultCell().setColspan(1);                                   
                                    table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase6 = new Phrase(dbObject.getDBObject(rset6.getObject(1), "-"), pFontHeader1);                                   
                                     table6.addCell(phrase6);
                                    
                                    table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);                                   
                                    phrase6 = new Phrase(dbObject.getDBObject(rset6.getObject(2), "-"), pFontHeader1);
                                    table6.addCell(phrase6);
                                    
                                    table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase6 = new Phrase(dbObject.getDBObject(rset6.getObject(3), "-"), pFontHeader1);
                                    table6.addCell(phrase6);
                                   
                                    table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase6 = new Phrase(dbObject.getDBObject(rset6.getObject(4), "-"), pFontHeader1);
                                    table6.addCell(phrase6);
                                    
                                    table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase6 = new Phrase(dbObject.getDBObject(rset6.getObject(5), "-"), pFontHeader1);
                                    table6.addCell(phrase6);
                                   
                                    table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase6 = new Phrase(dbObject.getDBObject(rset6.getObject(6), "-"), pFontHeader1);                                                                     
                                    table6.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                                                        
                                    table6.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                    
                                    table6.addCell(phrase6);
                                    
                                }
                                
                                
                                
                                while (rset7.next()) {
                                   
                                    table6.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table6.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
                                    table6.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                    table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    table6.getDefaultCell().setColspan(1);
                                    
                                    
                                    table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase6 = new Phrase(dbObject.getDBObject(rset7.getObject(1), "-"), pFontHeader1);
                                    table6.addCell(phrase6);
                                   
                                    
                                    
                                    table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase6 = new Phrase(dbObject.getDBObject(rset7.getObject(2), "-"), pFontHeader1);
                                    table6.addCell(phrase6);
                                  
                                    
                                    
                                    table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase6 = new Phrase(dbObject.getDBObject(rset7.getObject(3), "-"), pFontHeader1);
                                    table6.addCell(phrase6);
                                   
                                    
                                    
                                    table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase6 = new Phrase(dbObject.getDBObject(rset7.getObject(4), "-"), pFontHeader1);
                                    table6.addCell(phrase6);
                                    
                                    
                                    table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase6 = new Phrase(dbObject.getDBObject(rset7.getObject(5), "-"), pFontHeader1);
                                    table6.addCell(phrase6);                                                                      
                                    
                                    table6.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                                                        
                                    table6.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                    
                                    
                                    docPdf.add(table6);
                                    
                                    
                                }
                                
                            }
                            
                            
                            
                            
                            
                            
                            
                        } catch(java.sql.SQLException SqlExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        
                    } catch(com.lowagie.text.BadElementException BadElExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                        
                    }
                    
                    
                    
                    
              try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table7 = new com.lowagie.text.pdf.PdfPTable(8);
                     
                        
                        int headerwidths[] = {15,10,20,15,13,11,8,8};
                        
                        table7.setWidths(headerwidths);
                       
                        table7.setWidthPercentage((100));
                       
                        table7.setHeaderRows(1);
                       
                        
                        Phrase phrase7 = new Phrase("");
                       
                        
                        table7.getDefaultCell().setColspan(8);
                       
                        table7.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table7.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table7.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
                        table7.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        
                        phrase7 = new Phrase( "Extra Curricular & Responsibilities " , pFontHeader);
                          table7.addCell(phrase7);
                        
                              
                       
                        
                        
                        table7.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
                        phrase7 = new Phrase(" " , pFontHeader2);
                                                
                        table7.addCell(phrase7);
                        
                        
                        table7.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table7.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table7.getDefaultCell().setBorderColor(java.awt.Color.BLACK);                       
                        table7.getDefaultCell().setColspan(1);
                   
                        
                        table7.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase7 = new Phrase("Languages : " , pFontHeader2);
                        table7.addCell(phrase7);                    
                        
                        
                        table7.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);                        
                        phrase7 = new Phrase("Sports : " , pFontHeader2);
                        table7.addCell(phrase7);
                       
                        
                        
                        table7.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);                        
                        phrase7 = new Phrase("Hobbies : " , pFontHeader2);
                        table7.addCell(phrase7);
                        
                        
                        
                        table7.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);                        
                        phrase7 = new Phrase("Clubs : " , pFontHeader2);
                        table7.addCell(phrase7);
                        
                        
                        table7.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase7 = new Phrase("Office held : " , pFontHeader2);
                        table7.addCell(phrase7);
                      
                        
                        table7.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase7 = new Phrase("Post : " , pFontHeader2);
                        table7.addCell(phrase7);
                        
                        
                        table7.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase7 = new Phrase("Begin Date : " , pFontHeader2);
                        table7.addCell(phrase7);
                       
                        
                        table7.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);                        
                        phrase7 = new Phrase("End Date : " , pFontHeader2);
                        table7.addCell(phrase7);
                       
                                                
                                               
                        table7.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
                        
                        
                        try {
                            
                            
                            java.sql.Statement st8 = connectDB.createStatement();
                            
                            java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                            
                            for (int j = 0; j < listofStaffNos.length; j++) {
                               
                                java.sql.Statement sst8 = connectDB.createStatement();
                                
                                
                                java.sql.ResultSet rset8 = sst8.executeQuery("select languages,sports,hobbies,clubs,club_office,post,begindate,enddate from hr.hr_referees_xtras where staff_id = '"+listofStaffNos[j]+"'");
                               
                                
                                while (rset8.next()) {
                                    
                                    table7.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table7.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
                                    table7.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                    
                                    
                                    table7.getDefaultCell().setColspan(1);                                   
                                    table7.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase7 = new Phrase(dbObject.getDBObject(rset8.getObject(1), "-"), pFontHeader1);                                   
                                     table7.addCell(phrase7);
                                    
                                    table7.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);                                   
                                    phrase7 = new Phrase(dbObject.getDBObject(rset8.getObject(2), "-"), pFontHeader1);
                                    table7.addCell(phrase7);
                                    
                                    table7.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);                                   
                                    phrase7 = new Phrase(dbObject.getDBObject(rset8.getObject(2), "-"), pFontHeader1);
                                    table7.addCell(phrase7);
                                   
                                    table7.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);                                   
                                    phrase7 = new Phrase(dbObject.getDBObject(rset8.getObject(2), "-"), pFontHeader1);
                                    table7.addCell(phrase7);
                                    
                                    table7.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);                                   
                                    phrase7 = new Phrase(dbObject.getDBObject(rset8.getObject(2), "-"), pFontHeader1);
                                    table7.addCell(phrase7);
                                   
                                    table7.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);                                   
                                    phrase7 = new Phrase(dbObject.getDBObject(rset8.getObject(2), "-"), pFontHeader1);
                                    table7.addCell(phrase7);
                                    
                                    table7.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);                                   
                                    phrase7 = new Phrase(dbObject.getDBObject(rset8.getObject(2), "-"), pFontHeader1);
                                    table7.addCell(phrase7);
                                    
                                    table7.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);                                   
                                    phrase7 = new Phrase(dbObject.getDBObject(rset8.getObject(2), "-"), pFontHeader1);
                                    table7.addCell(phrase7);
                                    
                                    
                                                                                                      
                                    table7.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                                                        
                                    table7.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                    
                                    table7.addCell(phrase7);
                                    
                                   
                                     docPdf.add(table7);
                                    
                                }
                                                                                               
                                                                
                                    
                                   
                                    
                                }
                                
                            
                            
                            
                            
                            
                            
                            
                            
                        } catch(java.sql.SQLException SqlExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        
                    } catch(com.lowagie.text.BadElementException BadElExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                        
                    }
                    
                    
                } catch(java.io.FileNotFoundException fnfExec) {
                    
                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), fnfExec.getMessage());
                    
                }
            } catch(com.lowagie.text.DocumentException lwDocexec) {
                
                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), lwDocexec.getMessage());
                
            }
            
             
            
docPdf.close();  com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);
            
            
            
        } catch(java.io.IOException IOexec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());
            
        }
        
    }
    
    
    public java.lang.Object[] getListofStaffNos() {
        
        java.lang.Object[] listofStaffNos = null;
        
        java.util.Vector listStaffNoVector = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT distinct staff_id from hr.hr_staffresume_fileinfo WHERE staff_id ILIKE  initcap('"+names+"%')");
            
            
            while (rSet1.next()) {
                
                listStaffNoVector.addElement(rSet1.getObject(1).toString());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofStaffNos = listStaffNoVector.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNos;
    }
}





