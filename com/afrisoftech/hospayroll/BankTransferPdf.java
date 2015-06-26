//Author Charles Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;
package com.afrisoftech.hospayroll;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.lowagie.text.Image;
//import //java.awt.Desktop;

public class BankTransferPdf implements java.lang.Runnable {

    ////java.awt.Desktop deskTop = Desktop.getDesktop();
    java.util.Date beginDate = null;
    java.util.Date endDate = null;
    java.lang.String bankName = null;
    String letter_det = null;
    String addresse = null;
    String contact = null;
    String mainTitle = null;
    String hrContact = null;
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

    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void BankTransferPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String bank) {
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

                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while (rset2.next()) {
                            compName = rset2.getObject(1).toString();
                        }
                        while (rset4.next()) {
                            date = rset4.getObject(1).toString();
                        }

                        java.sql.Statement stmt = connectDB.createStatement();
                        java.sql.ResultSet rset = stmt.executeQuery("SELECT ref_details, other_details, main_head FROM payroll_letter_head");
                        while (rset.next()) {
                            contacts = rset.getObject(1).toString();
                            refs = rset.getObject(2).toString();
                            mainTitle = rset.getObject(3).toString();


                        }
                        java.sql.ResultSet rset4s = st4s.executeQuery("SELECT contact_person,addresse,letter_details,hr_contact FROM banks_setup WHERE bank_name = '" + bankName + "'");
                        while (rset4s.next()) {
                            contact = rset4s.getObject(1).toString();
                            addresse = rset4s.getObject(2).toString();
                            letter_det = rset4s.getObject(3).toString();
                            hrContact = rset4s.getObject(4).toString();
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
                        table.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));

                        // table.addCell(phrase);

                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase(contacts, pFontHeader);
                        table.addCell(phrase);
                        try {

                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("", pFontHeader);
                            table.addCell(phrase);
                            //java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.LONG);


                           // java.util.Date formattedDate = dateFormat.parse(endDate.toString());

                           

                            java.text.SimpleDateFormat dateFormat1 = new java.text.SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                            com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat1.parse(endDate.toString().trim()), "MMMM");

                            java.lang.String monthString = dateFormatter.getDateString();

                            com.afrisoftech.lib.DateFormatter dateFormatters = new com.afrisoftech.lib.DateFormatter(dateFormat1.parse(endDate.toString().trim()), "yyyy");

                            java.lang.String yearString = dateFormatters.getDateString();


                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Date : "+monthString+" "+yearString, pFontHeader);

                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(7);

                            phrase = new Phrase(" " + contact + " \n " + bankName + "\n BOX " + addresse + "", pFontHeader);

                            table.addCell(phrase);


                            phrase = new Phrase("Dear Sir/Madam, \n \n             RE:  MONEY TRANSFER \n " + letter_det + " " + monthString + " " + yearString + ".", pFontHeader1);

                            table.addCell(phrase);

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
                        phrase = new Phrase("NO.", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("PAY NO.", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("NAME", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);

                        phrase = new Phrase("NET PAY", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("ACCOUNT NO.", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("BRANCH", pFontHeader);
                        table.addCell(phrase);

                        //  phrase = new Phrase("Account No",pFontHeader);
                        //  table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);



                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        //table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);


                        try {

                            java.sql.Statement st = connectDB.createStatement();

                            java.sql.Statement st2 = connectDB.createStatement();

                            // java.sql.ResultSet rsetTotals = st2.executeQuery("select sum(pt.amount) - (select sum(amount) from posting where (allowance_deduction ='Deduction' or allowance_deduction ='Less Relief') and date BETWEEN '"+beginDate+"' AND '"+endDate+"') as amount from posting pt where (pt.allowance_deduction = 'Earning' or pt.allowance_deduction = 'Non Cash Benefit') and pt.date BETWEEN '"+beginDate+"' AND '"+endDate+"'");//SELECT SUM(amount) from deductions_view1 WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                            java.sql.ResultSet rsetTotals = st2.executeQuery("select sum(pt.amount) - (select sum(amount) from posting where (allowance_deduction ='Deduction' or allowance_deduction ='Less Relief') and date BETWEEN '" + beginDate + "' AND '" + endDate + "') as amount from posting pt where (pt.allowance_deduction = 'Earning' or pt.allowance_deduction = 'Non Cash Benefit') and pt.date BETWEEN '" + beginDate + "' AND '" + endDate + "'");//SELECT SUM(amount) from deductions_view1 WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"'");


                            java.lang.Object listofStaffNos[] = this.getListofStaffNos();

                            for (int j = 0; j < listofStaffNos.length; j++) {
                                //java.sql.ResultSet rset = st.executeQuery("select pt.staff_no,initcap(pt.staff_name),mf.id_no,initcap(mf.bank),mf.bank_account_no,sum(pt.amount) - (select sum(amount) from posting where (allowance_deduction ='Deduction' or allowance_deduction ='Less Relief') and staff_no ='"+listofStaffNos[j]+"' and date BETWEEN '"+beginDate+"' AND '"+endDate+"') as amount from master_file mf, posting pt where (pt.allowance_deduction = 'Earning' or pt.allowance_deduction = 'Non Cash Benefit') and pt.staff_no = mf.employee_no and pt.staff_no = '"+listofStaffNos[j]+"' and mf.payment_mode ='Bank Transfer' and pt.date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND mf.bank = '"+bankName+"' group by pt.staff_name,pt.staff_no,mf.bank_account_no, mf.bank,mf.id_no");

                                java.sql.ResultSet rset = st.executeQuery("select ep.staff_no,initcap(mf.first_name||' '||mf.middle_name||' '||last_name) as name,sum(ep.amount),mf.bank_account_no,mf.bank_branch,initcap(mf.bank) from master_file mf, net_pay_view ep where ep.staff_no = mf.employee_no and ep.staff_no = '" + listofStaffNos[j] + "' and mf.payment_mode ='Bank Transfer' and ep.date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND mf.bank ILIKE '" + bankName + "%' group by name,ep.staff_no, mf.bank,mf.bank_branch,mf.bank_account_no");


                                while (rset.next()) {

                                    table.getDefaultCell().setColspan(1);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                                    numberSeq = numberSeq + 1;
                                    phrase = new Phrase("" + numberSeq + "              ", pFontHeader);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset.getObject(1).toString(), pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(2);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset.getObject(2).toString().toUpperCase(), pFontHeader1);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(3)), pFontHeader1);
                                    table.addCell(phrase);

                                    total = total + rset.getDouble(3);

                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset.getObject(4).toString(), pFontHeader1);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset.getObject(5).toString(), pFontHeader1);

                                    table.addCell(phrase);


                                }
                            }

                            while (rsetTotals.next()) {

                                table.getDefaultCell().setColspan(4);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("Total", pFontHeader);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(total)), pFontHeader);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);

                                phrase = new Phrase(" ", pFontHeader);

                                table.addCell(phrase);


                            }
                            table.getDefaultCell().setBorder(Rectangle.TOP);

                            table.getDefaultCell().setColspan(7);

                            phrase = new Phrase("  ", pFontHeader);

                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);


                            table.getDefaultCell().setColspan(7);

                            phrase = new Phrase("Kindly advice us as soon as the transactions have gone through", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(7);

                            phrase = new Phrase("  ", pFontHeader);

                            table.addCell(phrase);

                            phrase = new Phrase("  ", pFontHeader);

                            table.addCell(phrase);
                            // table.getDefaultCell().setColspan(3);

                            //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("Yours,", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(7);

                            phrase = new Phrase("  ", pFontHeader);

                            table.addCell(phrase);
                            phrase = new Phrase("  ", pFontHeader);

                            table.addCell(phrase);

                            phrase = new Phrase(hrContact, pFontHeader);

                            table.addCell(phrase);

                            phrase = new Phrase(compName, pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(7);

                            //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("DATE :______/______/___________", pFontHeader);

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

            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT employee_no FROM master_file where bank ILIKE '" + bankName + "%' order by employee_no");

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





