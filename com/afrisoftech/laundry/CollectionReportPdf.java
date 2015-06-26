//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.laundry;

import com.afrisoftech.hr.*;
import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Arrays;

public class CollectionReportPdf implements java.lang.Runnable {

    com.afrisoftech.lib.DBObject dbObject;

    String[] details;

    java.lang.String bank = null;

    java.util.Date beginDate = null;

    java.util.Date endDate = null;

    public static java.sql.Connection connectDB = null;

    public java.lang.String dbUserName = null;

    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;

    boolean threadCheck = true;

    java.lang.Thread threadSample;

    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.NORMAL);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeader3 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
    com.lowagie.text.Font pFontHeader4 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 13, Font.NORMAL);

    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();

    java.lang.Process prThread;

    public void StaffListPdf(java.sql.Connection connDb, String lcDetails[]) {
        //public void OutstOrdersPdf(java.sql.Connection connDb) {

        connectDB = connDb;
        details = lcDetails;

        dbObject = new com.afrisoftech.lib.DBObject();

        threadSample = new java.lang.Thread(this, "SampleThread");

        System.out.println("threadSample created");

        threadSample.start();

        System.out.println("threadSample fired");

    }

    public static void main(java.lang.String[] args) {

        //		new TransactionsListPdf().TransactionsListPdf();
    }

    public void run() {

        System.out.println("System has entered running mode");

        while (threadCheck) {

            System.out.println("O.K. see how we execute target program");

            //this.generatePdf("", "", Connect);
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

    public void generatePdf(String colID, String tarehe, java.sql.Connection connDb) {

        java.lang.Process wait_for_Pdf2Show;

        java.util.Calendar cal = java.util.Calendar.getInstance();

        java.util.Date dateStampPdf = cal.getTime();

        java.lang.String pdfDateStamp = dateStampPdf.toString();

        try {

            java.io.File tempFile = java.io.File.createTempFile("HR_REP" + this.getDateLable() + "_", ".pdf");

            tempFile.deleteOnExit();

            java.lang.Runtime rt = java.lang.Runtime.getRuntime();

            //  java.lang.String debitTotal = null;
            // java.lang.String creditTotal = null;
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A3.rotate());

            try {

                com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));

                String compName = null;
                String date = null;

                try {

                    connectDB = connDb;
                    java.sql.Statement st3 = connectDB.createStatement();
                    java.sql.Statement st4 = connectDB.createStatement();

                    java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name FROM pb_hospitalprofile");
                    java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                    while (rset2.next()) {
                        compName = rset2.getObject(1).toString();
                    }

                    while (rset4.next()) {
                        date = rset4.getObject(1).toString();
                    }

                    com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase("" + compName + " ", pFontHeader), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                    headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                    //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                    headerFoter.setRight(7);
                    docPdf.setHeader(headerFoter);

                } catch (java.sql.SQLException SqlExec) {

                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                }

                com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase(
                        "FunSoft Laundry Management Systems                          Page:             Printed On: " + date + "", pFontHeader2), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                docPdf.setFooter(footer);

                docPdf.open();

                try {
                    String[] headers = new String[0];
                    String[] linenTypeID = new String[0];
                    int counter1 = 1;
                    try {
                        String sql2 = "select upper(typename), ltid from laundrylinentype order by typename asc";
                        Statement stmt2 = connectDB.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                ResultSet.CONCUR_UPDATABLE);
                        ResultSet rs2 = stmt2.executeQuery(sql2);
                        rs2.last();
                        headers = new String[rs2.getRow() + 2];
                        linenTypeID = new String[rs2.getRow() + 1];
                        int totalNumCols = rs2.getRow() + 1;
                        headers[0] = "Ward";
                        linenTypeID[0] = "";
                        if (rs2.getRow() > 0) {
                            rs2.beforeFirst();

                            while (rs2.next()) {
                                headers[counter1] = rs2.getObject(1).toString();
                                linenTypeID[counter1] = rs2.getString("ltid");
                                counter1++;

                            }
                            headers[counter1] = "Delivered By";
                            counter1++;
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(CollectionData.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(counter1);

                    float headerwidths[] = new float[counter1];

                    headerwidths[0] = 20;

                    float restWidth = 80 / (counter1 - 1);

                    for (int headerCounter = 1; headerCounter < counter1; headerCounter++) {
                        headerwidths[headerCounter] = restWidth;

                    }

                    table.setWidths(headerwidths);

                    table.setWidthPercentage((100));

                    table.setHeaderRows(2);

                    table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                    table.getDefaultCell().setColspan(5);

                    Phrase phrase = new Phrase("");

                    //  try {
                    //       java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                    //                        java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                    //
                    //    System.out.println(""+endDate1);
                    //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);
                    //  table.addCell(phrase);
                    table.getDefaultCell().setColspan(counter1);
                    table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                    phrase = new Phrase("Collection Data Report for " + tarehe, pFontHeader3);

                    table.addCell(phrase);

                    //   table.getDefaultCell().setColspan(2);
                    //    phrase = new Phrase("Printed on : " +date, pFontHeader);
                    //   table.addCell(phrase);
                    // } catch(java.text.ParseException psExec) {
                    //        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                    //    }
                    phrase = new Phrase(" ", pFontHeader);

                    table.addCell(phrase);

                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                    table.getDefaultCell().setColspan(1);
                    table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                    table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                    phrase = new Phrase("Ward", pFontHeader1);
                    table.addCell(phrase);

                    for (int fillHeaders = 1; fillHeaders < counter1 - 1; fillHeaders++) {
                        phrase = new Phrase(headers[fillHeaders], pFontHeader1);
                        table.addCell(phrase);
                    }
                    phrase = new Phrase(headers[counter1 - 1], pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                    table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                    try {

                        java.sql.Statement st2 = connectDB.createStatement();

                        java.sql.ResultSet rset = st2.executeQuery("select ward_name, ward_code from public.hp_wards order by ward_name desc;");

                        while (rset.next()) {

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            System.out.println("one");
                            phrase = new Phrase(rset.getString("ward_name"), pFontHeader2);
                            System.out.println("two");
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            String attendant = "";
                            for (int counter1b = 1; counter1b < counter1 - 1; counter1b++) {
                                String sql3 = "select count, linenattendantid from linencollectionitem where wardid like'" + rset.getString("ward_code") + "' AND typeid like '" + linenTypeID[counter1b] + "' and lcolid like '" + colID + "'";
                                Statement stmt3 = connectDB.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE);
                                ResultSet rs3 = stmt3.executeQuery(sql3);
                                rs3.last();
                                if (rs3.getRow() > 0) {
                                    phrase = new Phrase(rs3.getString("count"), pFontHeader2);

                                    table.addCell(phrase);

                                    attendant = rs3.getString("linenattendantid");
                                } else {
                                    phrase = new Phrase("-", pFontHeader2);

                                    table.addCell(phrase);
                                }

                            }
                            if (!attendant.equals("")) {
                                String sql3 = "select CONCAT(first_name,' ',middle_name) AS \"Name\" from master_file where employee_no like '" + attendant + "'";
                                Statement stmt3 = connectDB.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE);
                                ResultSet rs3 = stmt3.executeQuery(sql3);
                                rs3.last();
                                if (rs3.getRow() > 0) {
                                    phrase = new Phrase(rs3.getString("Name"), pFontHeader2);

                                    table.addCell(phrase);

                                } else {
                                    phrase = new Phrase("-", pFontHeader2);

                                    table.addCell(phrase);
                                }
                            } else {
                                phrase = new Phrase("-", pFontHeader2);

                                table.addCell(phrase);
                            }

                        }

                        docPdf.add(table);

                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                } catch (com.lowagie.text.BadElementException BadElExec) {

                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                }
            } catch (com.lowagie.text.DocumentException lwDocexec) {

                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), lwDocexec.getMessage());

            }

            docPdf.close();
            com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);

        } catch (java.io.IOException IOexec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }

    }

    public void generatePdfDistribution(String colID, String tarehe, java.sql.Connection connDb) {

        java.lang.Process wait_for_Pdf2Show;

        java.util.Calendar cal = java.util.Calendar.getInstance();

        java.util.Date dateStampPdf = cal.getTime();

        java.lang.String pdfDateStamp = dateStampPdf.toString();

        try {

            java.io.File tempFile = java.io.File.createTempFile("HR_REP" + this.getDateLable() + "_", ".pdf");

            tempFile.deleteOnExit();

            java.lang.Runtime rt = java.lang.Runtime.getRuntime();

            //  java.lang.String debitTotal = null;
            // java.lang.String creditTotal = null;
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();

            try {

                com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));

                String compName = null;
                String date = null;

                try {

                    connectDB = connDb;
                    java.sql.Statement st3 = connectDB.createStatement();
                    java.sql.Statement st4 = connectDB.createStatement();

                    java.sql.ResultSet rset2 = st3.executeQuery("SELECT UPPER(organisation_name) from hr.hr_company_profile");
                    java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                    while (rset2.next()) {
                        compName = rset2.getObject(1).toString();
                    }

                    while (rset4.next()) {
                        date = rset4.getObject(1).toString();
                    }

                    com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase("" + compName + " ", pFontHeader), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                    headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                    //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                    headerFoter.setRight(7);
                    docPdf.setHeader(headerFoter);

                } catch (java.sql.SQLException SqlExec) {

                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                }

                com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase(
                        "FunSoft Laundry Management Systems                          Page:             Printed On: " + date + "", pFontHeader2), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                docPdf.setFooter(footer);

                docPdf.open();

                try {
                    String[] headers = new String[0];
                    String[] linenTypeID = new String[0];
                    int counter1 = 1;
                    try {
                        String sql2 = "select typename, ltid from laundrylinentype order by typename asc";
                        Statement stmt2 = connectDB.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                ResultSet.CONCUR_UPDATABLE);
                        ResultSet rs2 = stmt2.executeQuery(sql2);
                        rs2.last();
                        headers = new String[rs2.getRow() + 2];
                        linenTypeID = new String[rs2.getRow() + 1];
                        int totalNumCols = rs2.getRow() + 1;
                        headers[0] = "Ward";
                        linenTypeID[0] = "";
                        if (rs2.getRow() > 0) {
                            rs2.beforeFirst();

                            while (rs2.next()) {
                                headers[counter1] = rs2.getString("typename");
                                linenTypeID[counter1] = rs2.getString("ltid");
                                counter1++;

                            }
                            headers[counter1] = "Delivered By";
                            counter1++;
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(CollectionData.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(counter1);

                    float headerwidths[] = new float[counter1];

                    headerwidths[0] = 20;

                    float restWidth = 80 / (counter1 - 1);

                    for (int headerCounter = 1; headerCounter < counter1; headerCounter++) {
                        headerwidths[headerCounter] = restWidth;

                    }

                    table.setWidths(headerwidths);

                    table.setWidthPercentage((100));

                    table.setHeaderRows(2);

                    table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                    table.getDefaultCell().setColspan(5);

                    Phrase phrase = new Phrase("");

                    //  try {
                    //       java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                    //                        java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                    //
                    //    System.out.println(""+endDate1);
                    //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);
                    //  table.addCell(phrase);
                    table.getDefaultCell().setColspan(counter1);
                    table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                    phrase = new Phrase("Collection Data Report for " + tarehe, pFontHeader3);

                    table.addCell(phrase);

                    //   table.getDefaultCell().setColspan(2);
                    //    phrase = new Phrase("Printed on : " +date, pFontHeader);
                    //   table.addCell(phrase);
                    // } catch(java.text.ParseException psExec) {
                    //        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                    //    }
                    phrase = new Phrase(" ", pFontHeader);

                    table.addCell(phrase);

                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                    table.getDefaultCell().setColspan(1);
                    table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                    table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                    phrase = new Phrase("Ward", pFontHeader1);
                    table.addCell(phrase);

                    for (int fillHeaders = 1; fillHeaders < counter1 - 1; fillHeaders++) {
                        phrase = new Phrase(headers[fillHeaders], pFontHeader1);
                        table.addCell(phrase);
                    }
                    phrase = new Phrase(headers[counter1 - 1], pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setBorder(Rectangle.BOX);
                    table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                    table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                    try {

                        java.sql.Statement st2 = connectDB.createStatement();

                        java.sql.ResultSet rset = st2.executeQuery("select ward_name, ward_code from public.hp_wards order by ward_name desc;");

                        while (rset.next()) {

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            System.out.println("one");
                            phrase = new Phrase(rset.getString("ward_name"), pFontHeader2);
                            System.out.println("two");
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            String attendant = "";
                            for (int counter1b = 1; counter1b < counter1 - 1; counter1b++) {
                                String sql3 = "select count, linenattendantid from linencollectionitem where wardid like'" + rset.getString("ward_code") + "' AND typeid like '" + linenTypeID[counter1b] + "' and lcolid like '" + colID + "'";
                                Statement stmt3 = connectDB.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE);
                                ResultSet rs3 = stmt3.executeQuery(sql3);
                                rs3.last();
                                if (rs3.getRow() > 0) {
                                    phrase = new Phrase(rs3.getString("count"), pFontHeader2);

                                    table.addCell(phrase);

                                    attendant = rs3.getString("linenattendantid");
                                } else {
                                    phrase = new Phrase("-", pFontHeader2);

                                    table.addCell(phrase);
                                }

                            }
                            if (!attendant.equals("")) {
                                String sql3 = "select CONCAT(first_name,' ',middle_name) AS \"Name\" from master_file where employee_no like '" + attendant + "'";
                                Statement stmt3 = connectDB.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE);
                                ResultSet rs3 = stmt3.executeQuery(sql3);
                                rs3.last();
                                if (rs3.getRow() > 0) {
                                    phrase = new Phrase(rs3.getString("Name"), pFontHeader2);

                                    table.addCell(phrase);

                                } else {
                                    phrase = new Phrase("-", pFontHeader2);

                                    table.addCell(phrase);
                                }
                            } else {
                                phrase = new Phrase("-", pFontHeader2);

                                table.addCell(phrase);
                            }

                        }

                        docPdf.add(table);

                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                } catch (com.lowagie.text.BadElementException BadElExec) {

                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                }
            } catch (com.lowagie.text.DocumentException lwDocexec) {

                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), lwDocexec.getMessage());

            }

            docPdf.close();
            com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);

        } catch (java.io.IOException IOexec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }

    }

    /*  public java.lang.Object[] getListofStaffNos() {
   
     /*       java.lang.Object[] listofStaffNos = null;
   
     java.util.Vector listStaffNoVector = new java.util.Vector(1,1);
   
   
     try {
   
     //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
   
     java.sql.Statement stmt1 = connectDB.createStatement();
   
     java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT distinct staff_no from tax_card WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' order by staff_no");
   
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
     */
}
