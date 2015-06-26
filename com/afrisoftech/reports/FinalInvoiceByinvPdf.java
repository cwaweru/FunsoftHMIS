//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.reports;

import static com.afrisoftech.reports.FinalDescInPatientIntmlnvPdf.connectDB;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class FinalInvoiceByinvPdf implements java.lang.Runnable {

    java.lang.String MNo = null;
    com.afrisoftech.lib.DBObject dbObject;
    java.lang.String beginDate = null;
    java.lang.String endDate = null;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    String ks;
    String claimNumber = "-";
    String nhifOPCardNumber = "-";
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    //  public void FinalPatientInvoicePdf(java.sql.Connection connDb, java.lang.String begindate, java.lang.String endate, java.lang.String combox) {
    public void FinalInvoiceByinvPdf(java.sql.Connection connDb, java.lang.String inv1, java.lang.String inv2) {

        dbObject = new com.afrisoftech.lib.DBObject();

        connectDB = connDb;

        beginDate = inv1;

        endDate = inv2;
        threadSample = new java.lang.Thread(this, "SampleThread");

        System.out.println("threadSample created");

        threadSample.start();

        System.out.println("threadSample fired");

    }

    public void FinalInvoiceByinvPdf(java.sql.Connection connDb, java.lang.String inv1, java.lang.String inv2, java.lang.String claimNo, java.lang.String cardNo) {

        dbObject = new com.afrisoftech.lib.DBObject();

        connectDB = connDb;

        beginDate = inv1;

        this.nhifOPCardNumber = cardNo;

        this.claimNumber = claimNo;

        endDate = inv2;
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

                java.lang.Thread.currentThread().sleep(100);

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


        //  try {

        try {

            //   Image img = Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo());
            //   //Image imgWaterMark = Image.getInstance(System.getProperty("company.watermark"));

            java.io.File tempFile = java.io.File.createTempFile("REP" + this.getDateLable() + "_", ".pdf");

            tempFile.deleteOnExit();

            java.lang.Runtime rt = java.lang.Runtime.getRuntime();

            java.lang.String debitTotal = null;

            java.lang.String creditTotal = null;

            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();



            try {

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));



                    String Address = null;
                    String Tel = null;
                    String compName = null;
                    String Fax = null;
                    String Email = null;
                    String date = null;

                    try {
                        docPdf.open();

                        java.util.Calendar calendar = java.util.Calendar.getInstance();

                        long dateNow = calendar.getTimeInMillis();

                        java.sql.Date datenowSql = new java.sql.Date(dateNow);

                        System.out.println(datenowSql.toString());

                        //       java.lang.Object listofStaffNos[] = this.getListofStaffNos();


                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(7);
                        //  com.lowagie.text.Table table = new com.lowagie.text.Table(7);

                        // table.endHeaders();

                        int headerwidths[] = {15, 15, 30, 15, 15, 15, 15};
                        try {
                            table1.setWidths(headerwidths);
                            //  if (docPdf.getPageNumber() > 1) {
                            //  table1.setHeaderRows(4);
                            //  }
                            Phrase phrase = new Phrase();
                            table1.setWidthPercentage((100));
                            table1.getDefaultCell().setBorder(Rectangle.BOX);
                            table1.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table1.getDefaultCell().setColspan(2);
                            table1.getDefaultCell().setFixedHeight(70);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table1.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
                            table1.getDefaultCell().setFixedHeight(16);
                            java.sql.PreparedStatement st321 = connectDB.prepareStatement("select header_name from pb_header");
                            java.sql.ResultSet rset3 = st321.executeQuery();
                            table1.getDefaultCell().setBorder(Rectangle.BOX);
                            table1.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            while (rset3.next()) {
                                table1.getDefaultCell().setColspan(5);

                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(rset3.getObject(1).toString().toUpperCase(), pFontHeader1);
                                table1.addCell(phrase);
                            }


                            table1.getDefaultCell().setBorder(Rectangle.BOTTOM);

                            table1.getDefaultCell().setColspan(7);

                            //       Phrase phrase = new Phrase();

                            //  table.addCell(phrase);

                            table1.getDefaultCell().setColspan(1);
                            table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table1.getDefaultCell().setBorderColor(java.awt.Color.WHITE);



                            java.sql.Statement st2x = connectDB.createStatement();

                            java.sql.ResultSet rset2x = st2x.executeQuery("SELECT rep_currency from pb_hospitalprofile");
                            while (rset2x.next()) {
                                ks = rset2x.getObject(1).toString();
                            }
                            java.sql.Statement st3 = connectDB.createStatement();
                            /* java.sql.ResultSet rset3 = st3.executeQuery("select header_name from pb_header");
                             while (rset3.next()){
                             table1.getDefaultCell().setColspan(7);
                                
                             table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                             phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader11);
                             table1.addCell(phrase);
                             }
                             */
                        } catch (java.sql.SQLException SqlExec) {

                            SqlExec.printStackTrace();

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }


                        docPdf.add(table1);

                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        BadElExec.printStackTrace();

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }




                    try {

                        java.sql.Statement st2x = connectDB.createStatement();

                        java.sql.ResultSet rset2x = st2x.executeQuery("SELECT rep_currency from pb_hospitalprofile");
                        while (rset2x.next()) {
                            ks = rset2x.getObject(1).toString();
                        }
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                        java.sql.ResultSet rset2 = st3.executeQuery("select hospital_name,postal_code||' '||box_no||' '||town,main_telno||' '||other_telno,initcap(street),main_faxno,email||'   '||website,room_no from pb_hospitalprofile");

                        while (rset2.next()) {
                            compName = rset2.getObject(1).toString();
                            Address = rset2.getObject(2).toString();
                            Tel = rset2.getObject(3).toString();
                            Fax = rset2.getObject(5).toString();
                            Email = rset2.getObject(6).toString();

                        }
                        //  while(rset4.next())
                        //    date = rset4.getObject(1).toString();

                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase("" + compName + "\n Address: " + Address + "\n Tel: " + Tel + " \n Fax: " + Fax + "\n Email: " + Email + ""), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));

                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        //  headerFoter.ALIGN_CENTER;
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);

                        headerFoter.setRight(5);
                        docPdf.setHeader(headerFoter);


                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }


                    String Messg = null;

                    try {


                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                        java.sql.ResultSet rset2 = st3.executeQuery("select name from pb_notice");

                        // java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name from pb_hospitalprofile");
                        //   java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while (rset2.next()) {
                            Messg = rset2.getObject(1).toString();
                        }


                        //  while(rset4.next())
                        //    date = rset4.getObject(1).toString();

                        com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("" + Messg + ""), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));

                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        //  headerFoter.ALIGN_CENTER;
                        //  headerFoter.setRight(5);
                        docPdf.setFooter(footer);


                    } catch (java.sql.SQLException SqlExec) {

                        SqlExec.printStackTrace();

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }






                    try {

                        java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                        String visitId = "";
                        for (int j = 0; j < listofStaffNos.length; j++) {
                            com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(6);

                            int headerwidths[] = {12, 43, 15, 15, 15, 15};

                            table.setWidths(headerwidths);

                            table.setWidthPercentage((100));


                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                            table.getDefaultCell().setColspan(6);

                            Phrase phrase = new Phrase();

                            //  table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            // table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            System.out.println(listofStaffNos[j]);
                            try {
                                java.sql.Statement st311 = connectDB.createStatement();
                                java.sql.Statement st12 = connectDB.createStatement();
                                java.sql.Statement st6 = connectDB.createStatement();
                                java.sql.Statement st61 = connectDB.createStatement();
                                java.sql.Statement st11 = connectDB.createStatement();
                                java.sql.Statement st = connectDB.createStatement();
                                java.sql.Statement st1 = connectDB.createStatement();
                                java.sql.Statement st1d = connectDB.createStatement();
                                java.sql.Statement st2 = connectDB.createStatement();
                                java.sql.Statement st31 = connectDB.createStatement();
                                java.sql.Statement st4 = connectDB.createStatement();
                                java.sql.Statement st5 = connectDB.createStatement();
                                //java.sql.ResultSet rset311 = st311.executeQuery("select ap.payer_id from ac_debtors ac,ac_scheme_providers ap where ac.dealer = ap.scheme_manager AND ac.invoice_no = '"+listofStaffNos[j]+"'");


                                java.sql.ResultSet rset31 = st31.executeQuery("select pc.payee,sp.postal_code||' '||sp.address,sp.tel_main||' '||sp.other_tel,sp.main_fax from ac_schemes sp,ac_debtors pc where pc.account_no = sp.account_no and pc.invoice_no = '" + listofStaffNos[j] + "'");
                                java.sql.ResultSet rset4 = st4.executeQuery("select dealer from ac_debtors where invoice_no = '" + listofStaffNos[j] + "'");
                                java.sql.ResultSet rset5 = st5.executeQuery("select db.member_no,'-' from ac_debtors db where db.invoice_no = '" + listofStaffNos[j] + "'");
                                java.sql.ResultSet rset = st.executeQuery("select admission_no,initcap(item) from ac_debtors where invoice_no = '" + listofStaffNos[j] + "'");
                                java.sql.ResultSet rset1 = st1.executeQuery("select date::date,initcap(service) as service,dosage::int,reference::int4,debit-credit from hp_patient_card where invoice_no = '" + listofStaffNos[j] + "' AND reference NOT ILIKE 'C%' AND service not ilike 'Invoice' order by debit-credit desc");
                                java.sql.ResultSet rset1d = st1d.executeQuery("select date::date,initcap(service) as service,dosage::int,reference::int4,debit-credit from hp_patient_card where invoice_no = '" + listofStaffNos[j] + "' AND service not ilike 'Invoice'  AND reference ILIKE 'C%'");
                                java.sql.ResultSet rsetTotals = st2.executeQuery("select sum(credit) from hp_patient_card where invoice_no = '" + listofStaffNos[j] + "' AND paid = true");
                                java.sql.ResultSet rset12 = st12.executeQuery("select date from ac_debtors where invoice_no = '" + listofStaffNos[j] + "'");
                                java.sql.ResultSet rset11 = st11.executeQuery("select invoice_no, claim_no, journal_no from ac_debtors where invoice_no = '" + listofStaffNos[j] + "' and invoice_no IS NOT NULL");

                                double osBalance = 0.00;
                                double current = 0.00;
                                System.out.println(listofStaffNos[j]);
                                System.out.println("Step1 Complete");



                                table.getDefaultCell().setColspan(6);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("FINAL INVOICE", pFontHeader1);
                                table.addCell(phrase);

                                while (rset4.next()) {
                                    table.getDefaultCell().setColspan(3);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Payer Name: " + dbObject.getDBObject(rset4.getObject(1), "-"), pFontHeader1);
                                    table.addCell(phrase);
                                    // }
                                    table.getDefaultCell().setColspan(3);

                                    while (rset11.next()) {
                                        //  table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(" ", pFontHeader);
                                        //  phrase = new Phrase("Invoice No. ", pFontHeader);

                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(6);

                                        while (rset31.next()) {

                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase("Scheme Name: " + dbObject.getDBObject(rset31.getObject(1), "-"), pFontHeader1);
                                            table.addCell(phrase);

                                            System.out.println("This Is the scheme name" + phrase.toString());

                                            table.getDefaultCell().setColspan(3);

                                            table.getDefaultCell().setColspan(3);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase("Address.: " + dbObject.getDBObject(rset31.getObject(2), "-"), pFontHeader);
                                            table.addCell(phrase);
                                            while (rset12.next()) {
                                                table.getDefaultCell().setColspan(3);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase("Invoice Date.: " + dbObject.getDBObject(rset12.getObject(1), "-"), pFontHeader);
                                                table.addCell(phrase);

                                                table.getDefaultCell().setColspan(3);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase("Tel.: " + dbObject.getDBObject(rset31.getObject(3), "-"), pFontHeader);

                                                table.addCell(phrase);

                                                table.getDefaultCell().setColspan(3);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase("Claim Number.: " + dbObject.getDBObject(rset11.getObject(2), "-"), pFontHeader);

                                                table.addCell(phrase);

                                                table.getDefaultCell().setColspan(3);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase("Fax.: " + dbObject.getDBObject(rset31.getObject(4), "-"), pFontHeader);

                                                table.addCell(phrase);      //  table.getDefaultCell().setColspan(1);
                                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase("Invoice No.: " + dbObject.getDBObject(rset11.getObject(1), "-"), pFontHeader);
                                                //  phrase = new Phrase("Invoice No. ", pFontHeader);

                                                table.addCell(phrase);
                                                System.out.println("Step3 Complete");
                                                table.getDefaultCell().setColspan(3);
                                                while (rset5.next()) {

                                                    table.getDefaultCell().setColspan(3);
                                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                    // phrase = new Phrase("Member No.", pFontHeader);

                                                    phrase = new Phrase("Card/Referral No.: " + dbObject.getDBObject(rset11.getObject(3), "-"), pFontHeader);

                                                    table.addCell(phrase);

                                                    System.out.println("Step4 Complete");


                                                    while (rset.next()) {

                                                        table.getDefaultCell().setColspan(3);
                                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                        phrase = new Phrase("Patient Name.: " + dbObject.getDBObject(rset.getObject(2), "-"), pFontHeader);
                                                        table.addCell(phrase);



                                                        table.getDefaultCell().setColspan(3);
                                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                        phrase = new Phrase("Member Name.: " + dbObject.getDBObject(rset5.getObject(2), "-"), pFontHeader);
                                                        //   phrase = new Phrase("Member Name  ", pFontHeader);

                                                        table.addCell(phrase);



                                                        table.getDefaultCell().setColspan(3);
                                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                        //   phrase = new Phrase("Member Name  "+rset5.getString(2), pFontHeader);
                                                        phrase = new Phrase("Patient No.: " + dbObject.getDBObject(rset.getObject(1), "-"), pFontHeader);

                                                        table.addCell(phrase);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }


                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setBorderWidth(Rectangle.TOP);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Date", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Description", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("Qty", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Ref", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Amount", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Balance " + ks, pFontHeader1);
                                table.addCell(phrase);

                                while (rset1.next()) {
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset1.getObject(1), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset1.getObject(2), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    int dos = 0;
                                    dos = rset1.getInt(3);

                                    phrase = new Phrase(java.lang.String.valueOf(dos), pFontHeader);

                                    //  phrase = new Phrase(dbObject.getDBObject(rset1.getObject(3), "-"), pFontHeader);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);
                                    //table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset1.getObject(4), "-"), pFontHeader);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(5)), pFontHeader);
                                    table.addCell(phrase);
                                    osBalance = osBalance + rset1.getDouble(5);

                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)), pFontHeader);
                                    table.addCell(phrase);


                                }
                                table.getDefaultCell().setColspan(3);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase("", pFontHeader);

                                table.addCell(phrase);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);

                                while (rsetTotals.next()) {

                                    table.getDefaultCell().setColspan(1);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase("Invoice Amt.", pFontHeader1);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);

                                    //   phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)), pFontHeader);
                                    phrase = new Phrase(" ", pFontHeader);

                                    table.addCell(phrase);

                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)), pFontHeader1);

                                    table.addCell(phrase);


                                    //phrase = new Phrase(" ");



                                }

                                while (rset1d.next()) {
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset1d.getObject(1), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset1d.getObject(2), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset1d.getObject(3), "-"), pFontHeader);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset1d.getObject(4), "-"), pFontHeader);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1d.getString(5)), pFontHeader);
                                    // osBalance = osBalance + rset.getDouble(5);
                                    table.addCell(phrase);
                                    osBalance = osBalance - rset1d.getDouble(5);

                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)), pFontHeader);
                                    //   current = current + osBalance;

                                    table.addCell(phrase);
                                    //  osBalance = osBalance + rset.getDouble(5);
                                    // table.addCell(phrase);


                                }

                                table.getDefaultCell().setColspan(3);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase("", pFontHeader);

                                table.addCell(phrase);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);


                                table.getDefaultCell().setColspan(1);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase("Total", pFontHeader1);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);

                                //   phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)), pFontHeader);
                                phrase = new Phrase(" ", pFontHeader);

                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)), pFontHeader1);

                                table.addCell(phrase);




                                table.getDefaultCell().setColspan(36);

                                //   phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)), pFontHeader);
                                phrase = new Phrase(" ", pFontHeader);

                                table.addCell(phrase);
                                //   table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setColspan(6);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                //  phrase = new Phrase("Patient's Signature : ____________________________", pFontHeader1);
                                phrase = new Phrase(" ", pFontHeader1);

                                table.addCell(phrase);
                                docPdf.add(table);

                            } catch (java.sql.SQLException SqlExec) {

                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                            }
                            boolean boolNewPage = docPdf.newPage();
                        }  // }

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


            //     PdfContentByte under;
            //     PdfReader reader = new PdfReader(tempFile.getPath());
            //      int n = reader.getNumberOfPages();
            //      int i = 0;
            //               tempFile.delete();
            //       java.io.File tempFile2 = java.io.File.createTempFile("REP" + this.getDateLable() + "_", ".pdf");
//                tempFile = tempFile.createNewFile();
               /* try{
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
             */ docPdf.close();
            com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);



        } catch (java.io.IOException IOexec) {

            IOexec.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());
        }


        /* } catch(com.lowagie.text.BadElementException badEl){
        
         badEl.printStackTrace();
         }*/
    }

    public java.lang.Object[] getListofStaffNos() {

        java.lang.Object[] listofStaffNos = null;

        java.util.Vector listStaffNoVector = new java.util.Vector(1, 1);


        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT  invoice_no FROM ac_debtors WHERE invoice_no  BETWEEN ? AND ? AND transaction_type = ? AND payee != ? order by invoice_no");
           // java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT distinct invoice_no FROM ac_debtors WHERE invoice_no  = ?  AND payee != ? order by invoice_no");

            //  java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT distinct patient_no FROM hp_patient_register WHERE last_visit  BETWEEN ? AND ? AND pay_mode = ? order by patient_no");
            pSet1.setString(1, beginDate.toString());
            pSet1.setString(2, endDate.toString());
            pSet1.setString(3, "Raise Invoice");
            pSet1.setString(4, "''");
            java.sql.ResultSet rSet1 = pSet1.executeQuery();

            // java.sql.Statement stmt1 = connectDB.createStatement();

            // java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT distinct patient_no FROM hp_patient_card WHERE date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND payment_mode = 'Scheme' order by patient_no");

            while (rSet1.next()) {

                listStaffNoVector.addElement(rSet1.getObject(1).toString());

            }

        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofStaffNos = listStaffNoVector.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNos;
    }
}
