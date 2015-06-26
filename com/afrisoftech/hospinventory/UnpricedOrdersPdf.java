//Author Charles Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;
package com.afrisoftech.hospinventory;

import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;

public class UnpricedOrdersPdf implements java.lang.Runnable {

    java.lang.String selectSupp = null;
    com.afrisoftech.lib.DBObject dbObject;
    java.lang.String beginDate = null;
    java.lang.String endDate = null;
    double Total = 0.00;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    PdfWriter pdfWriter;
    //  java.lang.String memNo2Use = null;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD);
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeader3 = FontFactory.getFont(FontFactory.HELVETICA, 13, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void UnpricedOrdersPdf(java.sql.Connection connDb, java.lang.String combox) {
        //  public void OrdersPdf() {
        selectSupp = combox;
        connectDB = connDb;
        //beginDate = begindate;
        dbObject = new com.afrisoftech.lib.DBObject();

        threadSample = new java.lang.Thread(this, "SampleThread");

        System.out.println("threadSample created");

        threadSample.start();

        System.out.println("threadSample fired");

    }

    public static void main(java.lang.String[] args) {
        // public static void main() {
        //	new OrdersPdf().OrdersPdf();
    }

    public void run() {

        System.out.println("System has entered running mode");

        while (threadCheck) {

            System.out.println("O.K. see how we execute target program");

            this.generatePdf(selectSupp);

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


        try {

            java.io.File tempFile = java.io.File.createTempFile("REP" + this.getDateLable() + "_", ".pdf");

            tempFile.deleteOnExit();

            java.lang.Runtime rt = java.lang.Runtime.getRuntime();

            java.lang.String debitTotal = null;

            java.lang.String creditTotal = null;

            //        com.lowagie.text.Document docPdf = new com.lowagie.text.Document(com.lowagie.text.PageSize.A4,40,40,40,40);

            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(com.lowagie.text.PageSize.A4);

            try {

                try {

                    pdfWriter = com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));


                    System.out.println("Current Doc size 1 " + pdfWriter.getCurrentDocumentSize());


                    String compName = null;
                    String date = null;

                    try {


                        java.sql.Statement st6 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();

                    /*    java.sql.ResultSet rset2 = st6.executeQuery("select sum(discount_value),sum(vat_amount),sum(net_value) from st_orders where supplier ='" + selectSupp + "' and ordered = false");// where member_no = '"+memNo+"'  AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");


                        while (rset2.next()) {

                            compName = rset2.getObject(1).toString();
                            String compName1 = rset2.getObject(2).toString();
                            String compName2 = rset2.getObject(3).toString();

                        }
*/

                        com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Prepared By :________________________________________________________________ \n Authorised By____________________________________________________________________:\n Purchase Order - Page:", pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                        docPdf.setFooter(footer);
                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    docPdf.open();


                    int numColumns = 9;
                    try {
                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(9);
                        table1.getDefaultCell().setPadding(3);

                        int headerwidths1[] = {24, 10, 10, 10, 6, 10, 6, 10, 14};

                        //  table1.setWidths(headerwidths1);

                        table1.setWidthPercentage((100));




                        Phrase phrase;
                        table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table1.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        try {


                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st4 = connectDB.createStatement();
                            java.sql.Statement st5 = connectDB.createStatement();
                            java.sql.Statement st6 = connectDB.createStatement();
                            java.sql.ResultSet rset3 = st3.executeQuery("select hospital_name,box_no,main_telno||' '||other_telno,initcap(street),initcap(town),main_faxno,email,initcap(building_name),room_no from pb_hospitalprofile");
                            java.sql.ResultSet rset2 = st2.executeQuery("select supplier_name,short_name,postal_address||' '||town,tel1||' '||tel2,initcap(street),initcap(avenue),fax_no,email_address,initcap(building_name) from st_suppliers WHERE supplier_name ='" + selectSupp + "'");
                            java.sql.ResultSet rset4 = st4.executeQuery("select order_no,date from st_orders where supplier = '" + selectSupp + "' and ordered = false");// where supplier_name = 'Uchumi'member_no = '"+memNo+"'  AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                            java.sql.ResultSet rset5 = st5.executeQuery("select date_due from st_orders where supplier = '" + selectSupp + "' and ordered = false");// where supplier_name = 'Uchumi'member_no = '"+memNo+"'  AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");


                            while (rset3.next()) {

                                table1.getDefaultCell().setColspan(9);

                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader1);
                                table1.addCell(phrase);

                                table1.getDefaultCell().setBorder(Rectangle.BOTTOM);

                                table1.getDefaultCell().setColspan(9);
                                table1.getDefaultCell().setBorderColor(java.awt.Color.black);

                                table1.getDefaultCell().setColspan(3);

                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Address:" + "\t" + rset3.getObject(2).toString(), pFontHeader);
                                table1.addCell(phrase);

                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Tel:" + "\t" + rset3.getObject(3).toString(), pFontHeader);

                                table1.addCell(phrase);


                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Fax:" + "\t" + rset3.getObject(5).toString(), pFontHeader);

                                table1.addCell(phrase);
                            }
                            //  table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            // phrase = new Phrase("Email:"+"\t"+ rset3.getObject(6).toString(), pFontHeader);

                            // table1.addCell(phrase);
                            table1.getDefaultCell().setBorderColor(java.awt.Color.white);



                            while (rset4.next()) {
                                table1.getDefaultCell().setColspan(2);
                                phrase = new Phrase(" ", pFontHeader);
                                table1.addCell(phrase);
                                table1.getDefaultCell().setColspan(3);
                                phrase = new Phrase("L.P.O No. " + "\t" + rset4.getObject(1).toString(), pFontHeader);
                                table1.addCell(phrase);
                                table1.getDefaultCell().setColspan(3);

                                phrase = new Phrase("L.P.O Date: " + "\t" + rset4.getObject(2).toString(), pFontHeader);
                                table1.addCell(phrase);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table1.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader);
                                table1.addCell(phrase);


                            }

                            while (rset2.next()) {
                                table1.getDefaultCell().setColspan(5);

                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("To : " + dbObject.getDBObject(rset2.getObject(1), "-"), pFontHeader3);
                                table1.addCell(phrase);
                                table1.getDefaultCell().setColspan(4);

                                phrase = new Phrase(" ", pFontHeader);
                                table1.addCell(phrase);


                                table1.getDefaultCell().setColspan(2);

                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Address: " + "\t" + dbObject.getDBObject(rset2.getObject(3), "-"), pFontHeader);
                                table1.addCell(phrase);

                                table1.getDefaultCell().setColspan(3);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("     Tel: " + "\t" + dbObject.getDBObject(rset2.getObject(4), "-"), pFontHeader);

                                table1.addCell(phrase);


                                table1.getDefaultCell().setColspan(3);


                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Fax: " + "\t" + dbObject.getDBObject(rset2.getObject(7), "-"), pFontHeader);

                                table1.addCell(phrase);

                                table1.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader);
                                table1.addCell(phrase);

                            }

                            while (rset5.next()) {
                                table1.getDefaultCell().setColspan(5);
                                phrase = new Phrase("Please Supply the following items at :  ", pFontHeader);
                                table1.addCell(phrase);

                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                table1.getDefaultCell().setColspan(3);
                                phrase = new Phrase("On/Before: " + rset5.getObject(1).toString(), pFontHeader);
                                table1.addCell(phrase);

                                table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                table1.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader);
                                table1.addCell(phrase);

                                table1.getDefaultCell().setColspan(9);
                                phrase = new Phrase("       ", pFontHeader);
                                table1.addCell(phrase);
                            }





                            docPdf.add(table1);
                            //       System.out.println("Current Doc size "+ pdfWriter.getTableBottom(table1));
                            System.out.println("Current Doc size " + pdfWriter.getCurrentDocumentSize());
                        } catch (java.sql.SQLException SqlExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }

                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }

                    try {

                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(8);
                        table.getDefaultCell().setPadding(3);

                        int headerwidths[] = {10, 25, 10, 10, 15, 10, 10, 10,};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        Phrase phrase;
                        table.getDefaultCell().setColspan(1);


                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        //  table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);

                        //  table.addCell(phrase);

                        //  table.getDefaultCell().setBorder(Rectangle.LEFT);
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                        phrase = new Phrase("Stock Code ", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);

                        phrase = new Phrase("Item (Print) ", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("Strength", pFontHeader);
                        table.addCell(phrase);
                        // table.getDefaultCell().setColspan(2);

                        phrase = new Phrase("Unit Pack ", pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(1);

                        phrase = new Phrase("Quantity", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("Unit Price", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("Net value", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("Total:", pFontHeader);

                        table.addCell(phrase);

                        phrase = new Phrase("Remarks", pFontHeader);

                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);


                        table.setHeaderRows(1);

                        System.out.println("First ");
                        System.out.println("First Bottom " + docPdf.bottom());

                        table.getDefaultCell().setColspan(1);



                        try {

                            // java.sql.Statement st6 = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();


                            java.sql.ResultSet rset1 = st1.executeQuery("select code,initcap(item),item_strength,units,quantity, unit_price,net_value from st_orders where supplier = '" + selectSupp + "'");// where supplier_name = 'Uchumi'member_no = '"+memNo+"'  AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");


                            table.getDefaultCell().setBorderColor(java.awt.Color.lightGray);


                            while (rset1.next()) {
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset1.getObject(1).toString(), pFontHeader2);

                                table.addCell(phrase);
                                //
                                // table.getDefaultCell().setBorderColor(java.awt.Color.white);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset1.getObject(2).toString(), pFontHeader2);

                                table.addCell(phrase);
                                System.out.println("Second " + docPdf.top());
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset1.getObject(3).toString(), pFontHeader2);

                                table.addCell(phrase);

                                phrase = new Phrase(rset1.getObject(4).toString(), pFontHeader2);

                                table.addCell(phrase);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(5)), pFontHeader2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(6)), pFontHeader2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(7)), pFontHeader2);





                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(8)), pFontHeader2);

                                table.addCell(phrase);
                                Total = Total + rset1.getDouble(8);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("      ", pFontHeader2);

                                table.addCell(phrase);



                            /*

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(5)),pFontHeader2);

                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(6)),pFontHeader2);

                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(7)),pFontHeader2);

                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(8)),pFontHeader2);

                            table.addCell(phrase);
                            System.out.println("Current Doc size "+ pdfWriter.getCurrentDocumentSize());

                            // table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            //  table.getDefaultCell().setBorder(Rectangle.RIGHT);


                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(9)),pFontHeader2);

                            table.addCell(phrase);
                             */
                            }

                            docPdf.add(table);

                            java.sql.PreparedStatement pstmt3 = connectDB.prepareStatement("UPDATE st_orders SET ordered ='true' WHERE supplier = '" + selectSupp + "'");
                            pstmt3.executeUpdate();

                            System.out.println("Current Doc size " + pdfWriter.getCurrentDocumentSize());

                        } catch (java.sql.SQLException SqlExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }

                    // }

                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }

                } catch (java.io.FileNotFoundException fnfExec) {

                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), fnfExec.getMessage());

                }
            } catch (com.lowagie.text.DocumentException lwDocexec) {

                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), lwDocexec.getMessage());

            }
            System.out.println("Current Doc size " + pdfWriter.getCurrentDocumentSize());
             
docPdf.close();  com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);



        } catch (java.io.IOException IOexec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }



    }
}





