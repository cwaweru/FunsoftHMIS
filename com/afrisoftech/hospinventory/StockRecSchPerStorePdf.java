//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.hospinventory;

import com.afrisoftech.lib.CurrencyFormatter;
import com.afrisoftech.lib.PDFRenderer;
import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import org.openide.util.Exceptions;

public class StockRecSchPerStorePdf implements java.lang.Runnable {

    com.afrisoftech.lib.DBObject dbObject;

    java.util.Date beginDate = null;

    java.util.Date endDate = null;

    public static java.sql.Connection connectDB = null;

    public java.lang.String dbUserName = null;

    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;

    boolean threadCheck = true;

    java.lang.Thread threadSample;
    java.lang.String bank = null;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);

    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();

    java.lang.Process prThread;

    public void StockRecSchPerStorePdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String combox) {
        //public void IssuedItemsPdf(java.sql.Connection connDb) {
        dbObject = new com.afrisoftech.lib.DBObject();
        connectDB = connDb;
        beginDate = begindate;
        endDate = endate;

        // dbObject = new com.afrisoftech.lib.DBObject();
        bank = combox;

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

            this.generatePdf();

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

    public void generatePdf() {

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

            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();

            docPdf = new com.lowagie.text.Document(com.lowagie.text.PageSize.A3);
            try {

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));

                    String Store = null;
                    String compName = null;
                    String date = null;
                    try {

                        //   java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                        java.sql.Statement st = connectDB.createStatement();

                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name from pb_hospitalprofile");
                        java.sql.ResultSet rset = st.executeQuery("select current_date");

                        while (rset2.next()) {
                            compName = rset2.getObject(1).toString();
                        }
                        while (rset.next()) {
                            date = rset.getObject(1).toString();
                            //  Store = rset.getObject(2).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase("" + compName, pFontHeader), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));

                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        docPdf.setHeader(headerFoter);

                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Stock Reconcilliation list - Page: ", pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);

                    docPdf.open();
                    double osBalance = 0.00;

                    try {

                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(8);

                        int headerwidths[] = {7, 25, 10, 10, 10, 10, 10, 14};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        table.setHeaderRows(2);

                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        table.getDefaultCell().setColspan(8);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        Phrase phrase = new Phrase("Reconcilliation By Store", pFontHeader1);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(8);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        phrase = new Phrase("Store Name : " + bank, pFontHeader1);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(5);

                        //      table.getDefaultCell().setColspan(6);
                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);

                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                            java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());

                            System.out.println("" + endDate1);
                            //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);

                            //  table.addCell(phrase);
                            phrase = new Phrase("Period : " + dateFormat.format(endDate11) + " - " + dateFormat.format(endDate1), pFontHeader1);

                            table.addCell(phrase);
                        } catch (java.text.ParseException psExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                        }
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        table.getDefaultCell().setColspan(3);
                        phrase = new Phrase("Printed on : " + date, pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(1);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Code", pFontHeader);

                        table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Item", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Unit Price", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Salable", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Expired", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Damaged", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Total Count", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        phrase = new Phrase("Entered by", pFontHeader);
                        table.addCell(phrase);

                        java.lang.Object[] listofAct = this.getListofActivities();

                        for (int i = 0; i < listofAct.length; i++) {

                            try {

                                table.getDefaultCell().setColspan(8);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase((i + 1) + "." + listofAct[i].toString(), pFontHeader);
                                table.addCell(phrase);

                                java.sql.Statement st = connectDB.createStatement();

                                java.sql.Statement st2 = connectDB.createStatement();

                                //java.sql.ResultSet rset1 = st.executeQuery("select DISTINCT input_date::date FROM st_stock_counts WHERE input_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND department ilike '" + bank + "'");
                                // while (rset1.next()) {
                                java.sql.ResultSet rset = st.executeQuery("select distinct product_code,description,sum(qty),buying_price,sum(qty*buying_price),user_name from st_stock_counts  WHERE input_date::date ='" + listofAct[i] + "' and department ilike '" + bank + "' AND condition NOT LIKE '%Balance%' GROUP BY product_code,description,buying_price,user_name ORDER BY description");
                                //  java.sql.ResultSet rset = st.executeQuery("select distinct product_code,description,sum(qty),price,user_name from st_stock_counts  WHERE input_date::date ='" + listofAct[i] + "' and department ilike '" + bank + "' GROUP BY product_code,description ORDER BY description");

                                System.err.println("select  distinct product_code,description,sum(qty),buying_price,sum(qty*buying_price),user_name from st_stock_counts  WHERE input_date::date ='" + listofAct[i] + "' and department ilike '" + bank + "' AND condition NOT LIKE '%Balance%' GROUP BY product_code,description,buying_price,user_name ORDER BY description");
                                while (rset.next()) {

                                    //code
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(1), "-"), pFontHeader1);
                                    table.addCell(phrase);

                                    //item
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(2), "-"), pFontHeader1);
                                    table.addCell(phrase);

                                    //bp
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(CurrencyFormatter.getFormattedDouble(rset.getDouble(4)), pFontHeader1);
                                    table.addCell(phrase);

                                    //salable
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(CurrencyFormatter.getFormattedDouble(getSpecifiedQty(listofAct[i].toString(), rset.getObject(1).toString(), bank, "Salable")), pFontHeader1);
                                    table.addCell(phrase);

                                    //expired
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(CurrencyFormatter.getFormattedDouble(getSpecifiedQty(listofAct[i].toString(), rset.getObject(1).toString(), bank, "Expired")), pFontHeader1);
                                    table.addCell(phrase);

                                    //damaged
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(CurrencyFormatter.getFormattedDouble(getSpecifiedQty(listofAct[i].toString(), rset.getObject(1).toString(), bank, "Damaged")), pFontHeader1);
                                    table.addCell(phrase);

                                    //total_qty
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(CurrencyFormatter.getFormattedDouble(rset.getDouble(3)), pFontHeader1);
                                    table.addCell(phrase);

                                    //user
                                    table.getDefaultCell().setColspan(2);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset.getObject(6).toString(), pFontHeader1);
                                    table.addCell(phrase);

                                }

                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);

                            } catch (java.sql.SQLException SqlExec) {

                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                            }

                        }
                        docPdf.add(table);
                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }

                } catch (java.io.FileNotFoundException fnfExec) {

                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), fnfExec.getMessage());

                }
            } catch (com.lowagie.text.DocumentException lwDocexec) {

                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), lwDocexec.getMessage());

            }
            docPdf.close();
            PDFRenderer.renderPDF(tempFile);

        } catch (java.io.IOException IOexec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }

    }

    private Double getSpecifiedQty(String date, String code, String store, String condition) {
        double qty = 0.00;
        try {
            PreparedStatement pst = connectDB.prepareStatement("select sum(qty) from st_stock_counts WHERE input_date::date ='" + date + "' and product_code ='" + code + "' and department ilike '" + store + "' and condition ilike '" + condition + "' and condition not like '%Balance%'");
            ResultSet rset = pst.executeQuery();
            while (rset.next()) {
                qty = rset.getDouble(1);
            }

        } catch (SQLException ex) {
                        ex.printStackTrace();             //Exceptions.printStackTrace(ex);
        }

        return qty;
    }

    public java.lang.Object[] getListofActivities() {

        java.lang.Object[] listofActivities = null;

        java.util.Vector listActVector = new java.util.Vector(1, 1);

        try {

            java.sql.Statement stmt1 = connectDB.createStatement();

            java.sql.ResultSet rSet1 = stmt1.executeQuery("select DISTINCT input_date::date FROM st_stock_counts WHERE input_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND department ilike '" + bank + "' ");

            while (rSet1.next()) {

                listActVector.addElement(rSet1.getObject(1).toString());
                System.out.println(rSet1.getObject(1).toString());
                // System.out.println(OrderNo);

            }

        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofActivities = listActVector.toArray();
        System.out.println("Done list of Items in the Requisition ...");
        return listofActivities;
    }

}
