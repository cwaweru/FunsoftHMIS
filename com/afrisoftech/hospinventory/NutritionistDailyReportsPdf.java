//Author Charles Waweru & Amimo Benja

//Made to test Java support for Threads.

//Revision : Ver 1.0b

//import java.lang.*;

package com.afrisoftech.hospinventory;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class NutritionistDailyReportsPdf implements java.lang.Runnable {
    
    com.afrisoftech.lib.DBObject dbObject;
    
     
    
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    
    java.lang.String bank = null;
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    
    public void NutritionistDailyReportItemsPdf(java.sql.Connection connDb,java.util.Date begindate,java.util.Date endate,java.lang.String combox) {
        
        dbObject = new com.afrisoftech.lib.DBObject();
        connectDB = connDb;
        beginDate = begindate;
        endDate = endate;
        
        bank = combox;
        
        threadSample = new java.lang.Thread(this,"SampleThread");
        
        System.out.println("threadSample created");
        
        threadSample.start();
        
        System.out.println("threadSample fired");
        
    }
    
    public static void main(java.lang.String[] args) {
        
    }
    
    
    public void run() {
        
        System.out.println("System has entered running mode");
        
        while (threadCheck) {
            
            System.out.println("O.K. see how we execute target program");
            
            this.generatePdf();
            
            try {
                
                System.out.println("Right, let's wait for task to complete or fail");
                
                java.lang.Thread.currentThread().sleep(200);
                
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
            
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            
            try {
                
                try {
                    
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    
                    double osBalance = 0;
                    String compName = null;
                    String date = null, user_n;
                    try {
                        
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                        
                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while(rset2.next()){
                            compName = rset2.getObject(1).toString();
                        }
                        while(rset4.next()){
                            date = rset4.getObject(1).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(compName,pFontHeader),false);
                        
                        headerFoter.setRight(7);
                        docPdf.setHeader(headerFoter);
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Nutrition Services Offered by "+ bank +""
                            + " - Page: ",pFontHeader), true);
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(8);
                        
                        int headerwidths[] = {12, 18, 12, 10, 25, 10, 10, 10};
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
//                        table.setHeaderRows(2);
//                        
//                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        try {
                            table.getDefaultCell().setBorder(Rectangle.BOX);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setFixedHeight(70);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
                            table.getDefaultCell().setFixedHeight(16);
                            java.sql.PreparedStatement st321 = connectDB.prepareStatement("select header_name from pb_header");
                            java.sql.ResultSet rset3 = st321.executeQuery();
                            table.getDefaultCell().setBorder(Rectangle.BOX);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            while (rset3.next()) {
                                table.getDefaultCell().setColspan(6);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                Phrase phrase = new Phrase(rset3.getObject(1).toString().toUpperCase(), pFontHeader);
                                table.addCell(phrase);
                            }
                        } catch (java.sql.SQLException SqlExect) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExect.getMessage());

                        }
                        
                        
                        table.getDefaultCell().setColspan(8);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        Phrase  phrase = new Phrase("NUTRITION DEPARTMENT DAILY WARD REPORT", pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setColspan(6);
                        phrase = new Phrase("", pFontHeader);
                        
                        String  amt, qnt;
                        String debit, credit, g_total, unit_price;
                    
                        double a, b, c, d, e, f;
                      
                           try {
                                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);
                                
                                java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());
                                java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());
                                
                                System.out.println("From : "+endDate11 + " to "+endDate1);
                                
                                
                                phrase = new Phrase(bank + "\nService Period : " +dateFormat.format(endDate11)+" - "+dateFormat.format(endDate1), 
                                        pFontHeader);
                                table.addCell(phrase);
                                
                            } catch(java.text.ParseException psExec) {
                                
                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                                
                            }
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("\nPrinted on : " +date,pFontHeader);
                        table.addCell(phrase);   
                        
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Date",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Name of Patient",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("IP No.",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Ward",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Service/Suppliment",pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        phrase = new Phrase("Quantity",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Price",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Amt",pFontHeader);
                        table.addCell(phrase);
                        
                        
//                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
//                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        
                        try {
                            
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            
                            java.sql.ResultSet rset2 = st2.executeQuery("select distinct login_name from secure_menu_access where initcap(f_name)"
                                    + " || ' '|| initcap(l_name) ilike '"+bank+"' ORDER BY login_name");
                            
                            System.out.println("What has " + bank + " done?");
                            
                            while (rset2.next()) {
                                
                                user_n = rset2.getObject(1).toString(); 
                                
                                String user_n2, pat_no = null, pat_ward = null, pat_name = null, serv_supp, qnty, pat_no2 = null;
                                
                                System.out.println(user_n + " - FOUND!");
                                
                                java.sql.ResultSet rset = st.executeQuery("select distinct date, service, sum(dosage) as Quantity, "
                                                + "user_name, sum(debit) as Amount, sum(credit) as Credit, patient_no from hp_patient_card where "
                                                + "gl_account = '600-60601-60035' and user_name ilike '"+user_n+"'"
                                                + " and paid = true and date between '"+beginDate+"' AND '"+endDate+"' group by date, service, "
                                                + "user_name, patient_no order by date");
                                        
                                        
                                    
                                while (rset.next()) {
                                    
                                    pat_no2 = rset.getObject(7).toString();
                                    
                                    user_n2 = rset.getObject(4).toString();
                                    
                                    serv_supp = rset.getObject(2).toString();
                                    
                                    qnty = rset.getObject(3).toString();
                                    
                                    debit = rset.getObject(5).toString();
                                    
                                    credit = rset.getObject(6).toString();
                                    
                                    qnt = rset.getObject(3).toString();
                                    
                                    d = Double.valueOf(credit);
                                    
                                    f = Double.valueOf(debit);
                                    
                                    if (d != 0 || d != 0.00) {
                                        e = f - d;
                                        amt = Double.toString(e);
                                    } else {
                                        amt = debit;
                                    }
                                    
                                    a = Double.valueOf(qnt);
                                    b = Double.valueOf(amt);
                                    c = Math.round((b/a)*100)/100;
                                    
                                    unit_price = Double.toString(c);
                                    
                                    g_total = Double.toString(b);
                                    
                                    java.sql.ResultSet rset3 = st3.executeQuery("select distinct patient_name, ward, patient_no from hp_admission "
                                        + " where patient_no ilike '"+pat_no2+"' group by patient_name, ward, patient_no order by patient_no");
                                    
                                    while (rset3.next()) {
                                        
                                        pat_name = rset3.getObject(1).toString();
                                        pat_ward = rset3.getObject(2).toString();
                                        pat_no = rset3.getObject(3).toString();
                                    
                                    }
                                    
                                    table.getDefaultCell().setColspan(1);
                                    
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(rset.getObject(1).toString().substring(0, 10), pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(pat_name, pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(pat_no, pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(pat_ward, pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(serv_supp, pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase(qnty, pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(unit_price),pFontHeader);
                                        table.addCell(phrase);

                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(g_total),pFontHeader);
                                        osBalance = osBalance + b;
                                        table.addCell(phrase);
                                    
                                    if (user_n.equals(user_n2)) {
                                        
                                        System.out.println("SEARCHING FOR patient " + pat_no2 + "!");

                                        if (pat_no2.equals(pat_no)) {
                                            System.out.println(pat_no + " - FOUND!");
                                        } else {
                                            System.out.println("SEARCHING... \n" + pat_no2 + " NO!");
                                        }
                                    } else {
                                        System.out.println(user_n + " - NOT FOUND!");
                                    }
                                }
                                
                            }
                            
                            
//                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
//                            
//                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            table.getDefaultCell().setColspan(7);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Gross Total ", pFontHeader);
                            table.addCell(phrase);
                            
                                
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);                                  
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)),pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setFixedHeight(35);
                            try {               
                                
                                java.sql.PreparedStatement pstmtUser = connectDB.prepareStatement("SELECT (SELECT upper(f_name||' '||l_name) FROM secure_menu_access where login_name = current_user order by 1 limit 1), date_part('day', now()::date) ||'-'||date_part('month', now()::date) ||'-'||date_part('year', now()::date)");
                                java.sql.ResultSet rsetUser = pstmtUser.executeQuery();

                                    while (rsetUser.next()) {
                                        table.getDefaultCell().setColspan(3);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Nutrition Name : ".toUpperCase() + rsetUser.getString(1), pFontHeader);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(3);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Signature : " , pFontHeader);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(2);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Date : ".toUpperCase() + rsetUser.getString(2), pFontHeader);
                                        table.addCell(phrase);

                                    }

                            } catch (java.sql.SQLException SqlExec) {

                                SqlExec.printStackTrace();

                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

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
    
}





