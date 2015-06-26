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

public class ImprestSurrenderPdf implements java.lang.Runnable {

    java.lang.String MNo = null;
    com.afrisoftech.lib.DBObject dbObject;
    java.lang.String vouchNo = null;
    java.lang.String beginDate = null;
    java.lang.String endDate = null;
    String suppName = null,ks=null;
    String compName = null;
    String date = null,reason=null;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    boolean previewPrint;
   
    //  java.lang.String memNo2Use = null;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHead = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLDITALIC);
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
    com.lowagie.text.Font pFontUnderline = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.UNDERLINE);
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.TIMES, 11, Font.BOLD);
    com.lowagie.text.Font pFontHeader3 = FontFactory.getFont(FontFactory.TIMES, 16, Font.BOLDITALIC);
    com.lowagie.text.Font pFontHeader31 = FontFactory.getFont(FontFactory.TIMES, 16, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
   String dealer,dealer_code,debit,local_date;
double debitor=0.0,overspent=0.0;
    // public void ReceiptsPdf(java.sql.Connection connDb, java.lang.String begindate, ,java.lang.String endate, java.lang.String combox) {
    public void ImprestSurrenderPdf(java.sql.Connection connDb,java.lang.String voucno) {

        dbObject = new com.afrisoftech.lib.DBObject();
        vouchNo =voucno;
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
                    double Vat = 0.00;
                    double Net = 0.00;
                    double Net1 = 0.00;
                    int vno = 0;
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
                           
                            java.sql.Statement st7 = connectDB.createStatement();
                            
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st33 = connectDB.createStatement();
 java.sql.ResultSet rset81 = st7.executeQuery("select DISTINCT dealer,dealer_code,local_date from ac_unstockable_items where voucher_no = '" + vouchNo + "'");

                            while (rset81.next()) {
                                dealer = rset81.getString("dealer");
                                dealer_code= rset81.getString("dealer_code");
                                local_date= rset81.getString("local_date");
                                 
                            }
        java.sql.Statement strs = connectDB.createStatement();                    
        java.sql.ResultSet rset813 = strs.executeQuery("select debit,(surrender_amount-debit) from ac_bills where final_voucher_no = '" + vouchNo + "'");

                            while (rset813.next()) {

                                  debit= rset813.getString(1);
                                  overspent= rset813.getDouble(2);
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
                            table.getDefaultCell().setColspan(6);
                            // table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                            //table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            phrase = new Phrase("FINANCE DEPARTMENT", pFontHeader31);

                            table.addCell(phrase);
                            // table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);


                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                           


                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("From : "+dealer , pFontHeader);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("PF No: "+dealer_code , pFontHeader);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Date: "+local_date , pFontHeader);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(6);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Thro'" , pFontHead);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("The Head : ............................... ", pFontHeader);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Department : ......................." , pFontHeader);
                                table.addCell(phrase);
                                
                                
     
                                table.getDefaultCell().setColspan(6);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("The Finance Manager" , pFontHeader11);
                                table.addCell(phrase);
//                                
                                String cashWord="";
                            java.sql.ResultSet rset_cash = st7.executeQuery("select replace(cash_words('" + Double.parseDouble(debit) + "'::money),'dollars','Kenya Shillings only')");
                                if(rset_cash.next()){
                                 cashWord=rset_cash.getString(1);
                                }
                            
                            
                            table.getDefaultCell().setColspan(6);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            phrase = new Phrase("Accounting for Imprest", pFontHead);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            phrase = new Phrase("Attached here with find relevant receipts accounting for imprest form No .............. of Kshs "+debit+" (Amount in words) "+cashWord+" only and entered in the imprest register as Folio No.................", pFontHeader1);
                            table.addCell(phrase);
                            
                            
                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("This was disbursed as follows:-", pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            phrase = new Phrase("Receipts : (Attach receipts)", pFontHeader11);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            
                            String description=null,particular=null,debits=null;                
       java.sql.Statement strs1 = connectDB.createStatement();                    
        java.sql.ResultSet rset8134 = strs1.executeQuery("select * from ac_unstockable_items where voucher_no = '" + vouchNo + "'");
                             while (rset8134.next()) {
                                description= rset8134.getString("description");
                                 particular= rset8134.getString("particular");
                                  debits= rset8134.getString("debit");
                                  debitor=debitor+Double.parseDouble(rset8134.getString("debit"));
                                  reason=rset8134.getString("reason");
                                  
                             table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(description, pFontHeader1);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(particular, pFontHeader1);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Ksh."+debits, pFontHeader1);
                            table.addCell(phrase);
                            }  
                            
 
                           table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                           table.getDefaultCell().setColspan(5);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            phrase = new Phrase("Total.:" , pFontHead);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            phrase = new Phrase(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(debitor), pFontHead);
                            table.addCell(phrase);
                           
//             
                            
                           table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            if(overspent>0){
                                phrase = new Phrase("Over Spent Ksh.: "+com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(overspent), pFontHeader1);
                            }else{
                                phrase = new Phrase("Over Spent Ksh.:................... ", pFontHeader1);
                            }
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              if(overspent>0){
                               phrase = new Phrase("Reason.: .."+reason, pFontHeader1);
                            }else{
                                phrase = new Phrase("Reason.: ....................", pFontHeader1);
                            }
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("P.V NO:.......................................... ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("MR. NO:............................................... ", pFontHeader1);
                            table.addCell(phrase);
                            
                            
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Claimant:.......................................... ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Signature............................................... ", pFontHeader1);
                            table.addCell(phrase);
                            
                        
                            
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Note: ", pFontHeader1);
                            table.addCell(phrase);  
                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("i). Over expenditure refund is subject to 15% of amount advanced and must be approved by the Director or the Deputy Director, Finance and Administration.", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader1);
                            table.addCell(phrase);  
                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("ii). Taxi fare is payable to and from the airport only where authority was sought.", pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader1);
                            table.addCell(phrase);  
                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("iii). Use of personal vehicle is considered only where prior approval was sought.", pFontHeader1);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader1);
                            table.addCell(phrase);  
                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("iv). Expenses must be accompanied by receipts.", pFontHeader1);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader1);
                            table.addCell(phrase);  
                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("v). Unsurrendered or under expenditure will result to the salary deduction of the same if cash is not paid back to the cashier.", pFontHeader1);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader1);
                            table.addCell(phrase);  
                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("vi). The surrender may be rejected if the accounting documents are found not to be genuine", pFontHeader1);
                            table.addCell(phrase);
                            
                
                            table.getDefaultCell().setFixedHeight(0);
                            table.getDefaultCell().setColspan(6);
                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);
//                           
                            
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Passed by:................... ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Date............................................... ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Signature............................................... ", pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("Expenditure", pFontHead);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Finance Manager", pFontHead);
                            table.addCell(phrase);
                            
                            
                            table.getDefaultCell().setColspan(6);
                            phrase = new Phrase("", pFontHead);
                            table.addCell(phrase);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Passed by:................ ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Date............................................... ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Imprest Register Posted by :............................................... ", pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(6);
                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Internal Audit", pFontHead);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Date......................", pFontHead);
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
