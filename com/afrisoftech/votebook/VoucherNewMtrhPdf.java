//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.votebook;

import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;

public class VoucherNewMtrhPdf implements java.lang.Runnable {

    java.lang.String MNo = null;
    com.afrisoftech.lib.DBObject dbObject;
    java.lang.String vouchNo = null;
    java.lang.String beginDate = null;
    java.lang.String endDate = null;
    String suppName = null;
    String compName = null;
    String date = null;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    boolean previewPrint;
    String ks;
    //  java.lang.String memNo2Use = null;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader112 = FontFactory.getFont(FontFactory.TIMES, 12, Font.NORMAL);
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.TIMES, 11, Font.BOLD);
    com.lowagie.text.Font pFontHeader3 = FontFactory.getFont(FontFactory.TIMES, 16, Font.BOLDITALIC);
    com.lowagie.text.Font pFontHeader31 = FontFactory.getFont(FontFactory.TIMES, 13, Font.BOLD);
    com.lowagie.text.Font pFontHeader32 = FontFactory.getFont(FontFactory.TIMES, 13, Font.UNDERLINE);
    com.lowagie.text.Font pFontHeader12 = FontFactory.getFont(FontFactory.TIMES, 12, Font.UNDERLINE|Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    // public void ReceiptsPdf(java.sql.Connection connDb, java.lang.String begindate, java.lang.String endate, java.lang.String combox) {
    public void VoucherNewMtrhPdf(java.sql.Connection connDb, java.lang.String voucno) {

        dbObject = new com.afrisoftech.lib.DBObject();
        vouchNo = voucno;
        connectDB = connDb;
        //  previewPrint = printPreview;
        // beginDate = begindate;

        // endDate = endate;
        threadSample = new java.lang.Thread(this, "SampleThread");

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

            } catch (java.lang.InterruptedException IntExec) {

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

            year_now_strs = "200" + year_now_abs;

        } else {

            year_now_strs = "20" + year_now_abs;

        }

        switch (month_now_str) {

            case 0:
                month_now_strs = "JAN";

                break;

            case 1:
                month_now_strs = "FEB";

                break;

            case 2:
                month_now_strs = "MAR";

                break;

            case 3:
                month_now_strs = "APR";

                break;

            case 4:
                month_now_strs = "MAY";

                break;

            case 5:
                month_now_strs = "JUN";

                break;

            case 6:
                month_now_strs = "JUL";

                break;

            case 7:
                month_now_strs = "AUG";

                break;

            case 8:
                month_now_strs = "SEP";

                break;

            case 9:
                month_now_strs = "OCT";

                break;

            case 10:
                month_now_strs = "NOV";

                break;

            case 11:
                month_now_strs = "DEC";

                break;

            default:
                if (month_now_str < 10) {

                    month_now_strs = "0" + month_now_str;

                } else {

                    month_now_strs = "" + month_now_str;

                }

        }

        if (date_now_str < 10) {

            date_now_strs = "0" + date_now_str;

        } else {

            date_now_strs = "" + date_now_str;

        }

        if (minute_now_str < 10) {

            minute_now_strs = "0" + minute_now_str;

        } else {

            minute_now_strs = "" + minute_now_str;

        }

        if (hour_now_str < 10) {

            hour_now_strs = "0" + hour_now_str;

        } else {

            hour_now_strs = "" + hour_now_str;

        }

        date_label = date_now_strs + month_now_strs + year_now_strs + "@" + hour_now_strs + minute_now_strs;

        return date_label;

    }

    public void generatePdf(java.lang.String memNo) {

        java.lang.Process wait_for_Pdf2Show;

        java.util.Calendar cal = java.util.Calendar.getInstance();

        java.util.Date dateStampPdf = cal.getTime();

        java.lang.String pdfDateStamp = dateStampPdf.toString();


        try {

            java.io.File tempFile = java.io.File.createTempFile("REP" + this.getDateLable() + "_", ".pdf");

            tempFile.deleteOnExit();

            java.lang.Runtime rt = java.lang.Runtime.getRuntime();

            java.lang.String debitTotal = null;

            java.lang.String creditTotal = null;

            //   com.lowagie.text.Document docPdf = new com.lowagie.text.Document(new Rectangle(java.lang.Float.parseFloat(System.getProperty("papersize_width")), java.lang.Float.parseFloat(System.getProperty("papersize_legnth"))));
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();

            try {

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));



                    try {


                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                        java.sql.Statement st2x = connectDB.createStatement();

                        java.sql.ResultSet rset2x = st2x.executeQuery("SELECT rep_currency from pb_hospitalprofile");
                        while (rset2x.next()) {
                            ks = rset2x.getObject(1).toString();
                        }
                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT user_name,dealer from ac_cash_book where voucher_no = '" + vouchNo + "'");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while (rset2.next()) {
                            compName = rset2.getObject(1).toString();
                            suppName = rset2.getObject(2).toString();
                        }
                        // while(rset4.next())
                        //   date = rset4.getObject(1).toString();
                        com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Withholding Tax, where applicable,has been deducted and will be remitted to the Tax Authorities"), false);
                        //: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                        //docPdf.setFooter(footer);

                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }


                    docPdf.open();
                    double Vat = 0.00;
                    double Net = 0.00;
                    double Net1 = 0.00;
                    int vno = 0;
                    try {


                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(20);

                        int headerwidths[] = {5,5,5,5,5,10,5,5,5,5,10,5,5,5,10,5,10,5,5,10};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(1);

                        int headerwidths1[] = {100};

                        table1.setWidths(headerwidths1);

                        com.lowagie.text.pdf.PdfPTable table2 = new com.lowagie.text.pdf.PdfPTable(2);

                        int headerwidths2[] = {40, 60};

                        table2.setWidths(headerwidths2);

                        // table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        //table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                        try {

                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st5 = connectDB.createStatement();
                            java.sql.Statement st6 = connectDB.createStatement();
                            java.sql.Statement st6x = connectDB.createStatement();
                            java.sql.Statement st7 = connectDB.createStatement();
                            java.sql.Statement st61 = connectDB.createStatement();
                            java.sql.Statement st611 = connectDB.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st33 = connectDB.createStatement();
                            java.sql.ResultSet rset81 = st5.executeQuery("SELECT DISTINCT cb.supplier FROM ac_aie_commitment cb WHERE cb.voucher_no = '" + vouchNo + "' ORDER BY cb.supplier");

                            while (rset81.next()) {
                                this.suppName = rset81.getString(1);
                            }
                            java.sql.ResultSet rset3 = st3.executeQuery("select hospital_name,postal_code||' '||box_no||' '||town,main_telno||' '||other_telno,initcap(street),main_faxno,email,website,room_no,pin_no,vat_no  from pb_hospitalprofile");
                             
                            Phrase phrase = new Phrase();


                           // table.getDefaultCell().setColspan(2);

                            Image img = Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo());
                            //Image imgWaterMark = Image.getInstance(System.getProperty("company.watermark"));
                            table.getDefaultCell().setColspan(3);
                            //table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            //table.getDefaultCell().setFixedHeight(70);
                            table.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            //Phrase phrases1 = new Phrase("",pFontHeader);
                            table.getDefaultCell().setFixedHeight(21);
                            //table.addCell("");
                            //java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.ResultSet rs = st.executeQuery("SELECT DISTINCT hospital_name,box_no,main_telno,main_faxno FROM pb_hospitalprofile");
                            while (rs.next()) {

                                table.getDefaultCell().setColspan(17);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rs.getObject(1).toString() + "\nP.O. Box " + rs.getObject(2).toString() + " - Tel: " + rs.getObject(3).toString()+" ", pFontHeader31);
                                table.addCell(phrase);
    
            //document.add(table);
                                
//                                table.getDefaultCell().setColspan(4);
//                                
//                        com.lowagie.text.pdf.PdfPTable innerTbl = new com.lowagie.text.pdf.PdfPTable(4);
//
//                        int headerwidthsinner[] = {80, 60,30,80};
//
//                        innerTbl.setWidths(headerwidthsinner);
//                        innerTbl.setWidthPercentage(100);
//                        
//                         Phrase str_head = new Phrase("PV NO......\n", pFontHeader);
//                         Phrase act_head = new Phrase("FO 20 VOTED", pFontHeader);
//                          innerTbl.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
//                           innerTbl.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
//                         Phrase box1 = new Phrase("", pFontHeader);
//                         PdfPCell cell1;
//                         cell1 = new PdfPCell(str_head);
//                         table.addCell(cell1); 
//                         
//                    
//                         cell1 = new PdfPCell(act_head);
//                        cell1.setBorder(Rectangle.NO_BORDER);
//                        //innerTbl.addCell("");
//                        table.addCell(cell1);
//                        
//                         cell1 = new PdfPCell(box1);
//                        cell1.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
//                        //innerTbl.addCell("");
//                        table.addCell(cell1);
//                        cell1 = new PdfPCell(innerTbl);
                           
                           
                                
                                
                                
                                //phrase = new Phrase("P.O. Box " + rs.getObject(2).toString() + " - Tel: " + rs.getObject(3).toString() + " - Fax " + rs.getObject(3).toString(), pFontHeader1);
                                //table.addCell(phrase);

                            }

                            java.sql.ResultSet rset1 = st33.executeQuery("select  distinct dealer from  ac_ledger  where  voucher_no ='" + vouchNo + "'");

                            System.out.println(MNo);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setColspan(20);
                            phrase = new Phrase("PAYMENT VOUCHER", pFontHeader32);
                            table.addCell(phrase);




                            while (rset1.next()) {

                                table.getDefaultCell().setColspan(20);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase( rset1.getString("dealer"), pFontHeader);

                                table.addCell(phrase);
                                
                                 java.sql.ResultSet rset189 = st33.executeQuery("SELECT postal_address,pin_no,vat_no FROM st_suppliers WHERE supplier_name ILIKE '" +rset1.getString("dealer")+ "'");
                                
                                 while (rset189.next()) {
                                 table.getDefaultCell().setColspan(20);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Address: " + dbObject.getDBObject(rset1.getObject(1), "-"), pFontHeader);

                                table.addCell(phrase);
                                 }
                            }


                            table.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setColspan(9);
                            

                            phrase = new Phrase("Particulars", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                           // table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("LPO/LSO No.", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("Invoice", pFontHeader11);
                            table.addCell(phrase);
                             table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Amt(Kshs) " , pFontHeader11);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("(Cts) ", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

//                            // if(vno > 0){
//                            java.sql.ResultSet rset8 = st2.executeQuery("SELECT DISTINCT cb.payment_reason FROM ac_aie_commitment cb WHERE cb.voucher_no = '" + vouchNo + "'");
//
//
//                            java.sql.ResultSet rset6 = st6.executeQuery("SELECT cb.refno,cb.invoice_no,cb.paid_amount FROM ac_aie_commitment cb WHERE cb.voucher_no = '" + vouchNo + "' ORDER BY cb.invoice_no");
//
//                            table.getDefaultCell().setFixedHeight(200);
//                            //table.getDefaultCell().setColspan(6);
//                            while (rset8.next()) {
//
//                                table.getDefaultCell().setColspan(6);
//                                // table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
//
//                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                                phrase = new Phrase(rset8.getObject(1).toString(), pFontHeader);
//                                table.addCell(phrase);
//                            }
//
//                            while (rset6.next()) {
//
//                                table.getDefaultCell().setColspan(2);
//                                //table1.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
//
//                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                                phrase = new Phrase(rset6.getObject(1).toString(), pFontHeader);
//
//                                table.addCell(phrase);
//                                table.getDefaultCell().setColspan(3);
//                                phrase = new Phrase(rset6.getObject(2).toString(), pFontHeader);
//
//                                table.addCell(phrase);
//
//                                table.getDefaultCell().setColspan(2);
//                                table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//
//                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset6.getObject(3).toString()), pFontHeader);
//                                Net = Net + rset6.getDouble(3);
//                                table.addCell(phrase);
//                                //table.getDefaultCell().setColspan(1);
//                                //phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset6.getObject(3).toString()), pFontHeader);
//                                //Vat = Vat + rset6.getDouble(3);
//                                //table.addCell(phrase);
//                            }
                            
                            
                            ////////////////////loading the particulars data
                              java.sql.ResultSet rset8167 = st5.executeQuery("select  ac.dealer,ac.order_no,ac.voucher_no,ac.invoice_no,ac.description,ac.credit from ac_accounts_payable ac, ac_aie_commitment com where ac.voucher_no=com.voucher_no and ac.voucher_no='" + vouchNo + "'  ");

                            while (rset8167.next()) {
                                this.suppName = rset8167.getString(1);
                           
                            table.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setColspan(9);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                            phrase = new Phrase( rset8167.getString("description"), pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                           // table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase(rset8167.getString("invoice_no"), pFontHeader11);
                            table.addCell(phrase);
                             table.getDefaultCell().setColspan(2);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(rset8167.getString("credit"), pFontHeader11);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("", pFontHeader11);
                            table.addCell(phrase);
       
                            }
                            ////amount payable
                           // table.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setColspan(16);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.LEFT);
                            phrase = new Phrase("Amount Payable in words(Kshs......................................... ", pFontHeader11);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(2);
                             table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                           //  table.getDefaultCell().setBorder(Rectangle.BOTTOM|Rectangle.LEFT|Rectangle.RIGHT);
                            phrase = new Phrase("", pFontHeader11);
                            table.addCell(phrase);
                             table.getDefaultCell().setColspan(2);
                             table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                             //table.getDefaultCell().setBorder(Rectangle.BOTTOM|Rectangle.RIGHT);
                            phrase = new Phrase("", pFontHeader11);
                            table.addCell(phrase);
                            
                            
                             table.getDefaultCell().setColspan(20);
                              table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader11);
                            table.addCell(phrase);
                            
                            //table.getDefaultCell().setColspan(1);
                            //table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            table.getDefaultCell().setColspan(1);
                             table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader11);
                            table.addCell(phrase);
                            
                            

                            table.getDefaultCell().setColspan(8);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            phrase = new Phrase("Authority Reference No",pFontHeader);
                             table.getDefaultCell().setPaddingLeft(10);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                             table.addCell(phrase);
                             table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                             table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
                            phrase = new Phrase("", pFontHeader11);
                            table.addCell(phrase);
                             table.getDefaultCell().setColspan(10);
                             table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Certification................................................................", pFontHeader11);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(20);
                            phrase = new Phrase("", pFontHeader11);
                            table.addCell(phrase);
          /////////////////////accounts and analysis
                                 table.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setColspan(12);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            phrase = new Phrase("Accounts and analysis Code", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(4);
                           // table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Description", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Debit(Kshs)", pFontHeader11);
                            table.addCell(phrase);

                            
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("(Credits) ", pFontHeader11);
                            table.addCell(phrase);
                            
               ///////////////////////////accounts and analysis//
                            ////////////the 12 columns table...............START
                            
                             java.sql.ResultSet rset8134 = st5.executeQuery("select  com.activity_code,ac.voucher_no,com.description,com.credit,com.debit from ac_accounts_payable ac, ac_ledger com where ac.voucher_no=com.voucher_no and  com.voucher_no ='" + vouchNo + "'");

                            while (rset8134.next()) {
                                //this.suppName = rset8134.getString(1);
                          
                             table.getDefaultCell().setColspan(12);
                             table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                           //  table.getDefaultCell().setBorder(Rectangle.BOTTOM|Rectangle.LEFT|Rectangle.RIGHT);
                            phrase = new Phrase(rset8134.getString("activity_code"), pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(4);
                             table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                           //  table.getDefaultCell().setBorder(Rectangle.BOTTOM|Rectangle.LEFT|Rectangle.RIGHT);
                            phrase = new Phrase(rset8134.getString("description"), pFontHeader11);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(2);
                             table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                           //  table.getDefaultCell().setBorder(Rectangle.BOTTOM|Rectangle.LEFT|Rectangle.RIGHT);
                            phrase = new Phrase(rset8134.getString("debit"), pFontHeader11);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(2);
                             table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                           //  table.getDefaultCell().setBorder(Rectangle.BOTTOM|Rectangle.LEFT|Rectangle.RIGHT);
                            phrase = new Phrase(rset8134.getString("credit"), pFontHeader11);
                            table.addCell(phrase);
                            
                            }
                            /////cash book to pay out
                             table.getDefaultCell().setColspan(12);
                             table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                           //  table.getDefaultCell().setBorder(Rectangle.BOTTOM|Rectangle.LEFT|Rectangle.RIGHT);
                            phrase = new Phrase("CASH BOOK TO PAY OUT", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(4);
                             table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                           //  table.getDefaultCell().setBorder(Rectangle.BOTTOM|Rectangle.LEFT|Rectangle.RIGHT);
                            phrase = new Phrase("", pFontHeader11);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(2);
                             table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                           //  table.getDefaultCell().setBorder(Rectangle.BOTTOM|Rectangle.LEFT|Rectangle.RIGHT);
                            phrase = new Phrase("", pFontHeader11);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(2);
                             table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                           //  table.getDefaultCell().setBorder(Rectangle.BOTTOM|Rectangle.LEFT|Rectangle.RIGHT);
                            phrase = new Phrase("", pFontHeader11);
                            table.addCell(phrase);
                            
                            
                            //////////////////////total
                            table.getDefaultCell().setColspan(16);
                             table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            table.getDefaultCell().setPaddingRight(5);
                           //  table.getDefaultCell().setBorder(Rectangle.BOTTOM|Rectangle.LEFT|Rectangle.RIGHT);
                            phrase = new Phrase("Total(Kshs)", pFontHeader11);
                            table.addCell(phrase);

                            
                             table.getDefaultCell().setColspan(2);
                             table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                           //  table.getDefaultCell().setBorder(Rectangle.BOTTOM|Rectangle.LEFT|Rectangle.RIGHT);
                            phrase = new Phrase("", pFontHeader11);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(2);
                             table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                           //  table.getDefaultCell().setBorder(Rectangle.BOTTOM|Rectangle.LEFT|Rectangle.RIGHT);
                            phrase = new Phrase("", pFontHeader11);
                            table.addCell(phrase);
                            
                            /////////////////prepared by
                            //table.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
                           
                            table.getDefaultCell().setColspan(4);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setBorder(Rectangle.LEFT);
                             table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            phrase = new Phrase("Prepared By:", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(3);
                           // table.getDefaultCell().setColspan(1);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            phrase = new Phrase("........................", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(3);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            phrase = new Phrase("Signature", pFontHeader11);
                            table.addCell(phrase);

                            
                            table.getDefaultCell().setColspan(3);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            phrase = new Phrase("........................", pFontHeader11);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(3);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            phrase = new Phrase("Date", pFontHeader11);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(4);
                              table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                               table.getDefaultCell().setBorder(Rectangle.RIGHT);
                             table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            phrase = new Phrase(".........................", pFontHeader11);
                            table.addCell(phrase);
                            
                            ////////checked by
                            table.getDefaultCell().setColspan(4);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setBorder(Rectangle.LEFT|Rectangle.BOTTOM);
                           
                             table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            phrase = new Phrase("Checked By", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(3);
                           // table.getDefaultCell().setColspan(1);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                               table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            phrase = new Phrase("..........................", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(3);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                               table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            phrase = new Phrase("Signature", pFontHeader11);
                            table.addCell(phrase);

                            
                            table.getDefaultCell().setColspan(3);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            phrase = new Phrase("........................", pFontHeader11);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(3);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                               table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            phrase = new Phrase("Date", pFontHeader11);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(4);
                              table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                               table.getDefaultCell().setBorder(Rectangle.RIGHT|Rectangle.BOTTOM);
                             table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            phrase = new Phrase(".........................", pFontHeader11);
                            table.addCell(phrase);
                            
                            
                            ///////////////////////////votebook section
                              table.getDefaultCell().setColspan(10);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                               table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setBorder(Rectangle.RIGHT|Rectangle.LEFT);
                            phrase = new Phrase("Vote Book Section", pFontHeader12);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(10);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                               table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setBorder(Rectangle.RIGHT);
                            phrase = new Phrase("Vote Holder's Certificate(AIE)", pFontHeader12);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(5);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                               table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                               table.getDefaultCell().setBorder(Rectangle.LEFT);
                            phrase = new Phrase("Account Code", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(5);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                               table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            phrase = new Phrase(".........................", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(5);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                               table.getDefaultCell().setBorder(Rectangle.LEFT);
                            phrase = new Phrase("Name", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(5);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                               table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                               table.getDefaultCell().setBorder(Rectangle.RIGHT);
                            phrase = new Phrase(".........................", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(5);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                               table.getDefaultCell().setBorder(Rectangle.LEFT);
                            phrase = new Phrase("Budget Allocation(Kshs)", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(5);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                               table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            phrase = new Phrase(".........................", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                             table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.LEFT);
                            phrase = new Phrase("Signature", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(3);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                               table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            phrase = new Phrase("..............", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                               table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            phrase = new Phrase("Date", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(3);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                               table.getDefaultCell().setBorder(Rectangle.RIGHT);
                            phrase = new Phrase("..............", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(5);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                               table.getDefaultCell().setBorder(Rectangle.LEFT);
                            phrase = new Phrase("Total Expenditure \n $ Committment(Kshs) ", pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(5);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                             table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                               table.getDefaultCell().setBorder(Rectangle.RIGHT);
                            phrase = new Phrase("..........", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(5);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                               table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            phrase = new Phrase("Department", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(5);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                             table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.RIGHT);
                            phrase = new Phrase("..........................", pFontHeader112);
                            table.addCell(phrase);
                            
//                            table.getDefaultCell().setColspan(5);
//                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
//                               table.getDefaultCell().setBorder(Rectangle.LEFT);
//                            phrase = new Phrase("Balance(Kshs) ", pFontHeader112);
//                            table.addCell(phrase);
//                            
//                            table.getDefaultCell().setColspan(5);
//                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
//                             table.getDefaultCell().setBorder(Rectangle.RIGHT);
//                            phrase = new Phrase("......................", pFontHeader112);
//                            table.addCell(phrase);
//                            
//                            table.getDefaultCell().setColspan(5);
//                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                               table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
//                             // table.getDefaultCell().setBorder(Rectangle.LEFT);
//                            phrase = new Phrase("Examined by Name", pFontHeader112);
//                            table.addCell(phrase);
                            
//                            table.getDefaultCell().setColspan(5);
//                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
//                               table.getDefaultCell().setBorder(Rectangle.RIGHT);
//                            phrase = new Phrase("..................", pFontHeader112);
//                            table.addCell(phrase);
                              table.getDefaultCell().setColspan(5);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                               table.getDefaultCell().setBorder(Rectangle.LEFT);
                            phrase = new Phrase("Balance(Kshs)", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(5);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                             table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                              table.getDefaultCell().setBorder(Rectangle.RIGHT);
                            phrase = new Phrase("..................", pFontHeader112);
                            table.addCell(phrase);
//                            
                            table.getDefaultCell().setColspan(5);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                               table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                              table.getDefaultCell().setBorder(Rectangle.LEFT);
                            phrase = new Phrase("Designation", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(5);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                               table.getDefaultCell().setBorder(Rectangle.RIGHT);
                            phrase = new Phrase(".............................", pFontHeader112);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(5);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                               table.getDefaultCell().setBorder(Rectangle.LEFT);
                            phrase = new Phrase("This entry (Kshs)", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(5);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.RIGHT);
                            phrase = new Phrase("............................", pFontHeader112);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(10);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.RIGHT);
                            phrase = new Phrase("Examination", pFontHeader12);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(5);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                               table.getDefaultCell().setBorder(Rectangle.LEFT);
                            phrase = new Phrase("Balance(Kshs)", pFontHeader112);
                            table.addCell(phrase);
                            
                              table.getDefaultCell().setColspan(5);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                               table.getDefaultCell().setBorder(Rectangle.RIGHT);
                            phrase = new Phrase("...................", pFontHeader112);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(5);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.LEFT);
                            phrase = new Phrase("Examined by Name", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(5);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.RIGHT);
                            phrase = new Phrase(".....................", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.LEFT);
                            phrase = new Phrase("Name", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            
                            phrase = new Phrase("......", pFontHeader112);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(2);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            // table.getDefaultCell().setBorder(Rectangle.LEFT);
                            phrase = new Phrase("Signature", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            
                            phrase = new Phrase(".......", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                             
                            phrase = new Phrase("Date", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                             //table.getDefaultCell().setBorder(Rectangle.RIGHT);
                            phrase = new Phrase(".......", pFontHeader112);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(3);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.LEFT);
                            phrase = new Phrase("Signature", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            
                            phrase = new Phrase("........", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                             
                            phrase = new Phrase("Date", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(3);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.RIGHT);
                            phrase = new Phrase("........", pFontHeader112);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(10);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.LEFT|Rectangle.BOTTOM);
                            phrase = new Phrase("Account I/C Vote Book Section", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(10);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.LEFT|Rectangle.BOTTOM|Rectangle.RIGHT);
                            phrase = new Phrase("", pFontHeader112);
                            table.addCell(phrase);
                            
                            ////////////////Authority to pay
                            
                            table.getDefaultCell().setColspan(10);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.LEFT|Rectangle.RIGHT);
                            phrase = new Phrase("Authority to pay", pFontHeader12);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(10);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.RIGHT);
                            phrase = new Phrase("", pFontHeader112);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(10);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.LEFT);
                            phrase = new Phrase("", pFontHeader12);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(5);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.LEFT);
                            phrase = new Phrase("Paid by Cash /Cheque No", pFontHeader112);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(5);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.RIGHT);
                            phrase = new Phrase("...........................", pFontHeader112);
                            table.addCell(phrase);
                            
                          
                            
                             table.getDefaultCell().setColspan(5);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.LEFT);
                            phrase = new Phrase("Name", pFontHeader112);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(5);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.RIGHT);
                            phrase = new Phrase(".......................", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(8);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.LEFT);
                            phrase = new Phrase("Entered in Cash Book, Folio No", pFontHeader112);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(3);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.RIGHT);
                            phrase = new Phrase("...........................", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(10);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.LEFT|Rectangle.RIGHT);
                            phrase = new Phrase("For Finance Manager", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(10);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.RIGHT);
                            phrase = new Phrase("", pFontHeader112);
                            table.addCell(phrase);
                            
                            
                             table.getDefaultCell().setColspan(2);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.LEFT|Rectangle.BOTTOM);
                            phrase = new Phrase("Signature", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(3);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            phrase = new Phrase("...............", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            phrase = new Phrase("Date", pFontHeader112);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(3);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setBorder(Rectangle.RIGHT|Rectangle.BOTTOM);
                            
                            phrase = new Phrase("...............", pFontHeader112);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(2);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.LEFT|Rectangle.BOTTOM);
                            phrase = new Phrase("Name", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.black);
                             table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            phrase = new Phrase("...........", pFontHeader112);
                            table.addCell(phrase);
                            
                            
                             table.getDefaultCell().setColspan(2);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.black);
                             table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            phrase = new Phrase("Signature", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.black);
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            phrase = new Phrase("..........", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.black);
                             table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            phrase = new Phrase("Date", pFontHeader112);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(2);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setBorder(Rectangle.RIGHT|Rectangle.BOTTOM);
                            phrase = new Phrase("..........", pFontHeader112);
                            table.addCell(phrase);
                            
                            

//                            table.getDefaultCell().setColspan(2);
//                            table.addCell(table1);
//                            table.getDefaultCell().setColspan(2);
//                            table.addCell(table2);

//                            table.getDefaultCell().setFixedHeight(20);
//
//                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
//
//                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP | Rectangle.LEFT | Rectangle.RIGHT);
//
//
////                            table.getDefaultCell().setColspan(2);
////
////                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
////                            phrase = new Phrase("Total", pFontHeader1);
////
////                            table.addCell(phrase);
////
////                            table.getDefaultCell().setColspan(1);
////
////                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
////
////                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Net)), pFontHeader1);
////
////                            table.addCell(phrase);
////                            table.getDefaultCell().setColspan(6);
////                            table.getDefaultCell().setBorder(Rectangle.TOP);
////
////                            phrase = new Phrase(" ", pFontHeader1);
////
////                            table.addCell(phrase);
////
////                            System.out.println("NETT "+ Net);
////                            
////                            table.getDefaultCell().setColspan(6);
////                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
////                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
////                            java.sql.ResultSet rset_cash = st7.executeQuery("select replace(cash_words('" + Net + "'::numeric),'dollars','" + ks + "')");
//
////                            while (rset_cash.next()) {
////                                phrase = new Phrase("Total Amount (in words) : " + rset_cash.getObject(1), pFontHeader1);
//
////                                table.addCell(phrase);
////                            }
//
//                            table.getDefaultCell().setColspan(2);
//                            phrase = new Phrase("Prepared by:............................ ", pFontHeader1);
//                            table.addCell(phrase);
//                            table.getDefaultCell().setColspan(2);
//                            phrase = new Phrase("Designation.................................. ", pFontHeader1);
//                            table.addCell(phrase);
//                            table.getDefaultCell().setColspan(2);
//                            phrase = new Phrase("Date...........................................", pFontHeader1);
//                            table.addCell(phrase);
//
//                            table.getDefaultCell().setColspan(2);
//                            phrase = new Phrase("Checked by:............................ ", pFontHeader1);
//                            table.addCell(phrase);
//                            table.getDefaultCell().setColspan(2);
//                            phrase = new Phrase("Designation.................................. ", pFontHeader1);
//                            table.addCell(phrase);
//                            table.getDefaultCell().setColspan(2);
//                            phrase = new Phrase("Date...........................................", pFontHeader1);
//                            table.addCell(phrase);
//
//                            table.getDefaultCell().setColspan(6);
//                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
//                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
//                            phrase = new Phrase("Account Code...........................................", pFontHeader1);
//                            table.addCell(phrase);
//                            table.getDefaultCell().setColspan(6);
//                            
//
//                           // phrase = new Phrase(" ", pFontHeader1);
//
//                            //table.addCell(phrase);
//                            table.getDefaultCell().setColspan(3);
//                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
//                            phrase = new Phrase("", pFontHeader1);
//                            table.addCell(phrase);
//                            phrase = new Phrase("Examined By..........................................................................", pFontHeader1);
//                            table.addCell(phrase);
//                            phrase = new Phrase("Vote Book Section", pFontHeader1);
//                            table.addCell(phrase);
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                            phrase = new Phrase("Date...........................................", pFontHeader1);
//                            table.addCell(phrase);
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                            phrase = new Phrase("Allocation...........................................................", pFontHeader1);
//                            table.addCell(phrase);
//                            phrase = new Phrase("Vote Holder's Certificate", pFontHeader1);
//                            table.addCell(phrase);
//                            phrase = new Phrase("Total Expenditure...........................................", pFontHeader1);
//                            table.addCell(phrase);
//                            phrase = new Phrase("Signature...........................................................", pFontHeader1);
//                            table.addCell(phrase);
//                            phrase = new Phrase("Balance.............................................................", pFontHeader1);
//                            table.addCell(phrase);
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                            phrase = new Phrase("Date...............................", pFontHeader1);
//                            table.addCell(phrase);
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                            phrase = new Phrase("This entry................................................", pFontHeader1);
//                            table.addCell(phrase);
//                            phrase = new Phrase("Designation...............................................................", pFontHeader1);
//                            table.addCell(phrase);
//                            phrase = new Phrase("Balance.............................................................", pFontHeader1);
//                            table.addCell(phrase);
//                            phrase = new Phrase("Authority to pay", pFontHeader1);
//                            table.addCell(phrase);
//                            table.getDefaultCell().setColspan(2);
//                            phrase = new Phrase("Date...........................................", pFontHeader1);
//                            table.addCell(phrase);
//                            phrase = new Phrase("Signature...........................................", pFontHeader1);
//                            table.addCell(phrase);
//                            phrase = new Phrase("Signature...........................................", pFontHeader1);
//                            table.addCell(phrase);
//                            table.getDefaultCell().setColspan(4);
//                            phrase = new Phrase("", pFontHeader1);
//                            table.addCell(phrase);
//                            table.getDefaultCell().setColspan(2);
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
//                            phrase = new Phrase("Finance Manager", pFontHeader1);
//                            table.addCell(phrase);
//                            table.getDefaultCell().setColspan(1);
//                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
//                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
//                            phrase = new Phrase("", pFontHeader1);
//                            table.addCell(phrase);
//                            table.getDefaultCell().setColspan(3);
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
//                           
//                            phrase = new Phrase("Accountant I/C Vote Book Section", pFontHeader1);
//                            table.addCell(phrase);
//
//                            table.getDefaultCell().setColspan(2);
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                            phrase = new Phrase("Date.........................................", pFontHeader1);
//                            table.addCell(phrase);
//
//
//                            table.getDefaultCell().setColspan(3);
//                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
//
//                            phrase = new Phrase("Payment Voucher No..........................................", pFontHeader1);
//                            table.addCell(phrase);
//                            table.getDefaultCell().setColspan(3);
//                            phrase = new Phrase("Entered in Cash Book Folio No.....................................", pFontHeader1);
//                            table.addCell(phrase);
//                            table.getDefaultCell().setColspan(3);
//                            phrase = new Phrase("Paid by Cash / Cheque No/ EFT No...............................", pFontHeader1);
//                            table.addCell(phrase);
//                            table.getDefaultCell().setColspan(3);
//                            phrase = new Phrase("Signature.................................................", pFontHeader1);
//                            table.addCell(phrase);
//                            table.getDefaultCell().setColspan(3);
//                            phrase = new Phrase("Payee Signature ID..................................................", pFontHeader1);
//                            table.addCell(phrase);
//                            table.getDefaultCell().setColspan(3);
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
//                            phrase = new Phrase("Account I/c of Cash Office", pFontHeader1);
//                            table.addCell(phrase);
                            docPdf.add(table);

                        } catch (java.sql.SQLException SqlExec) {

                            SqlExec.printStackTrace();
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }

                        // }

                    } catch (com.lowagie.text.BadElementException BadElExec) {
                       
                        BadElExec.printStackTrace();

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }

                } catch (java.io.FileNotFoundException fnfExec) {
                    
                    fnfExec.printStackTrace();

                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), fnfExec.getMessage());

                }
            } catch (com.lowagie.text.DocumentException lwDocexec) {

                lwDocexec.printStackTrace();
                
                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), lwDocexec.getMessage());

            }

            docPdf.close();

            docPdf.close();  com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);

        } catch (java.io.IOException IOexec) {
            
            IOexec.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }



    }
}
