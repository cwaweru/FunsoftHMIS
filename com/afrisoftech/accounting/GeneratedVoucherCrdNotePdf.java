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
import java.util.Vector;

public class GeneratedVoucherCrdNotePdf implements java.lang.Runnable {

    java.lang.String MNo = null;
    com.afrisoftech.lib.DBObject dbObject;
    java.lang.String Dealer = null;
    java.lang.String beginDate = null;
    java.lang.String endDate = null,transtype=null;
   // String suppName = null;
    String compName = null;
    String date = null;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    boolean previewPrint;
    String ks;
    String voucher_nos=null;
     Double total_amount=0.0;
     String activity_code=null,transnos=null,invs=null;
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
     com.lowagie.text.Font pFontHeader14 = FontFactory.getFont(FontFactory.TIMES, 9, Font.NORMAL);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
    java.lang.String Number=null;
    private String totalAmount="";

    // public void ReceiptsPdf(java.sql.Connection connDb, java.lang.String begindate, java.lang.String endate, java.lang.String combox) {
    public void GeneratedVoucherCrdNotePdf(java.sql.Connection connDb,java.lang.String number, java.lang.String dealer,java.lang.String invoice,java.lang.String voucher) {

        dbObject = new com.afrisoftech.lib.DBObject();
        Dealer = dealer;
        
        connectDB = connDb;
        voucher_nos=voucher;
        Number=number;
        //transnos=trans;
        invs=invoice;
        System.out.println("the number is "+number+"\n and the dealer is "+dealer+""
                + "\n and the invoiceno is "+invoice+"\n"
                + "and the voucher np is "+voucher);
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
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                     
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
//                            java.sql.ResultSet rset81 = st5.executeQuery("SELECT DISTINCT cb.supplier FROM ac_aie_commitment cb WHERE cb.voucher_no = '" + vouchNo + "' ORDER BY cb.supplier");
//
//                            while (rset81.next()) {
//                                this.suppName = rset81.getString(1);
//                            }
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
                            
                         
                            java.sql.ResultSet rs = st.executeQuery("SELECT DISTINCT hospital_name,box_no,main_telno,main_faxno FROM pb_hospitalprofile");
                            while (rs.next()) {

                                table.getDefaultCell().setColspan(14);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rs.getObject(1).toString() + "\nP.O. Box " + rs.getObject(2).toString() + " - Tel: " + rs.getObject(3).toString(), pFontHeader31);
                                table.addCell(phrase);
                                
                                /////PV NO
                                table.getDefaultCell().setColspan(3);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("PV NO:  "+ voucher_nos, pFontHeader112);
                                table.addCell(phrase);
    

                            }
                            table.getDefaultCell().setFixedHeight(0);
                            System.out.println(MNo);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setColspan(16);
                            phrase = new Phrase("PAYMENT VOUCHER", pFontHeader32);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(2);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("FO 20 VOTED [ ]:  ", pFontHeader112);
                                table.addCell(phrase);
    
                            table.getDefaultCell().setColspan(2);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("FO 21 VOTED [ ]:  " , pFontHeader112);
                                table.addCell(phrase);
    





                            //while (rset1.next()) {
                                table.getDefaultCell().setColspan(5);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Payee's Name : ", pFontHeader);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(15);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(Dealer, pFontHeader);

                                table.addCell(phrase);
                                
                                 java.sql.ResultSet rset189 = st33.executeQuery("SELECT postal_address,pin_no,vat_no FROM st_suppliers WHERE supplier_name ILIKE '%" +Dealer+ "%'");
                                
                                 while (rset189.next()) {
                                 table.getDefaultCell().setColspan(20);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Address: " + dbObject.getDBObject(rset189.getObject(1), "-"), pFontHeader);

                                table.addCell(phrase);
                                 }
                            //}


                            table.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setColspan(9);
                            

                            phrase = new Phrase("Particulars", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(3);
                           // table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("LPO/LSO No.", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Invoice", pFontHeader11);
                            table.addCell(phrase);
                             table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Amt(Kshs) " , pFontHeader11);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("(Cts) ", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                           
                               System.out.println(""
                                     + "(Select      distinct\n" +
"  st_stock_cardex.item,st_stock_cardex.activity_code,\n" +
"  Sum(st_stock_cardex.quantity_received) ,\n" +
"  Sum(st_stock_cardex.debit) \n" +
"From\n" +
"  st_stock_cardex\n" +
"Where\n" +
"  st_stock_cardex.order_no ='"+Number+"' And " +
"  st_stock_cardex.supplier ='"+Dealer+"' " +
"Group By\n" +
"  st_stock_cardex.item, st_stock_cardex.debit, st_stock_cardex.supplier,\n" +
"  st_stock_cardex.item_code, st_stock_cardex.delivery_note_no,\n" +
"  st_stock_cardex.grn_no, st_stock_cardex.quantity_received,st_stock_cardex.activity_code\n" +
"Having\n" +
"  Sum(st_stock_cardex.debit - st_stock_cardex.quantity_ordered) > 0\n" +
"Order By\n" +
"  st_stock_cardex.item)\n" +
" union (Select  concat(ac_bills.reason, '', ac_bills.description) , ac_bills.code,   ac_bills.credit ,ac_bills.debit From    ac_bills\n" +
" where ac_bills.final_voucher_no='"+voucher_nos+"' and ac_bills.voucher_no='"+invs+"' and ac_bills.dealer='"+Dealer+"')\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                            
                             java.sql.ResultSet rset8167 = st5.executeQuery(""
                                     + "(Select      distinct\n" +
"  st_stock_cardex.item,st_stock_cardex.activity_code,\n" +
"  Sum(st_stock_cardex.quantity_received) ,\n" +
"  Sum(st_stock_cardex.debit) \n" +
"From\n" +
"  st_stock_cardex\n" +
"Where\n" +
"  st_stock_cardex.order_no ='"+Number+"' And " +
"  st_stock_cardex.supplier ='"+Dealer+"' " +
"Group By\n" +
"  st_stock_cardex.item, st_stock_cardex.debit, st_stock_cardex.supplier,\n" +
"  st_stock_cardex.item_code, st_stock_cardex.delivery_note_no,\n" +
"  st_stock_cardex.grn_no, st_stock_cardex.quantity_received,st_stock_cardex.activity_code\n" +
"Having\n" +
"  Sum(st_stock_cardex.debit - st_stock_cardex.quantity_ordered) > 0\n" +
"Order By\n" +
"  st_stock_cardex.item)\n" +
" union (Select  concat(ac_bills.reason, '', ac_bills.description) , ac_bills.code,   ac_bills.credit ,ac_bills.debit From    ac_bills\n" +
" where ac_bills.final_voucher_no='"+voucher_nos+"' and ac_bills.voucher_no='"+invs+"' and ac_bills.dealer='"+Dealer+"')");
                             
                          
                             
                              
                                table.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setColspan(9);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setFixedHeight(100);
                               String particulars="";
                               
                               Vector partiK=new Vector();
                                Vector amount=new Vector();
                               
                            while (rset8167.next()) {
                              
                               partiK.addElement(rset8167.getString(1));
                               amount.addElement(rset8167.getString(4));
                            // total_amount=Double.parseDouble(rset8167.getString(3));
                              
                            activity_code=rset8167.getString(2).toString();
                            
                            transtype=rset8167.getString(3).toString();
       
                                  java.lang.Double psl=rset8167.getDouble(4);
                                 totalAmount=  totalAmount +"\n"+psl+"\n";
                                 total_amount=total_amount+psl;
                            }
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\nthe amount is "+amount.elements());
                            for(int k=0;k<partiK.size();k++){
                               particulars=  particulars.concat((k+1)+":"+partiK.elementAt(k).toString()+"\n");
                            }
                            System.out.println("the vector is"+ amount);
                         
                            
                            System.out.println(particulars+"\n\n\n\n\n\n");
                             phrase = new Phrase( particulars, pFontHeader14);
                            
                            table.addCell(phrase);
                          
                           
                            table.getDefaultCell().setColspan(3);
                           // table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(Number, pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(invs, pFontHeader11);
                            table.addCell(phrase);
                             table.getDefaultCell().setColspan(3);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase( totalAmount.toString(), pFontHeader11);
                            table.addCell(phrase);
                           
                            table.getDefaultCell().setColspan(2);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("00", pFontHeader11);
                            table.addCell(phrase);
                            
                          
                       
                           
                            String money_words=null;
                            
                            java.sql.Statement st4542 = connectDB.createStatement();
                            java.sql.ResultSet rs4542 = st4542.executeQuery("select replace(cash_words('"+total_amount+"' ::money), 'dollars', 'Shillings')");
                            while (rs4542 .next()) {

                                money_words=rs4542.getString(1);
                            }
                            
                           table.getDefaultCell().setFixedHeight(0);
                           // table.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setColspan(15);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.LEFT);
                            phrase = new Phrase("Amt Payable in words(Kshs)    "+money_words+"\n", pFontHeader1);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(3);
                             table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                           //  table.getDefaultCell().setBorder(Rectangle.BOTTOM|Rectangle.LEFT|Rectangle.RIGHT);
                            phrase = new Phrase(total_amount.toString(), pFontHeader11);
                            table.addCell(phrase);
                             table.getDefaultCell().setColspan(2);
                             table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                             //table.getDefaultCell().setBorder(Rectangle.BOTTOM|Rectangle.RIGHT);
                            phrase = new Phrase("00", pFontHeader11);
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
                            
                            // java.sql.ResultSet rset8134 = st5.executeQuery("select  code,activity  from pb_activity where  code ='" +activity_code+ "'"); 
//                            java.sql.ResultSet rset8134 = st5.executeQuery("select activity_code,description,transaction_no from ac_ledger   WHERE  cheque_no='" +lponumber+ "' and reconciled=true and transaction_no='"+transnos+"'"); 
//                            while (rset8134.next()) {
                                //this.suppName = rset8134.getString(1);
                          
                             table.getDefaultCell().setColspan(12);
                             table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                           //  table.getDefaultCell().setBorder(Rectangle.BOTTOM|Rectangle.LEFT|Rectangle.RIGHT);
                            phrase = new Phrase(activity_code, pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(4);
                             table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                           //  table.getDefaultCell().setBorder(Rectangle.BOTTOM|Rectangle.LEFT|Rectangle.RIGHT);
                            phrase = new Phrase(transtype, pFontHeader11);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(2);
                             table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                           //  table.getDefaultCell().setBorder(Rectangle.BOTTOM|Rectangle.LEFT|Rectangle.RIGHT);
                            phrase = new Phrase(total_amount.toString(), pFontHeader11);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(2);
                             table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                           //  table.getDefaultCell().setBorder(Rectangle.BOTTOM|Rectangle.LEFT|Rectangle.RIGHT);
                            phrase = new Phrase("00", pFontHeader11);
                            table.addCell(phrase);
                            
//                            }
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
                            phrase = new Phrase(total_amount.toString(), pFontHeader11);
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
                            phrase = new Phrase(total_amount.toString(), pFontHeader11);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(2);
                             table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                           //  table.getDefaultCell().setBorder(Rectangle.BOTTOM|Rectangle.LEFT|Rectangle.RIGHT);
                            phrase = new Phrase(total_amount.toString(), pFontHeader11);
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
                          String user=null,date_nows=null;
                            try{
                              
                             java.sql.Statement st112 = connectDB.createStatement();
                                java.sql.ResultSet rs112 = st112.executeQuery("SELECT CURRENT_USER");
                                while (rs112.next()) {


                                user = rs112.getObject(1).toString();

                            }
                           java.sql.Statement st1122 = connectDB.createStatement();
                                java.sql.ResultSet rs1122 = st1122.executeQuery("select current_date::date");
                                while (rs1122.next()) {


                                date_nows = rs1122.getObject(1).toString();

                            }     
                                
            
                        }
                        catch(Exception hds){
                        
                        hds.printStackTrace();
                        
                        }
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            phrase = new Phrase(user, pFontHeader11);
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
                            phrase = new Phrase(date_nows, pFontHeader11);
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
                            //////1
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
                            phrase = new Phrase(".............. ", pFontHeader112);
                            table.addCell(phrase);
                            /////2
                            table.getDefaultCell().setColspan(6);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                               table.getDefaultCell().setBorder(Rectangle.LEFT);
                            phrase = new Phrase("Budget Allocation(Kshs)", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(4);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                               table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            phrase = new Phrase(".............. ", pFontHeader112);
                            table.addCell(phrase);
                            //////3
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
                            ///////444
                            table.getDefaultCell().setColspan(6);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                               table.getDefaultCell().setBorder(Rectangle.LEFT);
                            phrase = new Phrase("Total Expenditure & Committment(Kshs) ", pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(4);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                             table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                               table.getDefaultCell().setBorder(Rectangle.RIGHT);
                            phrase = new Phrase("..........", pFontHeader112);
                            table.addCell(phrase);
                            //////33333
                            table.getDefaultCell().setColspan(5);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                               table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            phrase = new Phrase("Department", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(5);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                             table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.RIGHT);
                            phrase = new Phrase(".............. ", pFontHeader112);
                            table.addCell(phrase);
                            //////555

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
                            phrase = new Phrase(".............. ", pFontHeader112);
                            table.addCell(phrase);
//                            ///////gggg
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
                            phrase = new Phrase(".............. ", pFontHeader112);
                            table.addCell(phrase);
                            ////////ffffff
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
                            phrase = new Phrase(".............. ", pFontHeader112);
                            table.addCell(phrase);
                            ////////nn
                             table.getDefaultCell().setColspan(10);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.RIGHT);
                            phrase = new Phrase("Examination", pFontHeader12);
                            table.addCell(phrase);
                            ///////nnn
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
                            phrase = new Phrase(".............. ", pFontHeader112);
                            table.addCell(phrase);
                            //////yuyuy
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
                            phrase = new Phrase(".............. ", pFontHeader112);
                            table.addCell(phrase);
                            ////////ffff
                            table.getDefaultCell().setColspan(5);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.LEFT);
                            phrase = new Phrase("Name.....", pFontHeader112);
                            table.addCell(phrase);
                           
                             table.getDefaultCell().setColspan(5);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setBorder(Rectangle.RIGHT);
                            phrase = new Phrase("Signature....", pFontHeader112);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(5);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.LEFT);
                            phrase = new Phrase(" ", pFontHeader112);
                            table.addCell(phrase);
                           
                             table.getDefaultCell().setColspan(5);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.RIGHT);
                            phrase = new Phrase(" ", pFontHeader112);
                            table.addCell(phrase);
                           
                            
                            table.getDefaultCell().setColspan(5);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                               table.getDefaultCell().setBorder(Rectangle.LEFT);
                            phrase = new Phrase("Date .......", pFontHeader112);
                            table.addCell(phrase);
                            
                           
                            ///////ggggg
                             table.getDefaultCell().setColspan(5);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.RIGHT);
                            phrase = new Phrase("Signature........", pFontHeader112);
                            table.addCell(phrase);
                            
                          
                            table.getDefaultCell().setColspan(2);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                              table.getDefaultCell().setBorder(Rectangle.LEFT);
                            phrase = new Phrase("Date", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(3);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                            phrase = new Phrase("........", pFontHeader112);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(5);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.RIGHT);
                            phrase = new Phrase(" ", pFontHeader112);
                            table.addCell(phrase);
                            

                            /////hjhjh
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
                            /////hjhjjhhj
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
                            ///ghhghghghg
                          
                            
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
                            ////hhjhjh
                            table.getDefaultCell().setColspan(6);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.LEFT);
                            phrase = new Phrase("Entered in Cash Book, Folio No", pFontHeader112);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(4);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.RIGHT);
                            phrase = new Phrase("................... ", pFontHeader112);
                            table.addCell(phrase);
                           // /jjjjj
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
                            
                            /////ghj
                             table.getDefaultCell().setColspan(3);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.LEFT|Rectangle.BOTTOM);
                            phrase = new Phrase("Signature", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            phrase = new Phrase("........", pFontHeader112);
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
                            ////fdghjk
                            phrase = new Phrase("...............", pFontHeader112);
                            table.addCell(phrase);
                            ///ghjkljhg authority to pay
                             table.getDefaultCell().setColspan(3);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                              table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.LEFT|Rectangle.BOTTOM);
                            phrase = new Phrase("Name......", pFontHeader112);
                            table.addCell(phrase);
                          
                             table.getDefaultCell().setColspan(2);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.black);
                             table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            phrase = new Phrase("Signature", pFontHeader112);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.black);
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            phrase = new Phrase("........", pFontHeader112);
                            table.addCell(phrase);
                            ///gfhjk
                            table.getDefaultCell().setColspan(3);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              table.getDefaultCell().setBorderColor(java.awt.Color.black);
                             table.getDefaultCell().setBorder(Rectangle.RIGHT|Rectangle.BOTTOM);
                            phrase = new Phrase("Date.....", pFontHeader112);
                            table.addCell(phrase);
                            
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
