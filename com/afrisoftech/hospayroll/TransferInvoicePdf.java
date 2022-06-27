//Author Charles Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;
package com.afrisoftech.hospayroll;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.lowagie.text.Image;
//import //java.awt.Desktop;

public class TransferInvoicePdf implements java.lang.Runnable {

    ////java.awt.Desktop deskTop = Desktop.getDesktop();
    java.util.Date beginDate = null;
    java.util.Date endDate = null;
    java.lang.String bankName = null;
    String yearS=""; 
    String monthS="";
    String letter_det = null;
    String addresse = null;
    String contact = null;
    String mainTitle = null;
    String refs = null;
    String contacts = null;
    int numberSeq = 0;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.NORMAL);
    com.lowagie.text.Font pFontHeader10 = FontFactory.getFont(FontFactory.HELVETICA, 15, Font.BOLD);
    com.lowagie.text.Font pFontHeader21 = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL);
    com.lowagie.text.Font pFontHeaderx = FontFactory.getFont(FontFactory.TIMES_ITALIC, 9, Font.ITALIC);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void TransferInvoicePdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String bank) {
        //public void OutstOrdersPdf(java.sql.Connection connDb) {

        connectDB = connDb;
        beginDate = begindate;
        endDate = endate;

        bankName = bank;



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
                    Image img = Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo());

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));



                    try {

                        java.lang.Class.forName("org.postgresql.Driver");

                    } catch (java.lang.ClassNotFoundException cnfExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), cnfExec.getMessage());

                    }



                    String compName = null;
                    String date = null;
                    //String compName = null;

                    try {

                        //   java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");

                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                        java.sql.Statement st4s = connectDB.createStatement();
                        java.sql.Statement stw = connectDB.createStatement();

                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while (rset2.next()) {
                            compName = rset2.getObject(1).toString();
                        }
                        while (rset4.next()) {
                            date = rset4.getObject(1).toString();
                        }

                        /* java.sql.ResultSet rs = stw.executeQuery("SELECT date('now') as Date");
                        while (rs.next()) {
                        compName = rs.getObject(1).toString();
                        }*/

                        java.sql.Statement stmt = connectDB.createStatement();
                        java.sql.ResultSet rset = stmt.executeQuery("SELECT ref_details, other_details, main_head FROM payroll_letter_head");
                        while (rset.next()) {
                            contacts = rset.getObject(1).toString();
                            refs = rset.getObject(2).toString();
                            mainTitle = rset.getObject(3).toString();


                        }
                        java.sql.ResultSet rset4s = st4s.executeQuery("SELECT contact_person,addresse,letter_details FROM banks_setup WHERE bank_name = '" + bankName + "'");
                        while (rset4s.next()) {
                            contact = rset4s.getObject(1).toString();
                            addresse = rset4s.getObject(2).toString();
                            letter_det = rset4s.getObject(3).toString();
                        }

                    // com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase("" + compName + "                                                        ", pFontHeader), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));

                    //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                    //headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                    // headerFoter.setRight(7);
                    //docPdf.setHeader(headerFoter);

                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    //  com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Deductions - Page: ",pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    //
                    //   docPdf.setFooter(footer);


                    docPdf.open();


                    try {
                        double total = 0.00;
                        double nhif = 0.00;
                        double nssf = 0.00;
                        double paye = 0.00;

                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(7);

                        int headerwidths[] = {7, 15, 15, 20, 15, 20, 25};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        table.setHeaderRows(2);

                        //table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        table.getDefaultCell().setColspan(7);

                        Phrase phrase;

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);


                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setColspan(7);
                        phrase = new Phrase(mainTitle.toUpperCase(), pFontHeader10);
                        table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                        table.getDefaultCell().setColspan(3);
                        phrase = new Phrase(refs, pFontHeader21);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("", pFontHeader);
                        table.getDefaultCell().setFixedHeight(50);
                        table.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));

                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase(contacts, pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setFixedHeight(16);

                        try {
                            /*

                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);

                            */
                            //java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                            //java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());

                           /* System.out.println("" + endDate1);
                            //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);

                            //  table.addCell(phrase);*/
                            //java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);


                            //java.util.Date formattedDate = dateFormat.parse(endDate.toString());//dateInstance.toLocaleString());

                            java.text.SimpleDateFormat dateFormat1 = new java.text.SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                            com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat1.parse(endDate.toString().trim()), "MMMM");

                            java.lang.String monthString = dateFormatter.getDateString();

                            com.afrisoftech.lib.DateFormatter dateFormatters = new com.afrisoftech.lib.DateFormatter(dateFormat1.parse(endDate.toString().trim()), "yyyy");

                            java.lang.String yearString = dateFormatters.getDateString();

                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("  ", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                             phrase = new Phrase("Date : "+monthString+" "+yearString, pFontHeader);
                             yearS=yearString;
                             monthS =monthString;

                            //table.addCell(phrase);
                            table.addCell(phrase);

                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setColspan(7);
                            phrase = new Phrase(" ", pFontHeader);
                            //table.addCell(phrase);

                            //table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Payroll Summary", pFontHeader10);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(7);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                            phrase = new Phrase(" Mathari National Teaching & Referral Hospital", pFontHeader);

                            table.addCell(phrase);


                        // phrase = new Phrase("Dear Sir/Madam, \n \n             RE:  MONEY TRANSFER \n " + letter_det + " " + monthString + " " + yearString + ".", pFontHeader1);

                        // table.addCell(phrase);

                        } catch (java.text.ParseException psExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                        }
                        /*   try {
                        java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.LONG);//MEDIUM);

                        java.util.Date formattedDate = dateFormat.parse(endDate);//dateInstance.toLocaleString());
                        java.util.Date formattedDate1 = dateFormat.parse(beginDate);

                        phrase = new Phrase("Bank Transfer" +dateFormat.format(formattedDate)+ " - " +dateFormat.format(formattedDate), pFontHeader);

                        table.addCell(phrase);
                        } catch(java.text.ParseException psExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                        }
                         */
                        phrase = new Phrase(" ", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                        table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP | Rectangle.LEFT | Rectangle.RIGHT);

                        //    table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        // phrase = new Phrase("", pFontHeader);
                        // table.addCell(phrase);

                        ////phrase = new Phrase("", pFontHeader);
                        //table.addCell(phrase);

                        table.getDefaultCell().setColspan(5);
                        phrase = new Phrase("DESCRIPTION", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(2);

                        //phrase = new Phrase("", pFontHeader);
                        //table.addCell(phrase);

                        phrase = new Phrase("KSHS", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("", pFontHeader);
                        //  table.addCell(phrase);

                        //  phrase = new Phrase("Account No",pFontHeader);
                        //  table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);



                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        //table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);


                        try {

                            java.sql.Statement st = connectDB.createStatement();

                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st4 = connectDB.createStatement();
                            int staffCount = 0;



                            java.lang.Object listofStaffNos[] = this.getListofStaffNos();

                            for (int j = 0; j < listofStaffNos.length; j++) {

                                java.sql.ResultSet rset = st.executeQuery("SELECT SUM(ep.amount) FROM net_pay_view ep WHERE ep.staff_no = '" + listofStaffNos[j] + "' AND ep.date BETWEEN '" + beginDate + "' AND '" + endDate + "'");
                                java.sql.ResultSet rset1 = st2.executeQuery("SELECT SUM(ep.amount) FROM posting ep WHERE ep.staff_no = '" + listofStaffNos[j] + "' AND ep.date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND description ILIKE 'N.H.I.F%' ");
                                java.sql.ResultSet rset2 = st3.executeQuery("SELECT SUM(ep.amount)*2 FROM posting ep WHERE ep.staff_no = '" + listofStaffNos[j] + "' AND ep.date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND description ILIKE 'N.S.S.F%' ");
                                java.sql.ResultSet rset3 = st4.executeQuery("SELECT SUM(ep.amount) FROM posting ep WHERE ep.staff_no = '" + listofStaffNos[j] + "' AND ep.date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND description ILIKE 'P.A.Y.E%' ");

                                java.sql.ResultSet rsetd = st.executeQuery("SELECT COUNT( DISTINCT staff_no) FROM posting  WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'");
                               while (rsetd.next()) {

                                    staffCount =  rsetd.getInt(1);

                                }

                                while (rset.next()) {

                                    total = total + rset.getDouble(1);

                                }

                                while (rset1.next()) {

                                    nhif = nhif + rset1.getDouble(1);

                                }

                                while (rset2.next()) {

                                    nssf = nssf + rset2.getDouble(1);

                                }

                                while (rset3.next()) {

                                    paye = paye + rset3.getDouble(1);

                                }


                            }

                            // while (rsetTotals.next()) {
                            table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);

                            table.getDefaultCell().setColspan(5);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Net Pay", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(total)), pFontHeader);

                            table.addCell(phrase);


                            table.getDefaultCell().setColspan(5);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("N.H.I.F.", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(nhif)), pFontHeader);

                            table.addCell(phrase);


                            table.getDefaultCell().setColspan(5);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("N.S.S.F", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(nssf)), pFontHeader);

                            table.addCell(phrase);


                            table.getDefaultCell().setColspan(5);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("P.A.Y.E.", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(paye)), pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(5);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Bank Charges", pFontHeader);

                            //table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(0.00)), pFontHeader);

                            //table.addCell(phrase);

                            table.getDefaultCell().setColspan(5);
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP | Rectangle.LEFT | Rectangle.RIGHT);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Being payroll costs for month "+monthS+" "+yearS+" ["+staffCount+" Employees]", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(total + nhif + nssf + paye)), pFontHeader);

                            table.addCell(phrase);
                            table.getDefaultCell().setBorder(Rectangle.TOP);

                            table.getDefaultCell().setColspan(7);

                            phrase = new Phrase("  ", pFontHeader);

                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                            //table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);


                            table.getDefaultCell().setColspan(7);

                            phrase = new Phrase("Make all payments from KCB A/C No: 1102198919 KICC Branch Nairobi.", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(7);

                            phrase = new Phrase("  ", pFontHeader);

                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Prepared by (HR Manager): ", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("Name..............................................................", pFontHeader);

                            table.addCell(phrase);
                            
                            
                            
                            
                            
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("Signature........................................................", pFontHeader);

                            table.addCell(phrase);
                            
                            
                            
                             
                            table.getDefaultCell().setColspan(7);
                            phrase = new Phrase(" ", pFontHeader);

                            table.addCell(phrase);
                            
                            
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("Date...............................................................", pFontHeader);

                            table.addCell(phrase);

                             table.getDefaultCell().setColspan(14);
                            phrase = new Phrase(" ", pFontHeader);

                            table.addCell(phrase);
                            
                            
                            
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Checked by (Head of Accounts): ", pFontHeader);

                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("Name..............................................................", pFontHeader);

                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(7);
                            phrase = new Phrase(" ", pFontHeader);

                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader);

                            table.addCell(phrase);
                            
                             
                            

                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("Signature........................................................", pFontHeader);

                            table.addCell(phrase);

                             
                            table.getDefaultCell().setColspan(7);
                            phrase = new Phrase(" ", pFontHeader);

                            table.addCell(phrase);
                            
                            
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("", pFontHeader);

                            table.addCell(phrase);
                            

                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("Date...............................................................", pFontHeader);

                            table.addCell(phrase);
                            
                            
                            table.getDefaultCell().setColspan(14);

                            phrase = new Phrase("  ", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Approved by (AIE Holder): ", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("Name..............................................................", pFontHeader);

                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(7);

                            phrase = new Phrase("  ", pFontHeader);

                            //table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("Signature........................................................", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(7);

                            phrase = new Phrase("  ", pFontHeader);

                            table.addCell(phrase);
                            
                            
                            
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("Date...............................................................", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(7);

                            phrase = new Phrase("  ", pFontHeader);

                            table.addCell(phrase);
                            
                            
                            
                            phrase = new Phrase("  ", pFontHeader);

                            table.addCell(phrase);

                            phrase = new Phrase("  ", pFontHeader);

                            table.addCell(phrase);
                            phrase = new Phrase("  ", pFontHeader);

                            table.addCell(phrase);


                            phrase = new Phrase("  ", pFontHeader);

                            table.addCell(phrase);
                            phrase = new Phrase("  ", pFontHeader);

                            table.addCell(phrase);

                            phrase = new Phrase("  ", pFontHeader);

                            table.addCell(phrase);
                            phrase = new Phrase("  ", pFontHeader);

                            table.addCell(phrase);

                            phrase = new Phrase("For any clarifications concerning this payroll, contact head of accounts ", pFontHeaderx);

                            table.addCell(phrase);



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

    public java.lang.Object[] getListofStaffNos() {

        java.lang.Object[] listofStaffNos = null;

        java.util.Vector listStaffNoVector = new java.util.Vector(1, 1);


        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");

            java.sql.Statement stmt1 = connectDB.createStatement();

            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT staff_no FROM posting WHERE company_name ILIKE '" + bankName + "'  AND date BETWEEN '" + beginDate + "' AND '" + endDate + "' ORDER BY 1");

            while (rSet1.next()) {

                listStaffNoVector.addElement(rSet1.getObject(1).toString());
                System.out.println(rSet1.getObject(1).toString());
            }

        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofStaffNos = listStaffNoVector.toArray();
        System.out.println("Done list of Staff Nos ...");
        System.out.println(listofStaffNos);


        return listofStaffNos;



    }
}





