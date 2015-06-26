//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.hospinventory;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class ReversedTransferedItemsPdf implements java.lang.Runnable {

    com.afrisoftech.lib.DBObject dbObject;
    java.util.Date beginDate = null;
    java.util.Date endDate = null;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    java.lang.String Receipt = null;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void ReversedTransferedItemsPdf(java.sql.Connection connDb, java.lang.String receipt) {
        //public void IssuedItemsPdf(java.sql.Connection connDb) {

        dbObject = new com.afrisoftech.lib.DBObject();

        connectDB = connDb;
        //  beginDate = begindate;
        //  endDate = endate;
        Receipt = receipt;

        System.out.println("Printing transaction number on report [" + Receipt + "]");

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
        double osBalance = 0.00;

        try {

            java.io.File tempFile = java.io.File.createTempFile("REP" + this.getDateLable() + "_", ".pdf");

            tempFile.deleteOnExit();

            java.lang.Runtime rt = java.lang.Runtime.getRuntime();

            java.lang.String debitTotal = null;

            java.lang.String creditTotal = null;

            //  com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();

            try {

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));

                    docPdf.open();

                    String compName = null;
                    String date = null;

                    com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(7);

                    int headerwidths1[] = {15, 15, 30, 15, 15, 15, 15};

                    table1.setWidths(headerwidths1);

                    table1.setWidthPercentage((100));


                    table1.getDefaultCell().setBorder(Rectangle.BOTTOM);

                    //    table1.getDefaultCell().setColspan(7);

                    Phrase phrase = new Phrase();
                    //    Image img = Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo());
                    //    //Image imgWaterMark = Image.getInstance(System.getProperty("company.watermark"));

                    //   com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    try {

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
                            //  table1.addCell(phrase);
                        }
                    } catch (java.sql.SQLException SqlExec) {

                        SqlExec.printStackTrace();

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    table1.addCell(phrase);
                    docPdf.add(table1);



                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Transfered Items list - Page: ", pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);


                    //              docPdf.open();


                    try {


                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(4);

                        int headerwidths[] = {50, 25, 25, 20};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        table.setHeaderRows(2);

                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        table.getDefaultCell().setColspan(4);
                        phrase = new Phrase("REVERSED STOCK ITEMS", pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setColspan(2);

//                        Phrase phrase = new Phrase("Transfered Items", pFontHeader);
                        date = com.afrisoftech.lib.ServerTime.serverDate(connectDB);
                      //  table.addCell("");

                        phrase = new Phrase("Original Transaction No. : " + Receipt, pFontHeader);

                        table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("Printed on : " + date, pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(1);

                        //    table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
String store = null;
                        try {

                              // java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement st21 = connectDB.createStatement();
                            java.sql.Statement st22 = connectDB.createStatement();

                            java.sql.Statement st = connectDB.createStatement();

                            java.sql.Statement st2 = connectDB.createStatement();

                            java.sql.ResultSet rset21 = st21.executeQuery("select distinct store_name,sub_store,issiued_to,user_name from st_sub_stores WHERE transaction_no = '" + Receipt + "' AND issuing > 0 ORDER BY 1 DESC LIMIT 1");


                            //  java.sql.ResultSet rset = st.executeQuery("select item,units,issuing from st_sub_stores WHERE transaction_no = '"+Receipt+"' ORDER BY item");
                            //  java.sql.ResultSet rsetTotals = st2.executeQuery("SELECT SUM(net_value) from orders WHERE received = false");// WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                            //java.sql.Statement stt = connectDB.createStatement();

                            //java.sql.ResultSet rsett = stt.executeQuery("select sum(total) from st_sub_stores WHERE transaction_no = '"+Receipt+"'");

                            while (rset21.next()) {
                                table.getDefaultCell().setColspan(2);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Store : " + rset21.getObject(1).toString(), pFontHeader);
                                table.addCell(phrase);
                                store = rset21.getObject(1).toString();
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Destination : " + rset21.getObject(2).toString(), pFontHeader);

                                table.addCell(phrase);
                            }

                

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Item", pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Qty Reversed", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);


                        phrase = new Phrase("Cost", pFontHeader);
                        table.addCell(phrase);

                        // phrase = new Phrase("Price",pFontHeader);
                        //table.addCell(phrase);

                        phrase = new Phrase("Amount", pFontHeader);
                        table.addCell(phrase);





                         
                            //  System.out.println("Value of Receipt ["+Receipt+"]");

                            java.sql.ResultSet rset22 = st22.executeQuery("select distinct store_name,sub_store,issiued_to,user_name from st_sub_stores WHERE transaction_no = '" + Receipt + "' AND store_name = '"+store+"' GROUP BY store_name,sub_store,issiued_to,user_name");

                            java.sql.ResultSet rset2 = st2.executeQuery("select distinct store_name,store_name,issiued_to,user_name from st_sub_stores WHERE transaction_no = '" + Receipt + "' AND store_name = '"+store+"' ");


                            java.sql.ResultSet rset = st.executeQuery("select initcap(ss.item),sum(ss.issuing),sum(ss.buying_price)::numeric(10,2),sum(ss.total) from st_sub_stores ss WHERE ss.transaction_no = '" + Receipt + "' AND ss.issuing > 0  AND store_name = '"+store+"'  group by ss.item ORDER BY ss.item");
                            //  java.sql.ResultSet rsetTotals = st2.executeQuery("SELECT SUM(net_value) from orders WHERE received = false");// WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                            java.sql.Statement stt = connectDB.createStatement();

                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                            while (rset.next()) {
                                table.getDefaultCell().setColspan(1);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset.getObject(1).toString(), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rset.getObject(2), "-"), pFontHeader1);

                                table.addCell(phrase);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(rset.getObject(3).toString(), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(4)), pFontHeader1);
                                osBalance = osBalance + rset.getDouble(4);
                                table.addCell(phrase);

                            }

                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);

                            table.getDefaultCell().setColspan(2);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                            phrase = new Phrase("Total", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)), pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(8);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("     ", pFontHeader);
                            table.addCell(phrase);

                            Object issuedTo = null;
                            Object receivedBy = null;



                            while (rset22.next()) {
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Reversed By : " + com.afrisoftech.lib.UserName.getUserName(connectDB).toUpperCase(), pFontHeader);
                                // table.addCell(phrase);

                                issuedTo = rset22.getObject(4);

                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Originally Issued To: " + dbObject.getDBObject(rset22.getObject(3), "-"), pFontHeader);
                                // table.addCell(phrase);
                                receivedBy = rset22.getObject(3);

                            }

                            phrase = new Phrase("Reversed By : " + issuedTo, pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("Received By : " + receivedBy, pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Checked By : ______________", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Signed By : ______________", pFontHeader);
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
}
