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

public class ImprestVoucherPdf implements java.lang.Runnable {

    java.lang.String MNo = null;
    com.afrisoftech.lib.DBObject dbObject;
    java.lang.String vouchNo = null;
    java.lang.String beginDate = null;
    java.lang.String endDate = null;
    String suppName = null;
    String compName = null;
    String date = null,dates2=null;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    boolean previewPrint;
    String particulars,
  order_no,
  invoice,
  invoice_amt,
  invoice_total_amt,
  prepared_by,
  accounts_code,dates;
  double allocation,total_commitments,balance1,this_entry,balance2;
    
    String ks;
    //  java.lang.String memNo2Use = null;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
    com.lowagie.text.Font pFontUnderline = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.UNDERLINE);
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.TIMES, 11, Font.BOLD);
    com.lowagie.text.Font pFontHeader3 = FontFactory.getFont(FontFactory.TIMES, 16, Font.BOLDITALIC);
    com.lowagie.text.Font pFontHeader31 = FontFactory.getFont(FontFactory.TIMES, 16, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    // public void ReceiptsPdf(java.sql.Connection connDb, java.lang.String begindate, java.lang.String endate, java.lang.String combox) {
    public void ImprestVoucherPdf(java.sql.Connection connDb, java.lang.String voucno) {

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
                        
                       
                        com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Withholding Tax, where applicable,has been deducted and will be remitted to the Tax Authorities"), false);
                        //: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                        //docPdf.setFooter(footer);

                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }


                    docPdf.open();
                    
                    try {


                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(6);

                        int headerwidths[] = {20, 25, 25, 20, 20, 30};

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
                        table.getDefaultCell().setColspan(1);
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
                            
                            java.sql.ResultSet rset81 = st5.executeQuery("select * from ac_reprinting_vouchers where voucher_no = '" + vouchNo + "'");

                            while (rset81.next()) {
                                this.suppName = rset81.getString("payee");
                                particulars= rset81.getString("particulars");
                                 order_no= rset81.getString("order_no");
                                    invoice= rset81.getString("invoice");
                                    invoice_amt= rset81.getString("invoice_amt");
                                    invoice_total_amt= rset81.getString("invoice_total_amt");
                                    prepared_by= rset81.getString("prepared_by");
                                    accounts_code= rset81.getString("accounts_code");
                                    allocation= rset81.getDouble("allocation");
                                    total_commitments= rset81.getDouble("total_commitments");
                                    balance1= rset81.getDouble("balance1");
                                    this_entry= rset81.getDouble("this_entry");
                                    balance2= rset81.getDouble("balance2");
                                    dates= rset81.getString("date");
                                   
                                    java.sql.Statement df=connectDB.createStatement();
                                    java.sql.ResultSet rd=df.executeQuery("select '"+dates+"'::date +14");
                                    while(rd.next()){
                                    dates2=rd.getString(1);
                                    
                                    }
                                    
                            }
                            java.sql.ResultSet rset3 = st3.executeQuery("select hospital_name,postal_code||' '||box_no||' '||town,main_telno||' '||other_telno,initcap(street),main_faxno,email,website,room_no,pin_no,vat_no  from pb_hospitalprofile");
                            
                            Phrase phrase = new Phrase("");


                            table.getDefaultCell().setColspan(2);

                            Image img = Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo());
                            //Image imgWaterMark = Image.getInstance(System.getProperty("company.watermark"));
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setFixedHeight(70);
                            table.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setFixedHeight(25);
                            //java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.ResultSet rs = st.executeQuery("SELECT DISTINCT hospital_name,box_no,main_telno,main_faxno FROM pb_hospitalprofile");
                            while (rs.next()) {

                                table.getDefaultCell().setColspan(5);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rs.getObject(1).toString() + " \nP.O. Box " + rs.getObject(2).toString() + " - Tel: " + rs.getObject(3).toString() + " - Fax " + rs.getObject(3).toString(), pFontHeader31);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(6);
                                //phrase = new Phrase("P.O. Box " + rs.getObject(2).toString() + " - Tel: " + rs.getObject(3).toString() + " - Fax " + rs.getObject(3).toString(), pFontHeader1);
                                //table.addCell(phrase);

                            }

                            java.sql.ResultSet rset1 = st33.executeQuery("SELECT postal_address,pin_no,vat_no FROM st_suppliers WHERE supplier_name ILIKE '" + suppName + "'");

                            System.out.println(MNo);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setColspan(5);
                            // table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                            //table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            phrase = new Phrase("IMPREST FORM", pFontHeader31);

                            table.addCell(phrase);
                            // table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);


                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("No : " + vouchNo, pFontHeader);
                            table.addCell(phrase);



//                            while (rset1.next()) {

                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("From : " + this.suppName, pFontHeader);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("PF No: "+invoice , pFontHeader);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Designation: ............................" , pFontHeader);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Department:............................... ", pFontHeader);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Date : "+dates , pFontHeader);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("" , pFontHeader);
                                table.addCell(phrase);
     
                    //        }
                                table.getDefaultCell().setColspan(6);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("TO  :      SAD - FINANCE" , pFontHeader11);
                                table.addCell(phrase);
                                
                                String cashWord="";
                            java.sql.ResultSet rset_cash = st7.executeQuery("select replace(cash_words('" + Double.parseDouble(invoice_total_amt) + "'::money),'dollars','Kenya Shillings')");
                                if(rset_cash.next()){
                                 cashWord=rset_cash.getString(1);
                                }
                            
                            
                            table.getDefaultCell().setColspan(6);
                             table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("(A). Application:", pFontHeader11);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            phrase = new Phrase("1. Please advance to me Kshs "+invoice_total_amt+" (amount in words) "+ cashWord +" only to meet the disbursement of the following purposes:", pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Purpose ;"+particulars, pFontHeader1);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("The expenditure from the amount is chargeable to vote "+accounts_code, pFontHeader1);
                            table.addCell(phrase);

                           
//             
                            
                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Declaration:", pFontUnderline);
                            table.addCell(phrase);
                        
                            
                              
                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setFixedHeight(35);
                            phrase = new Phrase("2.I undertake to submit and account for the imprest given to me and the balance of money to the hospital by date  "+dates2+". In the event of not accounting this within 14 days of the said date you are hereby authorised to recover the whole amount of the imprest from my salary.", pFontHeader1);
                            table.addCell(phrase);
                
                            table.getDefaultCell().setFixedHeight(0);
                            table.getDefaultCell().setColspan(6);
                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);
//                           
                            
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Applicant Sign:.......................................... ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("AIE Holder............................................... ", pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(6);
//                             table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
//                             table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);
                            
                            
 // expenditure
                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);
                            
                                                 
                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("(B). Expenditure Section:", pFontHeader11);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);  
                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setFixedHeight(35);
                            phrase = new Phrase("I certify that the amount has been entered in the imprest register Folio No: ..................... and the applicant does not have any outstanding imprest.", pFontHeader1);
                            table.addCell(phrase);
                
                            table.getDefaultCell().setFixedHeight(0);
                            table.getDefaultCell().setColspan(6);
                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);
//                         
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Name.............................", pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Signature.............................", pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Date.............................", pFontHeader1);
                            table.addCell(phrase);
                            
                            
                            table.getDefaultCell().setColspan(6);
//                             table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
//                             table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);
                            
                            
  //Burgetary control          
                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);
                            
                            
                             table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            phrase = new Phrase("(C). Budgetary Control :", pFontHeader11);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);
                            
                            
                             table.getDefaultCell().setBorderColor(java.awt.Color.WHITE); 
                            table.getDefaultCell().setColspan(6);
                            phrase = new Phrase("Vote book checked and confirmed funds available / not available as follows :-", pFontHeader1);
                            table.addCell(phrase);
  
                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Yearly Allocation..:", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(allocation), pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Name..............................................", pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Cummulative Expenditure..:", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(total_commitments), pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);
                            
                            
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Signature..............................................", pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Balance Available..:", pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(balance1), pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Accountant I/c -Vote Book", pFontHeader11);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("This Commitment..:", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(this_entry), pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Date :"+dates, pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Balance after this Entry..:", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(balance2), pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);
                            
 //examinations
                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            phrase = new Phrase("(D). Examinations :", pFontHeader11);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);
                            
                            
                             table.getDefaultCell().setBorderColor(java.awt.Color.WHITE); 
                            table.getDefaultCell().setColspan(6);
                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setBorderColor(java.awt.Color.WHITE); 
                            table.getDefaultCell().setColspan(6);
                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);
  
//                            table.getDefaultCell().setColspan(6);
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                            phrase = new Phrase("", pFontHeader1);
//                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Name.............................", pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Signature.............................", pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Date.............................", pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(5);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Accountant I/C", pFontHeader11);
                            table.addCell(phrase);
                            
                            
             // recommmended
//                               table.getDefaultCell().setColspan(6);
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
//                            phrase = new Phrase("(E)", pFontHeader11);
//                            table.addCell(phrase);
//                            
//                             table.getDefaultCell().setBorderColor(java.awt.Color.WHITE); 
//                            table.getDefaultCell().setColspan(6);
//                            phrase = new Phrase("Recommended / Not certified for payment", pFontHeader1);
//                            table.addCell(phrase);
//  
//                            table.getDefaultCell().setColspan(6);
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                            phrase = new Phrase("", pFontHeader1);
//                            table.addCell(phrase);
//                            
//                            table.getDefaultCell().setColspan(4);
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                            phrase = new Phrase("Date.......................", pFontHeader1);
//                            table.addCell(phrase);
//                            
//                            table.getDefaultCell().setColspan(2);
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                            phrase = new Phrase("Signature..................", pFontHeader1);
//                            table.addCell(phrase);
//                            
//                              table.getDefaultCell().setColspan(5);
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                            phrase = new Phrase("", pFontHeader1);
//                            table.addCell(phrase);
//                            
//                            table.getDefaultCell().setColspan(1);
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                            phrase = new Phrase("Chief Accountant", pFontHeader11);
//                            table.addCell(phrase);
//    //approved
                               table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            phrase = new Phrase("(E). Authority to Pay", pFontHeader11);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setBorderColor(java.awt.Color.WHITE); 
                            table.getDefaultCell().setColspan(6);
                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setBorderColor(java.awt.Color.WHITE); 
                            table.getDefaultCell().setColspan(6);
                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);
  
                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Name..................", pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Date.......................", pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Signature..................", pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(5);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("SAD Finance", pFontHeader11);
                            table.addCell(phrase);
                            
//                            table.getDefaultCell().setColspan(6);
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                            phrase = new Phrase("Year Allocation...........................................................", pFontHeader1);
//                            table.addCell(phrase);
//                            
//                            
//                            
//                            
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
