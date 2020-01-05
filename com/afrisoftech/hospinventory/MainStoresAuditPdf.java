//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.hospinventory;

import com.afrisoftech.lib.SQLDateFormat;
import com.afrisoftech.lib.StockFormulae;
import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;

public class MainStoresAuditPdf implements java.lang.Runnable {

    int seq = 0;

    com.afrisoftech.lib.DBObject dbObject;

    String storeType = null;
    
    java.util.Date beginDate = null;

    java.util.Date endDate = null;

    java.lang.String StoreName = null;

    public static java.sql.Connection connectDB = null;

    public java.lang.String dbUserName = null;

    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;

    boolean threadCheck = true;

    java.lang.Thread threadSample;

    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL);

    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();

    java.lang.Process prThread;

    public void MainStoresAuditPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String store, String storeTypes) {

        connectDB = connDb;

        beginDate = begindate;

        endDate = endate;

        StoreName = store;

        dbObject = new com.afrisoftech.lib.DBObject();
        
        storeType = storeTypes;
        
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

            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A3.rotate());

            try {

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));

                    try {

                        java.lang.Class.forName("org.postgresql.Driver");

                    } catch (java.lang.ClassNotFoundException cnfExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), cnfExec.getMessage());

                    }

                    String compName = null;
                    String date = null;

                    try {

                        //   java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();

                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while (rset2.next()) {
                            compName = rset2.getObject(1).toString();
                        }
                        while (rset4.next()) {
                            date = rset4.getObject(1).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase("" + compName + "", pFontHeader), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        headerFoter.setRight(5);
                        docPdf.setHeader(headerFoter);

                    } catch (java.sql.SQLException SqlExec) {

                        SqlExec.printStackTrace();

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Stock Consumption Report - Page: ", pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);

                    docPdf.open();

                    try {

                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(14);

                        int headerwidths[] = {5, 8, 33, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,8};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        table.setHeaderRows(2);

                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        table.getDefaultCell().setColspan(14);

                        Phrase phrase = new Phrase("", pFontHeader);

//                        try {
//                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
//
//                            java.util.Date endDate1 = dateFormat.parse(endDate.toString());//dateInstance.toLocaleString());
//                            java.util.Date endDate11 = dateFormat.parse(beginDate.toString());//dateInstance.toLocaleString());
//
//                            System.out.println("" + endDate1);
                            //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);

                            //  table.addCell(phrase);
                            table.getDefaultCell().setColspan(9);

                            phrase = new Phrase("STOCK CONSUMPTION REPORT FOR : " + StoreName.toUpperCase() + " STORE     Reporting Period : From " + beginDate + " To " + endDate, pFontHeader);

                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(5);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase("Printed On  : " + date, pFontHeader);

                            table.addCell(phrase);
//                        } catch (java.text.ParseException psExec) {
//
//                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
//
//                        }
                        // Phrase phrase = new Phrase("Patients List As At:" +endDate, pFontHeader);

                        //table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                        table.getDefaultCell().setColspan(1);

                        //    table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        phrase = new Phrase("SNO", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("ITEM CODE", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("ITEM DESCRIPTION", pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                        phrase = new Phrase("UNIT BUYING PRICE", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("UNIT SELLING PRICE", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("OPENING STOCK QTY", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("OPENING STOCK VALUE", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("SUPPLIES QTY", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("SUPPLIES VALUE", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("SALES/CONSUMPTION QTY", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("SALES/CONSUMPTION VALUE", pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("PROFIT/LOSS VALUE", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("CLOSING STOCK QTY", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("CLOSING STOCK VALUE", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                        try {

                            java.lang.Object[] listofAct = this.getListofActivities();

                            //    java.sql.Connection conDb1 = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                            System.out.println(listofAct.length);

                            //  java.sql.Statement pSet1 = connectDB.createStatement();
                            java.sql.Statement st = connectDB.createStatement();
                            //    java.sql.ResultSet rsetTotals = st.executeQuery("select sum((ph.quantity * st.transfer_price)::numeric(10,2)), sum(amount)::numeric(10,2),sum(amount - ph.quantity * st.transfer_price)::numeric(10,2) from hp_pharmacy ph,st_stock_prices st WHERE st.department = 'Pharmacy' AND ph.date_prescribed BETWEEN '"+beginDate+"' AND '"+endDate+"' AND ph.description = st.product");
                            double totalOpStock = 0.00;
                            double totalSupplies = 0.00;
                            double totalSales = 0.00;
                            double totalProfitLoss = 0.00;
                            double totalClosing = 0.00;

                            for (int i = 0; i < listofAct.length; i++) {

                                double opStock = 0.00;

                                double supplies = 0.00;

                                double sales = 0.00;

                                double closing = 0.00;

                                double amount = 0.00;

                                double price = 0.00;
                                double sellingPrice = 0.00;
                                double closingAmount = 0.00;
                                double salesAmount = 0.00;
                                double profitAmount = 0.00;
                                double suppliesAmount = 0.00;
                                double opStockAmount = 0.00;

                                int n = 0;
                                int m = 0;
                                int k = 0;
                                System.out.println("item is " + listofAct[i]);
                                /*  double opStock = 0.00;
                                double supplies = 0.00;
                                double sales = 0.00;
                                double closing = 0.00;
                                 */

                                java.sql.Statement st1 = connectDB.createStatement();
                                java.sql.Statement st2 = connectDB.createStatement();
                                java.sql.Statement st3 = connectDB.createStatement();
                                java.sql.Statement st21 = connectDB.createStatement();
                                java.sql.Statement st21x = connectDB.createStatement();
                                java.sql.Statement st31 = connectDB.createStatement();
                                java.sql.Statement st41 = connectDB.createStatement();
                                java.sql.Statement st32 = connectDB.createStatement();

                                java.sql.ResultSet rset32 = st32.executeQuery("select DISTINCT initcap(description)||' '||(CASE WHEN opd = true THEN strength ELSE '' END), (buying_price/packaging)::numeric(15,5) as buying_price, (buying_price/packaging * (SELECT mark_up FROM st_stores WHERE store_name ilike '" + StoreName + "'))::numeric(15,2) as sales_price from st_stock_item where item_code = '" + listofAct[i] + "'");
                                
                                java.sql.ResultSet rset = st21.executeQuery("select sum(ss.issuing) from st_sub_stores ss WHERE ss.trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND ss.item_code = '" + listofAct[i] + "' AND ss.store_name ilike '" + StoreName + "' AND UPPER(issiued_to) != UPPER('Stock Count') AND UPPER(issiued_to) != UPPER('Stock Difference') group by  ss.item_code");

                                java.sql.ResultSet rsetx = st21x.executeQuery("select (sum(ss.issuing*price)) - (sum(ss.issuing*buying_price))  from st_sub_stores ss WHERE ss.trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND ss.item_code = '" + listofAct[i] + "' AND ss.store_name ilike '" + StoreName + "' AND UPPER(issiued_to) != UPPER('Stock Count') AND UPPER(issiued_to) != UPPER('Stock Difference') group by  ss.item_code");

                                java.sql.ResultSet rset1 = st2.executeQuery("select sum(ss.receiving) from st_sub_stores ss WHERE ss.trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND ss.item_code = '" + listofAct[i] + "' AND ss.store_name ilike '" + StoreName + "' group by ss.item_code");

                                java.sql.ResultSet rset2 = st3.executeQuery("select SUM(qty) from stock_balance_qty WHERE item_code =  '" + listofAct[i] + "' AND department ilike '" + StoreName + "' and dates <= '" + endDate + "'");

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                table.getDefaultCell().setColspan(1);

                                while (rset32.next()) {

                                    seq = seq + 1;

                                    phrase = new Phrase(" " + seq + ".", pFontHeader1);

                                    table.addCell(phrase);

//                                    price = rset32.getDouble(2);
//
//                                    sellingPrice = rset32.getDouble(3);
                                    
                                    price = rset32.getDouble(2);
                                    sellingPrice = rset32.getDouble(3);

                                    phrase = new Phrase(listofAct[i].toString(), pFontHeader1);

                                    table.addCell(phrase);

                                    phrase = new Phrase(dbObject.getDBObject(rset32.getObject(1), "-"), pFontHeader1);

                                    table.addCell(phrase);

                                    while (rset.next()) {

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                        sales = rset.getDouble(1);

                                        salesAmount = sales * sellingPrice;

                                        totalSales = totalSales + salesAmount;
                                    }
                                    
                                    while (rsetx.next()) {

                                        
                                        profitAmount = rsetx.getDouble(1);

                                        totalProfitLoss = totalProfitLoss + profitAmount;
                                    }

                                    while (rset1.next()) {

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                        supplies = rset1.getDouble(1);

                                        suppliesAmount = supplies * price;

                                        totalSupplies = totalSupplies + suppliesAmount;
                                    }

                                    while (rset2.next()) {

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                        closing = rset2.getDouble(1);

                                        closingAmount = closing * price;

                                        totalClosing = totalClosing + closingAmount;
                                    }

                                    opStock = (closing + sales) - supplies;

                                    opStockAmount = opStock * price;

                                    totalOpStock = totalOpStock + opStockAmount;

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(price)), pFontHeader1);
                                    table.addCell(phrase);

                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(sellingPrice)), pFontHeader1);
                                    table.addCell(phrase);

                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(opStock)), pFontHeader1);
                                    table.addCell(phrase);

                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(opStockAmount)), pFontHeader1);
                                    table.addCell(phrase);

                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(supplies)), pFontHeader1);
                                    table.addCell(phrase);

                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(suppliesAmount)), pFontHeader1);
                                    table.addCell(phrase);

                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(sales)), pFontHeader1);
                                    table.addCell(phrase);

                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(salesAmount)), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(profitAmount)), pFontHeader1);
                                    table.addCell(phrase);

                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(closing)), pFontHeader1);
                                    table.addCell(phrase);

                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(closingAmount)), pFontHeader1);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                                    table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                                }
                            }

                            table.getDefaultCell().setColspan(6);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Totals", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(totalOpStock)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(totalSupplies)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(totalSales)), pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(totalProfitLoss)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(totalClosing)), pFontHeader);
                            table.addCell(phrase);
                            docPdf.add(table);

                        } catch (java.sql.SQLException SqlExec) {
                            SqlExec.printStackTrace();
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

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

            docPdf.close();
            com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);

        } catch (java.io.IOException IOexec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }

    }

    public java.lang.Object[] getListofActivities() {

        java.lang.Object[] listofActivities = null;

        java.util.Vector listActVector = new java.util.Vector(1, 1);

        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            java.sql.Statement stmt1 = connectDB.createStatement();
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT item_code, initcap(description) FROM st_stock_item where department ilike'" + storeType + "' AND item_code is not null and item_code != '' and item_code != ' ' ORDER BY 2");
//            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT item_code, UPPER(item) FROM st_sub_stores where trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND store_name ilike'" + StoreName + "' and item_code is not null and item_code != '' and item_code != ' ' ORDER BY 2");

            while (rSet1.next()) {

                listActVector.addElement(rSet1.getObject(1).toString());
                System.out.println("description" + rSet1.getObject(1).toString());

            }

        } catch (java.sql.SQLException sqlExec) {

            sqlExec.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;
    }
}
