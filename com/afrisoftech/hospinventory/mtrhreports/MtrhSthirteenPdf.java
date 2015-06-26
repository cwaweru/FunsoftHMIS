//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.hospinventory.mtrhreports;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class MtrhSthirteenPdf implements java.lang.Runnable {

    com.afrisoftech.lib.DBObject dbObject;
    //java.lang.String beginDate = null;
    //java.lang.String endDate = null;
    java.lang.String lpo = null;
    String cert_no= null,remarkz= null,checked_by= null,time_date;
    java.lang.String GRNno = null;
    java.lang.String ministry = null;
   java.lang.String suppName = null;
    java.lang.String dNote = null,received_by=null,dates=null,posted_by=null;
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
    com.lowagie.text.Font pFontHeads = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.TIMES, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.TIMES, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeader4 = FontFactory.getFont(FontFactory.TIMES, 16, Font.BOLD);
    
    com.lowagie.text.Font pFontHeaders = FontFactory.getFont(FontFactory.TIMES,9, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
    java.lang.String grnNumber = "";

    public void MtrhSthirteenPdf(java.sql.Connection connDb,java.lang.String lpono,java.lang.String supp,java.lang.String gr) {
        //public void OrderedItemsPdf(java.sql.Connection connDb) {
        //

        dbObject = new com.afrisoftech.lib.DBObject();

        connectDB = connDb;

      //  beginDate = begindate;

     //   endDate = endate;

       suppName = supp;
       GRNno =gr;
        lpo = lpono;

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
                            

                            //headers
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setFixedHeight(25);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("GRN No.: " + GRNno, pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(5);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("GOODS RECEIPT NOTE ", pFontHeader4);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Supplier : " + suppName, pFontHeader);
                                table.addCell(phrase);
   
                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("L.P.O/L.S.O No. " +lpo, pFontHeader);
                                table.addCell(phrase);
                           
                                docPdf.add(table);
                              java.sql.Statement pstmtSuppliers = connectDB.createStatement();

                            java.sql.ResultSet rsetSuppliers = pstmtSuppliers.executeQuery("select distinct delivery_note_no,received_by,date,user_name from st_stock_cardex where order_no='"+lpo+"' and supplier='"+suppName+"' and grn_no='"+GRNno+"'");
                            while (rsetSuppliers.next()) {
                               dNote=rsetSuppliers.getString(1);
                              received_by=rsetSuppliers.getString(2);
                              dates=rsetSuppliers.getString(3);
                              posted_by=rsetSuppliers.getString(4);
                            }
  
                           com.lowagie.text.pdf.PdfPTable table3 = new com.lowagie.text.pdf.PdfPTable(6);
                 int headerwidths3[] = {50, 10, 10, 10, 15, 15};
                table3.setWidths(headerwidths3);
                table3.setWidthPercentage((100));
                              
                                table3.getDefaultCell().setColspan(4);
                                table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Delivery Note No. " + dNote +"   Received by."+received_by, pFontHeader);
                                table3.addCell(phrase);
                                
                                table3.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Date: "+dates, pFontHeader);
                                table3.addCell(phrase);
                                
                                //prepare again
                                    table3.getDefaultCell().setColspan(1);

                                    table3.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table3.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                                    phrase = new Phrase("Item Description", pFontHeaders);
                                    table3.addCell(phrase);
                                    phrase = new Phrase("Qty Ordered", pFontHeaders);
                                    table3.addCell(phrase);

                                    table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                    phrase = new Phrase("Qty Received", pFontHeaders);
                                    table3.addCell(phrase);

                                    phrase = new Phrase("Unit Cost", pFontHeaders);
                                    table3.addCell(phrase);

                                    phrase = new Phrase("Total", pFontHeaders);
                                    table3.addCell(phrase);

                                    phrase = new Phrase("Remarks", pFontHeaders);
                                    table3.addCell(phrase);

                               docPdf.add(table3);
   
    
                           
                            
                            com.lowagie.text.pdf.PdfPTable table4 = new com.lowagie.text.pdf.PdfPTable(6);

                        int headerwidths4[] = {50, 10, 10, 10, 15, 15};

                        table4.setWidths(headerwidths4);

                        table4.setWidthPercentage((100));
             java.sql.Statement pstmtSupplier = connectDB.createStatement();
             java.sql.ResultSet rsetSupplier = pstmtSupplier.executeQuery("select * from st_stock_cardex where order_no='"+lpo+"' and supplier='"+suppName+"' and grn_no='"+GRNno+"'");              
              while (rsetSupplier.next()) {
                  String quantity=null;
                            java.sql.Statement pstmtSupplierq = connectDB.createStatement();
                            java.sql.ResultSet rsetSupplierq = pstmtSupplierq.executeQuery("select * from st_orders where order_no='"+lpo+"' and supplier='"+suppName+"' and item='"+rsetSupplier.getString("item")+"'");
                                while (rsetSupplierq.next()) {
                                    quantity=rsetSupplierq.getString("quantity");
                                }
                                checked_by=rsetSupplier.getString("received_by");
                                cert_no=rsetSupplier.getString("certficate_no");
                                time_date=rsetSupplier.getString("chairperson_date");
                                remarkz=rsetSupplier.getString("item");
                        
                                            table4.getDefaultCell().setColspan(1);
                                            table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(dbObject.getDBObject(rsetSupplier.getString("item"), "-"), pFontHeader1);
                                            table4.addCell(phrase);
                                            table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(quantity), pFontHeader1);
                                            table4.addCell(phrase);

                                            table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetSupplier.getString("quantity_received")), pFontHeader1);
                                            table4.addCell(phrase);

                                            table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetSupplier.getString("price_per_item")), pFontHeader1);
                                            table4.addCell(phrase);

                                            table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetSupplier.getString("debit")), pFontHeader1);
                                            table4.addCell(phrase);
                                            amountReceived=amountReceived+Double.valueOf(rsetSupplier.getString("debit"));
                                           

                                            phrase = new Phrase("", pFontHeader1);
                                            table4.addCell(phrase);
                                            
                                       
                             
                              
                               }
                       
                                            
docPdf.add(table4);

                        } catch (java.sql.SQLException SqlExec) {

                            SqlExec.printStackTrace();
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }
//total
                        com.lowagie.text.pdf.PdfPTable table5 = new com.lowagie.text.pdf.PdfPTable(6);
                                int headerwidths5[] = {50, 10, 10, 10, 15, 15};
                                             table5.setWidths(headerwidths5);
                                             table5.setWidthPercentage((100));            
                                table5.getDefaultCell().setColspan(4);
                                table5.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Total", pFontHeader);

                                table5.addCell(phrase);
                                table5.getDefaultCell().setColspan(1);
                                table5.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                                table5.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(amountReceived)), pFontHeader);
                                table5.addCell(phrase);
                                phrase = new Phrase("", pFontHeader);
                                table5.addCell(phrase);
                                docPdf.add(table5); 
                                
                                  //space
                                com.lowagie.text.pdf.PdfPTable table4 = new com.lowagie.text.pdf.PdfPTable(6);
                                             int headerwidths4[] = {50, 10, 10, 10, 15, 15};
                                             table4.setWidths(headerwidths4);
                                             table4.setWidthPercentage((100));

                                              table4.getDefaultCell().setColspan(6);
                                                     table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table4.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("");
                                                     table4.addCell(phrase);
                                                     
                                                     table4.getDefaultCell().setColspan(6);
                                                     table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table4.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("");
                                                     table4.addCell(phrase);
                                                     
                                                     docPdf.add(table4);
                            
                        
//grand totals
                          com.lowagie.text.pdf.PdfPTable table6 = new com.lowagie.text.pdf.PdfPTable(6);
                                             int headerwidths6[] = {50, 10, 10, 10, 15, 15};
                                             table6.setWidths(headerwidths6);
                                             table6.setWidthPercentage((100));     
                            table6.getDefaultCell().setColspan(4);

                                table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Grand Total", pFontHeader);

                                table6.addCell(phrase);

                                table6.getDefaultCell().setColspan(1);

                                table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);



                                table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                                //phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(2)), pFontHeader);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(amountReceived)), pFontHeader);

                                table6.addCell(phrase);

                                phrase = new Phrase("", pFontHeader);

                                table6.addCell(phrase);
        //adding signatories
                                          table6.getDefaultCell().setColspan(6);
                            table6.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table6.getDefaultCell().setBorder(Rectangle.TOP);
                            phrase = new Phrase(" ", pFontHeader1);
                            table6.addCell(phrase);

                            table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table6.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table6.getDefaultCell().setBorderColor(java.awt.Color.WHITE);


//                            table6.getDefaultCell().setColspan(2);
//                            phrase = new Phrase("Goods Received by: " , pFontHeader1);
//                            table6.addCell(phrase);
//                            table6.getDefaultCell().setColspan(2);
//                            phrase = new Phrase("Sign.................................. ", pFontHeader1);
//                            table6.addCell(phrase);
//                            table6.getDefaultCell().setColspan(2);
//                            phrase = new Phrase("Date...........................................", pFontHeader1);
//                            table6.addCell(phrase);

//                            table6.getDefaultCell().setColspan(2);
//                            phrase = new Phrase("Goods Posted by:.............................. ", pFontHeader1);
//                            table6.addCell(phrase);
//                            table6.getDefaultCell().setColspan(2);
//                            phrase = new Phrase("Sign.................................. ", pFontHeader1);
//                            table6.addCell(phrase);
//                            table6.getDefaultCell().setColspan(2);
//                            phrase = new Phrase("Date...........................................", pFontHeader1);
//                            table6.addCell(phrase);
                            try{      
                            java.sql.Statement pstmtSuppliert = connectDB.createStatement();

                            java.sql.ResultSet rsetSuppliert = pstmtSuppliert.executeQuery("select * from st_certificate where lpo='"+lpo+"' and cert_no='"+cert_no+"'");
                                    
                            
              while (rsetSuppliert.next()) {
                  checked_by=rsetSuppliert.getString("checked_by");
                  time_date=rsetSuppliert.getString("time_date");
                  remarkz=rsetSuppliert.getString("remarks");
              }
                            }catch(Exception t){
                                t.printStackTrace();
                            }    
                            
                            table6.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader1);
                            table6.addCell(phrase);
                            table6.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Cert No:  "+cert_no, pFontHeader1);
                            table6.addCell(phrase);
                            table6.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Date."+time_date, pFontHeader1);
                            table6.addCell(phrase);
                            
                                                              //space
                                                      table6.getDefaultCell().setColspan(6);
                                                     table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table6.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("");
                                                     table6.addCell(phrase);
                                                     
                                                     table6.getDefaultCell().setColspan(6);
                                                     table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table6.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("");
                                                     table6.addCell(phrase);
                                                    
                                                     
                       //end space
                                                     table6.getDefaultCell().setColspan(6);
                                                     table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table6.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("Received by", pFontHeader);
                                                     table6.addCell(phrase);
                                                     
                                                               //space
                                                      table6.getDefaultCell().setColspan(6);
                                                     table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table6.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("");
                                                     table6.addCell(phrase);
                                                     
                                                     table6.getDefaultCell().setColspan(6);
                                                     table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table6.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("");
                                                     table6.addCell(phrase);
                                                    
                                                     
                       //end space
                                                     
                                                     table6.getDefaultCell().setColspan(6);
                                                     table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table6.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("Name :"+received_by+"         Sign :...................................         Date :..............................", pFontHeader1);
                                                     table6.addCell(phrase);
                                                               //space
                                                      table6.getDefaultCell().setColspan(6);
                                                     table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table6.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("");
                                                     table6.addCell(phrase);
                                                     
                                                     table6.getDefaultCell().setColspan(6);
                                                     table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table6.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("");
                                                     table6.addCell(phrase);
                                                    
                                                     
                       //end space
                                                     
                                                     table6.getDefaultCell().setColspan(6);
                                                     table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table6.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("Posted by", pFontHeader);
                                                     table6.addCell(phrase);
                                                               //space
                                                      table6.getDefaultCell().setColspan(6);
                                                     table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table6.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("");
                                                     table6.addCell(phrase);
                                                     
                                                     table6.getDefaultCell().setColspan(6);
                                                     table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table6.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("");
                                                     table6.addCell(phrase);
                                                    
                                                     
                       //end space
                                                     table6.getDefaultCell().setColspan(6);
                                                     table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table6.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("Name :"+posted_by+"     Sign :....................................         Date :...............................", pFontHeader1);
                                                     table6.addCell(phrase);
                                                               //space
                                                      table6.getDefaultCell().setColspan(6);
                                                     table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table6.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("");
                                                     table6.addCell(phrase);
                                                     
                                                     table6.getDefaultCell().setColspan(6);
                                                     table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table6.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("");
                                                     table6.addCell(phrase);
                                                    
                                                     
                       //end space
                                                     table6.getDefaultCell().setColspan(6);
                                                     table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table6.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("Certified for Payment by", pFontHeader);
                                                     table6.addCell(phrase);
                                                     
                                                               //space
                                                      table6.getDefaultCell().setColspan(6);
                                                     table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table6.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("");
                                                     table6.addCell(phrase);
                                                     
                                                     table6.getDefaultCell().setColspan(6);
                                                     table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table6.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("");
                                                     table6.addCell(phrase);
                                                    
                                                     
                       //end space
                                                     
                                                     table6.getDefaultCell().setColspan(6);
                                                     table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table6.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("Name : "+checked_by +"        Sign :.....................................        Date :.......................................", pFontHeader1);
                                                     table6.addCell(phrase);
                            
                            
                            
                            table6.getDefaultCell().setColspan(6);
                            table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Remarks   :", pFontHeader1);
                            table6.addCell(phrase);
                            
                            table6.getDefaultCell().setColspan(1);

                            //table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            // table.getDefaultCell().setBorder(Rectangle.TOP);
                            table6.getDefaultCell().setColspan(6);
                            table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Head of Department", pFontHeader1);
                            table6.addCell(phrase);
                            
                             table6.getDefaultCell().setColspan(6);
                                                     table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table6.getDefaultCell().setBorder(0);
                                                      phrase = new Phrase("Distribution of Copies\n 1. Accounts 2. Store 3. Department copy 4. Book Copy", pFontHeader);
                                                     table6.addCell(phrase);






                            docPdf.add(table6);
                                



                       

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
