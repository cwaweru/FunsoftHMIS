//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.hospayroll;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.awt.Color;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.io.FileOutputStream;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.concurrent.TimeUnit;
//import org.openide.util.Exceptions;


public class SendPayslipPerStaffPdf1 implements java.lang.Runnable {

    com.afrisoftech.lib.DBObject dbObject;
    java.util.Date beginDate = null;
    java.util.Date endDate = null;
    java.util.Date dateEnd = null;
    java.lang.String StaffNo = null;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    Phrase phrase1;
    Phrase phrase2;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeaderx = FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD | Font.UNDERLINE | Font.ITALIC);
    com.lowagie.text.Font pFontHeaderxx = FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD | Font.UNDERLINE | Font.ITALIC);

    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD | Font.ITALIC);
    com.lowagie.text.Font pFontNum = FontFactory.getFont(FontFactory.COURIER, 7, Font.NORMAL | Font.TIMES_ROMAN |  Font.ITALIC);
    com.lowagie.text.Font pFontNum1 = FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD | Font.ITALIC);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
    String idNo = "-";

    public void SendPayslipPerStaffPdf1(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String staffno) {
        dbObject = new com.afrisoftech.lib.DBObject();

        connectDB = connDb;
        beginDate = begindate;
        endDate = endate;
        StaffNo = staffno;

        threadSample = new java.lang.Thread(this, "SampleThread");

        System.out.println("threadSample created");

        threadSample.start();

        System.out.println("threadSample fired");

    }

    public static void main(java.lang.String[] args) {
        //		new GlTransactPdf().GlTransactPdf();
    }

    public void run() {

        System.out.println("System has entered running mode");

        while (threadCheck) {

            System.out.println("O.K. see how we execute target program");

            try {
                this.generatePdf();
            } catch (DocumentException ex) {
                ex.printStackTrace();
            }

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

        java.util.Date date_now = endDate;//calinst.getTime();

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
                month_now_strs = "JANUARY";

                break;

            case 1:
                month_now_strs = "FEBRUARY";

                break;

            case 2:
                month_now_strs = "MARCH";

                break;

            case 3:
                month_now_strs = "APRIL";

                break;

            case 4:
                month_now_strs = "MAY";

                break;

            case 5:
                month_now_strs = "JUNE";

                break;

            case 6:
                month_now_strs = "JULY";

                break;

            case 7:
                month_now_strs = "AUGUST";

                break;

            case 8:
                month_now_strs = "SEPTEMBER";

                break;

            case 9:
                month_now_strs = "OCTBER";

                break;

            case 10:
                month_now_strs = "NOVEMBER";

                break;

            case 11:
                month_now_strs = "DECEMBER";

                break;

            default:
                if (month_now_str < 10) {

                    month_now_strs = ""; //0" + month_now_str;

                } else {

                    month_now_strs = "" + month_now_str;

                }

        }

        if (date_now_str < 10) {

            date_now_strs = ""; //0" + date_now_str;

        } else {

            date_now_strs = "";// + date_now_str;

        }

        if (minute_now_str < 10) {

            minute_now_strs = ""; //0" + minute_now_str;

        } else {

            minute_now_strs = ""; //+ minute_now_str;

        }

        if (hour_now_str < 10) {

            hour_now_strs = ""; //0" + hour_now_str;

        } else {

            hour_now_strs = ""; // + hour_now_str;

        }

        date_label = month_now_strs + " " + year_now_strs; // + "@" + hour_now_strs + minute_now_strs;

        return date_label;

    }

    public void generatePdf() throws DocumentException {
        java.sql.ResultSet rsetTotals1 = null;
        java.sql.ResultSet rsetTotals1o = null;
        java.lang.Process wait_for_Pdf2Show;

        java.util.Calendar cal = java.util.Calendar.getInstance();

        java.util.Date dateStampPdf = cal.getTime();

        java.lang.String pdfDateStamp = dateStampPdf.toString();

        try {

            //java.io.File tempFile = java.io.File.createTempFile("REP" + this.getDateLable() + "_", ".pdf");
            java.io.File tempFile = new java.io.File(System.getProperty("user.home"), StaffNo + " " + getDateLable() + ".pdf");
            java.io.File tempFile1 = new java.io.File(System.getProperty("user.home"), StaffNo + " - " + getDateLable() + ".pdf");

            tempFile.deleteOnExit();
            tempFile1.deleteOnExit();

            java.lang.Runtime rt = java.lang.Runtime.getRuntime();

            java.lang.String debitTotal = null;

            java.lang.String creditTotal = null;

            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();

            try {

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));

                    String compName = null;
                    String date = null;
                    try {

                        java.sql.Statement st31 = connectDB.createStatement();
                        java.sql.Statement st41 = connectDB.createStatement();

                        java.sql.ResultSet rset21 = st31.executeQuery("SELECT hospital_name from pb_hospitalprofile");
                        java.sql.ResultSet rset41 = st41.executeQuery("SELECT date('now') as Date");
                        while (rset21.next()) {
                            compName = rset21.getObject(1).toString();
                        }
                        while (rset41.next()) {
                            date = rset41.getObject(1).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase("" + compName + ""), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));

                        //         com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        headerFoter.setRight(2);
                        //docPdf.setHeader(headerFoter);

                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Payslip - Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    //docPdf.setFooter(footer);
                    docPdf.open();

                    try {

                        java.lang.Object listofStaffNos[] = this.getListofStaffNos();

                        for (int j = 0; j < listofStaffNos.length; j++) {
                            double chargeable = 0;
                            double relief = 0;
                            double earnings = 0;

                            com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(3);

                            int headerwidths[] = {20, 25, 35};

                            table.setWidths(headerwidths);

                            table.setWidthPercentage((100));

                            table.setHeaderRows(1);

//                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            table.getDefaultCell().setColspan(3);

                            Phrase phrase;

                            try {
                                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);

                                java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                                java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());

                                System.out.println("" + endDate1);
                                //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);

                                //  table.addCell(phrase);
                                table.getDefaultCell().setColspan(3);

                                Date date1 = endDate11; // your date
                                Calendar cal1 = Calendar.getInstance();
                                cal1.setTime(date1);
                                int year = cal1.get(Calendar.YEAR);
                                int month = cal1.get(Calendar.MONTH);
                                String month_now_strs = "";

                                switch (month) {

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

                                }
                                table.getDefaultCell().setBorderColor(Color.WHITE);

                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setFixedHeight(30);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                table.addCell(Image.getInstance(System.getProperty("company.logo")));
                                table.getDefaultCell().setFixedHeight(16);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("", pFontHeaderx);//From " + dateFormat.format(endDate11) + " To " + dateFormat.format(endDate1)
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(compName.toUpperCase(), pFontHeaderx);//From " + dateFormat.format(endDate11) + " To " + dateFormat.format(endDate1)
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("", pFontHeaderx);//From " + dateFormat.format(endDate11) + " To " + dateFormat.format(endDate1)
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);

                                
                                phrase = new Phrase("Pay Period : " + month_now_strs + ", " + year, pFontHeaderxx);//From " + dateFormat.format(endDate11) + " To " + dateFormat.format(endDate1)
                                table.addCell(phrase);

                                table.getDefaultCell().setFixedHeight(11);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("", pFontHeaderx);//From " + dateFormat.format(endDate11) + " To " + dateFormat.format(endDate1)
                                table.addCell(phrase);
                            } catch (java.text.ParseException psExec) {

                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                            }
                            //     phrase = new Phrase("Pay Period: " +beginDate+" --"+endDate , pFontHeader);

                            //          table.addCell(phrase);
                            //        } catch(java.text.ParseException psExec) {
                            //           javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                            //       }
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            double taxCharged = 0;
                            double paye = 0;
                            double noncash = 0;
                            double totalTaxed = 0;
                            double nonTaxed = 0;

                            try {

                                java.lang.Object[] listofAct = this.getListofActivities();

                                //  java.sql.Connection conDb1 = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                                System.out.println(listofAct.length);
                                //tmpdir
                                java.sql.Statement st = connectDB.createStatement();
                                java.sql.Statement st2 = connectDB.createStatement();
                                java.sql.Statement st3 = connectDB.createStatement();
                                java.sql.Statement st4 = connectDB.createStatement();
                                java.sql.Statement st3A = connectDB.createStatement();
                                java.sql.Statement st4A = connectDB.createStatement();
                                java.sql.Statement st31 = connectDB.createStatement();
                                java.sql.Statement st12 = connectDB.createStatement();
                                java.sql.Statement st13 = connectDB.createStatement();
                                java.sql.Statement st15 = connectDB.createStatement();
                                java.sql.Statement st15s = connectDB.createStatement();
                                java.sql.Statement st14 = connectDB.createStatement();
                                java.sql.Statement st16 = connectDB.createStatement();
                                java.sql.Statement st17 = connectDB.createStatement();
                                java.sql.Statement st18 = connectDB.createStatement();
                                java.sql.Statement st19 = connectDB.createStatement();
                                java.sql.Statement stss = connectDB.createStatement();
                                java.sql.Statement st4o = connectDB.createStatement();
                                System.out.println("Statements Created ...");

                                System.out.println(listofStaffNos[j].toString());

                                java.sql.ResultSet rset = st.executeQuery("select distinct employee_no,first_name||' '||middle_name||' '||last_name,official_desgnation,pin_no,id_no,date_employed,(current_date-date_employed)/365,birth_date,(current_date-birth_date)/365,employee_grade,department from master_file where employee_no = '" + listofStaffNos[j] + "' order by employee_no");
                                java.sql.ResultSet rsetss = stss.executeQuery("select distinct bank,bank_account_no,payment_mode from master_file where employee_no = '" + listofStaffNos[j] + "'");
                                java.sql.ResultSet rsetTotals = st2.executeQuery("SELECT sum(tt.amount) from posting tt where tt.staff_no = '" + listofStaffNos[j] + "' AND tt.date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND allowance_deduction ILIKE 'Earning%'");

                                String desgniation = "";
                                String grade = "";
                                String department = "";
                                String bank = "";
                                String bankaccNo = "";
                                
                                java.sql.Statement pst1 = connectDB.createStatement();
                                java.sql.ResultSet rs1 = pst1.executeQuery("SELECT desgination, grade, department, bank_name, account_no  FROM master_updates WHERE staff_no = '" + listofStaffNos[j] + "'  AND date = '"+endDate+"' ");
                                while (rs1.next()) {
                                    desgniation = (rs1.getString(1));
                                    grade = (rs1.getString(2));
                                    department = (rs1.getString(3));
                                    bank = (rs1.getString(4));
                                    bankaccNo = (rs1.getString(5));
                                }
                                
                                
                                while (rset.next()) {
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("EMP No.".toUpperCase() , pFontHeader);
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase( rset.getObject(1).toString().toUpperCase(), pFontHeader);
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase("", pFontHeader);
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase("Name".toUpperCase(), pFontHeader);
                                    table.addCell(phrase);

                                    phrase = new Phrase(rset.getObject(2).toString().toUpperCase(), pFontHeader);
                                    table.addCell(phrase);

                                    phrase = new Phrase("", pFontHeader);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                    //   phrase = new Phrase("Department       : " +dbObject.getDBObject(rset.getObject(3), "-"),pFontNum);
                                    phrase = new Phrase("Designation".toUpperCase(), pFontHeader);
                                    table.addCell(phrase);

                                    if(desgniation.isEmpty()){
                                        phrase = new Phrase( rset.getObject(3).toString().toUpperCase(), pFontHeader);
                                        table.addCell(phrase);
                                     }else{
                                         phrase = new Phrase( desgniation.toUpperCase(), pFontHeader);
                                        table.addCell(phrase);
                                     }

                                    phrase = new Phrase("", pFontHeader);
                                    table.addCell(phrase);

                                    phrase = new Phrase("Grade".toUpperCase(), pFontHeader);
                                    table.addCell(phrase);

                                    if(grade.isEmpty()){
                                        phrase = new Phrase(rset.getObject(10).toString().toUpperCase(), pFontHeader);
                                        table.addCell(phrase);
                                    }else{
                                        phrase = new Phrase(grade.toUpperCase(), pFontHeader);
                                        table.addCell(phrase);
                                    }

                                    phrase = new Phrase("", pFontHeader);
                                    table.addCell(phrase);

                                    phrase = new Phrase("Department".toUpperCase(), pFontHeader);
                                    table.addCell(phrase);

                                    if(department.isEmpty()){
                                        phrase = new Phrase(rset.getObject(11).toString().toUpperCase(), pFontHeader);
                                        table.addCell(phrase);
                                    }else{
                                        phrase = new Phrase(department.toUpperCase(), pFontHeader);
                                        table.addCell(phrase);
                                    } 

                                    phrase = new Phrase("", pFontHeader);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                     
                                    phrase = new Phrase("PIN No.".toUpperCase(), pFontHeader);
                                    table.addCell(phrase);

                                    phrase = new Phrase(rset.getObject(4).toString().toUpperCase(), pFontHeader);
                                    table.addCell(phrase);

                                    phrase = new Phrase("", pFontHeader);
                                    table.addCell(phrase);

                                    phrase = new Phrase("ID No.", pFontHeader);
                                    table.addCell(phrase);

                                    phrase = new Phrase(rset.getObject(5).toString(), pFontHeader);
                                    table.addCell(phrase);
                                    idNo = rset.getString(5);

                                    phrase = new Phrase("", pFontHeader);
                                    table.addCell(phrase);

                                    phrase = new Phrase("Appoint.", pFontHeader);
                                    table.addCell(phrase);

                                    phrase = new Phrase(rset.getObject(6).toString() + "(" + rset.getObject(7).toString() + " Yrs)", pFontHeader);
                                    table.addCell(phrase);

                                    phrase = new Phrase("", pFontHeader);
                                    table.addCell(phrase);

                                    phrase = new Phrase("Age.", pFontHeader);
                                    table.addCell(phrase);

                                    phrase = new Phrase(rset.getObject(8).toString() + "(" + rset.getObject(9).toString() + " Yrs)", pFontHeader);
                                    table.addCell(phrase);

                                    phrase = new Phrase("", pFontHeader);
                                    table.addCell(phrase);
                                }
                                table.getDefaultCell().setColspan(1);

                                phrase = new Phrase("Description", pFontHeader);

                                table.addCell(phrase);

                                // phrase = new Phrase("Activity", pFontHeader);
                                // table.addCell(phrase);
                                // phrase = new Phrase("Reference", pFontHeader);
                                // table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                phrase = new Phrase("Amount KShs.", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase("Days/Hrs", pFontHeader);
                                phrase = new Phrase("", pFontHeader);

                                table.addCell(phrase);

                                java.sql.ResultSet rset11F = st13.executeQuery("select initcap(description),sum(amount) from non_taxed_earnings WHERE staff_no = '" + listofStaffNos[j] + "' AND date BETWEEN '" + beginDate + "' AND '" + endDate + "' GROUP BY staff_no,description");

                                java.sql.ResultSet rset11 = st31.executeQuery("select initcap(description),sum(amount) from posting where allowance_deduction = 'Less Relief' and staff_no = '" + listofStaffNos[j] + "' AND date BETWEEN '" + beginDate + "' AND '" + endDate + "' group by staff_no,description");
                                java.sql.ResultSet rset12 = st12.executeQuery("select sum(amount) from posting where allowance_deduction = 'Less Relief' and staff_no = '" + listofStaffNos[j] + "' AND date BETWEEN '" + beginDate + "' AND '" + endDate + "'");
                                java.sql.ResultSet rset14 = st14.executeQuery("select sum(tx.amount) from posting tx where tx.staff_no = '" + listofStaffNos[j] + "' AND tx.date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND (tx.description ILIKE 'PAYE%' OR tx.description ILIKE 'Monthly Personal Relief%' OR tx.description ILIKE 'P.A.Y.E%')");
                                java.sql.ResultSet rset15s = st15s.executeQuery("select amount from posting where allowance_deduction = 'Insurance Relief' and staff_no = '" + listofStaffNos[j] + "' AND date BETWEEN '" + beginDate + "' AND '" + endDate + "'");

                                java.sql.ResultSet rset15 = st15.executeQuery("select amount from posting where description = 'Monthly Personal Relief' and staff_no = '" + listofStaffNos[j] + "' AND date BETWEEN '" + beginDate + "' AND '" + endDate + "'");
                                //  java.sql.ResultSet rset16 = st16.executeQuery("select tx.paye_tax from tax_card tx where tx.staff_no = '"+listofStaffNos[j]+"' AND tx.date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                                java.sql.ResultSet rset16 = st16.executeQuery("select tx.amount from posting tx where tx.staff_no = '" + listofStaffNos[j] + "' AND tx.date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND (tx.description ILIKE 'PAYE%' OR tx.description ILIKE 'P.A.Y.E%')");

                                java.sql.ResultSet rset18 = st18.executeQuery("select sum(dv.amount) from deduction_summary dv where dv.staff_no = '" + listofStaffNos[j] + "' AND dv.date BETWEEN '" + beginDate + "' AND '" + endDate + "'");
                                java.sql.ResultSet rset19 = st19.executeQuery("select sum(amount) from net_pay_view dv where dv.staff_no = '" + listofStaffNos[j] + "' AND dv.date BETWEEN '" + beginDate + "' AND '" + endDate + "'");
                                rsetTotals1o = st4o.executeQuery("SELECT sum(pt.amount) from amount_taxed pt WHERE pt.staff_no = '" + listofStaffNos[j] + "' AND pt.date BETWEEN '" + beginDate + "' AND '" + endDate + "'");

                                //   rsetTotals1 = st4.executeQuery("SELECT sum(pt.amount) from posting pt WHERE pt.staff_no = '"+listofStaffNos[j]+"' AND pt.date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND pt.allowance_deduction ILIKE 'Earning%' group by pt.allowance_deduction");
                                java.sql.ResultSet rsetTotals1A = st4A.executeQuery("SELECT sum(pt.amount) from posting pt WHERE pt.staff_no = '" + listofStaffNos[j] + "' AND pt.date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pt.allowance_deduction ILIKE 'NON CASH%' group by pt.allowance_deduction");
                                // java.sql.ResultSet rset1 = st3.executeQuery("select initcap(description),sum(amount),hoursdays from posting where staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND allowance_deduction ILIKE 'Earning%' group by allowance_deduction,description,hoursdays ORDER BY description");
                                java.sql.ResultSet rset1 = st3.executeQuery("select initcap(pt.description),sum(pt.amount),pt.hoursdays from posting pt, deductions_allowances da WHERE pt.staff_no = '" + listofStaffNos[j] + "' AND pt.date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pt.allowance_deduction ILIKE 'Earning%' AND pt.description::text ~~* da.description::text AND da.taxable = true AND da.benefit = true GROUP BY pt.allowance_deduction,pt.description,pt.hoursdays ORDER BY pt.description");

                                java.sql.ResultSet rset1A = st3A.executeQuery("select initcap(description),sum(amount) from posting where date BETWEEN '" + beginDate + "' AND '" + endDate + "' and staff_no = '" + listofStaffNos[j] + "' AND allowance_deduction ILIKE 'NON CASH%' group by allowance_deduction,description");
                                System.out.println("Select A done ...");

                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);

                                while (rsetTotals1o.next()) {
                                    totalTaxed = rsetTotals1o.getDouble(1);
                                }
                                //  while (rsetTotals1.next()) {

                                table.getDefaultCell().setColspan(3);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Taxable Earnings", pFontHeader);

                                table.addCell(phrase);

                                while (rset1.next()) {

                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset1.getObject(1).toString(), pFontNum);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(rset1.getString(2), "0.00")), pFontNum);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(rset1.getString(3), "0.00")), pFontNum);
                                    phrase = new Phrase("", pFontHeader);

                                    table.addCell(phrase);
                                    earnings = earnings + rset1.getDouble(2);

                                }

                                table.getDefaultCell().setColspan(1);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Total Taxable Earnings", pFontHeader);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(earnings)), pFontHeader);

                                table.addCell(phrase);

                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.addCell(" ");

                                table.getDefaultCell().setColspan(3);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Non Taxable Earnings", pFontHeader);

                                table.addCell(phrase);

                                while (rset11F.next()) {
                                    table.getDefaultCell().setColspan(1);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset11F.getObject(1).toString(), pFontNum);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(rset11F.getString(2), "0.00")), pFontNum);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase("", pFontNum);

                                    table.addCell(phrase);
                                    nonTaxed = nonTaxed + rset11F.getDouble(2);

                                }

                                table.getDefaultCell().setColspan(1);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Total Non Taxable Earnings", pFontHeader);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(nonTaxed)), pFontHeader);

                                table.addCell(phrase);

                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.addCell(" ");

                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Non Cash Benefits", pFontHeader);

                                table.addCell(phrase);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                while (rset1A.next()) {

                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset1A.getObject(1).toString(), pFontNum);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(rset1A.getObject(2).toString(), "-")), pFontNum);
                                    table.addCell(phrase);

                                    table.addCell(" ");
                                }

                                while (rsetTotals1A.next()) {

                                    table.getDefaultCell().setColspan(1);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Non Cash Benefits", pFontHeader);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(rsetTotals1A.getObject(1).toString(), "-")), pFontHeader);

                                    table.addCell(phrase);//phrase);
                                    table.addCell("");

                                    noncash = noncash + rsetTotals1A.getDouble(1);

                                }

                                table.getDefaultCell().setColspan(1);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Gross Earnings".toUpperCase(), pFontHeader);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(earnings + nonTaxed + noncash)), pFontHeader);

                                table.addCell(phrase);

                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.addCell(" ");

                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.addCell(" ");

                                table.getDefaultCell().setColspan(1);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Total Taxable Amount", pFontHeader);

                                table.addCell(phrase);

                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);

                                table.getDefaultCell().setColspan(1);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(earnings + noncash)), pFontHeader);

                                table.addCell(phrase);
                                earnings = earnings + noncash;
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.addCell("");

                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setColspan(3);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Less Relief", pFontHeader);

                                table.addCell(phrase);

                                while (rset11.next()) {

                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset11.getObject(1).toString(), pFontNum);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(rset11.getString(2), "-")), pFontNum);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    //   phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset11.getString(3)),pFontNum);
                                    table.addCell("  ");
                                }

                                while (rset12.next()) {

                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Total Relief", pFontHeader);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(rset12.getString(1), "0")), pFontHeader);
                                    table.addCell(phrase);
                                    table.addCell("");

                                    relief = rset12.getDouble(1);

                                }

                                // while (rset13.next()) {
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Net Taxable amount", pFontHeader);

                                table.addCell(phrase);

                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                //chargeable = earnings - relief;
                                chargeable = totalTaxed;

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(chargeable)), pFontHeader);

                                //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset13.getString(1)),pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.addCell("");

                                while (rset14.next()) {
                                    table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Tax Charged", pFontNum);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset14.getString(1)), pFontNum);
                                    table.addCell(phrase);
                                    table.addCell("");

                                }

                                while (rset15s.next()) {

                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Insurance Relief", pFontNum);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset15s.getString(1)), pFontNum);
                                    table.addCell(phrase);
                                    table.addCell("");
                                }

                                while (rset15.next()) {

                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Monthly Personal Relief", pFontNum);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset15.getString(1)), pFontNum);
                                    table.addCell(phrase);
                                    table.addCell("");
                                }

                                while (rset16.next()) {

                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Paye", pFontNum);

                                    table.addCell(phrase);

                                    //               table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //               table.addCell(rset.getObject(4).toString());
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset16.getString(1)), pFontNum);
                                    table.addCell(phrase);
                                    table.addCell("");
                                }
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setColspan(2);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("DEDUCTIONS", pFontHeader);

                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("YTD Total", pFontHeader);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                int Nob = 0;

                                boolean bal = false;
                                double balance = 0.00;
                                double deduction = 0.00;
                                double total = 0.00;
                                for (int k = 0; k < listofAct.length; k++) {

                                    // java.sql.ResultSet rset17 = st17.executeQuery("select av.description,sum(av.amount),(SELECT SUM(ac.acc_amount) from accubal_merge_view ac where ac.date <= '"+endDate+"' and ac.staff_no = '"+listofStaffNos[j]+"' and ac.description = '"+listofAct[k]+"') from accubal_merge_view av where av.staff_no = '"+listofStaffNos[j]+"' AND av.date BETWEEN '"+beginDate+"' AND '"+endDate+"' and av.description = '"+listofAct[k]+"' group by av.description UNION ALL select av.description,sum(av.amount),(SELECT SUM(ac.acc_amount) from nonaccmerge_view ac where ac.date BETWEEN '"+dateFirst+"' AND '"+endDate+"' and ac.staff_no = '"+listofStaffNos[j]+"' and ac.description = '"+listofAct[k]+"') from nonaccmerge_view av where av.staff_no = '"+listofStaffNos[j]+"' AND av.date BETWEEN '"+beginDate+"' AND '"+endDate+"' and av.description = '"+listofAct[k]+"' group by av.description UNION ALL select av.description,sum(av.amount),(SELECT SUM(ac.acc_amount) from paye_merge_view ac where ac.date BETWEEN '"+dateFirst+"' AND '"+endDate+"' and ac.staff_no = '"+listofStaffNos[j]+"' and ac.description = '"+listofAct[k]+"') from paye_merge_view av where av.staff_no = '"+listofStaffNos[j]+"' AND av.date BETWEEN '"+beginDate+"' AND '"+endDate+"' and av.description = '"+listofAct[k]+"' group by av.description");
                                    //                            //     java.sql.ResultSet rset17 = st17.executeQuery("select initcap(av.description),sum(av.amount),(SELECT SUM(ac.acc_amount) from accubal_merge_view ac where ac.date <= '"+endDate+"' and ac.staff_no = '"+listofStaffNos[j]+"' and ac.description = '"+listofAct[k]+"') from accubal_merge_view av where av.staff_no = '"+listofStaffNos[j]+"' AND av.date BETWEEN '"+beginDate+"' AND '"+endDate+"' and av.description = '"+listofAct[k]+"' group by av.description UNION ALL select initcap(av.description),sum(av.amount),(SELECT SUM(ac.acc_amount) from nonaccmerge_view ac where ac.date BETWEEN ('1-1-'|| EXTRACT(YEAR from date('"+endDate+"'))):: date AND '"+endDate+"' and ac.staff_no = '"+listofStaffNos[j]+"' and ac.description = '"+listofAct[k]+"') from nonaccmerge_view av where av.staff_no = '"+listofStaffNos[j]+"' AND av.date BETWEEN '"+beginDate+"' AND '"+endDate+"' and av.description = '"+listofAct[k]+"' group by av.description");
                                    java.sql.Statement st171 = connectDB.createStatement();
                                    java.sql.Statement st1711 = connectDB.createStatement();
                                    java.sql.Statement st3111 = connectDB.createStatement();
                                    java.sql.Statement sts = connectDB.createStatement();

                                    java.sql.ResultSet rset17 = st17.executeQuery("select initcap(av.description),sum(av.amount) as amount from deduction_summary av where av.staff_no = '" + listofStaffNos[j] + "' AND av.date BETWEEN '" + beginDate + "' AND '" + endDate + "' and av.description = '" + listofAct[k] + "' group by av.description");
                                    java.sql.ResultSet rset171 = st171.executeQuery("select av.balance as amount,av.acc_bal from sacco_deductions av where av.staff_no = '" + listofStaffNos[j] + "' and av.sacco_name ilike '" + listofAct[k] + "'");
                                    java.sql.ResultSet rset1711 = st1711.executeQuery("select count(staff_no) from sacco_deductions av where av.staff_no = '" + listofStaffNos[j] + "' and av.sacco_name ilike '" + listofAct[k] + "'");
                                    java.sql.ResultSet rset11w = st3111.executeQuery("select sum(np.amount) from posting np where np.description ilike '" + listofAct[k] + "' and np.staff_no ilike '" + listofStaffNos[j] + "' AND np.date > '" + endDate + "' and np.processed = true group by np.staff_no order by np.staff_no");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                                    java.sql.ResultSet rsets = sts.executeQuery("select count(staff_no) from posting np where np.description = '" + listofAct[k] + "' and np.staff_no ilike '" + listofStaffNos[j] + "' AND np.date > '" + endDate + "'  and np.processed = true");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");

                                    while (rset17.next()) {

                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        
                                        String descc = rset17.getObject(1).toString();
                                        String org = "";
                                        
                                        java.sql.Statement stmtTable1 = connectDB.createStatement();
                                        java.sql.ResultSet rsetTable1 = null ;
                    
                                        rsetTable1 = stmtTable1.executeQuery("SELECT description FROM sacco_welfare_members where employee_no ILIKE '"+listofStaffNos[j]+"' AND plan ilike '"+descc+"' AND (hire_purchase = TRUE OR insurance = TRUE)");
                   
                                        while (rsetTable1.next()) {
                                            org = rsetTable1.getString(1);
                                        }
                                        
                                        if (!org.isEmpty()) descc = descc +" ("+ org + ")";
                                        phrase = new Phrase(descc, pFontNum);
                                        
                                        

                                        table.addCell(phrase);
                                        while (rset11w.next()) {
                                            deduction = rset11w.getDouble(1);
                                        }
                                        if (rset17.getFloat(2) > 0) {
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset17.getString(2)), pFontNum);
                                            table.addCell(phrase);
                                            //  deduction = rset17.getDouble(2);
                                        } else {
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase("0.00", pFontNum);
                                            table.addCell(phrase);
                                            //  deduction = 0.00;
                                        }

                                        while (rset1711.next()) {
                                            Nob = rset1711.getInt(1);
                                        }

                                        if (Nob > 0) {

                                            while (rset171.next()) {
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                                bal = rset171.getBoolean(2);
                                                balance = rset171.getDouble(1);
                                                System.out.println("This is The balance " + balance);
                                            }
                                            if (bal == false) {
                                                System.out.println("This is The balance1 " + balance);
                                                System.out.println("This is The deduction " + deduction);
                                                total = balance + deduction;
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(total)), pFontNum);
                                                table.addCell("");
                                            } else {
                                                System.out.println("This is The balance2 " + balance);
                                                System.out.println("This is The deduction2 " + deduction);
                                                total = balance - deduction;
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(total)), pFontNum);
                                                table.addCell("");
                                            }
                                            //}

                                        } else {

                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase("0.00", pFontNum);
                                            phrase = new Phrase("", pFontNum);//added
                                            table.addCell(phrase);
                                            balance = 0.00;
                                        }

                                    }
                                }

                                while (rset18.next()) {

                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("TOTAL DEDUCTIONS", pFontHeader);

                                    table.addCell(phrase);

                                    //               table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //               table.addCell(rset.getObject(4).toString());
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset18.getString(1)), pFontHeader);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table.addCell(" ");
                                }

                                while (rset19.next()) {
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("NET PAY   :", pFontHeader);
                                    System.out.println("Net Pay done");
                                    table.addCell(phrase);

                                    //               table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //               table.addCell(rset.getObject(4).toString());
                                    table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                                    table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset19.getString(1)), pFontHeader);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table.addCell(" ");
                                }

                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Information ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);

                                java.sql.ResultSet rset1As = st3A.executeQuery("select sum(amount)*2 from posting where date BETWEEN '" + beginDate + "' AND '" + endDate + "' and staff_no = '" + listofStaffNos[j] + "' AND description ILIKE 'Pension Contr. self' ");

                                while (rset1As.next()) {
                                    if (rset1As.getDouble(1) > 0) {
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Pension(Company)    :  ", pFontNum);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase(rset1As.getString(1), pFontNum);
                                        table.addCell(phrase);

                                        phrase = new Phrase("", pFontNum);
                                        table.addCell(phrase);
                                    }
                                }

                                rset1As = st3A.executeQuery("select sum(amount) from posting where date BETWEEN '" + beginDate + "' AND '" + endDate + "' and staff_no = '" + listofStaffNos[j] + "' AND description ILIKE 'N.S.S.F' ");

                                while (rset1As.next()) {
                                    if (rset1As.getDouble(1) > 0) {
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("NSSF(Company)    :  ", pFontNum);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase(rset1As.getString(1), pFontNum);
                                        table.addCell(phrase);

                                        phrase = new Phrase("", pFontNum);
                                        table.addCell(phrase);
                                    }
                                }
                                
                                
                                //rset1As = st3A.executeQuery("select sacco_name, sum(amount) from payroll_balances where  staff_no = '"+listofStaffNos[j]+"' and sacco_name not ilike 'Staff Welfare' GROUP BY 1 ");
                         /*       rset1As = st3A.executeQuery("SELECT date, staff_no, staff_name, amount, month_deduction, deducted_amount, " +
"CASE WHEN ((( SELECT deductions_allowances.balance_category  FROM deductions_allowances " +
" WHERE deductions_allowances.description::text = payroll_balances2.sacco_name::text))::text) = 'TOTAL'::text " +
" THEN amount+deducted_amount   ELSE amount-deducted_amount END  as   balance, sacco_name , " +
                                "CASE WHEN ((( SELECT deductions_allowances.balance_category  FROM deductions_allowances " +
                                "WHERE deductions_allowances.description::text = payroll_balances2.sacco_name::text))::text) = 'TOTAL'::text " +
                                "THEN 'added'   ELSE 'lesss' END  as   outcome FROM payroll_balances2 where staff_no='"+listofStaffNos[j]+"' AND  sacco_name not ilike 'Staff Welfare' AND  sacco_name not ilike 'UNION DUE'");
                               
                                while(rset1As.next()){
                                    
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(rset1As.getString(8),pFontNum);
                                        table.addCell(phrase);
                                        
                                        
                                        Double bal_ =0.00;
                                        Double ded_ = 0.00;
                                        
                                        bal_ = rset1As.getDouble(4);
                                        
                                        java.sql.Statement st4c = connectDB.createStatement();
                                        java.sql.ResultSet rset4 = st4c.executeQuery("SELECT sum(posting.amount) AS sum   FROM posting   "
                                                + "  WHERE posting.staff_no = '"+listofStaffNos[j]+"' AND posting.description "
                                                        + "=  '"+rset1As.getString(8)+"' AND posting.date <= '"+endDate+"' AND  posting.date>'2018-02-28' ");
                                        while(rset4.next()){
                                            ded_ = rset4.getDouble(1);
                                        }
                                        if(rset1As.getString(9).equalsIgnoreCase("lesss")){
                                            bal_ = bal_ - ded_;
                                        }else{
                                             bal_ = bal_ + ded_;
                                        }
                                                
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        //phrase = new Phrase(rset1As.getString(2),pFontNum);
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(String.valueOf(bal_)), pFontHeader);
                                        table.addCell(phrase);
                                        
                                        phrase = new Phrase("",pFontNum);
                                        table.addCell(phrase);
                                    
                                } */
                         
                         
                         rset1As = st3A.executeQuery("SELECT date, staff_no, staff_name, amount, month_deduction, deducted_amount, " +
                                "CASE WHEN ((( SELECT deductions_allowances.balance_category  FROM deductions_allowances " +
                                "WHERE deductions_allowances.description::text =  payroll_balances2.sacco_name::text))::text) = 'TOTAL'::text " +
                                "THEN amount+deducted_amount   ELSE amount-deducted_amount END  as   balance, sacco_name , " +
                                "CASE WHEN ((( SELECT deductions_allowances.balance_category  FROM deductions_allowances " +
                                "WHERE deductions_allowances.description::text = payroll_balances2.sacco_name::text))::text) = 'TOTAL'::text " +
                                "THEN 'added'   ELSE 'lesss' END  as   outcome FROM payroll_balances2 where staff_no='"+listofStaffNos[j]+"' AND "
                                + " sacco_name not ilike 'Staff Welfare' AND  sacco_name not ilike 'UNION DUE'");
                                
                                while(rset1As.next()){
                                    
                                        Double balan = 0.00;
                                        
                                        java.sql.PreparedStatement cde = connectDB.prepareStatement("select balance FROM sacco_balances where staff_no='"+listofStaffNos[j]+"' and  end_date ='" + endDate+ "' and sacco_name ilike '"+rset1As.getString(8)+"' ");

                                        java.sql.ResultSet a = cde.executeQuery();
                                        while (a.next()) {
                                            balan = a.getDouble(1);
                                        }
                                        
                                        
                                        
                                        Double bal_ =0.00;
                                        Double ded_ = 0.00;
                                        
                                        bal_ = rset1As.getDouble(4);
                                        
                                        java.sql.Statement st4c = connectDB.createStatement();
                                        java.sql.ResultSet rset4 = st4c.executeQuery("SELECT sum(posting.amount) AS sum   FROM posting   "
                                                + "  WHERE posting.staff_no = '"+listofStaffNos[j]+"' AND posting.description "
                                                        + "=  '"+rset1As.getString(8)+"' AND posting.date <= '"+endDate+"' AND posting.date >= '"+rset1As.getDate(1)+"' AND  posting.date>'2018-02-28' ");
                                        while(rset4.next()){
                                            ded_ = rset4.getDouble(1);
                                        }
                                        if(rset1As.getString(9).equalsIgnoreCase("lesss")){
                                            bal_ = bal_ - ded_;
                                        }else{
                                             bal_ = bal_ + ded_;
                                        }
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(rset1As.getString(8),pFontNum);
                                        table.addCell(phrase);
                                        
                                                
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        //phrase = new Phrase(rset1As.getString(2),pFontNum);
                                        if(balan>0){
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(String.valueOf(balan)), pFontHeader);
                                        
                                        }else{
                                           phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(String.valueOf(bal_)), pFontHeader);
                                         
                                        }
                                        table.addCell(phrase);
                                        
                                        phrase = new Phrase("",pFontNum);
                                        table.addCell(phrase);
                                    
                                }
                         
                         
                         
                         

//                                rset1As = st3A.executeQuery("select description, sum(amount) from payroll_openning_bals where  staff_no = '" + listofStaffNos[j] + "' GROUP BY 1 ");
//
//                                while (rset1As.next()) {
//                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                                    phrase = new Phrase(rset1As.getString(1), pFontNum);
//                                    table.addCell(phrase);
//
//                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1As.getString(2)), pFontHeader);
//
//                                    table.addCell(phrase);
//
//                                    phrase = new Phrase("", pFontNum);
//                                    table.addCell(phrase);
//
//                                }

                                while (rsetss.next()) {
                                    table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                    table.getDefaultCell().setColspan(3);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Payment By    :   " + dbObject.getDBObject(rsetss.getObject(3).toString(), "-"), pFontHeader);
                                    table.addCell(phrase);
                                    if(bank.isEmpty()){
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Bank Name     :   " + dbObject.getDBObject(rsetss.getObject(1).toString(), "-"), pFontHeader);
                                        table.addCell(phrase);
                                    }else{
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Bank Name     :   " + dbObject.getDBObject(bank, "-"), pFontHeader);
                                        table.addCell(phrase);
                                    }
                                    
                                    if(bankaccNo.isEmpty()){
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Account No    :  " + dbObject.getDBObject(rsetss.getObject(2).toString(), "-"), pFontHeader);
                                        table.addCell(phrase);
                                    }else{
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Account No    :  " + dbObject.getDBObject(bankaccNo, "-"), pFontHeader);
                                        table.addCell(phrase);
                                    }

 

                                }

                                docPdf.add(table);

                                System.out.println("Status of new page : " + docPdf.getPageNumber());

                                boolean boolNewPage = docPdf.newPage();
                            } catch (java.sql.SQLException SqlExec) {

                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                            }

                        }

                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }

                } catch (java.io.FileNotFoundException fnfExec) {

                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), fnfExec.getMessage());

                }
            } catch (com.lowagie.text.DocumentException lwDocexec) {

                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), lwDocexec.getMessage());

            }

            docPdf.close(); // com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);
            
            PdfReader reader = new PdfReader(tempFile.toString());
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(tempFile1));
            stamper.setEncryption(idNo.getBytes(), idNo.getBytes(),PdfWriter.ALLOW_PRINTING, true);
            stamper.close();
            
            java.util.Vector filesVector = new java.util.Vector<String>(1);
            filesVector.addElement(tempFile1);
            java.lang.String[] filesArray = new String[1];
            filesArray[0] = filesVector.get(0).toString();

            java.util.Vector emailsVector = new java.util.Vector<String>(1);
            emailsVector.addElement(com.afrisoftech.lib.StaffDetails.getStaffEmail(connectDB, StaffNo));
            java.lang.String[] emailsArray = new String[1];
            emailsArray[0] = emailsVector.get(0).toString();
            
            if( emailsVector.get(0).toString().isEmpty() ||  emailsVector.get(0).toString().equalsIgnoreCase("-") ||  !emailsVector.get(0).toString().contains("@")){
                com.afrisoftech.lib.StaffDetails.logEmailSent(connectDB,emailsVector.get(0).toString() , StaffNo , "Payslip",  "Failed");
                System.err.println("Invalid Email for ............................................."+StaffNo+" <> "+com.afrisoftech.lib.StaffDetails.getStaffName(connectDB, StaffNo));
            }else{
                System.err.println("Send Payslip via Email Now .............................................");

                biz.systempartners.claims.AdvancedSendFile.SendFile(connectDB, filesArray, emailsArray, null, null, "Payslip for : " + StaffNo + " " + com.afrisoftech.lib.StaffDetails.getStaffName(connectDB, StaffNo), "Payslip for : " + StaffNo + " " + com.afrisoftech.lib.StaffDetails.getStaffName(connectDB, StaffNo));
            // com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);
            
//            try {
//                    TimeUnit.SECONDS.sleep(1);
//                    System.err.println("Slept for 1 Seconds...");
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                }
            }
            tempFile.delete();
            tempFile1.delete();

        } catch (java.io.IOException IOexec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }

    }

    public java.lang.Object[] getListofStaffNos() {

        java.lang.Object[] listofStaffNos = null;

        java.util.Vector listStaffNoVector = new java.util.Vector(1, 1);

        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            java.sql.Statement stmt1 = connectDB.createStatement();

            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT staff_no FROM posting WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' and staff_no = '" + StaffNo + "'  order by staff_no");

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

    public java.lang.Object[] getListofActivities() {

        java.lang.Object[] listofActivities = null;

        java.util.Vector listActVector = new java.util.Vector(1, 1);

        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            java.sql.Statement stmt1 = connectDB.createStatement();

            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT description FROM deduction_summary where amount > 0 AND date   BETWEEN '" + beginDate + "' AND '" + endDate + "' and staff_no = '" + StaffNo + "' order by description");

            while (rSet1.next()) {

                listActVector.addElement(rSet1.getObject(1).toString());

            }

        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;
    }
}
