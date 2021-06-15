//Author Charles Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;

package com.afrisoftech.hospayroll;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;


public class WelfareBreakDownPdf implements java.lang.Runnable {
    
    public static java.sql.Connection connectDB = null;
    
    java.lang.String bank = null;
    
    java.lang.String bank1 = null;
    
    
    java.lang.String beginDate = null;
    
    java.lang.String endDate = null;
    
    public java.lang.String dbUserName = null;
    
    com.afrisoftech.lib.DBObject dbObject;
    
    int numberSeq = 0;
    
    double total = 0.00;
    
    double totalt = 0.00;
    
    double balance = 0.00;
    
    double balance1 = 0.00;
    
    double rates = 0.00;
    
    
    
    boolean bal = false;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeaderx = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void TaxesPdf(java.sql.Connection connDb, java.lang.String begindate, java.lang.String endate, java.lang.String combox,java.lang.String combox1) {
        
        dbObject = new com.afrisoftech.lib.DBObject();
        
        bank = combox;
        
       bank1 = combox1;
        
        connectDB = connDb;
        
        beginDate = begindate;
        
        endDate = endate;
        
        threadSample = new java.lang.Thread(this,"SampleThread");
        
        System.out.println("threadSample created");
        
        threadSample.start();
        
        System.out.println("threadSample fired");
        
    }
    
    public static void main(java.lang.String[] args) {
        
        //	new CashBookListPdf().CashBookListPdf();
        
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
                
            } catch(java.lang.InterruptedException IntExec) {
                
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
            
            year_now_strs = "200"+year_now_abs;
            
        } else {
            
            year_now_strs = "20"+year_now_abs;
            
        }
        
        switch (month_now_str) {
            
            case 0 : month_now_strs = "JAN";
            
            break;
            
            case 1 : month_now_strs = "FEB";
            
            break;
            
            case 2 : month_now_strs = "MAR";
            
            break;
            
            case 3 : month_now_strs = "APR";
            
            break;
            
            case 4 : month_now_strs = "MAY";
            
            break;
            
            case 5 : month_now_strs = "JUN";
            
            break;
            
            case 6 : month_now_strs = "JUL";
            
            break;
            
            case 7 : month_now_strs = "AUG";
            
            break;
            
            case 8 : month_now_strs = "SEP";
            
            break;
            
            case 9 : month_now_strs = "OCT";
            
            break;
            
            case 10 : month_now_strs = "NOV";
            
            break;
            
            case 11 : month_now_strs = "DEC";
            
            break;
            
            default :         if (month_now_str < 10){
                
                month_now_strs = "0"+month_now_str;
                
            } else {
                
                month_now_strs = ""+month_now_str;
                
            }
            
        }
        
        if (date_now_str < 10) {
            
            date_now_strs = "0"+date_now_str;
            
        } else {
            
            date_now_strs = ""+date_now_str;
            
        }
        
        if (minute_now_str < 10) {
            
            minute_now_strs = "0"+minute_now_str;
            
        } else {
            
            minute_now_strs = ""+minute_now_str;
            
        }
        
        if (hour_now_str < 10) {
            
            hour_now_strs = "0"+hour_now_str;
            
        } else {
            
            hour_now_strs = ""+hour_now_str;
            
        }
        
        date_label = date_now_strs+month_now_strs+year_now_strs+"@"+hour_now_strs+minute_now_strs;
        
        return date_label;
        
    }
    
    
    public void generatePdf() {
        
        java.lang.Process wait_for_Pdf2Show;
        
        java.util.Calendar cal = java.util.Calendar.getInstance();
        
        java.util.Date dateStampPdf = cal.getTime();
        
        java.lang.String pdfDateStamp = dateStampPdf.toString();
        
        
        try {
            
            java.io.File tempFile = java.io.File.createTempFile("REP"+this.getDateLable()+"_", ".pdf");
            
            tempFile.deleteOnExit();
            
            java.lang.Runtime rt = java.lang.Runtime.getRuntime();
            
            java.lang.String debitTotal = null;
            
            java.lang.String creditTotal = null;
            
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A3.rotate());
            
            try {
                
                try {
                    
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    
                    
                    
     /*    try {
      
            java.lang.Class.forName("org.postgresql.Driver");
      
        } catch(java.lang.ClassNotFoundException cnfExec) {
      
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), cnfExec.getMessage());
      
        }
      */
                    
                    
                    String compName = null;
                    String date = null;
                    try {
                        
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                        
                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT header_name from pb_header");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while(rset2.next()){
                            compName = rset2.getObject(1).toString();
                        }
                        while(rset4.next()){
                            date = rset4.getObject(1).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(compName),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        
                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        
                        headerFoter.setRight(5);
                        docPdf.setHeader(headerFoter);
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    //   docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(6);
                        
                        int headerwidths[] = {10,40,20,20,20,20};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        table.setHeaderRows(2);
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(12);
                        
                        Phrase phrase;
                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.LONG);//MEDIUM);
                            
                            
                            java.util.Date formattedDate = dateFormat.parse(endDate);//dateInstance.toLocaleString());
                            
                            
                            phrase = new Phrase("WELFARE" +  "  LIST: " +dateFormat.format(formattedDate), pFontHeader);
                            
                            table.addCell(phrase);
                        } catch(java.text.ParseException psExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                            
                        }
                        
                        table.getDefaultCell().setColspan(1);
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        
                        
                        phrase = new Phrase("STAFF NO.",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("EMPLOYEE NAME",pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        
                        phrase = new Phrase("Staff Welfare",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        phrase = new Phrase("Loan 1",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Loan 2",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        phrase = new Phrase("Total",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        
                        
                        
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        
                        try {
                            // java.sql.Statement st3 = connectDB.createStatement();
                            //  java.sql.ResultSet rset3 = st3.executeQuery("select sum(amount) from deductions_summary_view WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND description = '"+bank+"'");
                            
                            
                            // java.sql.Statement st22 = connectDB.createStatement();
                            // java.sql.ResultSet rset11 = st22.executeQuery("SELECT SUM(amount) from posting WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND description = '"+bank+"'");
                            //
                            java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                            
                            Double staffwelfaret=0.00;
                            Double loan1t=0.00;
                            Double loan2t=0.00;
                            
                            
                            for (int j = 0; j < listofStaffNos.length; j++) {
                                
                                table.getDefaultCell().setColspan(12);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);        
                                phrase = new Phrase(""+listofStaffNos[j]+"   ", pFontHeaderx);
                                table.addCell(phrase);
                                    
                                    System.err.println("Working for ...................."+listofStaffNos[j]);
                                
                                
                                double rate = 0.00;
                                int staff = 0;
                                //  java.sql.Connection conDb1 = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                                
                                java.sql.Statement st = connectDB.createStatement();
                                
                                java.sql.Statement st2 = connectDB.createStatement();
                                java.sql.Statement st3 = connectDB.createStatement();
                                java.sql.Statement st31 = connectDB.createStatement();
                                java.sql.Statement st311 = connectDB.createStatement();
                                
                                java.sql.ResultSet rset = st.executeQuery("SELECT sacco_welfare_members.employee_no,sacco_welfare_members.staff_name from sacco_welfare_members where description ilike  '"+listofStaffNos[j]+"' order by 1");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
//                                java.sql.ResultSet rset1 = st3.executeQuery("select sum(np.amount),sum(hoursdays) as days from posting np where np.description ilike  '"+bank+"' and np.staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and company_name ilike '"+bank1+"' group by staff_no order by np.staff_no");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
//                                java.sql.ResultSet rset31 = st31.executeQuery("select sum(np.balance),np.acc_bal from sacco_deductions np where np.sacco_name = '"+bank+"' and np.staff_no = '"+listofStaffNos[j]+"' group by np.staff_no,np.acc_bal");
//                                java.sql.ResultSet rset311 = st311.executeQuery("select count(staff_no) from sacco_deductions np where np.sacco_name = '"+bank+"' and np.staff_no = '"+listofStaffNos[j]+"' group by np.staff_no");
                                
                                
                                while (rset.next()){
                                    table.getDefaultCell().setColspan(1);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    
                                    numberSeq = numberSeq+1;
                                    
                                    System.err.println("Staff no .................. "+numberSeq);
                                    
                                    phrase = new Phrase(""+rset.getObject(1)+"   ", pFontHeader);
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(2).toString().toUpperCase(),"-"),pFontHeader1);
                                    
                                    table.addCell(phrase);
                                    
                                    Double staffwelfare=0.00;
                                    Double loan1=0.00;
                                    Double loan2=0.00;
                                    
                                    
                                    
                                    java.sql.ResultSet rset1 = st3.executeQuery("SELECT   sum(posting.amount) FROM posting,sacco_welfare_members WHERE posting.description ilike 'staff welfare' and posting.company_name ='"+bank+"'   and \n" +
                                    "staff_no=employee_no and staff_no='"+rset.getObject(1)+"' and staff_no='"+rset.getObject(1)+"' and  welfare= true and posting.date  between '"+beginDate+"' and '"+endDate+"'  and  upper(sacco_welfare_members.description) =upper('"+listofStaffNos[j]+"')  ");
                                    while(rset1.next()){
                                        staffwelfare=rset1.getDouble(1);
                                        staffwelfaret=staffwelfaret+staffwelfare;
                                    }
                                    
                                    rset1 = st3.executeQuery("SELECT   sum(posting.amount) FROM posting,sacco_welfare_members WHERE posting.description ilike 'welfare loan 1' and posting.company_name ='"+bank+"'   and \n" +
                                    "staff_no=employee_no and staff_no='"+rset.getObject(1)+"' and staff_no='"+rset.getObject(1)+"' and  welfare= true and posting.date  between '"+beginDate+"' and '"+endDate+"'  and  upper(sacco_welfare_members.description) =upper('"+listofStaffNos[j]+"')  ");
                                    while(rset1.next()){
                                        loan1=rset1.getDouble(1);
                                        loan1t=loan1t+loan1;
                                    }
                                    
                                    rset1 = st3.executeQuery("SELECT   sum(posting.amount) FROM posting,sacco_welfare_members WHERE posting.description ilike 'welfare loan 2' and posting.company_name ='"+bank+"'   and \n" +
                                    "staff_no=employee_no and staff_no='"+rset.getObject(1)+"' and staff_no='"+rset.getObject(1)+"' and  welfare= true and posting.date  between '"+beginDate+"' and '"+endDate+"'  and  upper(sacco_welfare_members.description) =upper('"+listofStaffNos[j]+"')  ");
                                    while(rset1.next()){
                                        loan2=rset1.getDouble(1);
                                        loan2t=loan2t+loan2;
                                    }
                                    
                                    
                                    
                                    
                                    
                                    total=loan1+loan2+staffwelfare;
                                    totalt=totalt+total;
                                    
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(String.valueOf(staffwelfare),"0.00")),pFontHeader1);                    
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(String.valueOf(loan1),"0.00")),pFontHeader1);                    
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(String.valueOf(loan2),"0.00")),pFontHeader1);                    
                                    table.addCell(phrase);
                                    
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(String.valueOf(total),"0.00")),pFontHeader1);                    
                                    table.addCell(phrase);
                                    
                                    
                                }
                                
                                table.getDefaultCell().setColspan(12);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(" ",pFontHeader1);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Totals",pFontHeader);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(String.valueOf(staffwelfaret),"0.00")),pFontHeader);                    
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(String.valueOf(loan1t),"0.00")),pFontHeader);                    
                                table.addCell(phrase);
                                
                                
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(String.valueOf(loan2t),"0.00")),pFontHeader);                    
                                table.addCell(phrase);
                                
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(String.valueOf(totalt),"0.00")),pFontHeader);                    
                                table.addCell(phrase);
                                
                            }
                            
                            
                            docPdf.add(table);
                            
                            
                            
                        } catch(java.sql.SQLException SqlExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        
                    } catch(com.lowagie.text.BadElementException BadElExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                        
                    }
                    
                } catch(java.io.FileNotFoundException fnfExec) {
                    
                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), fnfExec.getMessage());
                    
                }
            } catch(com.lowagie.text.DocumentException lwDocexec) {
                
                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), lwDocexec.getMessage());
                
            }
            
docPdf.close();  com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);
            
            
            
        } catch(java.io.IOException IOexec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());
            
        }
        
        
        
    }
    public java.lang.Object[] getListofStaffNos() {
        
        java.lang.Object[] listofStaffNos = null;
        
        java.util.Vector listStaffNoVector = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT  distinct sacco_welfare_members.description from  sacco_welfare_members where description='"+bank1+"' AND welfare=true");
            //java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT staff_no FROM deduction_summary WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' and description ='"+bank+"' order by staff_no");
            
            while (rSet1.next()) {
                
                listStaffNoVector.addElement(rSet1.getObject(1).toString());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofStaffNos = listStaffNoVector.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNos;
    }
    
}





