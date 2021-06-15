//Author Charles Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;

package com.afrisoftech.reports;
import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import java.net.MalformedURLException;
//

public class ReceiptsCopyPdf implements java.lang.Runnable {
    
    java.lang.String MNo = null;
    
    java.lang.String beginDate = null;
    
    java.lang.String endDate = null;
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    String ks;
    //  java.lang.String memNo2Use = null;
    
    java.lang.Thread threadSample;
    
    //    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
    //    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    private double totals = 0.00;
    
    Image imgWaterMark = null;
    // public void ReceiptsPdf(java.sql.Connection connDb, java.lang.String begindate, java.lang.String endate, java.lang.String combox) {
    
    public void ReceiptsCopyPdf(java.sql.Connection connDb, java.lang.String combox) {
        
        MNo = combox;
        
        connectDB = connDb;
        try {
            Image imgWaterMark = Image.getInstance(System.getProperty("company.watermark"));
            // beginDate = begindate;
        } catch (BadElementException ex) {
                        ex.printStackTrace();             //ex.printStackTrace();
        } catch (MalformedURLException ex) {
                        ex.printStackTrace();             //ex.printStackTrace();
        } catch (IOException ex) {
                        ex.printStackTrace();             //ex.printStackTrace();
        }
        
        // endDate = endate;
        threadSample = new java.lang.Thread(this,"SampleThread");
        
        System.out.println("threadSample created");
        
        threadSample.start();
        
        System.out.println("threadSample fired");
        
    }
    
    public static void main(java.lang.String[] args) {
        
        //		new MemberStatementPdf().MemberStatementPdf(args[0]);
        
    }
    
    
    public void run() {
        
        System.out.println("System has entered running mode");
        
        while (threadCheck) {
            
            System.out.println("O.K. see how we execute target program");
            
            this.generatePdf(MNo);
            
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
    
    
    public void generatePdf(java.lang.String memNo) {
        
        java.lang.Process wait_for_Pdf2Show;
        
        java.util.Calendar cal = java.util.Calendar.getInstance();
        
        java.util.Date dateStampPdf = cal.getTime();
        
        java.lang.String pdfDateStamp = dateStampPdf.toString();
        
        try{
            try {
                Image img = Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo());
                //Image imgWaterMark = Image.getInstance(System.getProperty("company.watermark"));
                
                java.io.File tempFile = java.io.File.createTempFile("REP"+this.getDateLable()+"_", ".pdf");
                
                tempFile.deleteOnExit();
                
                java.lang.Runtime rt = java.lang.Runtime.getRuntime();
                
                java.lang.String debitTotal = null;
                
                java.lang.String creditTotal = null;
                
                int titleFont = 0;
                int bodyFont = 0;
                float Widths = 0;
                float Heights = 0;
                float Margins = 0;
                
                try {
                    connectDB.setAutoCommit(false);
                    
                    java.sql.Statement stm1 = connectDB.createStatement();
                    java.sql.ResultSet rse1 = stm1.executeQuery("select header_font,body_font,width,height,margins from receipt_pref");
                    while (rse1.next()){
                        titleFont = rse1.getInt(1);
                        bodyFont = rse1.getInt(2);
                        Widths = rse1.getFloat(3);
                        Heights = rse1.getFloat(4);
                        Margins = rse1.getFloat(5);
                    }
                }catch(java.sql.SQLException sq){
                    
                    try {
                        connectDB.rollback();
                    }catch (java.sql.SQLException sql){
                        //   javax.swing.JOptionPane.showMessageDialog(this,sql.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
                    }
                    System.out.println(sq.getMessage());
                    //   javax.swing.JOptionPane.showMessageDialog(this,sq.getMessage(), "Error",javax.swing.JOptionPane.ERROR_MESSAGE);
                    
                }
                // com.lowagie.text.Font pFontHeader = FontFactory.getFont(System.getProperty("font_type_name"), java.lang.Float.parseFloat(System.getProperty("receiptFontSize")), Font.NORMAL);
                // com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(System.getProperty("font_type_name"), java.lang.Float.parseFloat(System.getProperty("receiptTitleFontSize")), Font.BOLD);
                com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, bodyFont, Font.BOLD);
                com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, titleFont, Font.BOLD);
                com.lowagie.text.Font pFontHeaderx = FontFactory.getFont(FontFactory.HELVETICA, bodyFont, Font.NORMAL);
                
                
                // com.lowagie.text.Document docPdf = new com.lowagie.text.Document(new Rectangle(java.lang.Float.parseFloat(System.getProperty("papersize_width")), java.lang.Float.parseFloat(System.getProperty("papersize_legnth"))),java.lang.Float.parseFloat(System.getProperty("receiptPageMargin")),java.lang.Float.parseFloat(System.getProperty("receiptPageMargin")),java.lang.Float.parseFloat(System.getProperty("receiptPageMargin")),java.lang.Float.parseFloat(System.getProperty("receiptPageMargin")));
                // com.lowagie.text.Document docPdf = new com.lowagie.text.Document(new Rectangle((Widths), Heights),Margins,Margins,Margins,Margins);
                
                com.lowagie.text.Document docPdf = new com.lowagie.text.Document(new Rectangle((595), 419),5,5,5,5);
                
                //            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(new Rectangle(java.lang.Float.parseFloat(System.getProperty("papersize_width")), java.lang.Float.parseFloat(System.getProperty("papersize_legnth"))));
                
                try {
                    
                    try {
                        Image imgWaterMark = Image.getInstance(System.getProperty("company.watermark"));
                        
                        com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                        
                        
                        
                        
                        
                        String compName = null;
                        String date = null;
                        
                        
                        
                        docPdf.open();
                        
                        String footers = null;
                        try {
                            com.lowagie.text.pdf.PdfPTable table2 = new com.lowagie.text.pdf.PdfPTable(6);
                            
                            int headerwidths2[] = {15,20,20,15,15,15};
                            
                            table2.setWidths(headerwidths2);
                            
                            com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(2);
                            
                            int headerwidths1[] = {70,30};
                            
                            table1.setWidths(headerwidths1);
                            
                            table1.setWidthPercentage((100));
                            
                            
                            com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(6);
                            
                            int headerwidths[] = {15,20,20,15,15,15};
                            
                            table.setWidths(headerwidths);
                            
                            table.setWidthPercentage((100));
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM|Rectangle.LEFT|Rectangle.RIGHT|Rectangle.TOP);
                            
                            //  table.getDefaultCell().setColspan(6);
                            Phrase phrase2 = new Phrase("");
                            Phrase phrase = new Phrase("");
                            Phrase phrase1 = new Phrase("");
                            // table.addCell(phrase);
                            // table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setColspan(1);
                            // table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            //table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            
                            try {
                                
                                java.sql.Statement st = connectDB.createStatement();
                                java.sql.Statement st1 = connectDB.createStatement();
                                java.sql.Statement st2 = connectDB.createStatement();
                                java.sql.Statement st5 = connectDB.createStatement();
                                java.sql.Statement st6 = connectDB.createStatement();
                                java.sql.Statement st51 = connectDB.createStatement();
                                java.sql.Statement st3 = connectDB.createStatement();
                                java.sql.Statement st2x = connectDB.createStatement();
                                
                                java.sql.ResultSet rset2x = st2x.executeQuery("SELECT rep_currency from pb_hospitalprofile");
                                while(rset2x.next()){
                                    ks = rset2x.getObject(1).toString();
                                }
                                java.sql.ResultSet rset3 = st3.executeQuery("select header,footer from ac_receipt_header");
                                
                                
                                //  java.sql.ResultSet rset3 = st3.executeQuery("select hospital_name,postal_code||' '||box_no,main_telno||' '||other_telno,initcap(street),main_faxno,email,website,room_no from pb_hospitalprofile");
                                java.sql.ResultSet rset1 = st1.executeQuery("SELECT DISTINCT initcap(cb.dealer),initcap(cb.account_no) from ac_cash_collection cb where cb.receipt_no = '"+MNo+"' AND cb.transaction_type = 'Receipts'");
                                java.sql.ResultSet rsetTotals = st2.executeQuery("SELECT sum(pc.debit-credit) as debit from ac_cash_collection pc where receipt_no = '"+MNo+"' AND pc.transaction_type = 'Receipts'");
                                java.sql.ResultSet rs = st.executeQuery("select DISTINCT cb.receipt_no,date from ac_cash_collection cb where receipt_no = '"+MNo+"'");
                                java.sql.ResultSet rset5 = st5.executeQuery("SELECT DISTINCT account_no from ac_cash_collection where receipt_no = '"+MNo+"' AND transaction_type = 'Receipts' group by account_no,description");
                                java.sql.ResultSet rset51 = st51.executeQuery("select initcap(description),sum(debit-credit) from ac_cash_collection where receipt_no = '"+MNo+"' AND transaction_type = 'Receipts' group by account_no,description ORDER BY sum(debit) DESC");
                                
                                java.sql.ResultSet rset6 = st6.executeQuery("SELECT DISTINCT payment_mode, user_name,cash_point from ac_cash_collection where receipt_no = '"+MNo+"' AND transaction_type = 'Receipts'");
                                System.out.println(MNo);
                                
                          /*  while (rset3.next()){
                                table.getDefaultCell().setColspan(6);
                           
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader1);
                                table.addCell(phrase);
                                footers = rset3.getObject(2).toString();
                            }
                           */
                                while (rset3.next()){
                                    table2.getDefaultCell().setColspan(5);
                                    
                                    table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    phrase2 = new Phrase(rset3.getObject(1).toString(), pFontHeader1);
                                    table2.addCell(phrase2);
                                    
                                    footers = rset3.getObject(2).toString();
                                    
                                    
                                }
                                table2.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
                                
                                table.getDefaultCell().setColspan(6);
                                table.addCell(table2);
                          /*  com.lowagie.text.pdf.PdfPCell cell = new com.lowagie.text.pdf.PdfPCell(img,true);
                           
                            cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                           
                           
                           
                            //     cell.addElement(new Chunk(img, 5, -5));
                            table.addCell(cell);
                           // table.addCell(phrase);
                           
                           */
                                while (rs.next()){
                                    
                                    table.getDefaultCell().setColspan(2);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Receipt No : "+ rs.getString(1), pFontHeader);
                                    
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(3);
                                    phrase = new Phrase("RECEIPT", pFontHeader1);
                                    
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Date : "+ rs.getObject(2).toString(), pFontHeader);
                                    
                                    table.addCell(phrase);
                                }
                                
                                
                                
                                while (rset1.next()){
                                    
                                    table.getDefaultCell().setColspan(6);
                                    //    table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Received From : " +rset1.getObject(2).toString(), pFontHeader);
                                    
                                    table.addCell(phrase);
                                    while (rset5.next()){
                                        table.getDefaultCell().setColspan(6);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(" ", pFontHeader);
                                        
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(6);
                                        // table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("In Respect Of : " +rset1.getObject(1).toString() , pFontHeader);
                                        
                                        table.addCell(phrase);
                                        table.getDefaultCell().setBorder(Rectangle.LEFT|Rectangle.RIGHT);
                                        
                                        table1.getDefaultCell().setColspan(1);
                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase1 = new Phrase("Description", pFontHeader1);
                                        
                                        table1.addCell(phrase1);
                                        // table1.getDefaultCell().setColspan(2);
                                        phrase1 = new Phrase("Amount"+ks, pFontHeader1);
                                        
                                        table1.addCell(phrase1);
                                    }
                                }
                                while (rset51.next()){
                                    table1.getDefaultCell().setColspan(1);
                                    
                                    phrase1 = new Phrase("    " , pFontHeader);
                                    
                                    //table.addCell(phrase);
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    
                                    phrase1 = new Phrase(rset51.getObject(1).toString(), pFontHeaderx);
                                    
                                    table1.addCell(phrase1);
                                    
                                    table1.getDefaultCell().setColspan(1);
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    
                                    phrase1 = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset51.getString(2)),pFontHeaderx);
                                    table1.addCell(phrase1);
                                    totals = totals + rset51.getDouble(2);
                                    
                                }
                                table.getDefaultCell().setColspan(6);
                                
                                table.getDefaultCell().setFixedHeight(170);
                                table.addCell(table1);
                                table.getDefaultCell().setFixedHeight(15);
                                // while (rsetTotals.next()) {
                                
                                table.getDefaultCell().setBorder(Rectangle.LEFT|Rectangle.RIGHT|Rectangle.BOTTOM|Rectangle.TOP);
                                table.getDefaultCell().setColspan(1);
                                // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                // phrase = new Phrase(" ", pFontHeader);
                                
                                // table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Total "+ks, pFontHeader);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(2);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(totals)),pFontHeader);
                                
                                table.addCell(phrase);
                                
                                // table.getDefaultCell().setColspan(1);
                                // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                //  phrase = new Phrase(" ", pFontHeader);
                                
                                //table.addCell(phrase);
                                
                                
                                
                                
                             /*   table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(2)),pFontHeader);
                              
                                //phrase = new Phrase(" ");
                              
                                table.addCell(phrase);
                              
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                              
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(3)),pFontHeader);
                              
                                table.addCell(phrase);
                              
                              
                              */
                                // }
                                
                                while (rset6.next()){
                                    
                                    table.getDefaultCell().setColspan(3);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Payment Mode : " +rset6.getString(1).toUpperCase(), pFontHeader);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(3);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Served By : " +rset6.getString(2).toString().toUpperCase(), pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(6);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Cash Point : " +rset6.getString(3).toString().toUpperCase(), pFontHeader);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase(footers , pFontHeader);
                                table.addCell(phrase);
                                }
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("RECEIPT COPY" , pFontHeader);
                                table.addCell(phrase);
                                
                                docPdf.add(table);
                                //  java.sql.PreparedStatement pstmt4 = connectDB.prepareStatement("UPDATE hp_patient_card SET paid ='true' WHERE patient_no = '"+MNo+"' AND date::date = current_date");
                                //  pstmt4.executeUpdate();
                            } catch(java.sql.SQLException SqlExec) {
                                
                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                                
                            }
                            
                            // }
                            
                        } catch(com.lowagie.text.BadElementException BadElExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                            
                        }
                        
                    } catch(java.io.FileNotFoundException fnfExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), fnfExec.getMessage());
                        
                    }
                } catch(com.lowagie.text.DocumentException lwDocexec) {
                    
                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), lwDocexec.getMessage());
                    
                }
                
                docPdf.close();
                PdfContentByte under;
                PdfReader reader = new PdfReader(tempFile.getPath());
                int n = reader.getNumberOfPages();
                int i = 0;
                //               tempFile.delete();
                java.io.File tempFile2 = java.io.File.createTempFile("REP"+this.getDateLable()+"_", ".pdf");
//                tempFile = tempFile.createNewFile();
                try{
                    PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(tempFile2));
                    imgWaterMark.scaleToFit(docPdf.getPageSize().width(), docPdf.getPageSize().height());
                    //                      img.scalePercent(50);
                    
                    imgWaterMark.setAbsolutePosition(0, 0);
                    while (i < n) {
                        i++;
                        // watermark under the existing page
                        under = stamp.getUnderContent(i);
                        //                       under.showTextAligned(Element.ALIGN_LEFT, "DUPLICATE", 230, 430, 45);
                        //under.addImage(img);
                        under.addImage(imgWaterMark);
                    }
                    stamp.close();
                } catch(com.lowagie.text.DocumentException docEx){
                    docEx.printStackTrace();
                }
                
                try {
                    
                    if (System.getProperty("os.name").equalsIgnoreCase("Linux"))  {
                        
                        System.out.println(tempFile);
                        
                        wait_for_Pdf2Show = rt.exec("kghostview "+tempFile2+"");
                        //                    wait_for_Pdf2Show = rt.exec("cat kghostview "+tempFile+"");
                        
                        wait_for_Pdf2Show.waitFor();
                        
                    } else {
                        wait_for_Pdf2Show = rt.exec("c:/Program Files/Adobe/Acrobat 5.0/Reader/AcroRd32.exe "+tempFile);
                        
                        wait_for_Pdf2Show.waitFor();
                        //   wait_for_Pdf2Show = rt.exec("cmd.exe /C AcroRd32 /p /h "+tempFile2);
                        
                        //   wait_for_Pdf2Show.waitFor();
                        
                    }
                    
                } catch(java.lang.InterruptedException intrExec) {
                    
                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), intrExec.getMessage());
                    
                }
                
                
                
            } catch(java.io.IOException IOexec) {
                
                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());
                
            }
            
        } catch(com.lowagie.text.BadElementException badEl){
            
            badEl.printStackTrace();
        }
        
    }
    
    
    
    
    
    
}





