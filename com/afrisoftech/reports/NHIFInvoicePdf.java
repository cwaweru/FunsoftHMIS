//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.reports;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class NHIFInvoicePdf implements java.lang.Runnable {

    java.lang.String MNo = null;
    java.lang.String pMNo = null;
    com.afrisoftech.lib.DBObject dbObject;
    java.util.Date beginDate = null;
    java.util.Date endDate = null;
    String ks;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    // org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    String visitID = null;
    //    double osBalance = 0.00;
    //   double current = 0.00;
    //  java.lang.String memNo2Use = null;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
    private String Scheme="-";

    //  public void FinalPatientInvoicePdf(java.sql.Connection connDb, java.lang.String begindate, java.lang.String endate, java.lang.String combox) {
    public void NHIFInvoicePdf(java.sql.Connection connDb, java.lang.String patientNo, java.util.Date begindate, java.util.Date endate, java.lang.String invoiceNo, String scheme) {

        dbObject = new com.afrisoftech.lib.DBObject();

        connectDB = connDb;

        pMNo = invoiceNo;

        MNo = patientNo;

        beginDate = begindate;

        endDate = endate;

        threadSample = new java.lang.Thread(this, "SampleThread");

        System.out.println("threadSample created");

        threadSample.start();

        System.out.println("threadSample fired");
        
        Scheme= scheme;

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

        //   java.lang.Object listofStaffNos1[] = this.getListofStaffNos1();

        //    for (int k = 0; k < listofStaffNos1.length; k++) {

        try {

            java.io.File tempFile = java.io.File.createTempFile("REP" + this.getDateLable() + "_", ".pdf");

            tempFile.deleteOnExit();

            java.lang.Runtime rt = java.lang.Runtime.getRuntime();

            java.lang.String debitTotal = null;

            java.lang.String creditTotal = null;

            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            com.lowagie.text.Document docPdf1 = new com.lowagie.text.Document();
            try {


                try {
                    Image img = Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo());
                 //   //Image imgWaterMark = Image.getInstance(System.getProperty("company.watermark"));

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));


                    String compName = null;
                    String date = null;

                    docPdf.open();
//                    java.lang.Object listofStaffNos1[] = this.getListofStaffNos1();

                    // for (int k = 0; k < listofStaffNos1.length; k++) {


                    try {
                        java.lang.String invoice = null;
                        java.lang.Object listofStaffNos[] = this.getListofStaffNos();

                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(6);

                        int headerwidths[] = {15, 35, 2, 6, 21, 21};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));


                        table.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);

                        table.getDefaultCell().setColspan(6);

                        Phrase phrase = new Phrase();

                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        System.out.println("StepA Complete");
                        try {
                            //-    for (int j = 0; j < listofStaffNos.length; j++) {

                            java.sql.Statement st12 = connectDB.createStatement();
                            java.sql.Statement st6 = connectDB.createStatement();

                            java.sql.Statement st11 = connectDB.createStatement();
                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st4 = connectDB.createStatement();
                            java.sql.Statement st5 = connectDB.createStatement();
                            java.sql.Statement st111 = connectDB.createStatement();
                            java.sql.Statement stA = connectDB.createStatement();
                            java.sql.Statement stnh = connectDB.createStatement();
                            java.sql.Statement st1111 = connectDB.createStatement();
                            java.sql.Statement st2x = connectDB.createStatement();
                            java.sql.Statement stcc = connectDB.createStatement();
                            java.sql.ResultSet rsetcc = stcc.executeQuery("SELECT DISTINCT visit_id FROM hp_patient_card WHERE invoice_no = '"+MNo+"'");
                           // String visitID = "";
                            while(rsetcc.next()){
                                visitID = rsetcc.getString(1);
                            }
                            java.sql.ResultSet rset2x = st2x.executeQuery("SELECT rep_currency from pb_hospitalprofile");
                            while (rset2x.next()) {
                                ks = rset2x.getObject(1).toString();
                            }
                            java.sql.ResultSet rset3 = st3.executeQuery("select DISTINCT hospital_name,postal_code||' '||box_no||' '||town,main_telno from pb_hospitalprofile GROUP BY hospital_name,postal_code||' '||box_no||' '||town,main_telno");
                            java.sql.ResultSet rsetA = stA.executeQuery("select DISTINCT code from pb_nssf_rebeats");
                            java.sql.ResultSet rset = st.executeQuery("select DISTINCT patient_no,initcap(first_name||' '||second_name||' '||last_name),address from hp_inpatient_register where patient_no = '" + pMNo + "' GROUP BY patient_no,first_name||' '||second_name||' '||last_name,address");
                            java.sql.ResultSet rset111 = st111.executeQuery("select DISTINCT disease,discharge_date, claim_no from hp_patient_discharge where patient_no = '" + pMNo + "' ORDER BY discharge_date DESC LIMIT 1");
                            java.sql.ResultSet rset1111 = st1111.executeQuery("select DISTINCT date_admitted,discharge_date::date,discharge_date::date-date_admitted, visit_id from hp_admission where visit_id = '" + visitID + "' AND patient_no = '" + pMNo + "' GROUP BY 1,2,3,4");
                            ////java.sql.ResultSet rset1111 = st1111.executeQuery("select DISTINCT date_admitted,discharge_date::date,discharge_date::date-date_admitted, visit_id from hp_admission where invoice_no = '" + MNo + "' AND patient_no = '" + pMNo + "' GROUP BY 1,2,3,4");
                            //   java.sql.ResultSet rset1111 = st1111.executeQuery("select DISTINCT date_admitted,discharge_date::date,discharge_date::date-date_admitted from hp_admission where invoice_no = '" + MNo + "' AND patient_no = '" + pMNo + "' GROUP BY date_admitted,discharge_date::date");
                            //java.sql.ResultSet rset12 = st12.executeQuery("select distinct date::date from hp_patient_card where invoice_no = '"+MNo+"' AND date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND paid = true and reference IS NOT NULL");
                            //java.sql.ResultSet rset11 = st11.executeQuery("select distinct '"+MNo+"'");

                            double osBalance = 0.00;
                            double current = 0.00;
                            double patient = 0.00;
                            double paid = 0.00;
                            System.out.println("Step1 Complete");
                            while (rset3.next()) {
                                while (rset.next()) {
                                    // while (rset11.next()){
                                    while (rsetA.next()) {
                                        while (rset111.next()) {
                                            while (rset1111.next()) {
                                                visitID = rset1111.getString(4);
                                                table.getDefaultCell().setColspan(6);
                                                table.getDefaultCell().setFixedHeight(55);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                                table.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
                                                table.getDefaultCell().setFixedHeight(16);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                                phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader1);
                                                table.addCell(phrase);


                                                table.getDefaultCell().setColspan(6);
                                                table.getDefaultCell().setBorderColor(java.awt.Color.white);

                                                phrase = new Phrase("P.O. Box : ".toUpperCase() + rset3.getObject(2).toString().toUpperCase(), pFontHeader1);
                                                table.addCell(phrase);

                                                phrase = new Phrase("Tel : ".toUpperCase() + rset3.getObject(3).toString().toUpperCase(), pFontHeader1);

                                                table.addCell(phrase);


                                                table.getDefaultCell().setColspan(6);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                                phrase = new Phrase("N.H.I.F INVOICE".toUpperCase(), pFontHeader1);
                                                table.addCell(phrase);

                                                table.getDefaultCell().setColspan(3);

                                                //                          while (rset11.next())

                                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase("Invoice No.: ".toUpperCase() + MNo.toUpperCase(), pFontHeader1);

                                                table.addCell(phrase);

                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase("Claim Number No. : ".toUpperCase() + dbObject.getDBObject(rset111.getString(3), "-").toUpperCase(), pFontHeader1);

                                                table.addCell(phrase);

                                                //                            while (rset.next())
                                                table.getDefaultCell().setColspan(3);

                                                System.out.println("Step3 Complete");

                                                table.getDefaultCell().setColspan(3);

                                                System.out.println("Step4 Complete");

                                                table.getDefaultCell().setColspan(3);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase("Facility NHIF CODE NO : ".toUpperCase() + dbObject.getDBObject(rsetA.getObject(1), "-").toUpperCase(), pFontHeader1);
                                                table.addCell(phrase);

                                                //                            while (rset.next())
                                                table.getDefaultCell().setColspan(3);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase("Patient No : ".toUpperCase() + dbObject.getDBObject(rset.getObject(1), "-").toUpperCase(), pFontHeader1);

                                                table.addCell(phrase);

                                                table.getDefaultCell().setColspan(3);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase("Patient Name : ".toUpperCase() + dbObject.getDBObject(rset.getObject(2), "-").toUpperCase(), pFontHeader1);
                                                table.addCell(phrase);
                                                table.getDefaultCell().setColspan(6);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase("Address : ".toUpperCase() + dbObject.getDBObject(rset.getObject(3), "-").toUpperCase(), pFontHeader1);

                                                table.addCell(phrase);

                                                table.getDefaultCell().setColspan(3);

                                                phrase = new Phrase("D.O.A :  ".toUpperCase() + dbObject.getDBObject(rset1111.getObject(1), "-").toUpperCase(), pFontHeader1);

                                                table.addCell(phrase);

                                                phrase = new Phrase("D.O.D  :  ".toUpperCase() + dbObject.getDBObject(rset1111.getObject(2), "-").toUpperCase(), pFontHeader1);
                                                table.addCell(phrase);


                                                phrase = new Phrase("Diagnosis  :  ".toUpperCase() + dbObject.getDBObject(rset111.getObject(1), "-").toUpperCase(), pFontHeader1);
                                                table.addCell(phrase);

                                                phrase = new Phrase("Days Admitted :   ".toUpperCase() + dbObject.getDBObject(rset1111.getObject(3), "-").toUpperCase(), pFontHeader1);
                                                table.addCell(phrase);
                                            }
                                        }
                                        //}
                                    }
                                }
                            }

                            docPdf.add(table);

                        } catch (java.sql.SQLException SqlExec) {

                            SqlExec.printStackTrace();
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }
                        //    boolean boolNewPage = docPdf.newPage();
                        //}  // }

                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        BadElExec.printStackTrace();
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }

                    try {
                        java.lang.String invoice = null;

                        java.lang.Object listofStaffNos[] = this.getListofStaffNos();


                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(6);

                        int headerwidths[] = {15, 35, 2, 6, 21, 21};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        table.setHeaderRows(1);

                        table.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);

                        table.getDefaultCell().setColspan(6);

                        Phrase phrase = new Phrase();

                        //  table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        System.out.println("StepA Complete");
                        try {
                            //    for (int j = 0; j < listofStaffNos.length; j++) {

                            java.sql.Statement st12 = connectDB.createStatement();
                            java.sql.Statement st6 = connectDB.createStatement();

                            java.sql.Statement st11 = connectDB.createStatement();
                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st4 = connectDB.createStatement();
                            java.sql.Statement st5 = connectDB.createStatement();
                            java.sql.Statement st111 = connectDB.createStatement();
                            java.sql.Statement stA = connectDB.createStatement();
                            java.sql.Statement stnh = connectDB.createStatement();

                        //    java.sql.ResultSet rsetTotals = st2.executeQuery("select sum(debit-credit) from hp_patient_card where invoice_no = '" + MNo + "' AND patient_no = '" + pMNo + "' AND date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'");
                           java.sql.ResultSet rsetTotals = st2.executeQuery("select sum(debit-credit) from hp_patient_card where visit_id = '" + visitID + "' AND patient_no = '" + pMNo + "' AND date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'");

                            double osBalance = 0.00;
                            double current = 0.00;
                            double patient = 0.00;
                            double paid = 0.00;


                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Date", pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Description", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Qty", pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Price @", pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Total " + ks, pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);

                            for (int j = 0; j < listofStaffNos.length; j++) {
                                //      java.sql.ResultSet rset1 = st1.executeQuery("select date::date,initcap(service) as service,dosage,(debit-credit)/dosage,debit-credit from hp_patient_card where patient_no = '"+MNo+"' AND date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' and main_service = '"+listofStaffNos[j]+"' order by date::date");

                                java.sql.ResultSet rset1 = st1.executeQuery("select date::date,initcap(service) as service,dosage,(debit)/dosage,debit from hp_patient_card where visit_id = '" + visitID + "' AND patient_no = '" + pMNo + "' and main_service = '" + listofStaffNos[j] + "' AND debit >0  AND transaction_type != 'Remittance' UNION ALL select date::date,initcap(service) as service,dosage,(credit)/dosage,-credit from hp_patient_card where main_service = '" + listofStaffNos[j] + "' AND credit > 0 AND visit_id = '" + visitID + "' AND patient_no = '" + pMNo + "' AND transaction_type ILIKE 'Billing%' group by 1,2,3,4,5 order by 1");
                                table.getDefaultCell().setColspan(6);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(listofStaffNos[j].toString().toUpperCase(), pFontHeader1);

                                table.addCell(phrase);

                                while (rset1.next()) {


                                    table.getDefaultCell().setColspan(1);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset1.getObject(1), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(2);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset1.getObject(2), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(dbObject.getDBObject(rset1.getInt(3), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(4)), pFontHeader);

                                    table.addCell(phrase);

                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(5)), pFontHeader);

                                    table.addCell(phrase);

                                    osBalance = osBalance + rset1.getDouble(5);

                                }
                            }
                            table.getDefaultCell().setColspan(6);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);

                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setColspan(3);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("", pFontHeader);

                            table.addCell(phrase);

                            while (rsetTotals.next()) {

                                table.getDefaultCell().setColspan(2);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase("Bill Amt.", pFontHeader1);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);



                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)), pFontHeader);

                                table.addCell(phrase);

                            }

                           // java.sql.ResultSet rsetnh = stnh.executeQuery("select distinct sum(credit) from hp_patient_card where invoice_no = '" + MNo + "' AND reference IS NOT NULL AND main_service = 'N.H.I.F'");
                                java.sql.ResultSet rsetnh = stnh.executeQuery("select debit from ac_debtors where invoice_no = '"+MNo+"' and dealer ilike '"+Scheme+"' ");//dealer = 'N.H.I.F' AND date between '"+beginDate+"' AND '"+endDate+"'");


                            while (rsetnh.next()) {

                                table.getDefaultCell().setColspan(3);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(" ", pFontHeader1);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase("NHIF To Pay :", pFontHeader1);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);



                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetnh.getString(1)), pFontHeader);

                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(3);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(" ", pFontHeader1);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase("NHIF DIFF. :", pFontHeader1);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                paid = osBalance - rsetnh.getDouble(1);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(paid)), pFontHeader);

                                table.addCell(phrase);

                            }

                            //}
                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("PREPARED BY :".toUpperCase(), pFontHeader1);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);

                            //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("Accounts Clearance (Name) :.................................................................".toUpperCase(), pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);

                            //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("", pFontHeader);//HOSP. ADM :................................................................", pFontHeader);

                            table.getDefaultCell().setColspan(6);

                            //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("SIGNATURE :.......................................................................".toUpperCase(), pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);

                            //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("", pFontHeader);//SIGNATURE :...............................................................", pFontHeader);

                            //   table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);

                            //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("DATE :..................................................................................", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);

                            //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("", pFontHeader);//DATE :...........................................................................", pFontHeader);

                            table.addCell(phrase);

                            java.sql.PreparedStatement pstmtUser = connectDB.prepareStatement("SELECT current_user");
                            java.sql.ResultSet rsetUser = pstmtUser.executeQuery();
                            while (rsetUser.next()) {
                                java.sql.PreparedStatement pstmtUserName = connectDB.prepareStatement("SELECT DISTINCT f_name || ' ' || l_name  FROM secure_menu_access WHERE login_name = ?");
                                pstmtUserName.setString(1, rsetUser.getString(1));
                                java.sql.ResultSet rsetUserName = pstmtUserName.executeQuery();
                                while (rsetUserName.next()) {
                                    phrase = new Phrase("Printed by : ".toUpperCase() + rsetUserName.getString(1).toUpperCase(), pFontHeader1);
                                }
                            }

                            //HOSP. ADM :................................................................", pFontHeader);

                            table.addCell(phrase);
                            /*
                             java.sql.PreparedStatement pstmtDueDate = connectDB.prepareStatement("SELECT date_part('day', (select discharge_date from hp_patient_discharge where patient_no = ? LIMIT 1) + 30) || '-'|| date_part('month', now()::date + 30) ||'-'|| date_part('year', now()::date + 30)");
                             pstmtDueDate.setString(1, this.MNo);
                             java.sql.ResultSet rsetDueDate = pstmtDueDate.executeQuery();
                             while (rsetUser.next()) {
                             phrase = new Phrase("Invoice/Claim due 30 days from the date of this invoice.", pFontHeader1);
                             }
                             */
                            //   phrase = new Phrase("Invoice/Claim due date : "+, pFontHeader1);//HOSP. ADM :................................................................", pFontHeader);
                            phrase = new Phrase("Invoice/Claim due 30 days from the date of this invoice."/*+rsetDueDate.getString(1)*/, pFontHeader1);
                            table.addCell(phrase);


                            docPdf.add(table);
                            boolean boolNewPage = docPdf.newPage();

                        } catch (java.sql.SQLException SqlExec) {

                            SqlExec.printStackTrace();

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }
                        // }

                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        BadElExec.printStackTrace();
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }
                    //}
                } catch (java.io.FileNotFoundException fnfExec) {
                    fnfExec.printStackTrace();
                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), fnfExec.getMessage());

                }
            } catch (com.lowagie.text.DocumentException lwDocexec) {

                lwDocexec.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), lwDocexec.getMessage());

            }

     //       docPdf.close();
            docPdf.close();
            com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);



        } catch (java.io.IOException IOexec) {
            IOexec.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());
        }
    }

    public java.lang.Object[] getListofStaffNos() {

        java.lang.Object[] listofStaffNos = null;

        java.util.Vector listStaffNoVector = new java.util.Vector(1, 1);


        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            java.sql.Statement st4 = connectDB.createStatement();
            java.sql.ResultSet rSet1 = st4.executeQuery("SELECT distinct main_service FROM hp_patient_card WHERE visit_id = '" + visitID + "' and debit > 0 order by main_service");
           //// java.sql.ResultSet rSet1 = st4.executeQuery("SELECT distinct main_service FROM hp_patient_card WHERE invoice_no = '" + MNo + "' and debit > 0 order by main_service");

            // java.sql.ResultSet rSet1 = pSet1.executeQuery();

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
