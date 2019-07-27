//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.hospinventory.mtrhreports;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class SthirteenPdf implements java.lang.Runnable {

    com.afrisoftech.lib.DBObject dbObject;
    java.lang.String beginDate = null;
    java.lang.String endDate = null;
    java.lang.String invNo = null;
    java.lang.String suppName = null;
    java.lang.String ministry = null;
    java.lang.String deliveryNo = null;
    java.lang.String lpoNo = null;
    String compName = null;
    String date = null;
    String ks;
    String store;
    double amountReceived = 0.00;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeader4 = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD);
    
    com.lowagie.text.Font pFontHeaders = FontFactory.getFont(FontFactory.HELVETICA,9, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
    //java.lang.String grnNumber = "";
    private java.lang.String transactionNo=null;

    public void SthirteenPdf(java.sql.Connection connDb, java.lang.String begindate, java.lang.String endate, java.lang.String supp, java.lang.String invno, java.lang.String transNo) {
        //public void OrderedItemsPdf(java.sql.Connection connDb) {

        dbObject = new com.afrisoftech.lib.DBObject();

        connectDB = connDb;

        beginDate = begindate;

        endDate = endate;

        suppName = supp;

        invNo = invno;
        
        transactionNo=transNo;

        threadSample = new java.lang.Thread(this, "SampleThread");

        System.out.println("threadSample created");

        threadSample.start();

        System.out.println("threadSample fired");

      //  String grnNumber = grnNo;
        
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

            try {

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));



                    try {

                        java.lang.Class.forName("org.postgresql.Driver");

                    } catch (java.lang.ClassNotFoundException cnfExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), cnfExec.getMessage());

                    }



                    /*
                     * try {
                     *
                     *
                     * //com.lowagie.text.HeaderFooter headerFoter = new
                     * com.lowagie.text.HeaderFooter(new Phrase("" + compName +
                     * " Printed On: " + date + "", pFontHeader), false);//
                     * FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA,
                     * 14, Font.BOLDITALIC,java.awt.Color.blue)));
                     *
                     * // com.lowagie.text.HeaderFooter headerFoter = new
                     * com.lowagie.text.HeaderFooter(new
                     * Phrase(""+compName+""),false);//
                     * FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA,
                     * 14, Font.BOLDITALIC,java.awt.Color.blue))); //
                     * headerFoter.setRight(5); ///
                     * docPdf.setHeader(headerFoter);
                     *
                     * } catch (java.sql.SQLException SqlExec) {
                     *
                     * javax.swing.JOptionPane.showMessageDialog(new
                     * javax.swing.JFrame(), SqlExec.getMessage());
                     *
                     * }
                     */

                    //com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Creditors List - Page: ", pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    //docPdf.setFooter(footer);


                    docPdf.open();


                    try {


                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(6);

                        int headerwidths[] = {50, 10, 10, 10, 15, 15};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

//                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(6);
//
//                        int headerwidths1[] = {50, 10, 10, 10, 15, 15};

//                        table1.setWidths(headerwidths1);
//
//                        table1.setWidthPercentage((100));

                        table.setHeaderRows(2);

                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                        Phrase phrase = new Phrase("Form S.13", pFontHeader);
                        /*
                         * table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                         * Phrase phrase = new Phrase("Form S 13", pFontHeader);
                         * table.addCell(phrase); phrase = new Phrase("",
                         * pFontHeader); table.addCell(phrase);
                         * table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                         * phrase = new Phrase("ORIGINAL", pFontHeader);
                         * table.addCell(phrase);
                         */
                        try {

                            table.getDefaultCell().setColspan(6);

                            Image img = Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo());
                            //Image imgWaterMark = Image.getInstance(System.getProperty("company.watermark"));
                            table.getDefaultCell().setColspan(10);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setFixedHeight(70);
                            table.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setFixedHeight(25);
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.ResultSet rset3 = st3.executeQuery("SELECT DISTINCT hospital_name,box_no,main_telno,main_faxno FROM pb_hospitalprofile");
                            while (rset3.next()) {

                                table.getDefaultCell().setColspan(6);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader4);
                                table.addCell(phrase);

                                phrase = new Phrase("P.O. Box " + rset3.getObject(2).toString() + " - Tel: " + rset3.getObject(3).toString() + " - Fax " + rset3.getObject(3).toString(), pFontHeader1);
                                table.addCell(phrase);

                            }
                            
                            String inv_date=null,delivery_date=null;
                            
                            java.sql.Statement st = connectDB.createStatement();

                            java.sql.ResultSet rset = st.executeQuery("select DISTINCT invoice_no,store,date,supplier,delivery_note_no,order_no,requisition_no,inv_no from st_stock_cardex "
                                                                    + "WHERE  invoice_no = '" + invNo + "' AND supplier = '" + suppName + "' "
                                                                    + "AND requisition_no='"+transactionNo+"' AND (transaction_type NOT LIKE 'Stock Returns' OR delivery_note_no NOT LIKE 'PCRT%') ORDER BY requisition_no");

                            String type = "";
                            while (rset.next()) {


                                invNo = rset.getObject(1).toString();
                                store = rset.getObject(2).toString();
                                date = rset.getObject(3).toString();
                                suppName = rset.getObject(4).toString();
                                deliveryNo = rset.getObject(5).toString();
                                lpoNo = rset.getObject(6).toString();
                                //grnNumber = rset.getObject(7).toString();
                                inv_date=rset.getObject(3).toString();
                                delivery_date=rset.getObject("date").toString();
                                type = rset.getString(8);

                            }
                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("GOODS RECEIPT NOTE ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);
                                //table.getDefaultCell().setFixedHeight(25);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("GRN No.: " + transactionNo, pFontHeader);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(3);
                                //table.getDefaultCell().setFixedHeight(18);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Supplier :" + suppName + "", pFontHeader);

                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(3);
                                //table.getDefaultCell().setFixedHeight(18);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Type :" + type + "", pFontHeader);

                                table.addCell(phrase);
                                
                                
                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Delivery Note No. " + deliveryNo, pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(3);

                                phrase = new Phrase("Date: "+ delivery_date, pFontHeader);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("L.P.O/L.S.O No. " + lpoNo, pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(3);

                                phrase = new Phrase("Store: " + store, pFontHeader);

                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(6);
                                phrase = new Phrase("");
                                table.addCell(phrase);
                                

                        } catch (java.sql.SQLException SqlExec) {

                            SqlExec.printStackTrace();
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }

                        table.getDefaultCell().setColspan(1);

                        //table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                        phrase = new Phrase("Item Description", pFontHeaders);
                        table.getDefaultCell().setColspan(1);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Qty Ordered", pFontHeaders);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Qty Received", pFontHeaders);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Unit Cost", pFontHeaders);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Total", pFontHeaders);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Remarks", pFontHeaders);
                        table.addCell(phrase);



                        try {
                            String orderno = "";
                            String officer = "";
                            java.sql.Statement st = connectDB.createStatement();

                            java.sql.Statement st11 = connectDB.createStatement();

                            java.sql.Statement st2 = connectDB.createStatement();

                            java.sql.Statement st21 = connectDB.createStatement();


                            java.sql.ResultSet rset1 = st21.executeQuery("SELECT distinct received_by,order_no from st_stock_cardex "
                                    + "WHERE supplier = '" + suppName + "' and invoice_no = '" + invNo + "' "
                                    + "AND requisition_no='"+transactionNo+"' AND (transaction_type NOT LIKE 'Stock Returns' OR delivery_note_no NOT LIKE 'PCRT%') ");


                            java.sql.ResultSet rset = st.executeQuery("SELECT item,SUM(quantity_required),SUM(quantity_received), "
                                    + "price_per_item,sum(debit),item_code FROM st_stock_cardex "
                                    + "WHERE supplier = '" + suppName + "' "
                                    + "and invoice_no = '" + invNo + "' "
                                    + " and requisition_no='"+transactionNo+"' AND (transaction_type NOT LIKE 'Stock Returns' OR delivery_note_no NOT LIKE 'PCRT%') GROUP BY "
                                    + "item,price_per_item,item_code ORDER BY item");
                            
                            java.sql.ResultSet rsetTotals = st2.executeQuery("SELECT sum(credit),sum(debit) from st_stock_cardex "
                                    + "WHERE supplier = '" + suppName + "' and invoice_no = '" + invNo + "' "
                                    + "and requisition_no='"+transactionNo+"' AND (transaction_type NOT LIKE 'Stock Returns' OR delivery_note_no NOT LIKE 'PCRT%')");
                            //  java.sql.ResultSet rset11 = st11.executeQuery("select item,(sub_store_receiving * -1),units,price_per_item,debit,(quantity_ordered * -1) from st_stock_cardex WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND supplier = '"+suppName+"' and invoice_no = '"+invNo+"' and transaction_type ilike 'Stock Returns%' ORDER BY item");

//                            while (rset.next()) {
//
//                                table1.getDefaultCell().setColspan(1);
//                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                                phrase = new Phrase(dbObject.getDBObject(rset.getObject(1), "-"), pFontHeader1);
//                                table1.addCell(phrase);
//                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
//                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(2)), pFontHeader1);
//                                table1.addCell(phrase);
//
//                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
//                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(3)), pFontHeader1);
//                                table1.addCell(phrase);
//                                
//                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
//                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(4)), pFontHeader1);
//                                table1.addCell(phrase);
//
//                                //table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                               /*
//                                 * phrase = new
//                                 * Phrase(dbObject.getDBObject(rset.getObject(4),
//                                 * "-"),pFontHeader1); table.addCell(phrase);
//                                 */
//
//
//                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
//                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(5)), pFontHeader1);
//                                table1.addCell(phrase);
//                                amountReceived = amountReceived + rset.getDouble(5);
//
//                                phrase = new Phrase("", pFontHeader1);
//                                table1.addCell(phrase);
//
//                            }
//
//                            table.getDefaultCell().setFixedHeight(380);
//                            table.getDefaultCell().setColspan(6);
//                            table.addCell(table1);
                            
                            while (rset.next()) {

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rset.getObject(1), "-"), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(2)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(3)), pFontHeader1);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(4)), pFontHeader1);
                                table.addCell(phrase);

                                //table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                               /*
                                 * phrase = new
                                 * Phrase(dbObject.getDBObject(rset.getObject(4),
                                 * "-"),pFontHeader1); table.addCell(phrase);
                                 */


                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(5)), pFontHeader1);
                                table.addCell(phrase);
                                amountReceived = amountReceived + rset.getDouble(5);

                                phrase = new Phrase("", pFontHeader1);
                                table.addCell(phrase);

                            }

//                            table.getDefaultCell().setFixedHeight(380);
//                            table.getDefaultCell().setColspan(6);
//                            table.addCell(table1);

                            //  table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                            // table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
//                            table.getDefaultCell().setFixedHeight(20);

                            while (rsetTotals.next()) {

                                table.getDefaultCell().setColspan(4);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Total", pFontHeader);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);


                                //phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(2)), pFontHeader);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                table.getDefaultCell().setBorder(Rectangle.TOP+Rectangle.BOTTOM+Rectangle.LEFT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(amountReceived)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setBorder(Rectangle.TOP+Rectangle.BOTTOM+Rectangle.RIGHT);
                                phrase = new Phrase("", pFontHeader);

                                table.addCell(phrase);
                            }




                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setBorder(Rectangle.TOP);
                            phrase = new Phrase(" ", pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                            while (rset1.next()) {
                                orderno = rset1.getString(2);
                                officer = rset1.getString(1);

                                //phrase = new Phrase(dbObject.getDBObject(rset1.getObject(1), "-").toUpperCase(), pFontHeader1);

                                //table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Goods Received by: " + officer, pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Sign.................................. ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Date...........................................", pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Goods Posted by:.............................. ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Sign.................................. ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Date...........................................", pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Certified for payment by:.............................. ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Sign.................................. ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Date...........................................", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);

                            //table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            // table.getDefaultCell().setBorder(Rectangle.TOP);
//                            table.getDefaultCell().setColspan(6);
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
//                            phrase = new Phrase("Head of Department", pFontHeader1);
//                            table.addCell(phrase);






                            docPdf.add(table);

                        } catch (java.sql.SQLException SqlExec) {

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

             
docPdf.close();  com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);



        } catch (java.io.IOException IOexec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }



    }
}
