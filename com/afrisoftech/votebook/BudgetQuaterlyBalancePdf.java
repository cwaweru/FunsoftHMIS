//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.votebook;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class BudgetQuaterlyBalancePdf implements java.lang.Runnable {

    public static java.sql.Connection connectDB = null;
   double cumbal_head_amount=0.0,cumexp_head_amount=0.0,cumexp_q1_amount=0.0,
           cumexp_q2_amount=0.0,cumexp_q3_amount=0.0,cumexp_q4_amount=0.0,Budget=0.0;
    double bal_head_amount=0.0;
    double exp_head_amount=0.0;
     double exp_q1_amount=0.0;
     double exp_q2_amount=0.0;
     double exp_q3_amount=0.0;
     double exp_q4_amount=0.0;
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
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
    com.lowagie.text.Font pFontHeadere = FontFactory.getFont(FontFactory.TIMES_ITALIC, 10, Font.NORMAL);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
    String year=null;
    public void BudgetQuaterlyBalancePdf(java.sql.Connection connDb,String years) {
        connectDB = connDb;
      //  beginDate = begindate;
       // endDate = endate;
        year=years;

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

           // com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());
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

                        // com.lowagie.text.HeaderFooter headerFoters = new com.lowagie.text.HeaderFooter(new Phrase("Yearly Budget",pFontHeader),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        docPdf.setHeader(headerFoter);
                       // docPdf.setHeader(headerFoters);

                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Budget - Page: ", pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);


                    docPdf.open();


                    try {



                        java.lang.Object[] listofAct2 = this.getListofActivities2();
                       

                            com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(9);

                            int headerwidths[] = {10, 35, 20, 20, 20,20,20,20,20};

                            table.setWidths(headerwidths);

                            table.setWidthPercentage((100));
                            Phrase phrase = new Phrase("");


                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                            table.getDefaultCell().setColspan(9);
//                            try {
                                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("Yearly Budget Expenditure ", pFontHeader);
                                table.addCell(phrase);



                            

                            //    table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);

                            // table.addCell("");

                            /*  table.addCell("");
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            table.addCell("Amount KShs.");
                             */
//                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
//                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
for (int i = 0; i < listofAct2.length; i++) {
    System.out.println("nimefika hapa "+i);
                            try {

                                //java.lang.Object[] listofAct = this.getListofActivities();

                                //java.lang.Object[] listofAct1 = this.getListofActivities1();

                                //    java.sql.Connection conDb1 = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");

                                // System.out.println(listofAct.length);


                                totalLiabEquity = 0;

                                java.sql.Statement st10 = connectDB.createStatement();
                                java.sql.Statement st101 = connectDB.createStatement();
                                java.sql.Statement st8 = connectDB.createStatement();

                                java.sql.Statement st11 = connectDB.createStatement();

                                //   java.sql.ResultSet rset10 = st10.executeQuery("select * from equity_view");


                                java.sql.ResultSet rset11 = st11.executeQuery("select distinct head from ac_aie_allocation where financial_year='"+year+"' and aieno='"+listofAct2[i].toString()+"' and head_amount!=0 order by head ");

                                
                                System.out.println("This stage has been passed 3");
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setColspan(9);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                //table.addCell(listofAct2[i].toString());
                                //  table.addCell(phrase);

                                while (rset11.next()) {
                                    double head_amount=0.0;
                                    java.sql.ResultSet rset18 = st8.executeQuery("select distinct head,sum(subhead_quartely_amount) from ac_aie_allocation where financial_year='"+year+"' and aieno='"+listofAct2[i].toString()+"' and head='"+rset11.getObject("head").toString()+"' GROUP BY 1 ");
                                    while (rset18.next()) {
                                      head_amount=head_amount+Double.parseDouble(rset18.getObject(2).toString());
                                    }
                                    String division=null;
                                      java.sql.Statement  st1192= connectDB.createStatement();
                                        java.sql.ResultSet ress192= st1192.executeQuery("select headname from heads where head = '"+rset11.getObject("head").toString()+"'");
                                        if(ress192.next()){

                                         division=ress192.getString(1);

                                        }
                                    
                                    
                                    table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table.getDefaultCell().setColspan(9);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                    phrase = new Phrase(rset11.getObject("head").toString().toUpperCase()+" - "+division , pFontHeader);

                                    table.addCell(phrase);
                             //headings
                                    table.getDefaultCell().setColspan(2);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    //table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                    phrase = new Phrase("" , pFontHeader);
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase("Allocation" , pFontHeadere);
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase("1st Quarter Exp" , pFontHeadere);
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase("2nd Quarter Exp" , pFontHeadere);
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase("3rd Quarter Exp" , pFontHeadere);
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase("4th Quarter Exp" , pFontHeadere);
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase("Cumm Exp" , pFontHeadere);
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase("Balance" , pFontHeadere);
                                    table.addCell(phrase);
//                                

                                    //  }
                                java.sql.Statement st15 = connectDB.createStatement();
                                java.sql.ResultSet rset15 = st15.executeQuery("select distinct subhead from ac_aie_allocation where financial_year='"+year+"' and aieno='"+listofAct2[i].toString()+"' and head_amount!=0 and head='"+rset11.getObject("head").toString()+"' order by subhead ");

                                    while (rset15.next()) {
                                         double subhead_amount=0.0;
                                    java.sql.ResultSet rset188 = st10.executeQuery("select distinct subhead,sum(subhead_quartely_amount) from ac_aie_allocation where financial_year='"+year+"' and aieno='"+listofAct2[i].toString()+"' and head='"+rset11.getObject("head").toString()+"' and subhead='"+rset15.getString("subhead")+"' GROUP BY 1");
                                    while (rset188.next()) {
                                      subhead_amount=subhead_amount+Double.parseDouble(rset188.getObject(2).toString());
                                    }
                                    
                                      double subhead_expense=0.0,subhead_balance=0.0;
                                    java.sql.ResultSet rset1889 = st101.executeQuery("select DISTINCT refno,supplier,sum(allocated_amount) as allocated_amount from ac_aie_commitment where (reftype='LPO' or reftype ilike '%LPO cancelled by%' or reftype='Stock Supplies' or reftype='LPO Credited') and head='"+rset11.getObject("head").toString()+"' and subhead='"+rset15.getString("subhead")+"' and aie_no='"+listofAct2[i].toString()+"' and financial_year='"+year+"' group by 1,2 having sum(allocated_amount)>0 and (sum(allocated_amount)-sum(paid_amount))>0 union select DISTINCT voucher_no,supplier,sum(paid_amount)as paid_amount from ac_aie_commitment where head='"+rset11.getObject("head").toString()+"' and subhead='"+rset15.getString("subhead")+"' and aie_no='"+listofAct2[i].toString()+"' and financial_year='"+year+"' and allocated_amount=0 group by 1,2 having sum(paid_amount)>0 ");
                                    while (rset1889.next()) {
                                      subhead_expense=subhead_expense+Double.parseDouble(rset1889.getObject(3).toString());
                                    }
                                  //quater 1 expense  
                                    double quarter1_expense=0.0;
                                     java.sql.Statement st151 = connectDB.createStatement();
                                    java.sql.ResultSet rset18891 = st151.executeQuery("select DISTINCT refno,supplier,sum(allocated_amount) as allocated_amount from ac_aie_commitment where (reftype='LPO' or reftype ilike '%LPO cancelled by%' or reftype='Stock Supplies' or reftype='LPO Credited') and head='"+rset11.getObject("head").toString()+"' and subhead='"+rset15.getString("subhead")+"' and aie_no='"+listofAct2[i].toString()+"' and financial_year='"+year+"' and period='1st Quarter' group by 1,2 having sum(allocated_amount)>0 and (sum(allocated_amount)-sum(paid_amount))>0 union select DISTINCT voucher_no,supplier,sum(paid_amount)as paid_amount from ac_aie_commitment where head='"+rset11.getObject("head").toString()+"' and subhead='"+rset15.getString("subhead")+"' and aie_no='"+listofAct2[i].toString()+"' and financial_year='"+year+"' and period='1st Quarter' and allocated_amount=0 group by 1,2 having sum(paid_amount)>0");
                                    while (rset18891.next()) {
                                      quarter1_expense=quarter1_expense+Double.parseDouble(rset18891.getObject(3).toString());
                                    }
                                   //quater 2 expense  
                                    double quarter2_expense=0.0;
                                     java.sql.Statement st152 = connectDB.createStatement();
                                    java.sql.ResultSet rset18892 = st152.executeQuery("select DISTINCT refno,supplier,sum(allocated_amount) as allocated_amount from ac_aie_commitment where (reftype='LPO' or reftype ilike '%LPO cancelled by%' or reftype='Stock Supplies' or reftype='LPO Credited') and head='"+rset11.getObject("head").toString()+"' and subhead='"+rset15.getString("subhead")+"' and aie_no='"+listofAct2[i].toString()+"' and financial_year='"+year+"' and period='2nd Quarter' group by 1,2 having sum(allocated_amount)>0 and (sum(allocated_amount)-sum(paid_amount))>0 union select DISTINCT voucher_no,supplier,sum(paid_amount)as paid_amount from ac_aie_commitment where head='"+rset11.getObject("head").toString()+"' and subhead='"+rset15.getString("subhead")+"' and aie_no='"+listofAct2[i].toString()+"' and financial_year='"+year+"' and period='2nd Quarter' and allocated_amount=0 group by 1,2 having sum(paid_amount)>0");
                                    while (rset18892.next()) {
                                      quarter2_expense=quarter2_expense+Double.parseDouble(rset18892.getObject(3).toString());
                                    }
                                    //quater 3 expense  
                                    double quarter3_expense=0.0;
                                     java.sql.Statement st153 = connectDB.createStatement();
                                    java.sql.ResultSet rset18893 = st153.executeQuery("select DISTINCT refno,supplier,sum(allocated_amount) as allocated_amount from ac_aie_commitment where (reftype='LPO' or reftype ilike '%LPO cancelled by%' or reftype='Stock Supplies' or reftype='LPO Credited') and head='"+rset11.getObject("head").toString()+"' and subhead='"+rset15.getString("subhead")+"' and aie_no='"+listofAct2[i].toString()+"' and financial_year='"+year+"' and period='3rd Quarter' group by 1,2 having sum(allocated_amount)>0 and (sum(allocated_amount)-sum(paid_amount))>0 union select DISTINCT voucher_no,supplier,sum(paid_amount)as paid_amount from ac_aie_commitment where head='"+rset11.getObject("head").toString()+"' and subhead='"+rset15.getString("subhead")+"' and aie_no='"+listofAct2[i].toString()+"' and financial_year='"+year+"' and period='3rd Quarter' and allocated_amount=0 group by 1,2 having sum(paid_amount)>0");
                                    while (rset18893.next()) {
                                      quarter3_expense=quarter3_expense+Double.parseDouble(rset18893.getObject(3).toString());
                                    }
                                    //quater 4 expense  
                                    double quarter4_expense=0.0;
                                     java.sql.Statement st154 = connectDB.createStatement();
                                    java.sql.ResultSet rset18894 = st154.executeQuery("select DISTINCT refno,supplier,sum(allocated_amount) as allocated_amount from ac_aie_commitment where (reftype='LPO' or reftype ilike '%LPO cancelled by%' or reftype='Stock Supplies' or reftype='LPO Credited') and head='"+rset11.getObject("head").toString()+"' and subhead='"+rset15.getString("subhead")+"' and aie_no='"+listofAct2[i].toString()+"' and financial_year='"+year+"' and period='4th Quarter' group by 1,2 having sum(allocated_amount)>0 and (sum(allocated_amount)-sum(paid_amount))>0 union select DISTINCT voucher_no,supplier,sum(paid_amount)as paid_amount from ac_aie_commitment where head='"+rset11.getObject("head").toString()+"' and subhead='"+rset15.getString("subhead")+"' and aie_no='"+listofAct2[i].toString()+"' and financial_year='"+year+"' and period='4th Quarter' and allocated_amount=0 group by 1,2 having sum(paid_amount)>0");
                                    while (rset18894.next()) {
                                      quarter4_expense=quarter4_expense+Double.parseDouble(rset18894.getObject(3).toString());
                                    } 
                                    
                                    
                                    
                                    subhead_balance=subhead_amount-subhead_expense;
                                    String votehead=null; 
                                    java.sql.Statement  st119= connectDB.createStatement();
                                        java.sql.ResultSet ress19= st119.executeQuery("select subheadname from subheads where subhead = '"+rset15.getString("subhead")+"'");
                                        if(ress19.next()){

                                         votehead=ress19.getString(1);

                                        }
                                        //start
                                        
                                        
                                        
                                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                        table.getDefaultCell().setColspan(1);
                                        //code
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                        phrase = new Phrase(rset15.getObject("subhead").toString().toUpperCase(), pFontHeader1);
                                        table.addCell(phrase);
                                        //name
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(votehead, pFontHeader1);
                                        table.addCell(phrase);
                                        //yearly allocation
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(Double.toString(subhead_amount)), pFontHeader1);
                                        table.addCell(phrase);
                                        //1st quater expense
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(Double.toString(quarter1_expense)), pFontHeader1);
                                        table.addCell(phrase);
                                        //2nd quater expense
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(Double.toString(quarter2_expense)), pFontHeader1);
                                        table.addCell(phrase);
                                        //3rd quater expense
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(Double.toString(quarter3_expense)), pFontHeader1);
                                        table.addCell(phrase);
                                        //4th quater expense
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(Double.toString(quarter4_expense)), pFontHeader1);
                                        table.addCell(phrase);
                                        //yearly expense
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(Double.toString(subhead_expense)), pFontHeader1);
                                        table.addCell(phrase);
                                        //balance
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(Double.toString(subhead_balance)), pFontHeader1);
                                        table.addCell(phrase);

                                       // totalLiabEquity = totalLiabEquity + rset15.getDouble(3);
                                        //}
                                        bal_head_amount=bal_head_amount+subhead_balance;
                                        exp_head_amount=exp_head_amount+subhead_expense;
                                        exp_q4_amount=exp_q4_amount+quarter4_expense;
                                        exp_q3_amount=exp_q3_amount+quarter3_expense;
                                        exp_q2_amount=exp_q2_amount+quarter2_expense;
                                        exp_q1_amount=exp_q1_amount+quarter1_expense;
                                    }
                                table.getDefaultCell().setColspan(2);
                                 table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase("", pFontHeader);
                                table.addCell(phrase);
                                        
                                 table.getDefaultCell().setColspan(1);
                                 table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);       
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                               phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(Double.toString(head_amount)), pFontHeader);
                                table.addCell(phrase);
                              //quaterly expense here
                             //1   
                                table.getDefaultCell().setColspan(1);
                                 table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);       
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                               phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(Double.toString(exp_q1_amount)), pFontHeader);
                                table.addCell(phrase);
                             //2   
                                table.getDefaultCell().setColspan(1);
                                 table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);       
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                               phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(Double.toString(exp_q2_amount)), pFontHeader);
                                table.addCell(phrase);
                             //3 
                                table.getDefaultCell().setColspan(1);
                                 table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);       
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                               phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(Double.toString(exp_q3_amount)), pFontHeader);
                                table.addCell(phrase);
                            //4    
                                table.getDefaultCell().setColspan(1);
                                 table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);       
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                               phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(Double.toString(exp_q4_amount)), pFontHeader);
                                table.addCell(phrase);
                        //end of quatery expense
                                table.getDefaultCell().setColspan(1);
                                 table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);       
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                               phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(Double.toString(exp_head_amount)), pFontHeader);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);       
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(Double.toString(bal_head_amount)), pFontHeader);
                                table.addCell(phrase);
                                Budget=Budget+head_amount;
                                cumbal_head_amount=cumbal_head_amount+bal_head_amount;
                                cumexp_head_amount=cumexp_head_amount+exp_head_amount;
                                cumexp_q1_amount=cumexp_q1_amount+exp_q1_amount;
                                cumexp_q2_amount=cumexp_q2_amount+exp_q2_amount;
                                cumexp_q3_amount=cumexp_q3_amount+exp_q3_amount;
                                cumexp_q4_amount=cumexp_q3_amount+exp_q4_amount;
                                
                                bal_head_amount=0.0;
                                exp_head_amount=0.0;
                                exp_q1_amount=0.0;
                                exp_q2_amount=0.0;
                                exp_q3_amount=0.0;
                                exp_q4_amount=0.0;
                                }
                                
                                
                                
                                
                                
                                
//                                table.getDefaultCell().setColspan(3);
//                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                                table.addCell("  ");
//                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);


                                //    while (rset16.next()) {
//
//                                table.getDefaultCell().setColspan(3);
//
//                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
//                                table.getDefaultCell().setBorder(Rectangle.BOTTOM);

//                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                                // table.getDefaultCell().
//                            //    phrase = new Phrase("Total For AIE NUMBER " + listofAct2[i].toString().toUpperCase(), pFontHeader);
//
//                                table.addCell(phrase);
//
//                                table.getDefaultCell().setColspan(1);
//                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                                //   totalLiabEquity = equity + liabilities;
//                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(totalLiabEquity)), pFontHeader);
//
//                                table.addCell(phrase);

                                

                            } catch (java.sql.SQLException SqlExec) {

                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                            }
                       }
                       table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM );       
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase("", pFontHeader);
                                table.addCell(phrase);
                                
                                //budget
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);       
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(Double.toString(Budget)), pFontHeader);
                                table.addCell(phrase);
                                //1st q total
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);       
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(Double.toString(cumexp_q1_amount)), pFontHeader);
                                table.addCell(phrase);
                                
                                 //2nd q total
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);       
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(Double.toString(cumexp_q2_amount)), pFontHeader);
                                table.addCell(phrase);
                                
                                 //3rd q total
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);       
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(Double.toString(cumexp_q3_amount)), pFontHeader);
                                table.addCell(phrase);
                                
                                 //4th q total
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);       
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(Double.toString(cumexp_q4_amount)), pFontHeader);
                                table.addCell(phrase);
                                
                                 //cummulative exp total
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);       
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(Double.toString(cumexp_head_amount)), pFontHeader);
                                table.addCell(phrase);
                                
                                 //balance total
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);       
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(Double.toString(cumbal_head_amount)), pFontHeader);
                                table.addCell(phrase);
                                




                            boolean boolNewPage = docPdf.newPage();
                        

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
//order by head asc
            java.sql.ResultSet rSet1 = stmt1.executeQuery("select distinct aieno from ac_aie_allocation where financial_year='"+year+"' ");



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
