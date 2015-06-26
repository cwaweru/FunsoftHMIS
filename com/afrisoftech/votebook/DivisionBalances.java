//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.votebook;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class DivisionBalances implements java.lang.Runnable {

    public static java.sql.Connection connectDB = null;
    double assets = 0.00;
    double liabilities = 0.00;
    double equity = 0.00;
    double profitloss = 0.00;
    double totalLiabEquity = 0.00;
    double subTotal = 0.00;
    java.util.Date beginDate = null;
    java.lang.String dateLastyear = null;
    java.util.Date endDate = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
    com.lowagie.text.Font pFontHeader12 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
    String years,aies,divisions,heads;

    public void DivisionBalances(java.sql.Connection connDb,String year,String aie,String division,String head) {
        connectDB = connDb;
        years=year;
        aies=aie;
        divisions=division;
        heads=head;
        //beginDate = begindate;
        //endDate = endate;

        threadSample = new java.lang.Thread(this, "SampleThread");

        System.out.println("threadSample created");

        threadSample.start();

        System.out.println("threadSample fired");

    }

    public static void main(java.lang.String[] args) {
        //	new BalanceSheetPdf().BalanceSheetPdf();
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

        java.util.Calendar calLast = java.util.Calendar.getInstance();

        calLast.roll(java.util.Calendar.YEAR, -1);

        dateLastyear = calLast.getTime().toString();

        System.out.println(this.dateLastyear);

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




                    String compName = null;
                    String date = null;
                    try {

                        //    java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");

                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();

                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while (rset2.next()) {
                            compName = rset2.getObject(1).toString();

                            while (rset4.next()) {
                                date = rset4.getObject(1).toString();
                            }
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase("" + compName, pFontHeader), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));

                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        docPdf.setHeader(headerFoter);

                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Allocations - Page: ", pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);


                    docPdf.open();


                    try {



                        java.lang.Object[] listofAct2 = this.getListofActivities2();
                    //    for (int i = 0; i < listofAct2.length; i++) {

                            com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(3);

                            int headerwidths[] = {30, 50, 15};

                            table.setWidths(headerwidths);

                            table.setWidthPercentage((100));
                            Phrase phrase = new Phrase("");


                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                            table.getDefaultCell().setColspan(2);
                            try {
                                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);


                              //  java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("AIE : " + aies, pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase("Printed on : \n"/* + dateFormat.format(java.util.Calendar.getInstance())*/, pFontHeader1);
                                table.addCell(phrase);
                            } catch (Exception psExec) {

                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                            }

                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                            try {


                                totalLiabEquity = 0;
                                java.sql.Statement st11 = connectDB.createStatement();
                                java.sql.ResultSet rset11 = st11.executeQuery("select period, 'Budget Allocation' as From,subhead_quartely_amount as amount from ac_aie_allocation where subhead_yearly_amt!=0 and head_amount!=0 and aieno='"+aies+"' and head='"+divisions+"' and subhead='"+heads+"' and financial_year='"+years+"' union\n" +
" select period, modifiedby as From,subhead_quartely_amount as amount from ac_aie_allocation where subhead_yearly_amt=0 and head_amount=0 and aieno='"+aies+"' and head='"+divisions+"' and subhead='"+heads+"' and financial_year='"+years+"' order by 1 asc");
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                            table.getDefaultCell().setColspan(3);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                            phrase = new Phrase("Allocation ", pFontHeader12);
                                            table.addCell(phrase); 
                                          double amt=0.0;
                                while (rset11.next()) {
                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(rset11.getString(1), pFontHeader);
                                            table.addCell(phrase); 
                                            
                                            
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.LEFT);
                                            phrase = new Phrase(rset11.getString(2), pFontHeader);
                                            table.addCell(phrase); 
                                           
                                            
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(rset11.getString(3))), pFontHeader);
                                            table.addCell(phrase); 
                                            amt=amt+rset11.getDouble(3);

                                 }
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase("Total Yearly Allocation :", pFontHeader12);
                                table.addCell(phrase); 
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("", pFontHeader);
                                table.addCell(phrase); 
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(amt)), pFontHeader1);
                                table.addCell(phrase); 
                                
                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("", pFontHeader);
                                table.addCell(phrase);
                               
          //lpo commited
                                java.sql.Statement st112 = connectDB.createStatement();
                                java.sql.ResultSet rset112 = st112.executeQuery("select DISTINCT refno,supplier,sum(allocated_amount) as allocated_amount,sum(paid_amount)as paid_amount,(sum(allocated_amount)-sum(paid_amount))as balance,false as revert from ac_aie_commitment where (reftype='LPO' or reftype ilike '%LPO cancelled by%' or reftype='Stock Supplies' or reftype='LPO Credited') and head='"+divisions+"' and subhead='"+heads+"' and aie_no='"+aies+"' and financial_year='"+years+"' group by 1,2 having sum(allocated_amount)>0 and (sum(allocated_amount)-sum(paid_amount))>0");
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                    table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                            table.getDefaultCell().setColspan(3);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase("Commitments ", pFontHeader12);
                                            table.addCell(phrase); 
                                          double amts=0.0;
                                while (rset112.next()) {
                                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(rset112.getString(1), pFontHeader);
                                            table.addCell(phrase); 
                                            
                                            
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.LEFT);
                                            phrase = new Phrase(rset112.getString(2), pFontHeader);
                                            table.addCell(phrase); 
                                           
                                            
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(rset112.getString(3))), pFontHeader);
                                            table.addCell(phrase); 
                                            amts=amts+rset112.getDouble(3);

                                 }
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase("Total Commitments :", pFontHeader12);
                                table.addCell(phrase); 
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("", pFontHeader1);
                                table.addCell(phrase); 
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(amts)), pFontHeader1);
                                table.addCell(phrase); 
                                
                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("", pFontHeader);
                                table.addCell(phrase);
                                
   //payments
                                       
                                java.sql.Statement st1123 = connectDB.createStatement();
                                java.sql.ResultSet rset1123 = st1123.executeQuery("select DISTINCT voucher_no,supplier,sum(paid_amount)as paid_amount from ac_aie_commitment where head='"+divisions+"' and subhead='"+heads+"' and aie_no='"+aies+"' and financial_year='"+years+"' and allocated_amount=0 group by 1,2 having sum(paid_amount)>0 ");
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                            
                                table.getDefaultCell().setColspan(3);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                            phrase = new Phrase("Payments ", pFontHeader12);
                                            table.addCell(phrase); 
                                          double amts1=0.0;
                                while (rset1123.next()) {
                                         table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(rset1123.getString(1), pFontHeader);
                                            table.addCell(phrase); 
                                            
                                            
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.LEFT);
                                            phrase = new Phrase(rset1123.getString(2), pFontHeader);
                                            table.addCell(phrase); 
                                           
                                            
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(rset1123.getString(3))), pFontHeader);
                                            table.addCell(phrase); 
                                            amts1=amts1+rset1123.getDouble(3);

                                 }
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase("Total Payments :", pFontHeader12);
                                table.addCell(phrase); 
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("", pFontHeader1);
                                table.addCell(phrase); 
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(amts1)), pFontHeader1);
                                table.addCell(phrase); 
                                
                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("", pFontHeader1);
                                table.addCell(phrase); 
                                
                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("", pFontHeader1);
                                table.addCell(phrase); 
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase("BALANCE :", pFontHeader1);
                                table.addCell(phrase); 
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("", pFontHeader1);
                                table.addCell(phrase); 
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(amt-(amts+amts1))), pFontHeader1);
                                table.addCell(phrase); 
                                
                                
                                
//                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                                    phrase = new Phrase("VOTE : " + divisions + " - " + heads, pFontHeader);
//
//                                    table.addCell(phrase);
//
//                                    table.getDefaultCell().setColspan(1);
//
//                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
//                                    phrase = new Phrase("ACCOUNT DETAILS", pFontHeader);
//
//                                    table.addCell(phrase);
//
//                                    phrase = new Phrase("SUB ITEM DESCRIPTION", pFontHeader);
//
//                                    table.addCell(phrase);
//
//                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//
//                                    phrase = new Phrase("AMOUNT", pFontHeader);
//
//                                    table.addCell(phrase);
//
//                            
//                                table.getDefaultCell().setColspan(3);
//                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                                table.addCell("  ");
//                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
//
//
//                                //    while (rset16.next()) {
//
//                                table.getDefaultCell().setColspan(2);
//
//                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
//                                table.getDefaultCell().setBorder(Rectangle.BOTTOM);
//
//                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                                // table.getDefaultCell().
//                                phrase = new Phrase("Total For AIE NUMBER " + listofAct2[i].toString().toUpperCase(), pFontHeader);
//
//                                table.addCell(phrase);
//
//                                table.getDefaultCell().setColspan(1);
//                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                                //   totalLiabEquity = equity + liabilities;
//                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(totalLiabEquity)), pFontHeader);
//
//                                table.addCell(phrase);

                                docPdf.add(table);

                            } catch (java.sql.SQLException SqlExec) {

                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                            }
                            boolean boolNewPage = docPdf.newPage();
                      


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
docPdf.close();  com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);



        } catch (java.io.IOException IOexec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }



    }

    /* public java.lang.Object[] getListofActivities() {

    java.lang.Object[] listofActivities = null;

    java.util.Vector listActVector = new java.util.Vector(1,1);



    try {


    java.sql.Statement stmt1 = connectDB.createStatement();

    java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT main_code FROM pb_accounts_setup where class = 'ba' order by main_code");

    while (rSet1.next()) {

    listActVector.addElement(rSet1.getObject(1).toString());

    }

    }catch (java.sql.SQLException sqlExec) {

    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

    }

    listofActivities = listActVector.toArray();

    return listofActivities;
    }
    
    
    public java.lang.Object[] getListofActivities1() {

    java.lang.Object[] listofActivities1 = null;

    java.util.Vector listActVector = new java.util.Vector(1,1);



    try {


    java.sql.Statement stmt1 = connectDB.createStatement();

    java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT main_code FROM pb_accounts_setup where class = 'bl' order by main_code");

    while (rSet1.next()) {

    listActVector.addElement(rSet1.getObject(1).toString());

    }

    }catch (java.sql.SQLException sqlExec) {

    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

    }

    listofActivities1 = listActVector.toArray();

    return listofActivities1;
    }
     */
    public java.lang.Object[] getListofActivities2() {

        java.lang.Object[] listofActivities2 = null;

        java.util.Vector listActVector = new java.util.Vector(1, 1);





        try {

            //  java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/insurance","postgres","pilsiner");

            java.sql.Statement stmt1 = connectDB.createStatement();

            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT aieno FROM ac_aie_allocation ORDER BY 1");



            while (rSet1.next()) {

                listActVector.addElement(rSet1.getObject(1).toString());



            }

        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());



        }

        listofActivities2 = listActVector.toArray();

        System.out.println("Total list of activities = " + listofActivities2.length);



        return listofActivities2;

    }
}
