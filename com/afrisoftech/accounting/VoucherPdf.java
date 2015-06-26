//Author Charles Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;

package com.afrisoftech.accounting;
import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;


public class VoucherPdf implements java.lang.Runnable {
    java.lang.String MNo = null;
    
    java.lang.String vouchNo = null;
    
    java.lang.String beginDate = null;
    
    java.lang.String endDate = null;
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    
    //  java.lang.String memNo2Use = null;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 13, Font.BOLD);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    // public void ReceiptsPdf(java.sql.Connection connDb, java.lang.String begindate, java.lang.String endate, java.lang.String combox) {
    
    public void VoucherPdf(java.sql.Connection connDb, java.lang.String voucno) {
        
        //MNo = combox;
        vouchNo = voucno;
        connectDB = connDb;
        
        // beginDate = begindate;
        
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
        
        
        try {
            
            java.io.File tempFile = java.io.File.createTempFile("REP"+this.getDateLable()+"_", ".pdf");
            
            tempFile.deleteOnExit();
            
            java.lang.Runtime rt = java.lang.Runtime.getRuntime();
            
            java.lang.String debitTotal = null;
            
            java.lang.String creditTotal = null;
            
            //   com.lowagie.text.Document docPdf = new com.lowagie.text.Document(new Rectangle(java.lang.Float.parseFloat(System.getProperty("papersize_width")), java.lang.Float.parseFloat(System.getProperty("papersize_legnth"))));
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            
            try {
                
                try {
                    
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    
                    
                    
                    
                    
                    String compName = null;
                    String date = null;
                    try {
                        
                        
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                        
                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT current_user");
                        //java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while(rset2.next()){
                            compName = rset2.getObject(1).toString();
                        }
                        // while(rset4.next())
                        //   date = rset4.getObject(1).toString();
                        com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("PREPARED BY  "+compName+ "                CHEKED BY____________________    \n  AUTHORISED BY ___________________     RECEIVED BY ____________________________"),false);
                        //: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                        
                        docPdf.setFooter(footer);
                        // com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+"                                                        Printed On: "+date+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        
                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        //  headerFoter.setRight(5);
                        //  docPdf.setHeader(headerFoter);
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    /*
                      com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("AUTHORISED BY __________________________ : "), pFontHeader1);
                          //: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                     
                      docPdf.setFooter(footer);
                     */
                    
                    docPdf.open();
                    double Vat = 0.00;
                    double Net = 0.00;
                    double Net1 = 0.00;
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(6);
                        
                        int headerwidths[] = {20,25,25,10,10,10};
                        
                        //  table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        
                        // table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        try {
                            
                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st5 = connectDB.createStatement();
                            java.sql.Statement st6 = connectDB.createStatement();
                            java.sql.Statement st7 = connectDB.createStatement();
                            java.sql.Statement st61 = connectDB.createStatement();
                            java.sql.Statement st611 = connectDB.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            
                            java.sql.ResultSet rset3 = st3.executeQuery("select hospital_name,postal_code||' '||box_no||' '||town,main_telno||' '||other_telno,initcap(street),main_faxno,email,website,room_no from pb_hospitalprofile");
                            java.sql.ResultSet rset1 = st1.executeQuery("select cb.dealer,cb.description from ac_cash_book cb where cb.voucher_no = '"+vouchNo+"'");
                            //  java.sql.ResultSet rsetTotals = st2.executeQuery("select sum(pc.debit) as debit from ac_cash_collection pc where dealer = '"+MNo+"' AND pc.transaction_type = 'Receipts' AND pc.date::date = current_date");
                            java.sql.ResultSet rs = st.executeQuery("select cb.voucher_no,chq_date,cb.cheque_no from ac_cash_book cb where cb.voucher_no = '"+vouchNo+"'");
                            java.sql.ResultSet rset5 = st5.executeQuery("select sum(cb.credit) from ac_cash_book cb where cb.voucher_no = '"+vouchNo+"'");
                            java.sql.ResultSet rset6 = st6.executeQuery("select cb.invoice_no,cb.debit,vat from ac_accounts_payable cb where cb.voucher_no = '"+vouchNo+"' and transaction_type ilike 'Payment%'");
                            java.sql.ResultSet rset7 = st7.executeQuery("select upper(cb.account_no) ||'   '|| upper(cb.bank_name) from ac_cash_book cb where cb.voucher_no = '"+vouchNo+"'");
                            java.sql.ResultSet rset61 = st61.executeQuery("select description,credit from ac_creditors_deductions  where voucher_no = '"+vouchNo+"' UNION select cb.reference_no,cb.debit from ac_accounts_payable cb where cb.voucher_no = '"+vouchNo+"' and transaction_type ilike 'Stock Returns%'");
                            // java.sql.ResultSet rset611 = st611.executeQuery("select description,credit from ac_creditors_deductions  where voucher_no = '"+vouchNo+"'");
                            
                            System.out.println(MNo);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            Phrase phrase = new Phrase("PAYMENT VOUCHER");
                            
                            table.addCell(phrase);
                            // table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            while (rset3.next()){
                                table.getDefaultCell().setColspan(6);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader1);
                                table.addCell(phrase);
                                
                                
                                //  table.getDefaultCell().setColspan(4);
                                table.getDefaultCell().setBorderColor(java.awt.Color.white);
                                
                                //   table.getDefaultCell().setColspan(2);
                                
                                //    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("P.O. Box : "+rset3.getObject(2).toString(), pFontHeader);
                                table.addCell(phrase);
                       /*     while (rs.next())
                        
                                table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Voucher No : "+ rs.getString(1), pFontHeader);
                            table.addCell(phrase);
                        */
                                //   table.getDefaultCell().setColspan(4);
                                //      while (rset3.next())
                                //       table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Tel : "+rset3.getObject(3).toString(), pFontHeader);
                                
                                table.addCell(phrase);
                                //   table.getDefaultCell().setColspan(2);
                       /*     while (rs.next())
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Date "+ rs.getObject(2).toString(), pFontHeader);
                        
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(4);
                        */
                                //     while (rset3.next())
                                //        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Fax : "+ rset3.getObject(5).toString(), pFontHeader);
                                
                                table.addCell(phrase);
                                //    table.getDefaultCell().setColspan(6);
                                
                    /*        while (rs.next())
                                table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Cheque No. " + rs.getObject(3).toString(), pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(6);
                     */
                                //    while (rset3.next())
                                table.getDefaultCell().setBorderColor(java.awt.Color.white);
                                // table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                phrase = new Phrase("Email :  "+rset3.getObject(3).toString(), pFontHeader);
                                table.addCell(phrase);
                                
                                
                                // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Website:"+"\t" +rset3.getObject(7).toString(), pFontHeader);
                                
                                table.addCell(phrase);
                                
                                //  }
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            }
                            //  table.getDefaultCell().setBorderWidth(Rectangle.TOP);
                            while (rs.next()){
                                
                                table.getDefaultCell().setColspan(6);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase("Voucher No : "+ rs.getString(1), pFontHeader1);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase("Date of Cheque :"+ rs.getObject(2).toString(), pFontHeader1);
                                
                                table.addCell(phrase);
                                
                                // table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Cheque No. :" + rs.getObject(3).toString(), pFontHeader1);
                                table.addCell(phrase);
                            }
                            
                            while (rset1.next()){
                                
                                table.getDefaultCell().setColspan(6);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Payee :" +rset1.getObject(1).toString(), pFontHeader1);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Billed Items/Services :" +rset1.getObject(2).toString(), pFontHeader1);
                                table.addCell(phrase);
                            }
                            while (rset7.next()){
                                
                                table.getDefaultCell().setColspan(6);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Account :" +rset7.getObject(1).toString(), pFontHeader1);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setColspan(3);
                                
                                phrase = new Phrase("Description", pFontHeader1);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Inv. Amount", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Vat", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            }
                            while (rset6.next()){
                                
                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset6.getObject(1).toString(), pFontHeader);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset6.getObject(2).toString()),pFontHeader);
                                Net = Net + rset6.getDouble(2);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset6.getObject(3).toString()),pFontHeader);
                                Vat = Vat + rset6.getDouble(3);
                                table.addCell(phrase);
                            }
                  /*              table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                   
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(5)),pFontHeader);
                   
                                table.addCell(phrase);
                   
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                   
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(6)),pFontHeader);
                   
                                table.addCell(phrase);
                   
                            }
                   */
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            //while (rset5.next()) //{
                            
                            table.getDefaultCell().setColspan(3);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total", pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Net)),pFontHeader1);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Vat)),pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.white);
                            table.getDefaultCell().setColspan(30);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" ", pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            
                            
                            
                            
                            // table.getDefaultCell().setColspan(6);
                            // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(3);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Gross Pay  : ", pFontHeader1);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Net)),pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            
                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Deductions ", pFontHeader1);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            // table.getDefaultCell().setBorderColor(java.awt.Color.white);
                            
                            // table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Vat Amount  : ", pFontHeader);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Vat)),pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(6);
                            //     table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            //table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Credit Note(s) ", pFontHeader1);
                            table.addCell(phrase);
                            while (rset61.next()){
                                
                                table.getDefaultCell().setColspan(3);
                                // table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset61.getObject(1).toString(), pFontHeader);
                                
                                table.addCell(phrase);
                                
                                // table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset61.getObject(2).toString()),pFontHeader);
                                Net1 = Net1 + rset61.getDouble(2);
                                table.addCell(phrase);
                                
                            }
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Net Amount : ",pFontHeader1);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Net-Vat-Net1)),pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            
                             /*   table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(2)),pFontHeader);
                              
                                //phrase = new Phrase(" ");
                              
                                table.addCell(phrase);
                              
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                              
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(3)),pFontHeader);
                              
                                table.addCell(phrase);
                              
                              
                              */
                            //}
                          /*  phrase = new Phrase("--  \n"+"\n  ", pFontHeader1);
                            table.addCell(phrase);
                            int max1 = 5;
                            for (int i = 0; i < max1; i++) {
                                if (i % 2 == 0) {
                                    table.getDefaultCell().setGrayFill(0.9f);
                                }
                                int numColumns = 1;
                                for (int x = 0; x < numColumns; x++) {
                                    table.addCell(" -- ");
                                }
                            }
                           
                           /*  table.getDefaultCell().setColspan(6);
                            //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("AUTHORISED BY __________________________ : ", pFontHeader1);
                            table.addCell(phrase);
                           
                            phrase = new Phrase("  ", pFontHeader1);
                            table.addCell(phrase);
                           
                           
                            phrase = new Phrase("RECEIVED BY __________________________ : ", pFontHeader1);
                            table.addCell(phrase);
                            // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                           
                     /*       while (rset6.next())
                           
                                table.getDefaultCell().setColspan(3);
                           
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Payment Mode : " +rset6.getString(1), pFontHeader);
                           
                            table.addCell(phrase);
                           
                            table.getDefaultCell().setColspan(3);
                           
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Served By : " +rset6.getString(2).toString(), pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                            phrase = new Phrase("Cash Point : " +rset6.getString(3).toString(), pFontHeader);
                            table.addCell(phrase);
                           
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                           table.getDefaultCell().setColspan(6);
                           phrase = new Phrase("Thank You. For trading with us" , pFontHeader);
                            table.addCell(phrase);
                           */
                            docPdf.add(table);
                            //  java.sql.PreparedStatement pstmt4 = connectDB.prepareStatement("UPDATE ac_accounts_payable SET paid ='true' WHERE dealer = '"+MNo+"'");
                            //pstmt4.executeUpdate();
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
            
             
            
            try {
                
                if (System.getProperty("os.name").equalsIgnoreCase("Linux"))  {
                    
                    System.out.println(tempFile);
                    
                    wait_for_Pdf2Show = rt.exec("kghostview "+tempFile+"");
                    //                    wait_for_Pdf2Show = rt.exec("cat kghostview "+tempFile+"");
                    
                    wait_for_Pdf2Show.waitFor();
                    
                } else if (System.getProperty("os.name").equalsIgnoreCase("Windows 98")) {
                    
                    
                    wait_for_Pdf2Show = rt.exec("command.exe /C AcroRd32 /p /h "+tempFile);
                    
                    wait_for_Pdf2Show.waitFor();
                    // wait_for_Pdf2Show = rt.exec("c:/Program Files/Adobe/Acrobat 5.0/Reader/AcroRd32.exe "+tempFile);
                    
                    // wait_for_Pdf2Show.waitFor();
                    
                } else {
                    
                    wait_for_Pdf2Show = rt.exec("cmd.exe /C AcroRd32 /p /h "+tempFile);
                    
                    wait_for_Pdf2Show.waitFor();
                    // wait_for_Pdf2Show = rt.exec("c:/Program Files/Adobe/Acrobat 5.0/Reader/AcroRd32.exe "+tempFile);
                    
                    // wait_for_Pdf2Show.waitFor();
                    
                }
                
                
            } catch(java.lang.InterruptedException intrExec) {
                
                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), intrExec.getMessage());
                
            }
            
            
            
        } catch(java.io.IOException IOexec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());
            
        }
        
        
        
    }
    
    
    
    
    
    
}





